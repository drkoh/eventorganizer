package com.cs446teameo.repeater;

import java.util.Date;
import java.util.GregorianCalendar;


public class SingleSet extends TimeSet{	
	GregorianCalendar start;
	GregorianCalendar end;

	final public static int START = 0;
	final public static int END = 1;

	public SingleSet(){	
	}
	
	public GregorianCalendar nextTrigger(){
		GregorianCalendar now = new GregorianCalendar();
		if(start.after(now))
			return start;
		return null;
	}
	public String toString(){
		StringBuffer buf = new StringBuffer();
		buf.append("h");
		buf.append(start.get(GregorianCalendar.HOUR_OF_DAY));
		buf.append(":");
		buf.append("min");
		buf.append(start.get(GregorianCalendar.MINUTE));
		buf.append("@ (");
		buf.append(toMonth(start.get(GregorianCalendar.MONTH)));
		buf.append(",");
		buf.append("dom");
		buf.append(start.get(GregorianCalendar.DAY_OF_MONTH));
		buf.append(",");
		buf.append("y");
		buf.append(start.get(GregorianCalendar.YEAR));
		buf.append(")");
		buf.append(" $ ");
		buf.append("h");
		buf.append(end.get(GregorianCalendar.HOUR_OF_DAY));
		buf.append(":");
		buf.append("min");
		buf.append(end.get(GregorianCalendar.MINUTE));
		buf.append("@ (");
		buf.append(toMonth(end.get(GregorianCalendar.MONTH)));
		buf.append(",");
		buf.append("dom");
		buf.append(end.get(GregorianCalendar.DAY_OF_MONTH));
		buf.append(",");
		buf.append("y");
		buf.append(end.get(GregorianCalendar.YEAR));
		buf.append(")");
		return buf.toString();
	}
	public boolean setField(int field,GregorianCalendar date){
		if(!checkSetting(date))
			return false;
		if(field == START)
			start = date;
		else if(field == END)
			end = date;
		else{
			//TODO: add notification here
		}
		return true;
	
	}

	public boolean checkSetting(GregorianCalendar date) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public GregorianCalendar nextEndtime() {
		// TODO Auto-generated method stub
		return end;
	}

	
}
