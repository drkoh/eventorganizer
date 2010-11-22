package com.cs446teameo.UI;

import java.util.ArrayList;

import com.cs446teameo.Actor.EventFactory;
import com.cs446teameo.Main.Main;
import com.cs446teameo.Main.R;
import com.cs446teameo.Parameter.ErrorCode;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

public class EventAdderUI extends Frame{

	EditText description = null;	
	Spinner profile = null;
	Spinner repeatOption = null;
	AutoCompleteTextView repeatEvery = null;
	DatePicker date = null;
	TimePicker sTimePicker = null;
	TimePicker eTimePicker = null;
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
	public void registeListener() {
		// TODO Auto-generated method stub
		clearButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				Log.i(field,"trigger clear button");
				description.setText("");
				profile.setSelection(0);
				repeatOption.setSelection(0);
				repeatEvery.setText("");
			}
		});
		
		createButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Log.i(field,"trigger create button");
				ArrayList<Object> src = new ArrayList<Object>();
				src.add(description.getText().toString());
				src.add((profile.getSelectedItem()).toString());
				src.add((repeatOption.getSelectedItem()).toString());
				src.add(repeatEvery.getText().toString());
				src.add(date.getYear());
				src.add(date.getMonth());
				src.add(date.getDayOfMonth());
				src.add(sTimePicker.getCurrentHour());
				src.add(sTimePicker.getCurrentMinute());
				src.add(eTimePicker.getCurrentHour());
				src.add(eTimePicker.getCurrentMinute());
				int res = EventFactory.getInstance().createEvent(src);
				if(res != ErrorCode.SUCCESS){
					//TODO: ADD AN NOTIFICATION TO THE USER
					Log.e(field,"Add Event Error: " + res);
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
	
	@Override
	public void registeComponent() {
		// TODO Auto-generated method stub
		this.description = (EditText) owner.findViewById(R.eventadder.description);
		this.description.setSingleLine();
		this.profile = (Spinner)owner.findViewById(R.eventadder.profile);
		ArrayAdapter<CharSequence> profileAdapter = ArrayAdapter.createFromResource(Frame.owner, R.array.profile_array, android.R.layout.simple_spinner_item);
		profileAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    profile.setAdapter(profileAdapter);
		this.repeatOption = (Spinner) owner.findViewById(R.eventadder.repeatOption);
		ArrayAdapter<CharSequence> repeatAdapter = ArrayAdapter.createFromResource(Frame.owner, R.array.repeat_array, android.R.layout.simple_spinner_item);
		repeatAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    repeatOption.setAdapter(repeatAdapter);
		this.repeatEvery = (AutoCompleteTextView) owner.findViewById(R.eventadder.repeatEvery);
		this.repeatEvery.setSingleLine();
		this.date = (DatePicker) owner.findViewById(R.eventadder.date);
		this.sTimePicker = (TimePicker) owner.findViewById(R.eventadder.sTime);
		this.eTimePicker = (TimePicker) owner.findViewById(R.eventadder.eTime);
		this.clearButton = (Button) owner.findViewById(R.eventadder.clearButton);
		this.createButton = (Button) owner.findViewById(R.eventadder.createButton);
		this.exitButton = (Button) owner.findViewById(R.eventadder.exitButton);
	}


}
