package com.example.giftradar.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;


@Entity
@Table(name="users")
public class User {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@NotBlank
	@Column(name = "username")
	private String username;

	@NotBlank
	@Column(name = "email")
	private String email;
	
	@NotBlank
	@Column(name = "password")
	private String password;
	
	@Transient
    private String passwordConfirm;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Friend> friends = new HashSet<>();

	public User() {
		
	}
	
	public User(String username, String email,String password) {
		this.username = username; 
		this.password = password;
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Friend> getFriends() {
		return friends;
	}

	public void setFriends(Set<Friend> friends) {
		this.friends = friends;
	}
	
	public void addNewFriend(Friend friend) {
		this.friends.add(friend);
		friend.setUser(this);
	}
	
	public void removeFriend(Friend friend) {
		this.friends.remove(friend);
		friend.setUser(null);
	}
	

	
}
