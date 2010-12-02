package com.cs446teameo.UI;

import java.util.ArrayList;

import com.cs446teameo.Actor.EventFactory;
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

public class EventAdderUI extends Frame{

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
	Button createButton = null;
	Button exitButton = null;
	public static final short NONE = 0;
	public static final short DAILY = 1;
	public static final short WEEKLY = 2;
	public static final short MONTHLY = 3;
	public static final short YEARLY = 4;
	ArrayList<Profile> list = null;
	
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
		
		createButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Log.i(field,"trigger create button");
				
				ArrayList<Object> src = new ArrayList<Object>();

				if(description.getText().toString().equals(null) || description.getText().toString().equals(""))
				{
					Toast.makeText(owner, "You must enter an event description!", Toast.LENGTH_SHORT).show();
					EventAdderUI.contextSwitch();
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
		this.description = (EditText) owner.findViewById(R.eventadder.description);
		this.description.setSingleLine();
		this.location = (EditText) owner.findViewById(R.eventadder.location);
		this.location.setSingleLine();
		this.profile = (Spinner)owner.findViewById(R.eventadder.profile);
		loadProfile();
		this.repeatOption = (Spinner) owner.findViewById(R.eventadder.repeatOption);
		ArrayAdapter<CharSequence> repeatAdapter = ArrayAdapter.createFromResource(Frame.owner, R.array.repeat_array, android.R.layout.simple_spinner_item);
		repeatAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    repeatOption.setAdapter(repeatAdapter);
		this.startDate = (DatePicker) owner.findViewById(R.eventadder.startDate);
		this.endDate = (DatePicker) owner.findViewById(R.eventadder.endDate);
		this.sTimePicker = (TimePicker) owner.findViewById(R.eventadder.startTime);
		this.eTimePicker = (TimePicker) owner.findViewById(R.eventadder.endTime);
		this.clearButton = (Button) owner.findViewById(R.eventadder.clearButton);
		this.createButton = (Button) owner.findViewById(R.eventadder.createButton);
		this.exitButton = (Button) owner.findViewById(R.eventadder.exitButton);
		this.startDateLayout = (LinearLayout) owner.findViewById(R.eventadder.startDateLayout);
		this.endDateLayout = (LinearLayout) owner.findViewById(R.eventadder.endDateLayout);
		this.repeatStatusLayout = (LinearLayout) owner.findViewById(R.eventadder.repeatStatusLayout);
		this.repeatStatusText = (TextView) owner.findViewById(R.eventadder.repeatStatusText);
	}
	
	// Load all the profile names onto the profile spinner
	private void loadProfile()
	{
		list = new ArrayList<Profile>();
		ProfileManager.getInstance().listProfile(list);
		String profileNames[] = new String[list.size()];
		for(int i = 0; i < list.size(); i++)
		{
			profileNames[i] = (list.get(i)).getDescription();
		}
		ArrayAdapter<CharSequence> profileadapter = new ArrayAdapter<CharSequence>(owner, android.R.layout.simple_spinner_item, profileNames);
		profile.setAdapter(profileadapter);
	}
	
	private int getProfileID (String profileName)
	{
		for(int i = 0; i < list.size(); i++)
		{
			if((list.get(i)).getDescription().equals(profileName))
			{
				return (list.get(i)).getId();
			}
		}
		return -1;
	}

}
