package com.cs446teameo.Evfac;

import java.util.ArrayList;

import android.app.Activity;

import com.cs446teameo.Event.*;
import com.cs446teameo.Parameter.ErrorCode;
import com.cs446teameo.Storage.*;
import android.database.*;

public class EventManager implements EventAccess{
	private static EventManager _instance = null;
	private static Activity owner = null;
	private EventDatabase ebase = null;
	private ProfileDatabase pbase  = null;
	
	public static void setActivity(Activity ownergiven) {
		owner = ownergiven;
	}
	
	private EventManager(){
		ebase = new EventDatabase(owner);
	}
	
	public static EventManager getInstance(){
		if(_instance == null)
			_instance = new EventManager();
		return _instance;
	}
	
	
	public int initEventDatabase(){
		ebase.open();
		return ErrorCode.SUCCESS;
	}
	
	public int createNewEvent(Event e){
		ebase.query("inset " + e.sqlizeEvent());
		return ErrorCode.SUCCESS;
	}
	
	public int deleteEvent(int eId){
		ebase.query("delete from Events where _id="+eId);
		return ErrorCode.SUCCESS;
	}	

	public int filterEvent(String query, ArrayList<Event> dst){
		Cursor list = ebase.query("select * from Events where ");
		if (!list.isFirst())
			list.moveToFirst();
		Event tmpe = new Event("");
		for (int i = 0; i < list.getCount(); i++) {
			CharArrayBuffer buffer = new CharArrayBuffer(1);
			list.copyStringToBuffer(1, buffer);
			long time = Integer.parseInt(buffer.toString());
			tmpe.setTime(new Segment(time));
			dst.add(tmpe);
		}
		return ErrorCode.SUCCESS;
	}
	
	public int listEvent(ArrayList<Event> dst){
		return ErrorCode.SUCCESS;
	}
	
	public int updateEvent(Event e){
		return ErrorCode.SUCCESS;
	}
	
	public int initProfileDatabase(){
		return 0;
	}
}
