package com.cs446teameo.RepeatDriver;
import java.util.Date;


public abstract class SingleSet extends DateSet{
		int year,month;
		protected SingleSet(int year,int month){
			this.year = year;
			this.month = month;
		}
		public abstract String toString();
		protected abstract Date nextTriggerAfter(Date date); 
		protected abstract boolean contain(Date date);
		protected int nextYear(int thisyear){
			return thisyear + 1;
		}
		protected int nextMonth(int month){
			int tmp = this.month;
			//I don't know if it is right
			int time = 0;
		//	Integer.ro
			while(true){
				if(tmp == 0)
					return -1;
				if(tmp % 2 == 1 && time > month)
					return time;
				tmp = Integer.rotateRight(tmp, 1);
				time++;
			}
		}
}
