package com.cs446teameo.repeater;

import java.util.GregorianCalendar;
import java.util.Vector;


public abstract class RepeatSet extends TimeSet{
	Time time = null;
	
	final public static int TIME_OF_DAY = 0;
	final public static int DATE = 1;

	void setField(int field,Vector<Integer> target){
		if(field == TIME_OF_DAY){
			if(time == null)
				time = new Time();
			time.set(target.get(0), target.get(1), target.get(2), target.get(3));
		}
		else if(field == DATE)
			setDate(target);
		else{
			//TODO:
		}
	}
	abstract void setDate(Vector<Integer> target);
}
