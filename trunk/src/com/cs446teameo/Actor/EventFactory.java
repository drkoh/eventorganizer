package com.cs446teameo.Actor;

import java.util.ArrayList;

import android.util.Log;

import com.cs446teameo.Event.Event;
import com.cs446teameo.Event.Segment;
import com.cs446teameo.Event.SimpleText;
import com.cs446teameo.Event.StatusSetting;
import com.cs446teameo.Evfac.EventCreator;
import com.cs446teameo.Evfac.EventDecorator;
import com.cs446teameo.Evfac.EventManager;
import com.cs446teameo.Evfac.ManualAdder;
import com.cs446teameo.Main.Main;
import com.cs446teameo.Parameter.ErrorCode;

public class EventFactory {
	private static EventFactory _instance;
	
	private EventCreator creator;
	private EventManager accessor;
	private EventDecorator editor;
	
	
	
	private EventFactory(){
		ManualAdder.refreshInstance();
		creator = EventCreator.getInstance();
		accessor = EventManager.getInstance();
		editor = null;
	}
	
	public static EventFactory getInstance(){
		if(_instance == null)
			_instance = new EventFactory();
		return _instance;
	}

	public int createEvent(ArrayList<Object> src) {
		// TODO Auto-generated method stub
		Event event = new Event();
		event.setDescription((String)src.get(0));
		event.setStatus(new StatusSetting(src.get(1).toString()));
		event.setRepeatOption(src.get(2).toString());
		event.setRepeatText(src.get(3).toString());
		event.setTime(new Segment((Integer)src.get(4),(Integer)src.get(5),(Integer)src.get(6),(Integer)src.get(10),
				(Integer)src.get(11),(Integer)src.get(7),(Integer)src.get(8),(Integer)src.get(9),(Integer)src.get(12),
				(Integer)src.get(13)));
		event.setLocation((String) src.get(14));
		return accessor.createNewEvent(event) << ErrorCode.DB_BITS;
	}
}
