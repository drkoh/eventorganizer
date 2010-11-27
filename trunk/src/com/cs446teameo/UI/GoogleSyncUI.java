package com.cs446teameo.UI;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

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
		
		// Clear Button
		clearButton.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View arg0) {
				// TODO!!!
				Log.i(field,"trigger clear button");
				GoogleSyncUI.contextSwitch();
			}
		});
		
		// Create Button
		createButton.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View arg0) {
				// TODO!!!
				Log.i(field,"trigger create button");
				
				/*
				// Set up the URL and the object that will handle the connection
				URL feedUrl = null;
				try 
				{
					feedUrl = new URL("https://www.google.com/calendar/feeds/" + username.getText() + "/private/full");
				} 
				catch (MalformedURLException e) 
				{
					e.printStackTrace();
				}
				
				
				// Create a Calendar Service
				CalendarService myService = new CalendarService("Event Organizer - Calendar Application");
				
				// Authenticate the login information with Google Servers
				try 
				{
					myService.setUserCredentials("" + username.getText(), "" + password.getText());
				} 
				catch (AuthenticationException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// Send the request and receive the response
				try
				{
					CalendarEventFeed myFeed = myService.getFeed(feedUrl, CalendarEventFeed.class);
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				} 
				catch (ServiceException e) 
				{
					e.printStackTrace();
				}
				*/
				
				ListCalendarUI.contextSwitch();
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
		this.username = (EditText) owner.findViewById(R.googlecalendar.username);
		this.username.setSingleLine();
		this.password = (EditText) owner.findViewById(R.googlecalendar.password);
		this.clearButton = (Button) owner.findViewById(R.googlecalendar.clearButton);
		this.createButton = (Button) owner.findViewById(R.googlecalendar.synchronizeButton);
		this.exitButton = (Button) owner.findViewById(R.googlecalendar.exitButton);
	}
}
