package com.cs446teameo.UI;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
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
	}
}