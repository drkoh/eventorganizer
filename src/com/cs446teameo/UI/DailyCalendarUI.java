package com.cs446teameo.UI;

import java.util.Calendar;
import java.util.Date;

import android.database.Cursor;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

import com.cs446teameo.Evfac.EventManager;
import com.cs446teameo.Main.R;
import com.cs446teameo.Storage.Database;

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
	static TableLayout eventTable = null;
	int currentYear, currentMonth, currentDay, currentDayOfTheWeek;
	String dateString;
	public static boolean calendarSet = false;
	public static Calendar calendar;
	String[] monthNames = {"January", "February", "March", 
			"April", "May", "June", "July", "August", 
			"September", "October", "November", "December"};
	String[] dayNames = new String[] { "Sunday", "Monday", "Tuesday", 
			"Wednesday", "Thursday", "Friday", "Saturday" };

	
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
		if(calendarSet == false)
		{
			calendar = Calendar.getInstance();
			calendarSet = true;
		}
		else
		{
			calendarSet = false;
		}
		this.leftButton = (Button) owner.findViewById(R.dailycalendar.leftButton);
		this.rightButton = (Button) owner.findViewById(R.dailycalendar.rightButton);	
		this.createButton = (Button) owner.findViewById(R.dailycalendar.createButton);
		this.exitButton = (Button) owner.findViewById(R.dailycalendar.exitButton);	
		this.eventTable = (TableLayout) owner.findViewById(R.dailycalendar.eventTable);
		setUI(calendar);
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
        setEventsUI();
	}

	// Sets all the event-based UI components
	public void setEventsUI()
	{
		eventTable.removeAllViews();
		Log.i(field,"Start Set UI!");
		Cursor tempCursor = EventManager.allEventsOfDay(calendar);
        int cursorSize = tempCursor.getCount();
        Log.e("cal", "count=" + cursorSize);
        Button eventButtons[] = new Button[cursorSize];
		Log.i("ListCalendar","Cursor size = " + cursorSize);
        int count = 0;
        if(cursorSize != 0)
        {
    		Log.i("ListCalendar","Cursor size != 0");
			Log.i("ListCalendar", ""+ count);
    		while (tempCursor.moveToNext())
    		{
    			eventButtons[count] = new Button(owner);
	            
    			// Get the "ListCalendar" values of each event
	            int eventID = tempCursor.getInt(tempCursor.getColumnIndex(Database.EVENT_ID));
	            String name = tempCursor.getString(tempCursor.getColumnIndex(Database.EVENT_NAME));
	            String location = tempCursor.getString(tempCursor.getColumnIndex(Database.EVENT_LOCATION));
	            long startTime = tempCursor.getLong(tempCursor.getColumnIndex(Database.EVENT_START));
	            long endTime = tempCursor.getLong(tempCursor.getColumnIndex(Database.EVENT_END));
	            Date sday = new Date(startTime);
	            Date eday = new Date(endTime); 
	            
	            
	            Calendar today = Calendar.getInstance();
	            Calendar thisDayStart = CalendarSetting.toGregorianCalendar(startTime);
	            Calendar thisDayEnd = CalendarSetting.toGregorianCalendar(endTime);
	            if(today.equals(thisDayStart) || today.equals(thisDayEnd) || (today.after(thisDayStart) && today.before(thisDayEnd)))
	            {
	            	CalendarSetting.setPresentStyle(eventButtons[count]);
	            }
	            else if(thisDayStart.after(today))
	            {
	            	CalendarSetting.setFutureStyle(eventButtons[count]);
	            }
	            else
	            {
	            	CalendarSetting.setPastStyle(eventButtons[count]);
	            }

	            // Create a button, add event info onto it, and add it to the event-table layout (should display events here!!!)	            
	            eventButtons[count].setText("Name: " + name + ", Location: " + location + "\nStart: " + sday.toLocaleString() + "\nEnd:" + eday.toLocaleString());
	            eventTable.addView(eventButtons[count]);

	            final String Name = name;
	            final String Location = location;
	    		final int EventID = eventID;
				
				// Add functionality to the button
	    		eventButtons[count].setOnClickListener(new OnClickListener(){
					@Override
					public void onClick(View arg0) 
					{
						EditEvent.editedEventName = Name.toString();
						EditEvent.editedEventLocation = Location.toString();
						EditEvent.editedEventID = EventID;
						EditEvent.contextSwitch();
					}
				});
	    		count++;
	        } 
        }
        else
        {
    		Log.i("ListCalendar","Cursor size == 0");
        }

		Log.i("ListCalendar","End Set UI!");
	}

}
