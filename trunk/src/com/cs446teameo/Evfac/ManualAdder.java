package com.cs446teameo.Evfac;

import java.util.Vector;

import com.cs446teameo.Event.Event;

public class ManualAdder extends EventCreator{

	
	private ManualAdder(){
	}
	
	
	public static void refreshInstance(){
		_instance = new ManualAdder();
	}
	
	@Override
	public Event CreateEvent(int style) {
		return new Event(style);
	}
}
