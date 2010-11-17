package com.cs446teameo.RepeatDriver;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class DateView extends SingleSet{
	int dayofMonth;
	public DateView(int year,int month,int day){
		super(year,month);
		this.dayofMonth = day;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuffer ret = new StringBuffer();
		if(year == -1)
			ret.append("every year ");
		else 
			ret.append("year:"+ year + " ");
		if(month == -1)
			ret.append("every month ");
		else ret.append("month:" + month + " ");
		if(dayofMonth == -1)
			ret.append("every day");
		else
			ret.append("day:" + dayofMonth);
		return ret.toString();
	}
	
	@SuppressWarnings("deprecation")
	@Override
	protected Date nextTriggerAfter(Date date) {
		// TODO Auto-generated method stub
		int year = date.getYear();
		int month = date.getMonth();
		int day = date.getDay();
		int maxYear = this.year + 1;
		if(maxYear < 1990)
			maxYear = MAX_YEAR;
		day = nextdayofMonth(year,month,day);
		if(day == -1){
			day = 1;
			month = nextMonth(month);
			if(month == -1){
				year = nextYear(year);
				if(year > maxYear)
					return null;
				month = nextMonth(0);
			}
		}
		return new Date(year,month,day);
	}
	@Override
	protected boolean contain(Date date) {
		// TODO Auto-generated method stub
		int year = date.getYear();
		if(year > MAX_YEAR)
			return false;
		if(this.year != -1 && year > this.year)
			return false;
		int month = date.getMonth();
		// I don't know if this check is right 
		// please help me check it
		if(this.month!= -1 && Integer.rotateRight(this.month,month)%2 == 0)
			return false;
		int day = date.getDay();
		if(this.dayofMonth!= -1 && Integer.rotateRight(this.dayofMonth,day)%2 == 0)
			return false;
		return true;
	}
	
	protected int nextdayofMonth(int year,int month,int day){
		if(this.dayofMonth == -1){
			Calendar cal = new GregorianCalendar(year, month, 1);
			int days = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
			if(day == days)
				return -1;
			return day + 1;
		}
		int tmp = this.dayofMonth;
		//I don't know if it is right
		int time = 0;
		while(true){
			if(tmp == 0)
				return -1;
			if(tmp % 2 == 1 && time > day)
				return time;
			tmp = Integer.rotateRight(tmp, 1);
			time++;
		}
	}
	


}
