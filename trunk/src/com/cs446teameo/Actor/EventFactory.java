package com.cs446teameo.Actor;

import com.cs446teameo.Evfac.EventCreator;
import com.cs446teameo.Evfac.EventDecorator;
import com.cs446teameo.Evfac.EventManager;

public class EventFactory {
	private static EventFactory _instance;
	
	private EventCreator creator;
	private EventManager accessor;
	private EventDecorator editor;
	
	
	
	private EventFactory(){
		creator = null;
		accessor = null;
		editor = null;
	}
	
	public EventFactory getInstance(){
		if(_instance == null)
			_instance = new EventFactory();
		return _instance;
	}
	
	
	
	
	
}
