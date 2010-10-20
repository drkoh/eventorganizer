package com.cs446teameo.Event;

import java.io.Serializable;

import com.cs446teameo.Actor.BgProcesser;
import com.cs446teameo.Parameter.ErrorCode;

public class Event implements Activicable,Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8901889794218787753L;

	TextField text_field;
	Reminder reminder;
	//RepeatMode rpmode;
	StatusSetting st_setting;
	TimeToAttack time_to_attack;
	Segment segment;
	//event id
	int eid;
	
	public Event(){	
	}


	public int trigger(BgProcesser bgprocesser) {
		// TODO Auto-generated method stub
		return bgprocesser.trigger(this);
	}
	
	public Event setTextField(String content){
		
		return this;
	}
	
	//and the Integer returned is just the error code
	public int setAttribute(EField something){
		return ErrorCode.SUCCESS;
	}
	
	//Note:
	//should we implement Serializable in this class???
}
