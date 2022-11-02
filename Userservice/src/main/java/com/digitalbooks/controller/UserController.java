package com.digitalbooks.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.digitalbooks.Utill.JwtUtil;
import com.digitalbooks.entity.AuthRequest;
import com.digitalbooks.entity.User;
import com.digitalbooks.service.UserService;


import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/digitalbooks/")
@Slf4j
@CrossOrigin("http://localhost:4200")
public class UserController {
	 @Autowired
	private JwtUtil jwtUtil;
	 @Autowired
    private AuthenticationManager authenticationManager;
	@Autowired
	private UserService userService;
	@PostMapping("/signup")
	public User saveUser(@RequestBody User user) {
		log.info("inside saveuser in user controller");
		return userService.saveUser(user);
	}
	
	@PostMapping("/signin/{username}")
	public User signinUser(@PathVariable String username) {
		log.info("inside saveuser in user controller");
		return userService.findByUserName(username);
	}
	  @GetMapping("/")
	    public String welcome() {
	    	Authentication token=SecurityContextHolder.getContext().getAuthentication();
	       Collection<? extends GrantedAuthority> list=  	token.getAuthorities();
	       for (GrantedAuthority grantedAuthority : list) {
			System.out.println(grantedAuthority.getAuthority());
		}
	        return "Welcome to javatechie !!";
	    }
	   @PostMapping("/authenticate")
	    public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
	        try {
	            authenticationManager.authenticate(
	                    new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword())
	            );
	        } catch (Exception ex) {
	            throw new Exception("inavalid username/password");
	        }
	        return jwtUtil.generateToken(authRequest.getUserName());
	    }
	   @PreAuthorize("hasRole('READER')")
	    @GetMapping({"/api/v1/digitalbooks/readers/{emailId}/books"})
	    public ResponseEntity<?> allSubscribedBook(@PathVariable String emailId) {
	        log.info("############all subscribed books get#########");
	        return this.userService.allSubscribedBook(emailId);
	    }
}
