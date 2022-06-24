package com.example.giftradar;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.giftradar.model.Friend;
import com.example.giftradar.model.User;
import com.example.giftradar.repository.FriendRepository;
import com.example.giftradar.repository.UserRepository;

@SpringBootApplication
public class GiftradarApplication {

	public static void main(String[] args) {
		SpringApplication.run(GiftradarApplication.class, args);
	}
	
	@Bean
	ApplicationRunner init(UserRepository userRepository, 
			FriendRepository friendRepository) {
		return args -> {
		
			
			System.out.println("running server successfully");
			};
			
			
			
		}
}
