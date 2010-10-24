package com.cs446teameo.UI;

import java.util.ArrayList;

import com.cs446teameo.Actor.EventFactory;
import com.cs446teameo.Main.R;
import com.cs446teameo.Parameter.ErrorCode;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

public class RepeatedEventAdderUI extends Frame{

	Spinner repeatOption = null;
	AutoCompleteTextView repeatEvery = null;
	Button clearButton = null;
	Button exitButton = null;
	Button createButton = null;
	
	private static RepeatedEventAdderUI _instance = null;
	
	private RepeatedEventAdderUI() {
		super("RepeatedEventAdder");
		// TODO Auto-generated constructor stub
	}
	
	public static void contextSwitch(){
		if(_instance == null){
			_instance = new RepeatedEventAdderUI();
		}
		owner.setContentView(R.layout.repeatedeventadder);
		_instance.init();
	}
	
	@Override
	public void registeListener() {
		// TODO
		
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
		this.repeatOption = (Spinner) owner.findViewById(R.repeatedevent.repeatOption);
		this.repeatEvery = (AutoCompleteTextView) owner.findViewById(R.repeatedevent.repeatEvery);
		this.clearButton = (Button) owner.findViewById(R.repeatedevent.clearButton);
		this.createButton = (Button) owner.findViewById(R.repeatedevent.createButton);
		this.exitButton = (Button) owner.findViewById(R.repeatedevent.exitButton);
	}
}
