package Evfac;

import Event.EventAccess;
import Storage.EventDatabase;
import Storage.ProfileDatabase;

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
