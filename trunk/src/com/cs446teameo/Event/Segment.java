package com.cs446teameo.Event;

import java.util.GregorianCalendar;

public class Segment{
	GregorianCalendar start;
	GregorianCalendar end;
	
	public Segment(int start_year,int start_month,int start_day,int start_hour,int start_minute,
			int end_year,int end_month,int end_day,int end_hour,int end_minute){
		start = new GregorianCalendar(start_year,start_month,start_day,start_hour,start_minute);
		end = new GregorianCalendar(end_year,end_month,end_day,end_hour,end_minute);
	}
}


