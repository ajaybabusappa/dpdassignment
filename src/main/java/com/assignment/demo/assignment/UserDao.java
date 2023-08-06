package com.assignment.demo.assignment;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDao {
	@Autowired
	 UserInfoRepo repo;
	
	public UserRegister adduser (Map<String, Object> user) {
		UserInfo user1 = new UserInfo (user);
		repo.save(user1);
		UserRegister UserReg= new UserRegister (user1);
		return UserReg;
	}
	
	public boolean userauthentication (String username, String password) {
		UserInfo user = repo.findByUsernameAndPassword(username, password).orElse(null);
		if (user == null) return false;
		return true;
	}
	
	public boolean emailexists (String email) {
		if (repo.existsByEmail(email)) return true;
		return false;
	}
	
	public boolean usernameexists (String username) {
		if (repo.existsById(username)) return true;
		return false;
	}
}
