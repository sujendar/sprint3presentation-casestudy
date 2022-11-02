package com.digitalbooks.service;

import java.util.List;

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
import com.digitalbooks.entity.SubscribeBook;
import com.digitalbooks.entity.User;
import com.digitalbooks.repo.UserRepo;

import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class ReaderServices {
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private UserRepo userRepo;

	public List<BooksDetails> getSubscribedBooks(String email) {
		log.info("in get getSubscribedBooks");
		User user  =userRepo.findByEmail(email);
		List<BooksDetails> list=restTemplate.getForObject("http://localhost:2222/api/v1/digitalbooks/readers/books/"+user.getUserId(),List.class);
		return list;
	}

	public BooksDetails getBookDetails(String emailId, Long subscriptionId) {
		log.info("in  getBookDetails");
		User user  =userRepo.findByEmail(emailId);
		BooksDetails booksDetails=restTemplate.getForObject("http://localhost:2222/api/v1/digitalbooks/readers/books/"+user.getUserId()+"/"+subscriptionId,BooksDetails.class);
		return booksDetails;
	}

	public String getBookContent(Long subscriptionId) {
		log.info("*********in  getBookContent******");
		//User user  =userRepo.findByEmail(emailId);
		String content=restTemplate.getForObject("http://localhost:2222/api/v1/digitalbooks/readers/books/read/"+subscriptionId,String.class);
		return content;
	}

	public ResponseParms cancelSubscription(Long subscriptionId) {
		log.info("*********in  cancelSubscription******");
		ResponseParms content=restTemplate.getForObject("http://localhost:2222/api/v1/digitalbooks/readers/books/cancelsubscription/"+subscriptionId,ResponseParms.class);
		return content;
	}

	public ResponseParms subscribeBook(SubscribeBook book) {
		Authentication token=SecurityContextHolder.getContext().getAuthentication();
		log.info("in  createBook name :"+token.getName());
		User user=userRepo.findByUserName(token.getName());
		book.setUserId(user.getUserId());
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<SubscribeBook> entity = new HttpEntity<SubscribeBook>(book, headers);
		ResponseParms params=restTemplate.postForObject("http://localhost:2222/api/v1/digitalbooks/subscribe", entity, ResponseParms.class);
		return params;
	}

	public List<BooksDetails> search(String category, String title, String author, String price, String publisher) {
		log.info("in  search");
		List<BooksDetails> booksDetails=restTemplate.getForObject("http://localhost:2222/api/v1/digitalbooks/search?category="+category+"&title="+title+"&author="+author+"&price="+price+"&publisher="+publisher,List.class);
		return booksDetails;
	}

}
