package com.example.giftradar.controller;


import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.giftradar.model.User;
import com.example.giftradar.repository.UserRepository;
import com.example.giftradar.request.LoginRequest;
import com.example.giftradar.request.SignupRequest;
import com.example.giftradar.response.JwtResponse;
import com.example.giftradar.response.MessageResponse;
import com.example.giftradar.security.JwtUtils;
import com.example.giftradar.service.UserDetailsImpl;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	  @Autowired
	  AuthenticationManager authenticationManager;

	  @Autowired
	  UserRepository userRepository;
	  
	  @Autowired
	  PasswordEncoder encoder;

	  @Autowired
	  JwtUtils jwtUtils;
	  
	  @PostMapping("/signin")
	  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

	    Authentication authentication = authenticationManager.authenticate(
	        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

	    SecurityContextHolder.getContext().setAuthentication(authentication);
	    String jwt = jwtUtils.generateJwtToken(authentication);
	    
	    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();    
//	    System.out.println("login details: "+userDetails.getUsername()+""+ userDetails.getAuthorities()+""+userDetails.getId());
//

	    return ResponseEntity.ok(new JwtResponse(jwt, 
	                         userDetails.getId(), 
	                         userDetails.getUsername()));
	  }

	  @PostMapping("/signup")
	  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		  try {
			  if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			      return ResponseEntity
			          .badRequest()
			          .body(new MessageResponse("Error: Username is already taken!"));
			    }else {
			    	// Create new user's account
				    User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(),
				               encoder.encode(signUpRequest.getPassword()));
//				    System.out.println("new user created: "+user.getUsername());
				    userRepository.save(user);


				    MessageResponse msg =  new MessageResponse("User registered successfully!");
				    return new ResponseEntity<>(msg,HttpStatus.OK);
			    }
			  


			    
			    
		  } catch (Exception e) {
				MessageResponse msg = new MessageResponse("Server Error");
				return new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
		  }
		  
	    
	  }

}
