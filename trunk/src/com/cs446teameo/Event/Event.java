package com.cs446teameo.Event;

import java.io.Serializable;


import com.cs446teameo.Actor.BgProcesser;
import com.cs446teameo.Parameter.ErrorCode;

public class Event implements Activicable,Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8901889794218787753L;
	
	private int eId;
	private String description;
	public Segment time;
	private String location;
	//for status
	private String status;
	private int volume;
	//*******************
	public String repeat_option;
	public String repeat_text;
	
	public Event(){
		
	}
	
	public Event(String d, Segment t, String l){
		description = d;
		time = t;
		location = l;
	}
	
	public Event(int id, String d, Segment t, String l){
		eId = id;
		description = d;
		time = t;
		location = l;
	}
	
	public Event(int style){
	}
	
	public int getEid(){
		return eId;
	}
	
	public void setDescription(String desc){
		this.description = desc;
	}
	
	public String getDescription(){
		return description;
	}
	
	public void setStatus(String s){
		this.status = s;
	}
	
	//if String s=volume, int volume will be a positive int, else negative
	public void setStatus(String s, int volume){
		this.status = s;
		this.volume = volume;
	}
	
	public String getStatus(){
		return status;
	}
	
	public int getVolume(){
		return volume;
	}
	
	public void setTime(Segment t){
		this.time = t;
	}
	
	public long getStartTime(){
		return time.startTime();
	}
	
	public long getEndTime(){
		return time.endTime();
	}
	
	public void setLocation (String l){
		this.location = l;
	}
	
	public String getLocation(){
		return location;
	}
	
	public void setRepeatOption (String s){
		this.repeat_option = s;
	}
	
	public String getRepeatOption(){
		return repeat_option;
	}
	
	public void setRepeatText (String s){
		this.repeat_text = s;
	}
	
	public String getRepeatText(){
		return repeat_text;
	}
	
	@Override
	public int trigger(BgProcesser bgprocesser) {
		// TODO Auto-generated method stub
		return bgprocesser.trigger(this);
	}
	
	//and the Integer returned is just the error code
	public int setAttribute(EField something){
		return ErrorCode.SUCCESS;
	}
	
	/*public String sqlizeEvent(){
		String e = "_id " + eid + ", location " +location.location + ", start_time" + time.startTime() +
		", end_time " + time.endTime();
		return e;
	}*/
	
	//Note:
	//should we implement Serializable in this class???
}
