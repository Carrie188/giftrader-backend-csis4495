package com.example.giftradar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.giftradar.model.Friend;


public interface FriendRepository extends JpaRepository<Friend, Long> {

}
