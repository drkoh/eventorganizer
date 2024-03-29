package com.cs446teameo.UI;

import java.util.Calendar;
import java.util.Date;

import android.database.Cursor;
import android.graphics.Typeface;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

import com.cs446teameo.Evfac.EventManager;
import com.cs446teameo.Main.R;
import com.cs446teameo.Storage.Database;

public class WeeklyCalendarUI extends Frame{

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
	TableLayout sundayLayout = null;
	TableLayout mondayLayout = null;
	TableLayout tuesdayLayout = null;
	TableLayout wednesdayLayout = null;
	TableLayout thursdayLayout = null;
	TableLayout fridayLayout = null;
	TableLayout saturdayLayout = null;
	TextView sundayText = null;
	TextView mondayText = null;
	TextView tuesdayText = null;
	TextView wednesdayText = null;
	TextView thursdayText = null;
	TextView fridayText = null;
	TextView saturdayText = null;
	int currentYear, currentMonth, currentDay, currentDayOfTheWeek;
	Calendar calendar;
	String dateString;
	String[] monthNames = {"January", "February", "March", 
			"April", "May", "June", "July", "August", 
			"September", "October", "November", "December"};
	String[] dayNames = new String[] { "Sunday", "Monday", "Tuesday", 
			"Wednesday", "Thursday", "Friday", "Saturday" };

	private static WeeklyCalendarUI _instance = null;
	
	private WeeklyCalendarUI() {
		super("WeeklyCalendar");
		// TODO Auto-generated constructor stub
	}
	
	public static void contextSwitch(){
		if(_instance == null){
			_instance = new WeeklyCalendarUI();
		}
		owner.setContentView(R.layout.weeklycalendar);
		_instance.init();
	}

