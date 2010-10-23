package com.cs446teameo.Actor;

import java.util.ArrayList;

import com.cs446teameo.Evfac.EventCreator;
import com.cs446teameo.Evfac.EventDecorator;
import com.cs446teameo.Evfac.EventManager;
import com.cs446teameo.Parameter.ErrorCode;

public class EventFactory {
	private static EventFactory _instance;
	
	private EventCreator creator;
	private EventManager accessor;
	private EventDecorator editor;
	
	
	
	private EventFactory(){
		creator = new EventCreator();
		accessor = null;
		editor = null;
	}
	
	public static EventFactory getInstance(){
		if(_instance == null)
			_instance = new EventFactory();
		return _instance;
	}

	public int addEvent(ArrayList<Object> src) {
		// TODO Auto-generated method stub
		return ErrorCode.SUCCESS;
	}
	
	
	
	
	
}
