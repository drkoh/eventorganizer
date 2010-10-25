package com.cs446teameo.Event;

public class EventBuilder {
	protected Event event;
	
	public Event getEvent(){
		return event;
	}
	
	public void createEvent(){
		event = new Event(0);
	}
	
	public void setDescription(String desc){
		event.setDescription(new SimpleText(desc));
	}
	
	public void setStatus(boolean s){
		event.setStatus(new StatusSetting(s));
	}
	
	public void setLocation(String l){
		event.setLocation(new Location(l));
	}
	
	public void setTime(int sYear, int sMonth, int sDay, int sHour, int sMin,
						int eYear, int eMonth, int eDay, int eHour, int eMin){
		event.setTime(new Segment(sYear, sMonth, sDay, sHour, sMin, eYear, eMonth, eDay, eHour, eMin));
	}
	
}