	@Override
	public void registeListener() {
		// TODO Auto-generated method stub
		
		leftButton.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View arg0) {
				calendar.add(Calendar.DAY_OF_MONTH, -7);
				setUI(calendar);
			}
		});
		

		rightButton.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View arg0) {
				calendar.add(Calendar.DAY_OF_MONTH, 7);
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
		monthlyTab.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View arg0) {
				// TODO!!!
				Log.i(field,"trigger create button");
				MonthlyCalendarUI.contextSwitch();
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
		this.yearlyTab = (Button) owner.findViewById(R.weeklycalendar.yearlyTab);
		this.monthlyTab = (Button) owner.findViewById(R.weeklycalendar.monthlyTab);
		this.weeklyTab = (Button) owner.findViewById(R.weeklycalendar.weeklyTab);
		this.weeklyTab.setEnabled(false);
		this.dailyTab = (Button) owner.findViewById(R.weeklycalendar.dailyTab);
		this.listTab = (Button) owner.findViewById(R.weeklycalendar.listTab);
		this.date = (TextView) owner.findViewById(R.weeklycalendar.date);
		this.leftButton = (Button) owner.findViewById(R.weeklycalendar.leftButton);
		this.rightButton = (Button) owner.findViewById(R.weeklycalendar.rightButton);	
		this.createButton = (Button) owner.findViewById(R.weeklycalendar.createButton);
		this.exitButton = (Button) owner.findViewById(R.weeklycalendar.exitButton);
		this.sundayLayout = (TableLayout) owner.findViewById(R.weeklycalendar.sundayLayout);
		this.mondayLayout = (TableLayout) owner.findViewById(R.weeklycalendar.mondayLayout);
		this.tuesdayLayout = (TableLayout) owner.findViewById(R.weeklycalendar.tuesdayLayout);
		this.wednesdayLayout = (TableLayout) owner.findViewById(R.weeklycalendar.wednesdayLayout);
		this.thursdayLayout = (TableLayout) owner.findViewById(R.weeklycalendar.thursdayLayout);
		this.fridayLayout = (TableLayout) owner.findViewById(R.weeklycalendar.fridayLayout);
		this.saturdayLayout = (TableLayout) owner.findViewById(R.weeklycalendar.saturdayLayout);
		this.sundayText = (TextView) owner.findViewById(R.weeklycalendar.sundayText);
		this.mondayText = (TextView) owner.findViewById(R.weeklycalendar.mondayText);
		this.tuesdayText = (TextView) owner.findViewById(R.weeklycalendar.tuesdayText);
		this.wednesdayText = (TextView) owner.findViewById(R.weeklycalendar.wednesdayText);
		this.thursdayText = (TextView) owner.findViewById(R.weeklycalendar.thursdayText);
		this.fridayText = (TextView) owner.findViewById(R.weeklycalendar.fridayText);
		this.saturdayText = (TextView) owner.findViewById(R.weeklycalendar.saturdayText);
		calendar = Calendar.getInstance();
		setUI(calendar);
	}
	
	// Sets all the text-based UI components
	public void setUI (Calendar c)
	{
		Calendar tempCalendar;
		String beginTitle, endTitle;
		
		// Calculate current day
        currentYear = c.get(Calendar.YEAR);
        currentMonth = c.get(Calendar.MONTH);
        currentDay = c.get(Calendar.DAY_OF_MONTH);
        currentDayOfTheWeek = c.get(Calendar.DAY_OF_WEEK);
        dateString = dayNames[currentDayOfTheWeek - 1] + ", " + monthNames[currentMonth]  + " " + currentDay + ", " + currentYear;
        
        // Set the date's text for Sunday
        tempCalendar = (Calendar)c.clone();
        tempCalendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        dateString = dayNames[tempCalendar.get(Calendar.DAY_OF_WEEK) - 1] + ", " + 
        			monthNames[tempCalendar.get(Calendar.MONTH)]  + " " + 
        			tempCalendar.get(Calendar.DAY_OF_MONTH) + ", " + 
        			tempCalendar.get(Calendar.YEAR);
        beginTitle = tempCalendar.get(Calendar.DAY_OF_MONTH) + "/" +
		tempCalendar.get(Calendar.MONTH) + "/" +
		tempCalendar.get(Calendar.YEAR);
        sundayText.setText(dateString);
        setEventsUI(tempCalendar, sundayLayout);
        
        // Set the date's text for Monday
        tempCalendar = (Calendar)c.clone();
        tempCalendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        dateString = dayNames[tempCalendar.get(Calendar.DAY_OF_WEEK) - 1] + ", " + 
        			monthNames[tempCalendar.get(Calendar.MONTH)]  + " " + 
        			tempCalendar.get(Calendar.DAY_OF_MONTH) + ", " + 
        			tempCalendar.get(Calendar.YEAR);
        mondayText.setText(dateString);
        setEventsUI(tempCalendar, mondayLayout);
        
        // Set the date's text for Tuesday
        tempCalendar = (Calendar)c.clone();
        tempCalendar.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
        dateString = dayNames[tempCalendar.get(Calendar.DAY_OF_WEEK) - 1] + ", " + 
        			monthNames[tempCalendar.get(Calendar.MONTH)]  + " " + 
        			tempCalendar.get(Calendar.DAY_OF_MONTH) + ", " + 
        			tempCalendar.get(Calendar.YEAR);
        tuesdayText.setText(dateString);
        setEventsUI(tempCalendar, tuesdayLayout);
        
        // Set the date's text for Wednesday
        tempCalendar = (Calendar)c.clone();
        tempCalendar.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
        dateString = dayNames[tempCalendar.get(Calendar.DAY_OF_WEEK) - 1] + ", " + 
        			monthNames[tempCalendar.get(Calendar.MONTH)]  + " " + 
        			tempCalendar.get(Calendar.DAY_OF_MONTH) + ", " + 
        			tempCalendar.get(Calendar.YEAR);
        wednesdayText.setText(dateString);
        setEventsUI(tempCalendar, wednesdayLayout);
        
        // Set the date's text for Thursday
        tempCalendar = (Calendar)c.clone();
        tempCalendar.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
        dateString = dayNames[tempCalendar.get(Calendar.DAY_OF_WEEK) - 1] + ", " + 
        			monthNames[tempCalendar.get(Calendar.MONTH)]  + " " + 
        			tempCalendar.get(Calendar.DAY_OF_MONTH) + ", " + 
        			tempCalendar.get(Calendar.YEAR);
        thursdayText.setText(dateString);
        setEventsUI(tempCalendar, thursdayLayout);
        
        // Set the date's text for Friday
        tempCalendar = (Calendar)c.clone();
        tempCalendar.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
        dateString = dayNames[tempCalendar.get(Calendar.DAY_OF_WEEK) - 1] + ", " + 
        			monthNames[tempCalendar.get(Calendar.MONTH)]  + " " + 
        			tempCalendar.get(Calendar.DAY_OF_MONTH) + ", " + 
        			tempCalendar.get(Calendar.YEAR);
        fridayText.setText(dateString);
        setEventsUI(tempCalendar, fridayLayout);
        
        // Set the date's text for Saturday
        tempCalendar = (Calendar)c.clone();
        tempCalendar.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
        dateString = dayNames[tempCalendar.get(Calendar.DAY_OF_WEEK) - 1] + ", " + 
        			monthNames[tempCalendar.get(Calendar.MONTH)]  + " " + 
        			tempCalendar.get(Calendar.DAY_OF_MONTH) + ", " + 
        			tempCalendar.get(Calendar.YEAR);
        endTitle = tempCalendar.get(Calendar.DAY_OF_MONTH) + "/" +
		tempCalendar.get(Calendar.MONTH) + "/" +
		tempCalendar.get(Calendar.YEAR);
        saturdayText.setText(dateString); 
        setEventsUI(tempCalendar, saturdayLayout);   
		
        // Set the week date's text
        date.setText(beginTitle + " - " + endTitle);
	}
	
	// Sets all the event-based UI components
	public void setEventsUI(Calendar c, TableLayout eventTable)
	{
		eventTable.removeAllViews();
		Log.i(field,"Start Set UI!");
		Cursor tempCursor = EventManager.allEventsOfDay(c);
        int cursorSize = tempCursor.getCount();
        Log.e("cal", "count=" + cursorSize);
        Button eventButtons[] = new Button[cursorSize];
		Log.i("ListCalendar","Cursor size = " + cursorSize);
        int count = 0;
        if(cursorSize != 0)
        {
    		Log.i("ListCalendar","Cursor size != 0");
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
	            eventButtons[count].setText("Name: " + name  + ", Location: " + location + "\nStart: " + sday.toLocaleString() + "\nEnd:" + eday.toLocaleString());
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
						EditEvent.editedEventID = EventID;
						EditEvent.editedEventLocation = Location.toString();
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
