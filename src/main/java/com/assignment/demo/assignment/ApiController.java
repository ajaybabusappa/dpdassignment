package com.assignment.demo.assignment;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
public class ApiController {

	@Autowired
	private KVDao dao;
	@Autowired
	private UserDao udao;
	
	@Autowired
	private Validators validate;
	
	@Autowired
	private Jwtservice jwtService;
	
	@RequestMapping ("/")
	public String Home(){
		return "";
		
	}
	
	@PostMapping ("/api/register")
	public ResponseEntity<?> addUse(@RequestBody Map<String, Object> user){
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		
		if (user == null) {
			return new ResponseEntity<> (map, HttpStatus.BAD_REQUEST);
		}
		if (user.get("username") == null || user.get("username").toString() == "" || user.get("email") == null || user.get("email").toString() == ""
				|| user.get("full_name") == null || user.get("full_name").toString() == "" || user.get("password") == null || user.get("password").toString() == "") {
			map.put("status", "error");
			map.put("code", "INVALID_REQUEST");
			map.put("message", "Invalid request. Please provide all required fields: username, email, password, full_name.");
			return new ResponseEntity<> (map, HttpStatus.BAD_REQUEST);
		}
		if (user.get("gender") == null || !validate.ValidateGender(user.get("gender").toString())) {
			map.put("status", "error");
			map.put("code", "GENDER_REQUIRED");
			map.put("message", "Gender field is required. Please specify the gender (e.g., male, female, non-binary).");
			return new ResponseEntity<> (map, HttpStatus.BAD_REQUEST);
		}
		
		if (udao.usernameexists(user.get("username").toString())) {
			map.put("status", "error");
			map.put("code", "USERNAME_EXISTS");
			map.put("message", "The provided username is already taken. Please choose a different username.");
			return new ResponseEntity<> (map, HttpStatus.BAD_REQUEST);
		}
		
		if (udao.emailexists(user.get("email").toString())) {
			map.put("status", "error");
			map.put("code", "EMAIL_EXISTS");
			map.put("message", "The provided email is already registered. Please use a different email address.");
			return new ResponseEntity<> (map, HttpStatus.BAD_REQUEST);
		}
		
		if (!validate.ValidatePassword(user.get("password").toString())) {
			map.put("status", "error");
			map.put("code", "INVALID_PASSWORD");
			map.put("message", "The provided password does not meet the requirements. Password must be at least 8 characters long and contain a mix of uppercase and lowercase letters, numbers, and special characters.");
			return new ResponseEntity<> (map, HttpStatus.BAD_REQUEST);
		}
		
		if (user.get("age") != null) {
			
			if (!(user.get("age") instanceof Integer) || (Integer) user.get("age") <0) {
				map.put("status", "error");
				map.put("code", "INVALID_AGE");
				map.put("message", "Invalid age value. Age must be a positive integer.");
				return new ResponseEntity<> (map, HttpStatus.BAD_REQUEST);
			}
		}
		

		  try { 
			    UserRegister obj= udao.adduser(user);
				map.put("status", "success");
				map.put("message", "User successfully registered!");
				map.put("data", obj);
				return new ResponseEntity<> (map, HttpStatus.OK);
			
		  } catch (Exception e) { System.out.println("Some thing went wrong"); }
		 
		 
		  map.put("status", "error"); map.put("code", "INTERNAL_SERVER_ERROR");
		  map.put("message", "INTERNAL_SERVER_ERROR"); return new ResponseEntity<>
		  (map, HttpStatus.INTERNAL_SERVER_ERROR);
		 
	}
	
	
	
