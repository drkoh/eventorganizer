package com.cs446teameo.UI;

import java.util.Calendar;

import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.*;

import com.cs446teameo.Main.R;

public class MonthlyCalendarUI extends Frame{

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
	Button[][] monthlyButton = new Button[6][7];
	String dateString;
	int currentYear, currentMonth, currentDay;
	public static boolean calendarSet = false;
	public static Calendar calendar;
	String[] monthNames = {"January", "February", "March", 
			"April", "May", "June", "July", "August", 
			"September", "October", "November", "December"};
	
	private static MonthlyCalendarUI _instance = null;
	
	private MonthlyCalendarUI() {
		super("MonthlyCalendar");
		// TODO Auto-generated constructor stub
	}
	
	public static void contextSwitch(){
		if(_instance == null){
			_instance = new MonthlyCalendarUI();
		}
		owner.setContentView(R.layout.monthlycalendar);
		_instance.init();
	}

	@Override
	public void registeListener() {
		// TODO Auto-generated method stub

		leftButton.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View arg0) {
				calendar.add(Calendar.MONTH, -1);
				setUI();
			}
		});
		
		rightButton.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View arg0) {

				calendar.add(Calendar.MONTH, 1);
				setUI();
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
		dailyTab.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View arg0) {
				// TODO!!!
				Log.i(field,"trigger create button");
				DailyCalendarUI.contextSwitch();
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
		this.yearlyTab = (Button) owner.findViewById(R.monthlycalendar.yearlyTab);
		this.monthlyTab = (Button) owner.findViewById(R.monthlycalendar.monthlyTab);
		this.monthlyTab.setEnabled(false);
		this.weeklyTab = (Button) owner.findViewById(R.monthlycalendar.weeklyTab);
		this.dailyTab = (Button) owner.findViewById(R.monthlycalendar.dailyTab);
		this.listTab = (Button) owner.findViewById(R.monthlycalendar.listTab);
		this.date = (TextView) owner.findViewById(R.monthlycalendar.date);
		this.leftButton = (Button) owner.findViewById(R.monthlycalendar.leftButton);
		this.rightButton = (Button) owner.findViewById(R.monthlycalendar.rightButton);	
		this.createButton = (Button) owner.findViewById(R.monthlycalendar.createButton);
		this.exitButton = (Button) owner.findViewById(R.monthlycalendar.exitButton);
		if(calendarSet == false)
		{
			calendar = Calendar.getInstance();
			calendarSet = true;
		}
		else
		{
			calendarSet = false;
		}
		associateDayButtons();
		setUI();
	}
	
	// Sets all the text-based UI components
	public void setUI ()
	{
		// Disable all the buttons of each day cell and set text = " "
		for(int i = 0; i < 6; i++)
		{
			for(int j = 0; j < 7; j++)
			{
				monthlyButton[i][j].setText("");
				monthlyButton[i][j].setEnabled(false);
			}
		}
		int x = 0, y = 0;
        currentYear = calendar.get(Calendar.YEAR);
        currentMonth = calendar.get(Calendar.MONTH);
        dateString = monthNames[calendar.get(Calendar.MONTH)] + ", " + currentYear;
		date.setText(dateString);	
		int maxDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		Calendar tempCalendar = (Calendar)calendar.clone();
		tempCalendar.set(Calendar.DAY_OF_MONTH, 1);
		y = (tempCalendar.get(Calendar.DAY_OF_WEEK) - 1);
		for(int i = 1; i <= maxDays; i++)
		{
			if(y == 7)
			{
				y = 0;
				x++;
			}
			Log.i(field,"x:" + x + ", y:" + y);
			monthlyButton[x][y].setText("" + i);
			monthlyButton[x][y].setEnabled(true);
			
			// Clear the button's style
			CalendarSetting.clearStyle(monthlyButton[x][y]);
			
			// Set the button's style based on the event
			int eventOccurs = CalendarSetting.NO_EVENT_OCCURS;
			//eventOccurs = CalendarSetting.eventOccursWhen(i, Calendar.DAY_OF_MONTH, calendar);
			//CalendarSetting.setEventsTime(eventOccurs, monthlyButton[x][y]);
			
			final int xx = x;
			final int yy = y;
			
			// Add functionality to the button
			monthlyButton[x][y].setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View arg0) 
				{
					DailyCalendarUI.calendarSet = true;
					DailyCalendarUI.calendar = (Calendar)calendar.clone();
					DailyCalendarUI.calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt( (String) monthlyButton[xx][yy].getText()));
					DailyCalendarUI.contextSwitch();
				}
			});
			y++;
		}
	}
	
	// Associate all the buttons of each day in the grid to their xml counterparts 
	// (this method was actually generated using Java, lol).
	public void associateDayButtons()
	{
		monthlyButton[0][0] = (Button) owner.findViewById(R.monthlycalendar.monthlyButton0x0);
		monthlyButton[0][1] = (Button) owner.findViewById(R.monthlycalendar.monthlyButton0x1);
		monthlyButton[0][2] = (Button) owner.findViewById(R.monthlycalendar.monthlyButton0x2);
		monthlyButton[0][3] = (Button) owner.findViewById(R.monthlycalendar.monthlyButton0x3);
		monthlyButton[0][4] = (Button) owner.findViewById(R.monthlycalendar.monthlyButton0x4);
		monthlyButton[0][5] = (Button) owner.findViewById(R.monthlycalendar.monthlyButton0x5);
		monthlyButton[0][6] = (Button) owner.findViewById(R.monthlycalendar.monthlyButton0x6);
		monthlyButton[1][0] = (Button) owner.findViewById(R.monthlycalendar.monthlyButton1x0);
		monthlyButton[1][1] = (Button) owner.findViewById(R.monthlycalendar.monthlyButton1x1);
		monthlyButton[1][2] = (Button) owner.findViewById(R.monthlycalendar.monthlyButton1x2);
		monthlyButton[1][3] = (Button) owner.findViewById(R.monthlycalendar.monthlyButton1x3);
		monthlyButton[1][4] = (Button) owner.findViewById(R.monthlycalendar.monthlyButton1x4);
		monthlyButton[1][5] = (Button) owner.findViewById(R.monthlycalendar.monthlyButton1x5);
		monthlyButton[1][6] = (Button) owner.findViewById(R.monthlycalendar.monthlyButton1x6);
		monthlyButton[2][0] = (Button) owner.findViewById(R.monthlycalendar.monthlyButton2x0);
		monthlyButton[2][1] = (Button) owner.findViewById(R.monthlycalendar.monthlyButton2x1);
		monthlyButton[2][2] = (Button) owner.findViewById(R.monthlycalendar.monthlyButton2x2);
		monthlyButton[2][3] = (Button) owner.findViewById(R.monthlycalendar.monthlyButton2x3);
		monthlyButton[2][4] = (Button) owner.findViewById(R.monthlycalendar.monthlyButton2x4);
		monthlyButton[2][5] = (Button) owner.findViewById(R.monthlycalendar.monthlyButton2x5);
		monthlyButton[2][6] = (Button) owner.findViewById(R.monthlycalendar.monthlyButton2x6);
		monthlyButton[3][0] = (Button) owner.findViewById(R.monthlycalendar.monthlyButton3x0);
		monthlyButton[3][1] = (Button) owner.findViewById(R.monthlycalendar.monthlyButton3x1);
		monthlyButton[3][2] = (Button) owner.findViewById(R.monthlycalendar.monthlyButton3x2);
		monthlyButton[3][3] = (Button) owner.findViewById(R.monthlycalendar.monthlyButton3x3);
		monthlyButton[3][4] = (Button) owner.findViewById(R.monthlycalendar.monthlyButton3x4);
		monthlyButton[3][5] = (Button) owner.findViewById(R.monthlycalendar.monthlyButton3x5);
		monthlyButton[3][6] = (Button) owner.findViewById(R.monthlycalendar.monthlyButton3x6);
		monthlyButton[4][0] = (Button) owner.findViewById(R.monthlycalendar.monthlyButton4x0);
		monthlyButton[4][1] = (Button) owner.findViewById(R.monthlycalendar.monthlyButton4x1);
		monthlyButton[4][2] = (Button) owner.findViewById(R.monthlycalendar.monthlyButton4x2);
		monthlyButton[4][3] = (Button) owner.findViewById(R.monthlycalendar.monthlyButton4x3);
		monthlyButton[4][4] = (Button) owner.findViewById(R.monthlycalendar.monthlyButton4x4);
		monthlyButton[4][5] = (Button) owner.findViewById(R.monthlycalendar.monthlyButton4x5);
		monthlyButton[4][6] = (Button) owner.findViewById(R.monthlycalendar.monthlyButton4x6);
		monthlyButton[5][0] = (Button) owner.findViewById(R.monthlycalendar.monthlyButton5x0);
		monthlyButton[5][1] = (Button) owner.findViewById(R.monthlycalendar.monthlyButton5x1);
		monthlyButton[5][2] = (Button) owner.findViewById(R.monthlycalendar.monthlyButton5x2);
		monthlyButton[5][3] = (Button) owner.findViewById(R.monthlycalendar.monthlyButton5x3);
		monthlyButton[5][4] = (Button) owner.findViewById(R.monthlycalendar.monthlyButton5x4);
		monthlyButton[5][5] = (Button) owner.findViewById(R.monthlycalendar.monthlyButton5x5);
		monthlyButton[5][6] = (Button) owner.findViewById(R.monthlycalendar.monthlyButton5x6);
	}
}