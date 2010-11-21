package com.cs446teameo.UI;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

import com.cs446teameo.Main.R;

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
	}

}
