package com.example.giftradar.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.giftradar.model.Friend;
import com.example.giftradar.model.User;
import com.example.giftradar.repository.FriendRepository;
import com.example.giftradar.repository.UserRepository;
import com.example.giftradar.response.MessageResponse;
import com.example.giftradar.security.JwtUtils;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class userController {
	
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	JwtUtils jwtUtils;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	FriendRepository friendRepository;
	
	@GetMapping("/user/{id}")
	public ResponseEntity<?> getUserById(@PathVariable("id") long id) {
		Optional<User> userData = userRepository.findById(id);

		if (userData.isPresent()) {
			return new ResponseEntity<>(userData.get(), HttpStatus.OK);
		} else {
			MessageResponse msg = new MessageResponse("The user is not found!");
			return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
		}

	}
	
	@PutMapping("/user/{id}")
	public ResponseEntity<?> updateUser(@PathVariable("id") long id, @RequestBody User updateUser) {
		Optional<User> userData = userRepository.findById(id);
		
		if (userData.isPresent()) {
			User _user = userData.get();
			_user.setUsername(updateUser.getUsername());
			_user.setEmail(updateUser.getEmail());
			_user.setPassword(updateUser.getPassword());
			userRepository.save(_user);
			MessageResponse msg = new MessageResponse("update user-info Successfully!");
			return new ResponseEntity<>(msg, HttpStatus.OK);
		} else {
			MessageResponse msg = new MessageResponse("The user is not found!");
			return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
		}

	}
	
	
	
	@PutMapping("/user/{id}/addnewfriend")
	public ResponseEntity<?> addNewFriend(@PathVariable("id") long id, @RequestBody Friend newFriend) {
		Optional<User> userData = userRepository.findById(id);
		
		if (userData.isPresent()) {
			User _user = userData.get();
			_user.addNewFriend(newFriend);
			userRepository.save(_user);
			MessageResponse msg = new MessageResponse("Adding new friend Successfully!");
			return new ResponseEntity<>(msg, HttpStatus.OK);
		} else {
			MessageResponse msg = new MessageResponse("The user is not found!");
			return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
		}

	}
	
	@DeleteMapping("/user/removefriend/{id}")
	public ResponseEntity<?> removeFriend(@PathVariable("id") long id) {
		Optional<Friend> friendData = friendRepository.findById(id);
		
		if (friendData.isPresent()) {
			friendRepository.deleteById(id);
			MessageResponse msg = new MessageResponse("Remove friend Successfully!");
			return new ResponseEntity<>(msg, HttpStatus.OK);
		} else {
			MessageResponse msg = new MessageResponse("The user is not found!");
			return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
		}

	}
	
}
