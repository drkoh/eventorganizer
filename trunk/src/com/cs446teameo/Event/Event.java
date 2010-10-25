package com.cs446teameo.Event;

import java.io.Serializable;

import com.cs446teameo.Actor.BgProcesser;
import com.cs446teameo.Parameter.ErrorCode;

public class Event implements Activicable,Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8901889794218787753L;

	private TextField description;
	//private RepeatMode rpmode;
	private StatusSetting st_setting;
	//private TimeToAttack time_to_attack;
	private Segment time;
	private int eid;
	private Location location;
	
	public Event(int style){

	}
	
	public void setDescription(SimpleText desc){
		this.description = desc;
	}
	
	public void setStatus(StatusSetting s){
		this.st_setting = s;
	}
	
	public void setTime(Segment t){
		this.time = t;
	}
	
	public void setLocation (Location l){
		this.location = l;
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
	
	public String sqlizeEvent(){
		String e = "_id " + eid + ", location " location.location + ", start_time" + time.startUnixTime() +
		", end_time " + time.endUnixTime();
		return e;
	}
	
	//Note:
	//should we implement Serializable in this class???
}
