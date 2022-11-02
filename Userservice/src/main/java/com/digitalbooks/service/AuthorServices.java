package com.digitalbooks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.digitalbooks.entity.BooksDetails;
import com.digitalbooks.entity.ResponseParms;
import com.digitalbooks.entity.User;
import com.digitalbooks.repo.UserRepo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AuthorServices {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private UserRepo userRepo;

	public ResponseParms createBook(BooksDetails book) {
		Authentication token=SecurityContextHolder.getContext().getAuthentication();
		log.info("in  createBook name :"+token.getName());
		User user=userRepo.findByUserName(token.getName());
		book.setAuthorId(user.getUserId());
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<BooksDetails> entity = new HttpEntity<BooksDetails>(book, headers);
		ResponseParms params=restTemplate.postForObject("http://localhost:2222/api/v1/digitalbooks/author/books", entity, ResponseParms.class);
		return params;
	}

	public ResponseParms upadteBook(BooksDetails book) {
		Authentication token=SecurityContextHolder.getContext().getAuthentication();
		log.info("in  upadteBook name :"+token.getName());
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<BooksDetails> entity = new HttpEntity<BooksDetails>(book, headers);
		ResponseParms params=restTemplate.postForObject("http://localhost:2222/api/v1/digitalbooks/author/books/"+book.getBookId(), entity, ResponseParms.class);
		return params;
	}

	public ResponseParms blockUnblockBook(Long authorid, Long bookid, String block) {
		log.info("in  blockUnblockBook name :");
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		ResponseParms params=restTemplate.postForObject("http://localhost:2222/api/v1/digitalbooks/author/books/"+authorid+"/"+bookid+"/?block="+block, headers, ResponseParms.class);
		return params;
	}

}
