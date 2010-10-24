package com.cs446teameo.UI;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

import com.cs446teameo.Main.R;

public class GoogleSyncUI extends Frame{

	EditText username = null;
	EditText password = null;
	Button clearButton = null;
	Button exitButton = null;
	Button createButton = null;
	
	private static GoogleSyncUI _instance = null;
	
	private GoogleSyncUI() {
		super("GoogleSync");
		// TODO Auto-generated constructor stub
	}
	
	public static void contextSwitch(){
		if(_instance == null){
			_instance = new GoogleSyncUI();
		}
		owner.setContentView(R.layout.googlesync);
		_instance.init();
	}
	
	@Override
	public void registeListener() {
		// TODO Auto-generated method stub
		
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
		this.username = (EditText) owner.findViewById(R.googlecalendar.username);
		this.password = (EditText) owner.findViewById(R.googlecalendar.password);
		this.clearButton = (Button) owner.findViewById(R.googlecalendar.clearButton);
		this.createButton = (Button) owner.findViewById(R.googlecalendar.synchronizeButton);
		this.exitButton = (Button) owner.findViewById(R.googlecalendar.exitButton);
	}

}
