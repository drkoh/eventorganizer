package com.cs446teameo.repeater;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Vector;


public class Anniversary extends RepeatSet{
	int month;
	int dayofmonth;

	@Override
	public GregorianCalendar nextTrigger() {
		// TODO Auto-generated method stub
		GregorianCalendar now = new GregorianCalendar();
		GregorianCalendar start = new GregorianCalendar(now.get(GregorianCalendar.YEAR),month,dayofmonth);
		if(now.get(GregorianCalendar.HOUR_OF_DAY) > time.startHour ||(
				now.get(GregorianCalendar.HOUR_OF_DAY) == time.startHour &&
						now.get(GregorianCalendar.MINUTE) > time.startMinute)){
			now.add(GregorianCalendar.DAY_OF_MONTH, 1);
		}
		if(start.get(GregorianCalendar.MONDAY) == GregorianCalendar.FEBRUARY
				&& start.get(GregorianCalendar.DAY_OF_MONTH) == 29){
			if(now.isLeapYear(now.get(GregorianCalendar.YEAR))){
				if(now.get(GregorianCalendar.MONTH) <= GregorianCalendar.FEBRUARY){
					return new GregorianCalendar(now.get(GregorianCalendar.YEAR),GregorianCalendar.FEBRUARY,29,
							time.startHour,time.startMinute);
				}else{
					GregorianCalendar tmp = new GregorianCalendar(now.get(GregorianCalendar.YEAR),GregorianCalendar.FEBRUARY,29,
							time.startHour,time.startMinute);
					do{
						tmp.add(GregorianCalendar.YEAR, 4);
					}while(!tmp.isLeapYear(tmp.get(GregorianCalendar.YEAR)));
					return tmp;
				}
			}else{
				GregorianCalendar tmp = new GregorianCalendar(now.get(GregorianCalendar.YEAR),GregorianCalendar.FEBRUARY,29,
						time.startHour,time.startMinute);
				do{
					tmp.add(GregorianCalendar.YEAR, 4);
				}while(!tmp.isLeapYear(tmp.get(GregorianCalendar.YEAR)));
				return tmp;
			}
		}else{
			if(now.get(GregorianCalendar.MONTH) < start.get(GregorianCalendar.MONTH) ||(
					now.get(GregorianCalendar.DAY_OF_MONTH) == start.get(GregorianCalendar.MONTH)
					&& now.get(GregorianCalendar.DAY_OF_MONTH) <start.get(GregorianCalendar.DAY_OF_MONTH))){
				return new GregorianCalendar(now.get(GregorianCalendar.YEAR),start.get(GregorianCalendar.MONTH),
						start.get(GregorianCalendar.DAY_OF_MONTH),time.startHour,time.startMinute);
			}else{
				return new GregorianCalendar(now.get(GregorianCalendar.YEAR)+1,start.get(GregorianCalendar.MONTH),
						start.get(GregorianCalendar.DAY_OF_MONTH),time.startHour,time.startMinute);
			}
		}
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuffer buf = new StringBuffer();
		buf.append("yearly :");
		buf.append(toMonth(month));
		buf.append(",");
		buf.append("dom");
		buf.append(dayofmonth);
		return buf.toString();
	}

	@Override
	void setDate(Vector<Integer> target) {
		// TODO Auto-generated method stub
		month = target.get(0);
		dayofmonth = target.get(1);
	}
	
}
