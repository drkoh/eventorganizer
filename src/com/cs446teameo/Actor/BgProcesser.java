package com.cs446teameo.Actor;

import com.cs446teameo.Event.Event;

public class BgProcesser{
	private static BgProcesser _instance = null; 
	private BgProcesser(){
		
	}
	
	public BgProcesser getInstance(){
		if(_instance == null){
			_instance = new BgProcesser();
		}
		return _instance;
	}
	
	
	public int trigger(Event event){
		return 0;
	}
	
	
	
	
}
