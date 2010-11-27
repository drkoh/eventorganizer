package com.cs446teameo.Evfac;

import java.util.ArrayList;
import java.util.Calendar;

import android.app.Activity;

import com.cs446teameo.Event.*;
import com.cs446teameo.Parameter.ErrorCode;
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
		
		val.put(Database.EVENT_NAME, e.getDescription());
		val.put(Database.EVENT_START, e.getStartTime());
		val.put(Database.EVENT_END, e.getEndTime());
		val.put(Database.EVENT_LOCATION, e.getLocation());
		val.put(Database.EVENT_PROFILE_ID, "");
		
		long s = ebase.insert(ebase.getEventTable(), val);

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
		val.put(Database.EVENT_NAME, e.getDescription());
		val.put(Database.EVENT_REPEAT_OPTION, e.getRepeatOption());
		val.put(Database.EVENT_REPEAT_TEXT, e.getRepeatText());
		val.put(Database.EVENT_START, e.getStartTime());
		val.put(Database.EVENT_END, e.getEndTime());
		val.put(Database.EVENT_LOCATION, e.getLocation());
		
		ebase.update(ebase.getEventTable(), val, cond);
		
		/*if (ebase.update(ebase.getEventTable(), val, cond) > 0) {
			//TODO: notify the BG with that id
			notifyBG("ACTION_UPDATE_EVENT", ""+eId);
			return ErrorCode.SUCCESS;
		}*/
		return 0;
	}
	
	public static Cursor selectEvent(String cond){
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

	/* CALVIN - FILL THESE METHODS - START */	
	
	// Initialize the Profile Database
	public static int initProfileDatabase(){
		return 0;
	}

	public static int listEvent(ArrayList<Event> dst){
        return ErrorCode.SUCCESS;
	}
	
	// Returns all the events from the event table, first sorted by Start Date, then by Start time
	public static ArrayList<Event> allEvents()
	{
		return null;
	}

	// Returns all the events from the event table, that occur at the same time as calendar. Must be sorted by Start Time.
	public static ArrayList<Event> allEventsOfDay(Calendar calendar)
	{
		return null;
	}

	// Does any event occur on the specified day?
	public static boolean eventOccursOnDay(Calendar calendar)
	{
		return false;
	}
	
	// Does any event occur on the specified year?
	public static boolean eventOccursOnYear(Calendar calendar)
	{
		return false;
	}
	/* CALVIN - FILL THESE METHODS - END */
}
