package com.cs446teameo.UI;

import com.cs446teameo.Main.R;
import com.cs446teameo.Parameter.ErrorCode;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MenuUI extends Frame{

	Button AddEventButton = null;
	Button CalendarButton = null;
	Button CreateProfileButton = null;
	Button ExitButton = null;
	Button SynchronizeButton = null;
	
	private static MenuUI _instance = null;
	
	private MenuUI() {
		super("Menu");
		// TODO Auto-generated constructor stub
	}
	
	public static void contextSwitch(){
		if(_instance == null){
			_instance = new MenuUI();
		}
		owner.setContentView(R.layout.menu);
		_instance.init();
	}
	
	
	@Override
	public void registeListener() {
		// TODO Auto-generated method stub
		this.AddEventButton.setOnClickListener((new OnClickListener(){
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Log.i(field, "trigger the click listener");
				EventAdderUI.contextSwitch();
				return;
			}
		}));
		
		this.CalendarButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Log.i(field,"trigger the calender button");
			}
		});
		
		this.CreateProfileButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Log.i(field,"trigger the create profile button");
				CreateProfileUI.contextSwitch();
				return;
			}
		});
		
		this.ExitButton.setOnClickListener((new OnClickListener(){
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Log.i(field, "trigger the exitButton");
				owner.finish();
			}
		}));
		
		this.SynchronizeButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Log.i(field,"trigger the synchronize Button");
				GoogleSyncUI.contextSwitch();
				return;
			}
		});
	}

	@Override
	public void registeComponent() {
		// TODO Auto-generated method stub
		this.AddEventButton = (Button)owner.findViewById(R.menuId.AddEventButton);
		this.CalendarButton = (Button)owner.findViewById(R.menuId.CalendarButton);
		this.CreateProfileButton = (Button)owner.findViewById(R.menuId.CreateProfileButton);
		this.ExitButton = (Button)owner.findViewById(R.menuId.ExitButton);
		this.SynchronizeButton = (Button)owner.findViewById(R.menuId.SynchronizeButton);
		if(this.AddEventButton == null){
			Log.e(field, "reg error");
		}
	}
	
}
