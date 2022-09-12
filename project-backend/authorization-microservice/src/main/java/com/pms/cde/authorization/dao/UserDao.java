package com.pms.cde.authorization.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pms.cde.authorization.model.User;
import com.pms.cde.authorization.repository.UserRepository;

@Repository
public class UserDao {
 
 @Autowired
 private UserRepository userRepository;
	
 public User getUserByUsername(String username) {
	 return userRepository.findByUsername(username);
 } 
}
