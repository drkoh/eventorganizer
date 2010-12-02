package com.cs446teameo.repeater;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Vector;


public class Weekly extends RepeatSet{
	WeekView list;
	
	@Override
	void setDate(ArrayList<Integer> target) {
		// TODO Auto-generated method stub
		list = new WeekView();
		for(int i = 0;i<target.size();i++)
			list.dset[target.get(i) - 1] = true;
	}

	@Override
	public GregorianCalendar nextTrigger() {
		// TODO Auto-generated method stub
		GregorianCalendar now = new GregorianCalendar();
		if(now.get(GregorianCalendar.HOUR_OF_DAY) > time.startHour ||(
				now.get(GregorianCalendar.HOUR_OF_DAY) == time.startHour &&
						now.get(GregorianCalendar.MINUTE) > time.startMinute)){
			now.add(GregorianCalendar.DAY_OF_MONTH, 1);
		}
		now.set(GregorianCalendar.HOUR_OF_DAY, time.startHour);
		now.set(GregorianCalendar.MINUTE, time.startMinute);
		if(list.nextTrigger(now.get(GregorianCalendar.DAY_OF_WEEK))==-1){
			now.set(GregorianCalendar.DAY_OF_WEEK, GregorianCalendar.SUNDAY);
			now.add(GregorianCalendar.WEEK_OF_YEAR, 1);
		}
		now.set(GregorianCalendar.DAY_OF_WEEK, list.nextTrigger(now.get(GregorianCalendar.DAY_OF_WEEK)));
		now.set(GregorianCalendar.SECOND, 0);
		return now;
	}

	@Override
	public String toDate() {
		// TODO Auto-generated method stub
		StringBuffer buf = new StringBuffer();
		buf.append("weekly:");
		for(int i=0;i<7;i++)
			if(list.dset[i]){
				buf.append(toDay(i));
				buf.append(" ");
			}
		buf.deleteCharAt(buf.length()-1);
		return buf.toString();
	}

}

class WeekView{
	boolean[] dset = new boolean[7];
	public WeekView(){
		for(int i=0;i<7;i++)
			dset[i] = false;
	}
	public int nextTrigger(int now){
		//check the date mode
		// I am not sure
		while((now) <= 6 && !dset[now]) now++;
		return now % 7;
	}
}


