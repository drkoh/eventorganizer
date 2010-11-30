package com.cs446teameo.Event;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Vector;


import android.R;

import com.cs446teameo.Actor.BgProcesser;
import com.cs446teameo.Parameter.ErrorCode;
import com.cs446teameo.UI.EventAdderUI;
import com.cs446teameo.repeater.Anniversary;
import com.cs446teameo.repeater.Driver;
import com.cs446teameo.repeater.Monthly;
import com.cs446teameo.repeater.RepeatSet;
import com.cs446teameo.repeater.SingleSet;
import com.cs446teameo.repeater.TimeSet;
import com.cs446teameo.repeater.Weekly;
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

	public void setTime(Integer integer, List<Object> list) {
		// TODO Auto-generated method stub
		if(integer == EventAdderUI.NONE){
			GregorianCalendar start = new GregorianCalendar((Integer)list.get(2),(Integer)list.get(3),
					(Integer)list.get(4),(Integer)list.get(0),(Integer)list.get(1));
			GregorianCalendar end = new GregorianCalendar((Integer)list.get(7),(Integer)list.get(8),
					(Integer)list.get(9),(Integer)list.get(5),(Integer)list.get(6));
			SingleSet tset = new SingleSet();
			tset.setField(SingleSet.START, start);
			tset.setField(SingleSet.END, end);
			if(tset.nextTrigger() == null){
				this.start_time = -1;
				this.end_time = -1;
			}
			else{
				this.start_time = start.getTimeInMillis() / 1000;
				this.end_time = end.getTimeInMillis() / 1000;
			}
			this.time_text = tset.toString();
		}else{
			RepeatSet set = null;
			switch(integer){
				//case EventAdderUI.DAILY: set = new Daily();break;
				case EventAdderUI.WEEKLY: set = new Weekly();break;
				case EventAdderUI.MONTHLY: set = new Monthly();break;
				case EventAdderUI.YEARLY: set = new Anniversary();break;
			}
			ArrayList<Integer> src = new ArrayList<Integer>();
			for(int i=0;i<3;i++)
				src.add((Integer) list.get(i));
			set.setField(RepeatSet.TIME_OF_DAY, src);
			switch(integer){
				case EventAdderUI.DAILY:break;
				case EventAdderUI.WEEKLY: {
					set.setField(RepeatSet.DATE, (ArrayList<Integer>) list.get(4));
					break;
				}
				case EventAdderUI.MONTHLY:{
					ArrayList<Integer> src1 = new ArrayList<Integer>();
					src1.add((Integer) list.get(4));
					set.setField(RepeatSet.DATE, src1);
					break;
				}
				case EventAdderUI.YEARLY:{
			//		you forget to do something here;
			//		set = new Anniversary();
					break;
				}
			}
			this.time_text = set.toString();
			this.start_time = set.nextTrigger().getTimeInMillis() / 1000;
			this.end_time = this.start_time + set.period();
		}
	}
}
