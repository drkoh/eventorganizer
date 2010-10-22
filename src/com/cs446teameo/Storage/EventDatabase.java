package com.cs446teameo.Storage;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;

import com.cs446teameo.Event.Event;

public class EventDatabase {
	//Here we have to design our database considering concurrency
	
	
	
	private int currentId;
	
	private ObjectInputStream reader = null;
	private ObjectOutputStream writer = null;
	
	private static EventDatabase _instance = null;
	
	
	
	private EventDatabase(){
	}
	
	
	public static EventDatabase getInstance(){
		if(_instance == null)
			_instance = new EventDatabase();
		return _instance;
	}
	
	
	int getCurrentId(){
		return 0;
	}
	
	
	int createNew(Event event){
		return 0;
	}
	
	int getReadable(Event event){
		return 0;
	}
	
	int getReadable(Vector<Event> list){
		return 0;
	}
	
	int getEditable(Event event){
		return 0;
	}
	
	int getEditable(Vector<Event> list){
		return 0;
	}
	
	int write(Event event){
		return 0;
	}
	
	int write(Vector<Event> list){
		return 0;
	}
	
}
