package com.cs446teameo.Actor;

import java.util.ArrayList;

import android.util.Log;

import com.cs446teameo.Event.Event;
import com.cs446teameo.Evfac.EventManager;
import com.cs446teameo.Main.Main;
import com.cs446teameo.Parameter.ErrorCode;

public class EventFactory {
	private static EventFactory _instance;
	
	private EventManager accessor;
	
	private EventFactory(){
		accessor = EventManager.getInstance();
	}
	
	public static EventFactory getInstance(){
		if(_instance == null)
			_instance = new EventFactory();
		return _instance;
	}

	public int editEvent(ArrayList<Object> src, int eid) {
		// TODO Auto-generated method stub
		Log.d("ev", ((Integer)src.size()).toString());
		Event event = new Event();
		event.setName((String)src.get(1));
		event.setPid((Integer)src.get(2)); //The second parameter of this should be the volume to pass, which is either -1 or a positive number
		event.setLocation((String) src.get(3));
		
		Log.i("west", src.subList(4, src.size()).toString());
		event.setTime((Short)src.get(0),src.subList(4, src.size()));
		Log.i("eventfac", "go here");
		return accessor.updateEvent(event, eid) << ErrorCode.DB_BITS;
	}
	
	public int createEvent(ArrayList<Object> src) {
		// TODO Auto-generated method stub
		Log.d("ev", ((Integer)src.size()).toString());
		Event event = new Event();
		event.setName((String)src.get(1));
		event.setPid((Integer)src.get(2)); //The second parameter of this should be the volume to pass, which is either -1 or a positive number
		event.setLocation((String) src.get(3));
		
		Log.i("west", src.subList(4, src.size()).toString());
		event.setTime((Short)src.get(0),src.subList(4, src.size()));
		Log.i("eventfac", "go here");
		return accessor.createNewEvent(event) << ErrorCode.DB_BITS;
	}

}
