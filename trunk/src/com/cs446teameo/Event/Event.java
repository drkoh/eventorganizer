package com.cs446teameo.Event;

import java.io.Serializable;

import com.cs446teameo.Actor.BgProcesser;

public class Event implements Activicable,Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8901889794218787753L;

	TextField text_field;
	Reminder reminder;
	RepeatMode rpmode;
	StatusSetting st_setting;
	TimeToAttack time_to_attack;
	
	//event id
	int eid;



	public int trigger(BgProcesser bgprocesser) {
		// TODO Auto-generated method stub
		return bgprocesser.trigger(this);
	}
	
	
	//Note:
	//should we implement Serializable in this class???
}
