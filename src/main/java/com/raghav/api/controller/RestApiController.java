package com.raghav.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.raghav.dao.UserRepository;
import com.raghav.entity.User;

@RestController
@RequestMapping("/api")
public class RestApiController {

//	@Autowired
//	UserRepository userRepo;

	@PostMapping("/register-user")

	public ResponseEntity<User> createTutorial(@RequestBody User tutorial) {
		try {
//			User usrObj = userRepo.save(new User(tutorial.getName(), tutorial.getEmail()));
//
//			return new ResponseEntity<>(usrObj, HttpStatus.CREATED);
			return null;
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
