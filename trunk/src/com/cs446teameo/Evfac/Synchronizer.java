//reference:
//http://svn.jimblackler.net/jimblackler/trunk/workspace/AndroidReadCalendarExample/src/net/jimblackler/readcalendar/Example.java
/*
package com.cs446teameo.Evfac;

import java.util.Date;
import java.util.HashSet;
import java.util.Vector;

import com.cs446teameo.Event.Event;
import com.cs446teameo.Event.EventBuilder;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.text.format.DateUtils;


public class Synchronizer extends EventCreator{

	
	public Synchronizer(){
		
	}
	
	public void syncCalendar(){
		getCalendar();
		
	}
	
	private void getCalendar(){
		Context context = null;
		ContentResolver contentResolver = context.getContentResolver();
		
		String[] list = new String[]{"id", "name"};
		Uri calendars = Uri.parse("content://calendar/calendars");
		
		Cursor c = contentResolver.query(calendars, list, null, null, null);
		
		HashSet<String> calendarIds = new HashSet<String>();
		
		while (c.moveToNext()){
			String id = c.getString(0);
			calendarIds.add(id);
		}
		
		for (String id : calendarIds){
			Uri.Builder builder = Uri.parse("content://calendar/instances/when").buildUpon();
			long current = new Date().getTime();
			ContentUris.appendId(builder, current - DateUtils.YEAR_IN_MILLIS);
			ContentUris.appendId(builder, current + DateUtils.YEAR_IN_MILLIS);
			
			Cursor eventCursor = contentResolver.query(builder.build(),
							new String[] {"title", "start", "end", "allDay", "event_location"}, "Calendars.id=" + id,
							null, "startDay ASC, startMinute ASC");
			
			while (eventCursor.moveToNext()){
				String title = eventCursor.getString(0);
				Date start = new Date(eventCursor.getLong(1));
				Date end = new Date(eventCursor.getLong(2));
				Boolean allDay = !eventCursor.getString(3).equals("0");
				String location = eventCursor.getString(4);
				
				EventBuilder eb = new EventBuilder();
				eb.setDescription(title);
				eb.setLocation(location);
				eb.setTime(start.getYear(), start.getMonth(), start.getDay(), start.getHours(), start.getMinutes(), 
						end.getYear(), end.getMonth(), end.getDay(), end.getHours(), end.getMinutes());
				
				Event e = eb.getEvent();
				EventManager em = EventManager.getInstance();				
			//	em.createNew(e);
			}
			
			
		}
	}

	@Override
	public Event CreateEvent(int style) {
		// TODO Auto-generated method stub
		return null;
	}

	public Vector<Event> CreateSome() {
		// TODO Auto-generated method stub
		return null;
	}
}
*/