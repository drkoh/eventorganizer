package com.cs446teameo.RepeatDriver;
import java.util.Date;


public class TimeSet {
	DateSet dset;
	Time time;
	public TimeSet(DateSet dset,Time time){	
		this.dset = dset;
		this.time = time;
	}
	public String toString(){
		StringBuffer ret = new StringBuffer();
		ret.append(time.toString());
		ret.append(" @ ");
		ret.append(dset.toString());
		return ret.toString();
	}
	@SuppressWarnings("deprecation")
	public Date nextTrigger(){
		Date day = dset.nextTrigger();
		return new Date(day.getYear(),day.getMonth(),day.getDay(),time.startHour,time.startMinute);
	}
	
	public int period(){
		return (time.endHour - time.startHour)*60 + time.endMinute - time.startMinute;
	}
}
