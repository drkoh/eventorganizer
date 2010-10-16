package com.cs446teameo.Evfac;

import com.cs446teameo.Event.EventAccess;
import com.cs446teameo.Storage.EventDatabase;
import com.cs446teameo.Storage.ProfileDatabase;

public class EventManager implements EventAccess{
	private static EventManager _instance = null;
	private EventDatabase ebase = null;
	private ProfileDatabase pbase  = null;
	
	private EventManager(){
	}
	
	
	public EventManager getInstance(){
		if(_instance == null)
			_instance = new EventManager();
		return _instance;
	}
	
	
	public int initEventDatabase(){
		return 0;
	}
	
	
	public int initProfileDatabase(){
		return 0;
	}
	
	
	
	
	
	
	
	
}
