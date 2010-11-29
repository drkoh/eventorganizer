package com.cs446teameo.Event;

import java.io.Serializable;
import java.util.ArrayList;


import android.R;

import com.cs446teameo.Actor.BgProcesser;
import com.cs446teameo.Parameter.ErrorCode;
import com.cs446teameo.Main.Main;

public class Event{
	/**
	 * 
	 */
	
	private int eId;
	private String name;
	private String location;
	private long start_time;
	private long end_time;
	private int pId;
	private String time_text;
	
	
	
	public Event() {
	
	}
	
	public Event(String n, long st, long et, String l, int p, String rt){
		name = n;
		location = l;
		this.start_time = st;
		this.end_time = et;
		pId = p;
		time_text = rt;
	}
	
	public Event(int i, String n, long st, long et, String l, int p, String rt){
		eId = i;
		name = n;
		location = l;
		this.start_time = st;
		this.end_time = et;
		pId = p;
		time_text = rt;
	}
	
	public void setStartTime(long st) {
		this.start_time = st;
	}
	
	public long getStartTime() {
		return this.start_time;
	}
	
	public void setEndTime(long st) {
		this.end_time = st;
	}
	
	public long getEndTime() {
		return this.end_time;
	}
	
	public int getPid(){
		return pId;
	}

	public void setPid(int pid) {
		this.pId = pid;
	}
	
	public int getEid(){
		return eId;
	}
	
	public void setName(String desc){
		this.name = desc;
	}
	
	public String getName(){
		return name;
	}
	
	public void setLocation (String l){
		this.location = l;
	}
	
	public String getLocation(){
		return location;
	}
	
	public String getRepeatText(){
		return time_text;
	}
	
	public int trigger(BgProcesser bgprocesser) {
		// TODO Auto-generated method stub
		return bgprocesser.trigger(this);
	}
	
}
