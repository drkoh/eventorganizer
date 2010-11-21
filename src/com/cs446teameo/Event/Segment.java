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
	public Segment (long startTime, long endTime)
	{
		start = toGregorianCalendar(startTime);
		end = toGregorianCalendar(endTime);
	}
	public long startTime(){
		return toUnixTime(start);
	}
	
	public long endTime(){
		return toUnixTime(end);
	}
	
	public static long toUnixTime(GregorianCalendar time)
	{
		return time.getTimeInMillis() / 1000;
	}
	
	public long endUnixtime()
	{
		return toUnixTime(start);
	}
	
	public long startUnixtime()
	{
		return toUnixTime(end);
	}
	
	public static GregorianCalendar toGregorianCalendar(long time)
	{
		GregorianCalendar temp = new GregorianCalendar();
		temp.setTimeInMillis(time * 1000);
		return temp;
	}
}

