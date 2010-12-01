package com.cs446teameo.Evfac;

import java.util.ArrayList;
import java.util.Calendar;

import android.app.Activity;

import com.cs446teameo.Event.*;
import com.cs446teameo.Storage.*;
import com.cs446teameo.Backend.*;

import android.content.*;
import android.os.*;
import android.util.Log;
import android.database.*;
import android.net.Uri;

public class EventManager implements EventAccess{
	private static EventManager _instance = null;
	private static Activity owner = null;
	private static Database ebase = null;
	
	//////////////////////////////////for Bind to BG/////////////////////////////////
	static BG backService;
	
	static ServiceConnection bConnection = new ServiceConnection () {
		public void onServiceConnected(ComponentName className, IBinder service) {
	        // This is called when the connection with the service has been
	        // established, giving us the service object we can use to
	        // interact with the service.  Because we have bound to a explicit
	        // service that we know is running in our own process, we can
	        // cast its IBinder to a concrete class and directly access it.
	        backService = ((BG.LocalBinder)service).getService();

	    }

	    public void onServiceDisconnected(ComponentName className) {
	        // This is called when the connection with the service has been
	        // unexpectedly disconnected -- that is, its process crashed.
	        // Because it is running in our same process, we should never
	        // see this happen.
	        backService = null;
	    }		
	};
	/////////////////////////////////////////////////////////////////////////////////////
	
	public static void notifyBG (String action, String changeID) {
		Uri data = Uri.parse(changeID);
		owner.bindService(new Intent(action, data, owner, BG.class), bConnection, Context.BIND_AUTO_CREATE);
		owner.unbindService(bConnection);
	}
	
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
		
		val.put(Database.EVENT_NAME, e.getName());
		val.put(Database.EVENT_START, e.getStartTime());
		val.put(Database.EVENT_REPEAT_TEXT, e.getRepeatText());
		val.put(Database.EVENT_END, e.getEndTime());
		val.put(Database.EVENT_LOCATION, e.getLocation());
		val.put(Database.EVENT_PROFILE_ID, e.getPid());
		
		ebase.insert(ebase.getEventTable(), val);
		notifyBG("ACTION_NEW_EVENT", ""+ebase.getNewestID());
		
		/*if (s > 0) {
			//TODO: find out the new event's id and notify the BG with that id
			notifyBG("ACTION_NEW_EVENT", ""+ebase.getNewestID());
			return ErrorCode.SUCCESS;
		}*/

