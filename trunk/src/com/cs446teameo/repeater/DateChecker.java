
import java.util.GregorianCalendar;


public class DateChecker {
	private static DateChecker _instance= null;
	
	private DateChecker(){}
	
	public static DateChecker getInstance(){
		if(_instance == null)
			_instance = new DateChecker();
		return _instance;
	}
	
	private boolean month_range(int year,int month){
		return 1990 <= year && 1<=month && month <13; 
	}
	private boolean isWeekday(int dayofWeek){
		return 1<=dayofWeek && dayofWeek<= 7;
	}
	
	public boolean isValid(int year,int month,int dayofMonth){
		if(!month_range(year,month)) return false;
		GregorianCalendar ck = new GregorianCalendar(year,month-1,dayofMonth);
		return (ck.get(GregorianCalendar.YEAR)==year) && (ck.get(GregorianCalendar.MONTH) == month-1) 
			&& (ck.get(GregorianCalendar.DAY_OF_MONTH) == dayofMonth);
	}
	public boolean isValid(int year,int month,int weekofMonth,int dayofWeek){
		if(!isWeekday(dayofWeek)) return false;
		GregorianCalendar ck = new GregorianCalendar(year,month-1,1);
		dayofWeek = dayofWeek % 7;
		int first = ck.get(GregorianCalendar.DAY_OF_WEEK) % 7;
		int dayofMonth = 7*(weekofMonth - 1) + (dayofWeek - first);
		return isValid(year,month,dayofMonth);
		
	}
	public boolean isValid(int hour,int minute){
		return (hour >= 0 && hour <24) && (minute >= 0 && minute <60);
	}
}
