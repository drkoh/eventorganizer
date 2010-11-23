package com.cs446teameo.UI;

import java.util.Calendar;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

import com.cs446teameo.Main.R;

public class YearlyCalendarUI extends Frame{

	Button yearlyTab = null;
	Button monthlyTab = null;
	Button weeklyTab = null;
	Button dailyTab = null;
	Button listTab = null;
	Button januaryButton = null;
	Button februaryButton = null;
	Button marchButton = null;
	Button aprilButton = null;
	Button mayButton = null;
	Button juneButton = null;
	Button julyButton = null;
	Button augustButton = null;
	Button septemberButton = null;
	Button octoberButton = null;
	Button novemberButton = null;
	Button decemberButton = null;
	TextView date = null;
	Button leftButton = null;
	Button rightButton = null;
	Button exitButton = null;
	Button createButton = null;
	TableLayout eventTable = null;
	int currentYear, currentMonth, currentDay, currentDayOfTheWeek;
	Calendar calendar;
	
	private static YearlyCalendarUI _instance = null;
	
	private YearlyCalendarUI() {
		super("YearlyCalendar");
		// TODO Auto-generated constructor stub
	}
	
	public static void contextSwitch(){
		if(_instance == null){
			_instance = new YearlyCalendarUI();
		}
		owner.setContentView(R.layout.yearlycalendar);
		_instance.init();
	}

