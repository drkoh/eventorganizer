package com.cs446teameo.repeater;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Vector;


public class Daily extends RepeatSet{

	@Override
	void setDate(ArrayList<Integer> target) {
		// TODO Auto-generated method stub
	}

	@Override
	String toDate() {
		// TODO Auto-generated method stub
		return "daily";
	}

	@Override
	public GregorianCalendar nextTrigger() {
		// TODO Auto-generated method stub
	//	Log.i("trig", "enter 1");
		GregorianCalendar now = new GregorianCalendar();
		if(now.get(GregorianCalendar.HOUR_OF_DAY) > time.startHour ||(
				now.get(GregorianCalendar.HOUR_OF_DAY) == time.startHour &&
						now.get(GregorianCalendar.MINUTE) > time.startMinute)){
			now.add(GregorianCalendar.DAY_OF_MONTH, 1);
		}
	//	Log.i("trig", "enter 2");
		now.set(GregorianCalendar.HOUR_OF_DAY, time.startHour);
	//	Log.i("trig", "enter 1");
		now.set(GregorianCalendar.MINUTE, time.startMinute);
		return now;
	}
	
}
