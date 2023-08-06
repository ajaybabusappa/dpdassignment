package com.assignment.demo.assignment;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class UserRegister {
	
	
	@NotBlank (message = "username shouldn't be null")
	private String username;
	@Email
	@NotBlank
	private String email;
	@NotBlank
	private String password; 
	@NotBlank
	private String full_name;
	@Min(0)
	private int age;
	private String gender;
	
	public UserRegister(String username, String email, String password, String full_name, int age, String gender) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.full_name = full_name;
		this.age = age;
		this.gender = gender;
	}
	
	public UserRegister (UserInfo user) {
		this.username = user.getUsername();
		this.email = user.getEmail();
		this.password = user.getPassword();
		this.full_name = user.getFull_name();
		this.age = user.getAge();
		this.gender = user.getGender();
	}

	public UserRegister() {
		super();
	}

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

	public void setGender(String gender) {
		this.gender = gender;
	}
}