	@Override
	public void registeListener() {
		// TODO Auto-generated method stub

		leftButton.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View arg0) {
				calendar.add(Calendar.YEAR, -1);
				setUI();
			}
		});
		

		rightButton.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View arg0) {
				calendar.add(Calendar.YEAR, 1);
				setUI();
			}
		});
		
		// Create Button
		dailyTab.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View arg0) {
				// TODO!!!
				Log.i(field,"trigger create button");
				DailyCalendarUI.contextSwitch();
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
		this.januaryButton = (Button) owner.findViewById(R.yearlycalendar.januaryButton);
		this.februaryButton = (Button) owner.findViewById(R.yearlycalendar.februaryButton);
		this.marchButton = (Button) owner.findViewById(R.yearlycalendar.marchButton);
		this.aprilButton = (Button) owner.findViewById(R.yearlycalendar.aprilButton);
		this.mayButton = (Button) owner.findViewById(R.yearlycalendar.mayButton);
		this.juneButton = (Button) owner.findViewById(R.yearlycalendar.juneButton);
		this.julyButton = (Button) owner.findViewById(R.yearlycalendar.julyButton);
		this.augustButton = (Button) owner.findViewById(R.yearlycalendar.augustButton);
		this.septemberButton = (Button) owner.findViewById(R.yearlycalendar.septemberButton);
		this.octoberButton = (Button) owner.findViewById(R.yearlycalendar.octoberButton);
		this.novemberButton = (Button) owner.findViewById(R.yearlycalendar.novemberButton);
		this.decemberButton = (Button) owner.findViewById(R.yearlycalendar.decemberButton);
		this.yearlyTab = (Button) owner.findViewById(R.yearlycalendar.yearlyTab);
		this.yearlyTab.setEnabled(false);
		this.monthlyTab = (Button) owner.findViewById(R.yearlycalendar.monthlyTab);
		this.weeklyTab = (Button) owner.findViewById(R.yearlycalendar.weeklyTab);
		this.dailyTab = (Button) owner.findViewById(R.yearlycalendar.dailyTab);
		this.listTab = (Button) owner.findViewById(R.yearlycalendar.listTab);
		this.date = (TextView) owner.findViewById(R.yearlycalendar.date);
		this.leftButton = (Button) owner.findViewById(R.yearlycalendar.leftButton);
		this.rightButton = (Button) owner.findViewById(R.yearlycalendar.rightButton);	
		this.createButton = (Button) owner.findViewById(R.yearlycalendar.createButton);
		this.exitButton = (Button) owner.findViewById(R.yearlycalendar.exitButton);	
		this.eventTable = (TableLayout) owner.findViewById(R.yearlycalendar.eventTable);
		calendar = Calendar.getInstance();
		setUI();
	}
	
	// Sets all the text-based UI components
	public void setUI ()
	{
        currentYear = calendar.get(Calendar.YEAR);
		date.setText("" + currentYear);
		
		// Set the buttons to navigate to the month it's supposed to
		januaryButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) 
			{
				MonthlyCalendarUI.calendarSet = true;
				MonthlyCalendarUI.calendar = (Calendar)calendar.clone();
				MonthlyCalendarUI.calendar.set(Calendar.MONTH, 0);
				MonthlyCalendarUI.contextSwitch();
			}
		});
		februaryButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) 
			{
				MonthlyCalendarUI.calendarSet = true;
				MonthlyCalendarUI.calendar = (Calendar)calendar.clone();
				MonthlyCalendarUI.calendar.set(Calendar.MONTH, 1);
				MonthlyCalendarUI.contextSwitch();
			}
		});
		marchButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) 
			{
				MonthlyCalendarUI.calendarSet = true;
				MonthlyCalendarUI.calendar = (Calendar)calendar.clone();
				MonthlyCalendarUI.calendar.set(Calendar.MONTH, 2);
				MonthlyCalendarUI.contextSwitch();
			}
		});
		aprilButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) 
			{
				MonthlyCalendarUI.calendarSet = true;
				MonthlyCalendarUI.calendar = (Calendar)calendar.clone();
				MonthlyCalendarUI.calendar.set(Calendar.MONTH, 3);
				MonthlyCalendarUI.contextSwitch();
			}
		});
		mayButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) 
			{
				MonthlyCalendarUI.calendarSet = true;
				MonthlyCalendarUI.calendar = (Calendar)calendar.clone();
				MonthlyCalendarUI.calendar.set(Calendar.MONTH, 4);
				MonthlyCalendarUI.contextSwitch();
			}
		});
		juneButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) 
			{
				MonthlyCalendarUI.calendarSet = true;
				MonthlyCalendarUI.calendar = (Calendar)calendar.clone();
				MonthlyCalendarUI.calendar.set(Calendar.MONTH, 5);
				MonthlyCalendarUI.contextSwitch();
			}
		});
		julyButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) 
			{
				MonthlyCalendarUI.calendarSet = true;
				MonthlyCalendarUI.calendar = (Calendar)calendar.clone();
				MonthlyCalendarUI.calendar.set(Calendar.MONTH, 6);
				MonthlyCalendarUI.contextSwitch();
			}
		});
		augustButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) 
			{
				MonthlyCalendarUI.calendarSet = true;
				MonthlyCalendarUI.calendar = (Calendar)calendar.clone();
				MonthlyCalendarUI.calendar.set(Calendar.MONTH, 7);
				MonthlyCalendarUI.contextSwitch();
			}
		});
		septemberButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) 
			{
				MonthlyCalendarUI.calendarSet = true;
				MonthlyCalendarUI.calendar = (Calendar)calendar.clone();
				MonthlyCalendarUI.calendar.set(Calendar.MONTH, 8);
				MonthlyCalendarUI.contextSwitch();
			}
		});
		octoberButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) 
			{
				MonthlyCalendarUI.calendarSet = true;
				MonthlyCalendarUI.calendar = (Calendar)calendar.clone();
				MonthlyCalendarUI.calendar.set(Calendar.MONTH, 9);
				MonthlyCalendarUI.contextSwitch();
			}
		});
		novemberButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) 
			{
				MonthlyCalendarUI.calendarSet = true;
				MonthlyCalendarUI.calendar = (Calendar)calendar.clone();
				MonthlyCalendarUI.calendar.set(Calendar.MONTH, 10);
				MonthlyCalendarUI.contextSwitch();
			}
		});
		decemberButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) 
			{
				MonthlyCalendarUI.calendarSet = true;
				MonthlyCalendarUI.calendar = (Calendar)calendar.clone();
				MonthlyCalendarUI.calendar.set(Calendar.MONTH, 11);
				MonthlyCalendarUI.contextSwitch();
			}
		});
        setEventsUI();
	}

	// Sets all the event-based UI components
	public void setEventsUI()
	{
		
	}

}
