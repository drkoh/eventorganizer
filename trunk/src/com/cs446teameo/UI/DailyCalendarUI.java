package com.cs446teameo.UI;

import java.util.Calendar;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

import com.cs446teameo.Main.R;

public class DailyCalendarUI extends Frame{

	Button yearlyTab = null;
	Button monthlyTab = null;
	Button weeklyTab = null;
	Button dailyTab = null;
	Button listTab = null;
	TextView date = null;
	Button leftButton = null;
	Button rightButton = null;
	Button exitButton = null;
	Button createButton = null;
	TableLayout eventTable = null;
	int currentYear, currentMonth, currentDay, currentDayOfTheWeek;
	String dateString;
	Calendar calendar;
	String[] monthNames = {"January", "February", "March", 
			"April", "May", "June", "July", "August", 
			"September", "October", "November", "December"};
	String[] dayNames = new String[] { "Sunday", "Monday", "Tuesday", 
			"Wednesday", "Thusday", "Friday", "Saturday" };

	
	private static DailyCalendarUI _instance = null;
	
	private DailyCalendarUI() {
		super("DailyCalendar");
		// TODO Auto-generated constructor stub
	}
	
	public static void contextSwitch(){
		if(_instance == null){
			_instance = new DailyCalendarUI();
		}
		owner.setContentView(R.layout.dailycalendar);
		_instance.init();
	}

	@Override
	public void registeListener() {
		// TODO Auto-generated method stub
		

		leftButton.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View arg0) {
				calendar.add(Calendar.DAY_OF_MONTH, -1);
				setUI(calendar);
			}
		});
		

		rightButton.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View arg0) {
				calendar.add(Calendar.DAY_OF_MONTH, 1);
				setUI(calendar);
			}
		});
		
		// Create Button
		yearlyTab.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View arg0) {
				// TODO!!!
				Log.i(field,"trigger create button");
				YearlyCalendarUI.contextSwitch();
			}
		});
		
		// Create Button
		weeklyTab.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View arg0) {
				// TODO!!!
				Log.i(field,"trigger create button");
				WeeklyCalendarUI.contextSwitch();
			}
		});
		
		// Create Button
		monthlyTab.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View arg0) {
				// TODO!!!
				Log.i(field,"trigger create button");
				MonthlyCalendarUI.contextSwitch();
			}
		});

		// Create Button
		listTab.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View arg0) {
				// TODO!!!
				Log.i(field,"trigger create button");
				ListCalendarUI.contextSwitch();
			}
		});
		
		// Create Button
		createButton.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View arg0) {
				// TODO!!!
				Log.i(field,"trigger create button");
				EventAdderUI.contextSwitch();
			}
		});
		
		// Exit Button
		exitButton.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Log.i(field,"trigger exit button");
				MenuUI.contextSwitch();
			}
		});
	}

	@Override
	public void registeComponent() {
		this.yearlyTab = (Button) owner.findViewById(R.dailycalendar.yearlyTab);
		this.monthlyTab = (Button) owner.findViewById(R.dailycalendar.monthlyTab);
		this.weeklyTab = (Button) owner.findViewById(R.dailycalendar.weeklyTab);
		this.dailyTab = (Button) owner.findViewById(R.dailycalendar.dailyTab);
		this.dailyTab.setEnabled(false);
		this.listTab = (Button) owner.findViewById(R.dailycalendar.listTab);
		this.date = (TextView) owner.findViewById(R.dailycalendar.date);
		calendar = Calendar.getInstance();
		setUI(calendar);
		this.leftButton = (Button) owner.findViewById(R.dailycalendar.leftButton);
		this.rightButton = (Button) owner.findViewById(R.dailycalendar.rightButton);	
		this.createButton = (Button) owner.findViewById(R.dailycalendar.createButton);
		this.exitButton = (Button) owner.findViewById(R.dailycalendar.exitButton);	
		this.eventTable = (TableLayout) owner.findViewById(R.dailycalendar.eventTable);	
	}

	// Sets all the text-based UI components
	public void setUI (Calendar c)
	{
        currentYear = c.get(Calendar.YEAR);
        currentMonth = c.get(Calendar.MONTH);
        currentDay = c.get(Calendar.DAY_OF_MONTH);
        currentDayOfTheWeek = c.get(Calendar.DAY_OF_WEEK);
        dateString = dayNames[currentDayOfTheWeek - 1] + ", " + monthNames[currentMonth]  + " " + currentDay + ", " + currentYear;
        date.setText(dateString);
        setEventsUI(c);
	}

	// Sets all the event-based UI components
	public void setEventsUI(Calendar c)
	{
	}

}
