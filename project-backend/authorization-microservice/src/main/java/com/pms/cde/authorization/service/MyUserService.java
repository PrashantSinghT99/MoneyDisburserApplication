package com.pms.cde.authorization.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pms.cde.authorization.dao.UserDao;
import com.pms.cde.authorization.model.MyUserDetail;
import com.pms.cde.authorization.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MyUserService implements UserDetailsService {
	private static final Logger log = LoggerFactory.getLogger(MyUserService.class);
	@Autowired
	private UserDao userDao;

	@Autowired
	private PasswordEncoder passwordEncoder;

	// Returning UserDetails Object if your is present in database.
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("getting user from username");
		User user = userDao.getUserByUsername(username);
		if (user == null) {
			log.warn("No users available");
			throw new UsernameNotFoundException("No Users Available With " + username + " !!!....");
		}
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		log.info("user found with the provided username");
		return new MyUserDetail(user);
	}

	/*
	 * Generates jwt token
	 */
	public String generateJwt(String user) {
		log.info("generating token");
		JwtBuilder builder = Jwts.builder();
		builder.setSubject(user);
		builder.setIssuedAt(new Date());
		builder.setExpiration(new Date(new Date().getTime() + (30 * 60 * 1000)));
		builder.signWith(SignatureAlgorithm.HS256, "secretkey");
		log.info("token generation successful");
		return builder.compact();
	}

	/**
	 * Extracting the username from token received
	 */
	public String getUsernameFromToken(String token) {
		log.info("extracting username from token");
		Jws<Claims> jws = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token.replace("Bearer ", ""));
		log.info("username extracted from token sucessfully");
		return jws.getBody().getSubject();
	}

}
