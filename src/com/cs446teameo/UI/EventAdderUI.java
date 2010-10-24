package com.cs446teameo.UI;

import java.util.ArrayList;

import com.cs446teameo.Actor.EventFactory;
import com.cs446teameo.Main.R;
import com.cs446teameo.Parameter.ErrorCode;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

public class EventAdderUI extends Frame{

	EditText description = null;
	TimePicker sTimePicker = null;
	TimePicker eTimePicker = null;
	Spinner profile = null;
	Button clearButton = null;
	Button createButton = null;
	Button exitButton = null;
	
	private static EventAdderUI _instance = null;
	
	
	private EventAdderUI() {
		super("EventAdder");
		// TODO Auto-generated constructor stub
	}
	
	public static void contextSwitch(){
		if(_instance == null){
			_instance = new EventAdderUI();
		}
		owner.setContentView(R.layout.eventadder);
		_instance.init();
	}
	

	@Override
	public void registeComponent() {
		// TODO Auto-generated method stub
		this.description = (EditText) owner.findViewById(R.eventadder.description);
		this.sTimePicker = (TimePicker) owner.findViewById(R.eventadder.sTime);
		this.eTimePicker = (TimePicker) owner.findViewById(R.eventadder.eTime);
		this.clearButton = (Button) owner.findViewById(R.eventadder.clearButton);
		this.createButton = (Button) owner.findViewById(R.eventadder.createButton);
		this.exitButton = (Button) owner.findViewById(R.eventadder.exitButton);
		this.profile = (Spinner)owner.findViewById(R.eventadder.profile);
	}

	@Override
	public void registeListener() {
		// TODO Auto-generated method stub
		clearButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				Log.i(field,"trigger clear button");
				EventAdderUI.contextSwitch();
			}
		});
		
		createButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Log.i(field,"trigger create button");
				ArrayList<Object> src = new ArrayList<Object>();
				src.add(sTimePicker.getCurrentHour());
				src.add(sTimePicker.getCurrentMinute());
				src.add(eTimePicker.getCurrentHour());
				src.add(eTimePicker.getCurrentMinute());
				int res = EventFactory.getInstance().addEvent(src);
				if(res != ErrorCode.SUCCESS){
					Log.e(field,"addevent error: " + res);
					return;
				}
				MenuUI.contextSwitch();
			}
		});
		
		exitButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Log.i(field,"trigger exit button");
				MenuUI.contextSwitch();
			}
		});
	}
}
