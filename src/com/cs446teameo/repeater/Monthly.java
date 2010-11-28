package com.cs446teameo.repeater;

import java.util.GregorianCalendar;
import java.util.Vector;


public class Monthly extends RepeatSet{
	int dayofmonth;
	
	@Override
	void setDate(Vector<Integer> target) {
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
		now.set(GregorianCalendar.HOUR_OF_DAY, time.startHour);
		now.set(GregorianCalendar.MINUTE, time.startMinute);
		if(now.get(GregorianCalendar.DAY_OF_MONTH) > start.get(GregorianCalendar.DAY_OF_MONTH))
			now.add(GregorianCalendar.MONTH, 1);
		while(start.get(GregorianCalendar.DAY_OF_MONTH) < now.getActualMaximum(GregorianCalendar.DATE));
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
