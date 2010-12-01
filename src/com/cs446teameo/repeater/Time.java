package com.cs446teameo.repeater;

import java.util.GregorianCalendar;


public class Time {
	int startHour,startMinute;
	int endHour,endMinute;

	public Time(){
		startHour = 0;
		startMinute = 0;
		endHour = 0;
		endMinute = 0;
	}
	public boolean set(int sh,int sm,int eh,int em){
		if(sh > eh)
			return false;
		if(sh == eh && sm >= em)
			return false;
		startHour = sh;
		startMinute = sm;
		endHour = eh;
		endMinute = em;
		return true;
	}
	public String toString(){
		return new String("h"+startHour+ ":" + "min"+startMinute + " $ " + "h"+endHour + ":" + "min" + endMinute);
	}
}
