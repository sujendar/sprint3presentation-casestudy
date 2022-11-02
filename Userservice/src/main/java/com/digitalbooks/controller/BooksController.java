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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.digitalbooks.entity.BooksDetails;
import com.digitalbooks.entity.ResponseParms;
import com.digitalbooks.entity.SubscribeBook;
import com.digitalbooks.interfaces.UserServiceConstants;
import com.digitalbooks.service.ReaderServices;

import lombok.extern.slf4j.Slf4j;
@RestController
@RequestMapping("/api/v1/digitalbooks")
@Slf4j
public class BooksController {
	@Autowired
	private ReaderServices readerServices;

	
	@GetMapping(value="/search")
	public ResponseEntity<List<BooksDetails>> search(@RequestParam String category,@RequestParam String title,
			@RequestParam String author,@RequestParam String price,
			@RequestParam String publisher) {
		List<BooksDetails> list=readerServices.search(category,title,author,price,publisher);
		return new ResponseEntity<List<BooksDetails>>(list,HttpStatus.OK);
	}
	
	@PostMapping(value="/subscribe")
	public ResponseEntity<?> subscribeBook(@RequestBody SubscribeBook book) {
		log.info("its in cancelSubscribe in restcontroller");
	 	Authentication token=SecurityContextHolder.getContext().getAuthentication();
	       Collection<? extends GrantedAuthority> list1=  	token.getAuthorities();
	       List<String> authorites=list1.stream().map(i->i.getAuthority()).collect(Collectors.toList());
	      if(authorites.contains("READER")) {
		ResponseParms response=readerServices.subscribeBook(book);
		return new ResponseEntity<ResponseParms>(response,HttpStatus.OK);
	}else {
  	  ResponseParms params=new ResponseParms();
  	  params.setErrorcode(UserServiceConstants.AUTH_ERROR.toString());
  	  params.setErrormessage(UserServiceConstants.READER_AUTH.toString());
  	  return new ResponseEntity<ResponseParms>(params,HttpStatus.NON_AUTHORITATIVE_INFORMATION);
    }
}
}
