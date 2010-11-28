package com.cs446teameo.repeater;

import java.util.Date;
import java.util.GregorianCalendar;


public abstract class TimeSet {
	
	protected TimeSet(){}
	protected String toDay(int dow){
		short d = (short) (dow + 1);
		switch(d){
		case GregorianCalendar.SUNDAY: return "Sun";
		case GregorianCalendar.MONDAY: return "Mon";
		case GregorianCalendar.TUESDAY: return "Tue";
		case GregorianCalendar.WEDNESDAY: return "Wed";
		case GregorianCalendar.THURSDAY: return "Thu";
		case GregorianCalendar.FRIDAY: return "Fri";
		case GregorianCalendar.SATURDAY: return "Sat";
		}
		return null;
	}
	protected String toMonth(int month){
		short m = (short)month;
		switch(m){
			case 0: return "JAN";
			case 1: return "Feb";
			case 2: return "Mar";
			case 3: return "Apr";
			case 4: return "May";
			case 5: return "Jun";
			case 6: return "Jul";
			case 7: return "Aug";
			case 8: return "Sep";
			case 9: return "Oct";
			case 10: return "Nov";
			case 11: return "Dec";
			default: 
				return "error";
		}
	}
	public abstract String toString();
	public abstract GregorianCalendar nextTrigger();
}
