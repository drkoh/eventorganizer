package com.cs446teameo.repeater;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Vector;

import android.util.Log;


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
		Log.i("repeater", "1");
		GregorianCalendar now = new GregorianCalendar();
		Log.i("repeater", "2");
		GregorianCalendar start = new GregorianCalendar(now.get(GregorianCalendar.YEAR),now.get(GregorianCalendar.MONTH),dayofmonth);
		Log.i("repeater", "3");
		Log.i("whatever",((Integer)time.startHour).toString());
		Log.i("whatever",((Integer)time.startMinute).toString());
		Log.i("whatever",((Integer)time.endHour).toString());
		Log.i("whatever",((Integer)time.endMinute).toString());
		if(now.get(GregorianCalendar.HOUR_OF_DAY) > time.startHour ||(
				now.get(GregorianCalendar.HOUR_OF_DAY) == time.startHour &&
						now.get(GregorianCalendar.MINUTE) > time.startMinute)){
			Log.i("repeater", "4");
			now.add(GregorianCalendar.DAY_OF_MONTH, 1);
		}
		Log.i("repeater", "5");
//		System.out.println("setting now:" + now.getTime().toLocaleString());
		Log.i("repeater", "6");
		now.set(GregorianCalendar.HOUR_OF_DAY, time.startHour);
		Log.i("repeater", "7");
		now.set(GregorianCalendar.MINUTE, time.startMinute);
//		System.out.println("adjust hour:" + now.getTime().toLocaleString());
		Log.i("repeater", "8");
		if(now.get(GregorianCalendar.DAY_OF_MONTH) > start.get(GregorianCalendar.DAY_OF_MONTH)){
			Log.i("repeater", "9");
			now.set(GregorianCalendar.DAY_OF_MONTH, dayofmonth);
			Log.i("repeater", "10");
			now.add(GregorianCalendar.MONTH, 1);
		}
		Log.i("repeater", "11");
//		System.out.println("try new month?:" + now.getTime().toLocaleString());
//		System.out.println("target?:" + now.getActualMaximum(GregorianCalendar.DATE));
		while(start.get(GregorianCalendar.DAY_OF_MONTH) > now.getActualMaximum(GregorianCalendar.DATE));
		Log.i("repeater", "12");
		now.set(GregorianCalendar.DAY_OF_MONTH, start.get(GregorianCalendar.DAY_OF_MONTH));
		Log.i("repeater", "13");
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