	@PostMapping("api/data")
	public ResponseEntity<?> Addmapping(@RequestBody KeyValuePair kvp) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		if (kvp == null || kvp.getKey() == null || kvp.getKey() == "") {
			map.put("status", "error");
			map.put("code", "INVALID_KEY");
			map.put ("message", "Value should not be null");
			return new ResponseEntity<> (map, HttpStatus.BAD_REQUEST);
		}
		if (kvp.getValue() == null) {
			map.put("status", "error");
			map.put("code", "INVALID_VALUE");
			map.put ("message", "Value should not be null");
			return new ResponseEntity<> (map, HttpStatus.BAD_REQUEST);
		}
		else if (dao.addpair(kvp)) {
			map.put("status", "success");
			map.put("messgae", "Data stored successfully.");
			return new ResponseEntity<> (map, HttpStatus.OK);
		}
		else {
			map.put("error", "The provided key already exists in the database. To update an existing key, use the update API.");
			return new ResponseEntity<> (map, HttpStatus.BAD_REQUEST);
		}	

	}
	
	@DeleteMapping("api/data/{key}")
	public ResponseEntity<?> DeleteMapping(@PathVariable String key) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		if (key == null || key == "") {
			map.put("status", "error");
			map.put("code", "INVALID_KEY");
			map.put ("message", "Key must have a value");
			return new ResponseEntity<> (map, HttpStatus.BAD_REQUEST);
		}
		
		if (dao.deletePair (key)) {
			map.put("status", "success" );
			map.put("message", "data_deleted_succesfully");
			return new ResponseEntity<> (map, HttpStatus.OK);
		}
		else {
			map.put("status", "error");
			map.put("code", "KEY_NOT_FOUND");
			map.put ("message", "Provided key does not exist in the Database");	
			return new ResponseEntity<> (map, HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@GetMapping("api/data/{key}")
	public ResponseEntity<?> GetMapping(@PathVariable String key) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		if (key == null || key == "") {
			map.put("status", "error");
			map.put("code", "INVALID_KEY");
			map.put ("message", "Key must have a value");
			return new ResponseEntity<> (map, HttpStatus.BAD_REQUEST);
		}
		KeyValuePair kvp = dao.getPair(key).orElse(null);
		if (kvp == null) {
			map.put("status", "error");
			map.put ("code","KEY_NOT_FOUND");
		    map.put("message", "Provided key does not exist in the Database");
			return new ResponseEntity<> (map, HttpStatus.BAD_REQUEST);
		}
		else {
			map.put("status", "success");
			map.put("data", kvp);
		}
		return new ResponseEntity<> (map, HttpStatus.OK);
	}
	
	@PutMapping("api/data/{key}")
	public ResponseEntity<?> UpdateMapping( @PathVariable String key, @RequestBody String value) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		if (value == null){
			map.put("status", "error");
			map.put("code", "INVALID_VALUE");
			map.put ("message", "Value should not be null");
			return new ResponseEntity<> (map, HttpStatus.BAD_REQUEST);
		}
		if (key == null || key == "") {
			map.put("status", "error");
			map.put("code", "INVALID_KEY");
			map.put ("message", "Key must have a value");
			return new ResponseEntity<> (map, HttpStatus.BAD_REQUEST);
		}
		if (dao.update(key, value)){
			map.put("status", "sucess");
			map.put ("message", "Data updated succesfully");
			return new ResponseEntity<> (map, HttpStatus.OK);
		}
		else {
			map.put("status", "error");
			map.put ("code","KEY_NOT_FOUND");
		    map.put("message", "Provided key does not exist in the Database");
		    return new ResponseEntity<> (map, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/api/token")
	public ResponseEntity<?> authenticate(@RequestBody Map<String, Object> user) {
		
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		
		if (user.get("username") == null || user.get("username").toString() == ""  || user.get("password") == null || user.get("password").toString() == "") {
			map.put("status", "error");
			map.put("code", "MISSING_FIELDS");
			map.put("message", "Missing fields. Please provide both username and password.");
			return new ResponseEntity<> (map, HttpStatus.BAD_REQUEST);
		}
		
		
		try {
			
			if (udao.userauthentication (user.get("username").toString(),user.get("password").toString())) {
				map.put("status", "success");
				map.put("message", "Access token generated successfully.");
				String token = jwtService.generateToken(user.get("username").toString());
				
				Map<String, Object> m = new LinkedHashMap <String, Object>();
				m.put("acess_token", token);
				m.put("expires_in", 3600);
				map.put("data", m);
				return new ResponseEntity<> (map, HttpStatus.OK);
			}
			else {
				map.put("status", "error");
				map.put("error_code", "Invalid credentials. The provided username or password is incorrect.");
				return new ResponseEntity<> (map, HttpStatus.BAD_REQUEST);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			map.clear();
			map.put("status", "error");
			map.put("INTERNAL_ERROR", "Internal server error occurred. Please try again later.");
			return new ResponseEntity<> (map, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
	}
	
	
}
