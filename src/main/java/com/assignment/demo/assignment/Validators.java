package com.assignment.demo.assignment;

import org.springframework.stereotype.Component;

@Component
public class Validators {

	public boolean ValidatePassword (String s) {
		
		if (s.length() < 8) return false;
		
		String specialChars = "~`!@#$%^&*()-_=+\\|[{]};:'\",<.>/?";
	    char currentCharacter;
	    int count = 0;

	    for (int i = 0; i < s.length(); i++) {
	        currentCharacter = s.charAt(i);
	        if (Character.isUpperCase(currentCharacter)) {
	            count++;
	        } else if (Character.isLowerCase(currentCharacter)) {
	            count++;
	        } else if (specialChars.contains(String.valueOf(currentCharacter))) {
	            count++;
	        }
	    }

	    System.out.println(count);
	    return count>1;
	}

	public boolean ValidateGender(String string) {
		// TODO Auto-generated method stub
		if (string.equals("male") || string.equals("female") || string.equals("non-binary")) {
			return true;
		}
		return false;
	}


}
