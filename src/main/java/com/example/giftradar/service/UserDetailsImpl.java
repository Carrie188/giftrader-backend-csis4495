package com.example.giftradar.service;

import java.util.Collection;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.giftradar.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;




// UserDetails contains necessary information (such as: username, password, authorities) to build an Authentication object
public class UserDetailsImpl implements UserDetails {

	
	
	private static final long serialVersionUID = 1L;

	private Long id;

	private String username;
	
	private String email;

	@JsonIgnore
	private String password;
	
	
	public UserDetailsImpl(Long id, String username, String email, String password) {
		    this.id = id;
		    this.username = username;
		    this.password = password;
		    this.email = email;
	}
	
	public static UserDetailsImpl build(User user) {

	    return new UserDetailsImpl(
	        user.getId(), 
	        user.getUsername(),
	        user.getEmail(),
	        user.getPassword());
	  }
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override
	  public boolean equals(Object o) {
	    if (this == o)
	      return true;
	    if (o == null || getClass() != o.getClass())
	      return false;
	    UserDetailsImpl user = (UserDetailsImpl) o;
	    return Objects.equals(id, user.id);
	  }

}
