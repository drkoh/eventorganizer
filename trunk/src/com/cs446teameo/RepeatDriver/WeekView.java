package com.cs446teameo.RepeatDriver;
import java.util.Date;


public class WeekView extends SingleSet{
	
	int weekofMonth;
	int dayofWeek;
	
	public WeekView(int year, int month,int week,int weekday) {
		super(year,month);
		weekofMonth = week;
		dayofWeek = weekday;
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuffer ret = new StringBuffer();
		if(year == -1)
			ret.append("every year ");
		else 
			ret.append("year:"+year + " ");
		if(month == -1)
			ret.append("every month ");
		else ret.append("month:" + month + " ");
		if(weekofMonth == -1)
			ret.append("every ");
		else ret.append("week:" + weekofMonth+ " ");
		ret.append("dayofWeek:" + dayofWeek);
		return ret.toString();
	}
	@Override
	protected Date nextTriggerAfter(Date date) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	protected boolean contain(Date date) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
