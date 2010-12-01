package com.cs446teameo.repeater;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Vector;


public class Monthly extends RepeatSet{
	int dayofmonth;
	
	@Override
	void setDate(ArrayList<Integer> target) {
		// TODO Auto-generated method stub
		dayofmonth = target.get(0);
	}

	@Override
	public GregorianCalendar nextTrigger() {
		// TODO Auto-generated method stub
		GregorianCalendar now = new GregorianCalendar();
		GregorianCalendar start = new GregorianCalendar(now.get(GregorianCalendar.YEAR),now.get(GregorianCalendar.MONTH),dayofmonth);
		if(now.get(GregorianCalendar.HOUR_OF_DAY) > time.startHour ||(
				now.get(GregorianCalendar.HOUR_OF_DAY) == time.startHour &&
						now.get(GregorianCalendar.MINUTE) > time.startMinute)){
			now.add(GregorianCalendar.DAY_OF_MONTH, 1);
		}
//		System.out.println("setting now:" + now.getTime().toLocaleString());
		now.set(GregorianCalendar.HOUR_OF_DAY, time.startHour);
		now.set(GregorianCalendar.MINUTE, time.startMinute);
//		System.out.println("adjust hour:" + now.getTime().toLocaleString());
		if(now.get(GregorianCalendar.DAY_OF_MONTH) > start.get(GregorianCalendar.DAY_OF_MONTH)){
			now.set(GregorianCalendar.DAY_OF_MONTH, dayofmonth);
			now.add(GregorianCalendar.MONTH, 1);
		}
//		System.out.println("try new month?:" + now.getTime().toLocaleString());
//		System.out.println("target?:" + now.getActualMaximum(GregorianCalendar.DATE));
		while(start.get(GregorianCalendar.DAY_OF_MONTH) > now.getActualMaximum(GregorianCalendar.DATE));
		now.set(GregorianCalendar.DAY_OF_MONTH, start.get(GregorianCalendar.DAY_OF_MONTH));
		return now;
	}

	@Override
	public String toDate() {
		// TODO Auto-generated method stub
		StringBuffer buf = new StringBuffer();
		buf.append("monthly:");
		buf.append("dom");
		buf.append(dayofmonth);
		return buf.toString();
	}

	
}
