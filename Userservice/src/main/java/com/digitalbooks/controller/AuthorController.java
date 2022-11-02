package com.digitalbooks.controller;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.digitalbooks.entity.BooksDetails;
import com.digitalbooks.entity.ResponseParms;
import com.digitalbooks.entity.SubscribeBook;
import com.digitalbooks.interfaces.UserServiceConstants;
import com.digitalbooks.service.AuthorServices;

import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/api/v1/digitalbooks")
@Slf4j
public class AuthorController {

	@Autowired
	private AuthorServices authorServices;
	
	@PostMapping(value="/author/books")
	public ResponseEntity<?> createBook(@RequestBody BooksDetails book) {
		log.info("its in createBook in AuthorController");
	 	Authentication token=SecurityContextHolder.getContext().getAuthentication();
	       Collection<? extends GrantedAuthority> list1=  	token.getAuthorities();
	       List<String> authorites=list1.stream().map(i->i.getAuthority()).collect(Collectors.toList());
	      if(authorites.contains("AUTHOR")) {
		ResponseParms response=authorServices.createBook(book);
		return new ResponseEntity<ResponseParms>(response,HttpStatus.CREATED);
	      }else {
	    	  ResponseParms params=new ResponseParms();
	    	  params.setErrorcode(UserServiceConstants.AUTH_ERROR.toString());
	    	  params.setErrormessage(UserServiceConstants.AUTHOR_AUTH.toString());
	    	  return new ResponseEntity<ResponseParms>(params,HttpStatus.NON_AUTHORITATIVE_INFORMATION);
	      }
	}
	@PutMapping(value="/author/books/{booksid}")
	public ResponseEntity<?> upadteBook(@RequestBody BooksDetails book,@PathVariable Long booksid) {
		log.info("its in upadteBook in AuthorController");
	 	Authentication token=SecurityContextHolder.getContext().getAuthentication();
	       Collection<? extends GrantedAuthority> list1=  	token.getAuthorities();
	       List<String> authorites=list1.stream().map(i->i.getAuthority()).collect(Collectors.toList());
	      if(authorites.contains("AUTHOR")) {
		      book.setBookId(booksid);
		      ResponseParms response=authorServices.upadteBook(book);
		return new ResponseEntity<ResponseParms>(response,HttpStatus.OK);
	      }else {
	    	  ResponseParms params=new ResponseParms();
	    	  params.setErrorcode(UserServiceConstants.AUTH_ERROR.toString());
	    	  params.setErrormessage(UserServiceConstants.AUTHOR_AUTH.toString());
	    	  return new ResponseEntity<ResponseParms>(params,HttpStatus.NON_AUTHORITATIVE_INFORMATION);
	      }
	}
	@PostMapping(value="/author/books/{authorid}/{bookid}")
	public ResponseEntity<?> blockUnblockBook(@PathVariable Long authorid,@PathVariable Long bookid,@RequestParam String block) {
		log.info("its in upadteBook in AuthorController");
	 	Authentication token=SecurityContextHolder.getContext().getAuthentication();
	       Collection<? extends GrantedAuthority> list1=  	token.getAuthorities();
	       List<String> authorites=list1.stream().map(i->i.getAuthority()).collect(Collectors.toList());
	      if(authorites.contains("AUTHOR")) {
		ResponseParms response=authorServices.blockUnblockBook(authorid,bookid,block);
		return new ResponseEntity<ResponseParms>(response,HttpStatus.OK);
	      }else {
	    	  ResponseParms params=new ResponseParms();
	    	  params.setErrorcode(UserServiceConstants.AUTH_ERROR.toString());
	    	  params.setErrormessage(UserServiceConstants.AUTHOR_AUTH.toString());
	    	  return new ResponseEntity<ResponseParms>(params,HttpStatus.NON_AUTHORITATIVE_INFORMATION);
	      }
	}
}
