package com.cs446teameo.UI;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import android.database.Cursor;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

import com.cs446teameo.Evfac.EventManager;
import com.cs446teameo.Main.R;
import com.cs446teameo.Storage.Database;

public class ListCalendarUI extends Frame{

	Button yearlyTab = null;
	Button monthlyTab = null;
	Button weeklyTab = null;
	Button dailyTab = null;
	Button listTab = null;
	Button exitButton = null;
	Button createButton = null;
	static TableLayout eventTable = null;
	
	private static ListCalendarUI _instance = null;
	
	private ListCalendarUI() {
		super("ListCalendar");
		// TODO Auto-generated constructor stub
	}
	
	public static void contextSwitch(){
		if(_instance == null){
			_instance = new ListCalendarUI();
		}
		owner.setContentView(R.layout.listcalendar);
		_instance.init();
	}

	@Override
	public void registeListener() {
		// TODO Auto-generated method stub

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
		yearlyTab.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View arg0) {
				// TODO!!!
				Log.i(field,"trigger create button");
				YearlyCalendarUI.contextSwitch();
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
		this.yearlyTab = (Button) owner.findViewById(R.listcalendar.yearlyTab);
		this.monthlyTab = (Button) owner.findViewById(R.listcalendar.monthlyTab);
		this.weeklyTab = (Button) owner.findViewById(R.listcalendar.weeklyTab);
		this.dailyTab = (Button) owner.findViewById(R.listcalendar.dailyTab);
		this.listTab = (Button) owner.findViewById(R.listcalendar.listTab);
		this.listTab.setEnabled(false);
		this.createButton = (Button) owner.findViewById(R.listcalendar.createButton);
		this.exitButton = (Button) owner.findViewById(R.listcalendar.exitButton);	
		this.eventTable = (TableLayout) owner.findViewById(R.listcalendar.eventTable);	
		setUI();
	}
	
	// Sets all the text-based UI components
	public static void setUI ()
	{
		// To do!
		setEventsUI();
	}

	// Sets all the event-based UI components
	public static void setEventsUI()
	{
		eventTable.removeAllViews();
		Log.i("ListCalendar","Start Set UI!");
		Cursor tempCursor = EventManager.selectEvent("order by " + Database.EVENT_START);
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
    			Log.d("E", "" + count);
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
