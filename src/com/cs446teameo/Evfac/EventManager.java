package com.cs446teameo.Evfac;

import java.util.ArrayList;

import com.cs446teameo.Event.Event;
import com.cs446teameo.Event.EventAccess;
import com.cs446teameo.Parameter.ErrorCode;
import com.cs446teameo.Storage.EventDatabase;
import com.cs446teameo.Storage.ProfileDatabase;

public class EventManager implements EventAccess{
	private static EventManager _instance = null;
	private EventDatabase ebase = null;
	private ProfileDatabase pbase  = null;
	
	private EventManager(){
	}
	
	
	public static EventManager getInstance(){
		if(_instance == null)
			_instance = new EventManager();
		return _instance;
	}
	
	
	public int initEventDatabase(){
		return 0;
	}
	
	public int createNewEvent(Event e){
		return ErrorCode.SUCCESS;
	}
	
	public int deleteEvent(int eId){
		return ErrorCode.SUCCESS;
	}	

	public int filterEvent(String query){
		return ErrorCode.SUCCESS;
	}
	
	public int listEvent(ArrayList<Event> dst){
		return ErrorCode.SUCCESS;
	}
	
	public int updateEvent(Event e){
		return ErrorCode.SUCCESS;
	}
	
	public int initProfileDatabase(){
		return 0;
	}
}
