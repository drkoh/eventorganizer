package com.cs446teameo.UI;

import java.util.Calendar;

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
	TableLayout eventTable = null;
	
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
		
		setUI(c);
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
	public void setUI ()
	{
		// To do!
		setEventsUI();
	}

	// Sets all the event-based UI components
	public void setEventsUI()
	{

		Log.i(field,"Start Set UI!");
		Cursor tempCursor = EventManager.selectEvent("");
        int cursorSize = tempCursor.getCount();
        Log.e("cal", "count=" + cursorSize);
        Button eventButtons[] = new Button[cursorSize];
		Log.i(field,"Cursor size = " + cursorSize);
        int count = 0;
        if(cursorSize != 0)
        {
    		Log.i(field,"Cursor size != 0");
    		while (tempCursor.moveToNext())
    		{
	            // Get the field values of each event
	            String name = tempCursor.getString(tempCursor.getColumnIndex(Database.EVENT_NAME));
	            long startTime = tempCursor.getLong(tempCursor.getColumnIndex(Database.EVENT_START));
	            long endTime = tempCursor.getLong(tempCursor.getColumnIndex(Database.EVENT_END));
	            //String profile = tempCursor.getString(tempCursor.getColumnIndex(Database.EVENT_PROFILE_ID));

	            // Create a button, add event info onto it, and add it to the event-table layout (should display events here!!!)
	            eventButtons[count] = new Button(owner);
	            eventButtons[count].setText("Name: " + name + "\nStart: " + startTime + "\nEnd:" + endTime);
	    		eventTable.addView(eventButtons[count]);
	    		count++;
	        } 
        }
        else
        {
    		Log.i(field,"Cursor size == 0");
        }

		Log.i(field,"End Set UI!");
	}

}
