package com.cs446teameo.Actor;

public class User {
	private static User _instance;
	
	private User(){
		
	}
	
	public User getInstance(){
		if(_instance == null)
			_instance = new User();
		return _instance;
	}
}
