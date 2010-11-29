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

	public int createEvent(ArrayList<Object> src) {
		// TODO Auto-generated method stub
		Event event = new Event();
		/*
		event.setName((String)src.get(0));
		event.setPid((Integer)src.get(1)); //The second parameter of this should be the volume to pass, which is either -1 or a positive number
		event.setTime(src.get(2).toString());
		//event.setTime(new Segment((Integer)src.get(4),(Integer)src.get(5),(Integer)src.get(6),(Integer)src.get(10),
		//		(Integer)src.get(11),(Integer)src.get(7),(Integer)src.get(8),(Integer)src.get(9),(Integer)src.get(12),
		//		(Integer)src.get(13)));
		event.setLocation((String) src.get(14));
		Log.i("eventfac", "go here");
		return accessor.createNewEvent(event) << ErrorCode.DB_BITS;
		*/
		return 0;
	}
}