		return 0;
	}
	
	public int deleteEvent(int eId){
		String cond = Database.EVENT_ID + "=" + eId;
		
		ebase.delete(ebase.getEventTable(), cond);
		notifyBG("ACTION_DELETE_EVENT", ""+eId);
		/*if (ebase.delete(ebase.getEventTable(), cond) > 0) {
			//TODO: notify the BG with that id
			notifyBG("ACTION_DELETE_EVENT", ""+eId);
			return ErrorCode.SUCCESS;
		}*/
		return 0;
	}	
	
	public int updateEvent(Event e, int eId)
	{
		String cond = Database.EVENT_ID + "=" + eId;
		ContentValues val = new ContentValues();
		val.put(Database.EVENT_NAME, e.getName());
		val.put(Database.EVENT_REPEAT_TEXT, e.getRepeatText());
		val.put(Database.EVENT_START, e.getStartTime());
		val.put(Database.EVENT_END, e.getEndTime());
		val.put(Database.EVENT_LOCATION, e.getLocation());
		val.put(Database.EVENT_PROFILE_ID, e.getPid());
		
		ebase.update(ebase.getEventTable(), val, cond);
		notifyBG("ACTION_UPDATE_EVENT", ""+eId);
		
		/*if (ebase.update(ebase.getEventTable(), val, cond) > 0) {
			//TODO: notify the BG with that id
			notifyBG("ACTION_UPDATE_EVENT", ""+eId);
			return ErrorCode.SUCCESS;
		}*/
		return 0;
	}
	
	public static Cursor selectEvent(String cond){
		String sel = "select * from " + ebase.getEventTable();
		
		if (cond == null || cond.length() > 0){

			sel = sel + " " + cond;
		}
		
		return ebase.select(sel);
	}
	
	//get a single event with the given id
	public Event getEvent(int id){
		Cursor c = selectEvent("where _id="+id);
		c.moveToFirst();
		if (c.getCount() > 0) {
			Event e = new Event(c.getInt(0), c.getString(1), c.getInt(2), c.getInt(3), 
					c.getString(4), c.getInt(5), c.getString(6));
			
			c.close();
			return e;
		}
		else {
			c.close();
			return null;
		}
	}

	/* CALVIN - FILL THESE METHODS - START */	
	/*
	public int listEvent(ArrayList<Event> dst){
		Log.i("bg", "here");
			Cursor list = null;
		try {
			list = selectEvent(null);
		} catch (Exception e) {
			Log.i("bg", e.toString());
		}
		if (!list.isFirst())
			list.moveToFirst();
		Log.i("bg", "here2");
		for (int i = 0; i < list.getCount(); i++) {
			Event e = new Event(c.getInt(0), c.getString(1), new Segment(c.getInt(2), c.getInt(3)),
					c.getString(4), c.getString(5), c.getString(6), c.getString(7));
			
			dst.add(new Event(id, name, tmptime, location, pn, ro, rt));
		}
		list.close();
		Log.i("bg", "here3");
		return ErrorCode.SUCCESS;
	}*/
	
	public int initProfileDatabase(){
		return 0;
	}
	
	// Returns all the events from the event table, first sorted by Start Date, then by Start time
	public ArrayList<Event> allEvents()
	{
		Log.i("bg", "here");
		String cond = "order by " + Database.EVENT_START;
		Cursor c = selectEvent(cond);
		
		ArrayList<Event> l = new ArrayList<Event>();
		Log.i("bg", "here2");
		while (c.moveToNext()){
			Event e = new Event(c.getInt(0), c.getString(1), c.getInt(2), c.getInt(3), c.getString(4), c.getInt(5), c.getString(6));
			l.add(e);
		}
		Log.i("bg", "here3");
		c.close();
		return l;
	}

	// Returns all the events from the event table, that occur at the same time as calendar. Must be sorted by Start Time.
	public ArrayList<Event> allEventsOfDay(Calendar calendar)
	{
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
				calendar.get(Calendar.DAY_OF_MONTH), 0, 0);
		long st = calendar.getTimeInMillis();
		
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
				calendar.get(Calendar.DAY_OF_MONTH), 23, 59);
		
		long ed = calendar.getTimeInMillis();
		
		String cond = "where " + Database.EVENT_START + ">=" + st + " and " + 
						Database.EVENT_START + "<=" + ed + " order by " + Database.EVENT_START;
		
		Cursor c = selectEvent(cond);
		
		if (c.getCount() > 0){
			ArrayList<Event> l = new ArrayList<Event>();
			Log.i("bg", "here2");
			while (c.moveToNext()){
				Event e = new Event(c.getInt(0), c.getString(1), c.getInt(2), c.getInt(3), c.getString(4), c.getInt(5), c.getString(6));
				l.add(e);
			}
			Log.i("bg", "here3");
			c.close();
			return l;
		}
		else
			return null;
		
	}

	// Does any event occur on the specified day?
	public boolean eventOccursOnDay(Calendar calendar)
	{
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
				calendar.get(Calendar.DAY_OF_MONTH), 0, 0);
		long st = calendar.getTimeInMillis();
		
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
				calendar.get(Calendar.DAY_OF_MONTH), 23, 59);
		
		long ed = calendar.getTimeInMillis();
		
		String cond = "where " + Database.EVENT_START + ">=" + st + " and " + 
						Database.EVENT_START + "<=" + ed + " order by " + Database.EVENT_START;
		
		Cursor c = selectEvent(cond);
		
		if (c.getCount() > 0){
			c.close();
			return true;
		}
		else{
			c.close();
			return false;
		}
	}
	
	// Does any event occur on the specified year?
	public static boolean eventOccursOnYear(Calendar calendar)
	{
		calendar.set(calendar.get(Calendar.YEAR), 1 ,1, 0, 0);
		long st = calendar.getTimeInMillis();
		
		calendar.set(calendar.get(Calendar.YEAR)+1, 1, 1, 0, 0);
		
		long ed = calendar.getTimeInMillis();
		
		String cond = "where " + Database.EVENT_START + ">=" + st + " and " + 
						Database.EVENT_START + "<" + ed + " order by " + Database.EVENT_START;
		
		Cursor c = selectEvent(cond);
		
		if (c.getCount() > 0){
			c.close();
			return true;
		}
		else {
			c.close();
			return false;
		}
		
	}
	/* CALVIN - FILL THESE METHODS - END */
}
