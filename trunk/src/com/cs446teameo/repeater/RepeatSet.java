package com.cs446teameo.repeater;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Vector;


public abstract class RepeatSet extends TimeSet{
	public Time time = null;
	
	final public static int TIME_OF_DAY = 0;
	final public static int DATE = 1;

	public void setField(int field,ArrayList<Integer> target){
		if(field == TIME_OF_DAY){
			//Log.d("ev", "enter set ddd");
			if(time == null)
				time = new Time();
			time.set(target.get(0), target.get(1), target.get(2), target.get(3));
		}
		else if(field == DATE){
		//	Log.d("ev", "enter set field");
			setDate(target);
		}
		else{
			//TODO:
		}
	}
	abstract void setDate(ArrayList<Integer> target);
	abstract String toDate();
	public String toString(){
		StringBuffer buf = new StringBuffer(time.toString());
		buf.append("@");
		buf.append(toDate());
		return buf.toString();
	}
	public long period(){
		return (time.endHour - time.startHour)* 3600000 + (time.endMinute - time.startMinute)*60000;
	}
	@Override
	public GregorianCalendar nextEndtime() {
		// TODO Auto-generated method stub
		GregorianCalendar ret = this.nextTrigger();
		if(ret == null)
			return null;
		ret.add(GregorianCalendar.MILLISECOND, (int) (period()));
		return ret;
	}
}
