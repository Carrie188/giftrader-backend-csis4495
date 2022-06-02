package com.example.giftradar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.giftradar.model.User;


public interface UserRepository extends JpaRepository<User, Long>  {
	
	
}
