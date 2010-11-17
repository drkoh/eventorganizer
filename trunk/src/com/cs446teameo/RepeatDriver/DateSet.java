package com.cs446teameo.RepeatDriver;
import java.util.Date;


public abstract class DateSet {
	
	public static int MAX_YEAR = 2050;
	
	public DateSet(){}
	public abstract String toString();
	public Date nextTrigger(){
		Date now = new Date();
		return nextTriggerAfter(now);
	}
	protected abstract Date nextTriggerAfter(Date date); 
	protected abstract boolean contain(Date date);
}


