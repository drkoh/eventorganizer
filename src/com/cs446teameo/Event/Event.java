package com.cs446teameo.Event;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;


import android.R;
import android.util.Log;

import com.cs446teameo.Actor.BgProcesser;
import com.cs446teameo.Parameter.ErrorCode;
import com.cs446teameo.UI.EventAdderUI;
import com.cs446teameo.repeater.*;
import com.cs446teameo.Main.Main;

public class Event {
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

	public void setTime(short integer, List<Object> list) {
		// TODO Auto-generated method stub
		Log.i("repeater", ""+integer);
		if(integer == EventAdderUI.NONE){
			GregorianCalendar start = new GregorianCalendar((Integer)list.get(2),(Integer)list.get(3),
					(Integer)list.get(4),(Integer)list.get(0),(Integer)list.get(1));
			GregorianCalendar end = new GregorianCalendar((Integer)list.get(7),(Integer)list.get(8),
					(Integer)list.get(9),(Integer)list.get(5),(Integer)list.get(6));
			SingleSet tset = new SingleSet();
			tset.setField(SingleSet.START, start);
			tset.setField(SingleSet.END, end);
			this.start_time = start.getTimeInMillis() ;
			this.end_time = end.getTimeInMillis();
			this.time_text = tset.toString();
			Log.i("repeater", this.time_text);
			Date sday = new Date(start_time);
			Date eday = new Date(end_time);
			Log.d("repeater","startTime:" + sday.toLocaleString() );
			Log.d("repeater","endTime:" + eday.toLocaleString());
		}else{
			RepeatSet set = null;
			switch(integer){
				case EventAdderUI.DAILY: set = new Daily();break;
				case EventAdderUI.WEEKLY: set = new Weekly();break;
				case EventAdderUI.MONTHLY: set = new Monthly();break;
				case EventAdderUI.YEARLY: set = new Anniversary();break;
			}
			ArrayList<Integer> src = new ArrayList<Integer>();
			for(int i=0;i<4;i++)
				src.add((Integer) list.get(i));
			Log.i("eeee", list.toString());
			set.setField(RepeatSet.TIME_OF_DAY, src);
			Log.i("eeee", "field = " + integer);
			Log.i("eeee", set.time.toString());
			switch(integer){
				case EventAdderUI.DAILY:break;
				case EventAdderUI.WEEKLY: {
					StringBuffer buf = new StringBuffer();
					Iterator<Integer> it = ((ArrayList<Integer>)list.get(4)).iterator();
					while(it.hasNext()){
						buf.append(it.next());
						buf.append(" ");
					}
					Log.d("weekly", buf.toString());
					set.setField(RepeatSet.DATE, ((ArrayList<Integer>)list.get(4)));
					break;
				}
				case EventAdderUI.MONTHLY:{
					ArrayList<Integer> src1 = new ArrayList<Integer>();
					src1.add((Integer) list.get(4));
					set.setField(RepeatSet.DATE, src1);
					break;
				}
				case EventAdderUI.YEARLY:{
				//	set = new Anniversary();
					break;
				}
			}
			this.time_text = set.toString();
			this.start_time = set.nextTrigger().getTimeInMillis();
			this.end_time = this.start_time + set.period();
			Log.i("repeater", time_text);
		}
		
		
		
	}
	
    public static long toUnixTime(GregorianCalendar time)
    {
            return time.getTimeInMillis() / 1000;
    }
    
    public static GregorianCalendar toGregorianCalendar(long time)
    {
            GregorianCalendar temp = new GregorianCalendar();
            temp.setTimeInMillis(time * 1000);
            return temp;
    }

}
