package com.cs446teameo.Evfac;

import java.util.ArrayList;

import android.app.Activity;

import com.cs446teameo.Event.*;
import com.cs446teameo.Parameter.ErrorCode;
import com.cs446teameo.Storage.*;

import android.content.ContentValues;
import android.database.*;

public class EventManager implements EventAccess{
	private static EventManager _instance = null;
	private static Activity owner = null;
	private Database ebase = null;
	
	public static void setActivity(Activity ownergiven) {
		owner = ownergiven;
	}
	
	private EventManager(){
		ebase = Database.open(owner);
	}
	
	public static EventManager getInstance(){
		if(_instance == null)
			_instance = new EventManager();
		return _instance;
	}
	
	
	/*public int initEventDatabase(){
		ebase = ebase.open(owner);
		return ErrorCode.SUCCESS;
	}*/
	
	public int createNewEvent(Event e){
		ContentValues val = new ContentValues();
		
		val.put(Database.EVENT_NAME, e.getDescription());
		val.put(Database.EVENT_START, e.getStartTime());
		val.put(Database.EVENT_END, e.getEndTime());
		val.put(Database.EVENT_LOCATION, e.getLocation());
		//val.put(Database.EVENT_PROFILE_ID, null);
		
		if (ebase.insert(ebase.getEventTable(), val) > 0)
			return ErrorCode.SUCCESS;
		return 0;
	}
	
	public int deleteEvent(int eId){
		String cond = Database.EVENT_ID + "=" + eId;
		
		if (ebase.delete(ebase.getEventTable(), cond) > 0)
			return ErrorCode.SUCCESS;
		return 0;
	}	
	
	public int updateEvent(Event e, int eId){
		String cond = Database.EVENT_ID + "=" + eId;
		ContentValues val = new ContentValues();
		
		val.put(Database.EVENT_NAME, e.getDescription());
		val.put(Database.EVENT_START, e.getStartTime());
		val.put(Database.EVENT_END, e.getEndTime());
		val.put(Database.EVENT_LOCATION, e.getLocation());
		
		if (ebase.update(ebase.getEventTable(), val, cond) > 0)
			return ErrorCode.SUCCESS;
		return 0;
	}
	
	public Cursor selectEvent(String cond){
		String sel = "select * from " + ebase.getEventTable();
		
		if (cond.length() > 0 || cond == null){
			sel = sel + " where " + cond;
		}
		
		return ebase.select(sel);
	}

	public int filterEvent(String query, ArrayList<Event> dst){
		Cursor list = selectEvent(null);
		if (!list.isFirst())
			list.moveToFirst();
		Event tmp = new Event(0);
		for (int i = 0; i < list.getCount(); i++) {
			CharArrayBuffer buffer = new CharArrayBuffer(1);
			list.copyStringToBuffer(list.getColumnIndex("start_time") , buffer);
			long starttime = Integer.parseInt(buffer.toString());
			list.copyStringToBuffer(list.getColumnIndex("end_time") , buffer);
			long endtime = Integer.parseInt(buffer.toString());
			tmp.setTime(new Segment(starttime, endtime));
			dst.add(tmp);
		}
		return ErrorCode.SUCCESS;
	}
	
	public int listEvent(ArrayList<Event> dst){
		return ErrorCode.SUCCESS;
	}
	
	
	
	public int initProfileDatabase(){
		return 0;
	}
}
