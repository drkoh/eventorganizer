package com.cs446teameo.Evfac;

import java.util.Vector;
import com.cs446teameo.Event.Event;

public abstract class EventCreator{

	
	protected static EventCreator _instance = null;
	
	public abstract Event CreateEvent(int style);
	
	public static EventCreator getInstance(){
		return _instance;
	}
}
