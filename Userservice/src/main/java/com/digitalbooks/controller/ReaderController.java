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
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping("/api/v1/digitalbooks/readers")
@Slf4j
public class ReaderController {
	@Autowired
	private ReaderServices readerServices;

	@GetMapping(value="/books/{email}")
	public ResponseEntity<?> getSubscribedBooks(@PathVariable String email) {
		log.info("its in getSubscribedBooks in restcontroller");
	 	Authentication token=SecurityContextHolder.getContext().getAuthentication();
	       Collection<? extends GrantedAuthority> list1=  	token.getAuthorities();
	       List<String> authorites=list1.stream().map(i->i.getAuthority()).collect(Collectors.toList());
	      if(authorites.contains("READER")) {
	    	  List<BooksDetails> list=readerServices.getSubscribedBooks(email);
	  		return new ResponseEntity<List<BooksDetails>>(list,HttpStatus.OK);
	      }else {
	    	  ResponseParms params=new ResponseParms();
	    	  params.setErrorcode(UserServiceConstants.AUTH_ERROR.toString());
	    	  params.setErrormessage(UserServiceConstants.READER_AUTH.toString());
	    	  return new ResponseEntity<ResponseParms>(params,HttpStatus.NON_AUTHORITATIVE_INFORMATION);
	      }
		
}
	@GetMapping(value="/books/{emailId}/{subscribeId}")
	public ResponseEntity<?> getBookDetails(@PathVariable String emailId,@PathVariable String subscribeId) {
		log.info("its in getBookDetails in restcontroller");
	 	Authentication token=SecurityContextHolder.getContext().getAuthentication();
	       Collection<? extends GrantedAuthority> list1=  	token.getAuthorities();
	       List<String> authorites=list1.stream().map(i->i.getAuthority()).collect(Collectors.toList());
	      if(authorites.contains("READER")) {
		BooksDetails bookDetails=readerServices.getBookDetails(emailId,Long.valueOf(subscribeId));
		return new ResponseEntity<BooksDetails>(bookDetails,HttpStatus.OK);
	      }else {
	    	  ResponseParms params=new ResponseParms();
	    	  params.setErrorcode(UserServiceConstants.AUTH_ERROR.toString());
	    	  params.setErrormessage(UserServiceConstants.READER_AUTH.toString());
	    	  return new ResponseEntity<ResponseParms>(params,HttpStatus.NON_AUTHORITATIVE_INFORMATION);
	      }
}
	@GetMapping(value="/books/read/{subscribeId}")
	public ResponseEntity<?> getContent(@PathVariable String subscribeId) {
		log.info("its in getContent in restcontroller");
	 	Authentication token=SecurityContextHolder.getContext().getAuthentication();
	       Collection<? extends GrantedAuthority> list1=  	token.getAuthorities();
	       List<String> authorites=list1.stream().map(i->i.getAuthority()).collect(Collectors.toList());
	      if(authorites.contains("READER")) {
		  String content=readerServices.getBookContent(Long.valueOf(subscribeId));
		return new ResponseEntity<String>(content,HttpStatus.OK);
	      }else {
	    	  ResponseParms params=new ResponseParms();
	    	  params.setErrorcode(UserServiceConstants.AUTH_ERROR.toString());
	    	  params.setErrormessage(UserServiceConstants.READER_AUTH.toString());
	    	  return new ResponseEntity<ResponseParms>(params,HttpStatus.NON_AUTHORITATIVE_INFORMATION);
	      }
}
	@PostMapping(value="/books/cancelsubscription/{subscribeId}")
	public ResponseEntity<ResponseParms> cancelSubscribe(@PathVariable String subscribeId) {
		log.info("its in cancelSubscribe in restcontroller");
	 	Authentication token=SecurityContextHolder.getContext().getAuthentication();
	       Collection<? extends GrantedAuthority> list1=  	token.getAuthorities();
	       List<String> authorites=list1.stream().map(i->i.getAuthority()).collect(Collectors.toList());
	      if(authorites.contains("READER")) {
		ResponseParms responseParams=readerServices.cancelSubscription(Long.valueOf(subscribeId));
		return new ResponseEntity<ResponseParms>(responseParams,HttpStatus.OK);
	      }else {
	    	  ResponseParms params=new ResponseParms();
	    	  params.setErrorcode(UserServiceConstants.AUTH_ERROR.toString());
	    	  params.setErrormessage(UserServiceConstants.READER_AUTH.toString());
	    	  return new ResponseEntity<ResponseParms>(params,HttpStatus.NON_AUTHORITATIVE_INFORMATION);
	      }
}
	
}
