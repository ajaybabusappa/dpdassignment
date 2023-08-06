package com.assignment.demo.assignment;

import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class UserInfo implements UserDetails{

	@Id
	private String username;
	private String email;
	private String password; 
	private String full_name;
	private int age;
	private String gender;
	public String getUsername() {
		return username;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFull_name() {
		return full_name;
	}
	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String male) {
		this.gender = male;
	}
	public UserInfo() {
		super();
	}
	public UserInfo(String username, String email, String password, String full_name, int age,
			String gender) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.full_name = full_name;
		this.age = age;
		this.gender = gender;
	}

	public UserInfo(Map<String, Object> obj) {
		// TODO Auto-generated constructor stub
		this.username = obj.get("username").toString();
		this.email = obj.get("email").toString();
		this.password = obj.get("password").toString();
		this.full_name = obj.get("full_name").toString();
		this.age = (int) obj.get("age");
		this.gender = obj.get("gender").toString();
		
	}
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
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
	
	
}
