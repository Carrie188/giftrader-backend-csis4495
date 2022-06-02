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
			
			// add users
			User[] users = {
					new User("Carrie","123456"),
					new User("Mei", "123456")
			};
			
			Friend[] friends = {
					new Friend("Aria",20,"female","2020/01/01"),
					new Friend("Jin",20,"male","2030/01/01"),
					new Friend("Kuku",20,"male","2020/01/01"),
					new Friend("James",20,"male","2010/01/01")
			};
			
			users[0].addNewFriend(friends[0]);
			users[0].addNewFriend(friends[1]);
			users[1].addNewFriend(friends[2]);
			users[1].addNewFriend(friends[3]);
			
			
			for(int i = 0; i < users.length; i++) {
				userRepository.save(users[i]);
			}
			for(int i = 0; i < friends.length; i++) {
				friendRepository.save(friends[i]);
			}
			
			
			userRepository.findAll().forEach(System.out::println);
			friendRepository.findAll().forEach(System.out::println);
			
			
			};
			
			
			
		}
}
