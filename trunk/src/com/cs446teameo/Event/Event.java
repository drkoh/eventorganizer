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
	private TextField description;
	public Segment time;
	private Location location;
	public StatusSetting st_setting;
	//private TimeToAttack time_to_attack;
	//private RepeatMode rpmode;
	
	public Event(int style){

	}
	
	public int getEid(){
		return eId;
	}
	
	public void setDescription(SimpleText desc){
		this.description = desc;
	}
	
	public String getDescription(){
		return description.toString();
	}
	
	public void setStatus(StatusSetting s){
		this.st_setting = s;
	}
	
	public int getStatus(){
		return st_setting.getStatus();
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
	
	public void setLocation (Location l){
		this.location = l;
	}
	
	public String getLocation(){
		return location.location;
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
