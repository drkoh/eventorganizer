package com.cs446teameo.UI;

import java.util.ArrayList;

import com.cs446teameo.Actor.EventFactory;
import com.cs446teameo.Event.Event;
import com.cs446teameo.Evfac.EventManager;
import com.cs446teameo.Main.Main;
import com.cs446teameo.Main.R;
import com.cs446teameo.Parameter.ErrorCode;
import com.cs446teameo.Profile.Profile;
import com.cs446teameo.Profile.ProfileManager;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class EditEvent extends Frame{

	EditText description = null;	
	EditText location = null;
	Spinner profile = null;
	Spinner repeatOption = null;
	DatePicker startDate = null;
	DatePicker endDate = null;
	LinearLayout startDateLayout = null;
	LinearLayout endDateLayout = null;
	LinearLayout repeatStatusLayout = null;
	public static TextView repeatStatusText = null;
	TimePicker sTimePicker = null;
	TimePicker eTimePicker = null;
	Button clearButton = null;
	Button deleteButton = null;
	Button editButton = null;
	Button exitButton = null;
	public static final short NONE = 0;
	public static final short DAILY = 1;
	public static final short WEEKLY = 2;
	public static final short MONTHLY = 3;
	public static final short YEARLY = 4;
	ArrayList<Profile> profileList = null;	
	ArrayList<Event> eventList = null;	
	private static EditEvent _instance = null;
	
	private EditEvent() {
		super("EditEvent");
		// TODO Auto-generated constructor stub
	}
	
	public static void contextSwitch(){
		if(_instance == null){
			_instance = new EditEvent();
		}
		owner.setContentView(R.layout.editevent);
		_instance.init();
		//_instance.refreshComponent();
	}
	
	@Override
	public void registeListener() {
		
		repeatOption.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
		    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) 
		    {
		    	// None
		    	if(pos == 0)
		    	{
		    		startDateLayout.setVisibility(View.VISIBLE);
		    		endDateLayout.setVisibility(View.VISIBLE);
		    		repeatStatusLayout.setVisibility(View.GONE);
		    	}
		    	// Daily
		    	else if(pos == 1)
		    	{
		    		startDateLayout.setVisibility(View.GONE);
		    		endDateLayout.setVisibility(View.GONE);
		    		repeatStatusLayout.setVisibility(View.VISIBLE);
		    		repeatStatusText.setText("Repeated every day.");
		    	}
		    	// Weekly
		    	else if(pos == 2)
		    	{
		    		WeeklyEventAdder weeklyDialog = new WeeklyEventAdder(owner);
		    		weeklyDialog.show();
		    		startDateLayout.setVisibility(View.GONE);
		    		endDateLayout.setVisibility(View.GONE);
		    		repeatStatusLayout.setVisibility(View.VISIBLE);
		    	}
		    	// Monthly
		    	else if(pos == 3)
		    	{
		    		MonthlyEventAdder monthlyDialog = new MonthlyEventAdder(owner);
		    		monthlyDialog.show();
		    		startDateLayout.setVisibility(View.GONE);
		    		endDateLayout.setVisibility(View.GONE);
		    		repeatStatusLayout.setVisibility(View.VISIBLE);
		    	}
		    	// Yearly
		    	else if(pos == 4)
		    	{
		    		// Just take days like you do normally
		    		startDateLayout.setVisibility(View.VISIBLE);
		    		endDateLayout.setVisibility(View.GONE);
		    		repeatStatusLayout.setVisibility(View.VISIBLE);
		    		repeatStatusText.setText("Repeated every year.");
		    	}
		    }
		    public void onNothingSelected(AdapterView<?> parent) { }
		});

		// TODO Auto-generated method stub
		clearButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				Log.i(field,"trigger clear button");
				description.setText("");
				profile.setSelection(0);
				repeatOption.setSelection(0);
			}
		});
		
		deleteButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				int res = ProfileManager.getInstance().deleteProfile(getEventID(description.getText().toString()));
				MenuUI.contextSwitch();
			}
		});
		
		editButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Log.i(field,"trigger create button");
				
				ArrayList<Object> src = new ArrayList<Object>();

				if(description.getText().toString().equals(null) || description.getText().toString().equals(""))
				{
					Toast.makeText(owner, "You must enter an event description!", Toast.LENGTH_SHORT).show();
					EditEvent.contextSwitch();
				}
				else 
				{
					if((repeatOption.getSelectedItem()).toString().equals("None"))
					{
	
						src.add(NONE); 													// Add the repeat option
						src.add(description.getText().toString()); 						// Add the event name
						if(profile.getSelectedItem()!=null)
							src.add(getProfileID((profile.getSelectedItem()).toString())); 	// Add the profile id of the event
						else
							src.add("");
						src.add(location.getText().toString()); 						// Add the event location
						
						// Add the start time
						src.add(sTimePicker.getCurrentHour());	//0
						src.add(sTimePicker.getCurrentMinute());	//1
						
						// Add the start date
						src.add(startDate.getYear());	//2
						src.add(startDate.getMonth());	//3
						src.add(startDate.getDayOfMonth());//4
	
						// Add the end time
						src.add(eTimePicker.getCurrentHour()); //5
						src.add(eTimePicker.getCurrentMinute()); //6
						
						// Add the end date
						src.add(endDate.getYear());//7
						src.add(endDate.getMonth());//8
						src.add(endDate.getDayOfMonth());//9
					}
					else if((repeatOption.getSelectedItem()).toString().equals("Daily"))
					{
						src.add(DAILY);													// Add the repeat option
						src.add(description.getText().toString()); 						// Add the event name
						src.add(getProfileID((profile.getSelectedItem()).toString())); 	// Add the profile id of the event
						src.add(location.getText().toString()); 						// Add the event location
						
						// Add the start time
						src.add(sTimePicker.getCurrentHour());	//0
						src.add(sTimePicker.getCurrentMinute());	//1
	
						// Add the end time
						src.add(eTimePicker.getCurrentHour());	//2
						src.add(eTimePicker.getCurrentMinute());	//3
					}
					else if((repeatOption.getSelectedItem()).toString().equals("Weekly"))
					{
						src.add(WEEKLY);												// Add the repeat option
						src.add(description.getText().toString()); 						// Add the event name
						src.add(getProfileID((profile.getSelectedItem()).toString())); 	// Add the profile id of the event
						src.add(location.getText().toString()); 						// Add the event location
						
						// Add the start time
						src.add(sTimePicker.getCurrentHour());	//0
						src.add(sTimePicker.getCurrentMinute());	//1
	
						// Add the end time
						src.add(eTimePicker.getCurrentHour());	//2
						src.add(eTimePicker.getCurrentMinute());	//3
						src.add((ArrayList <Integer>)WeeklyEventAdder.daysArray);	//4		// Add an array of days (as ints)
					}
					else if((repeatOption.getSelectedItem()).toString().equals("Monthly"))
					{
						src.add(MONTHLY);												// Add the repeat option
						src.add(description.getText().toString()); 						// Add the event name
						src.add(getProfileID((profile.getSelectedItem()).toString())); 	// Add the profile id of the event
						src.add(location.getText().toString()); 						// Add the event location
						
						// Add the start time
						src.add(sTimePicker.getCurrentHour());
						src.add(sTimePicker.getCurrentMinute());
	
						// Add the end time
						src.add(eTimePicker.getCurrentHour());
						src.add(eTimePicker.getCurrentMinute());
						
						src.add((int)MonthlyEventAdder.day);							// Add the day of the month
					}
					else if((repeatOption.getSelectedItem()).toString().equals("Yearly"))
					{
						src.add(YEARLY); 												// Add the repeat option
						src.add(description.getText().toString()); 						// Add the event name
						src.add(getProfileID((profile.getSelectedItem()).toString())); 	// Add the profile id of the event
						src.add(location.getText().toString()); 						// Add the event location
						
						// Add the start time
						src.add(sTimePicker.getCurrentHour());
						src.add(sTimePicker.getCurrentMinute());
						
						// Add the start date
						src.add(startDate.getMonth());
						src.add(startDate.getDayOfMonth());
	
						// Add the end time
						src.add(eTimePicker.getCurrentHour());
						src.add(eTimePicker.getCurrentMinute());
						
						// Add the end date
						src.add(endDate.getMonth());
						src.add(endDate.getDayOfMonth());
					}
					Log.d(field, "enter database create");
					int res = EventFactory.getInstance().createEvent(src);
					if(res != ErrorCode.SUCCESS){
						//TODO: ADD AN NOTIFICATION TO THE USER
						Log.e(field,"Add Event Error: " + res);
						return;
					}
					Log.d("!!","goes2 here");
					MenuUI.contextSwitch();
				}
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
		this.description = (EditText) owner.findViewById(R.editevent.description);
		this.description.setSingleLine();
		this.location = (EditText) owner.findViewById(R.editevent.location);
		this.location.setSingleLine();
		this.profile = (Spinner)owner.findViewById(R.editevent.profile);
		loadProfile();
		this.repeatOption = (Spinner) owner.findViewById(R.editevent.repeatOption);
		ArrayAdapter<CharSequence> repeatAdapter = ArrayAdapter.createFromResource(Frame.owner, R.array.repeat_array, android.R.layout.simple_spinner_item);
		repeatAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    repeatOption.setAdapter(repeatAdapter);
		this.startDate = (DatePicker) owner.findViewById(R.editevent.startDate);
		this.endDate = (DatePicker) owner.findViewById(R.editevent.endDate);
		this.sTimePicker = (TimePicker) owner.findViewById(R.editevent.startTime);
		this.eTimePicker = (TimePicker) owner.findViewById(R.editevent.endTime);
		this.clearButton = (Button) owner.findViewById(R.editevent.clearButton);
		this.editButton = (Button) owner.findViewById(R.editevent.editButton);
		this.deleteButton = (Button) owner.findViewById(R.editevent.deleteButton);
		this.exitButton = (Button) owner.findViewById(R.editevent.exitButton);
		this.startDateLayout = (LinearLayout) owner.findViewById(R.editevent.startDateLayout);
		this.endDateLayout = (LinearLayout) owner.findViewById(R.editevent.endDateLayout);
		this.repeatStatusLayout = (LinearLayout) owner.findViewById(R.editevent.repeatStatusLayout);
		this.repeatStatusText = (TextView) owner.findViewById(R.editevent.repeatStatusText);
	}
	
	// Load all the profile names onto the profile spinner
	private void loadProfile()
	{
		profileList = new ArrayList<Profile>();
		ProfileManager.getInstance().listProfile(profileList);
		String profileNames[] = new String[profileList.size()];
		for(int i = 0; i < profileList.size(); i++)
		{
			profileNames[i] = (profileList.get(i)).getDescription();
		}
		ArrayAdapter<CharSequence> profileadapter = new ArrayAdapter<CharSequence>(owner, android.R.layout.simple_spinner_item, profileNames);
		profile.setAdapter(profileadapter);
	}
	
	// Load all the event information
	private void loadEvents()
	{
		eventList = EventManager.allEvents();
	}
	
	private int getProfileID (String profileName)
	{
		for(int i = 0; i < profileList.size(); i++)
		{
			if((profileList.get(i)).getDescription().equals(profileName))
			{
				return (profileList.get(i)).getId();
			}
		}
		return -1;
	}
	
	private int getEventID(String eventName)
	{
		for(int i = 0; i < eventList.size(); i++)
		{
			if(eventName.equals((eventList.get(i)).getName()))
			{
				return (eventList.get(i)).getEid();
			}
		}
		return -1;
	}

}
