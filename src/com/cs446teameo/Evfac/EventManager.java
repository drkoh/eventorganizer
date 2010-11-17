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
		Event tmpe = new Event(0);
		for (int i = 0; i < list.getCount(); i++) {
			CharArrayBuffer buffer = new CharArrayBuffer(1);
			list.copyStringToBuffer(list.getColumnIndex("start_time") , buffer);
			long starttime = Integer.parseInt(buffer.toString());
			list.copyStringToBuffer(list.getColumnIndex("end_time") , buffer);
			long endtime = Integer.parseInt(buffer.toString());
			tmpe.setTime(new Segment(starttime, endtime));
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
