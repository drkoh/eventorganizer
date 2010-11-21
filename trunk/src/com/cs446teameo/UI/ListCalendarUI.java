package com.cs446teameo.UI;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

import com.cs446teameo.Main.R;

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
	}

}
