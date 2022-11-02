package com.digitalbooks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.digitalbooks.entity.User;
import com.digitalbooks.repo.UserRepo;

@Service
public class UserService {
  @Autowired
	private UserRepo userRepo;
  @Autowired
  private RestTemplate resttemplate;
  
  public User saveUser(User user) {
	  return userRepo.save(user);
  }

public User findByUserName(String userName) {
	return userRepo.findByUserName(userName);
}

public ResponseEntity<?> allSubscribedBook(String emailId) {
	// TODO Auto-generated method stub
	return null;
}
  

}
