package com.cs446teameo.UI;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

import com.cs446teameo.Main.R;
import com.cs446teameo.Parameter.ErrorCode;
import com.cs446teameo.Profile.Profile;
import com.cs446teameo.Profile.ProfileManager;

public class EditProfileUI extends Frame{
	
	Spinner name = null;
	SeekBar volume = null;
	TextView volumeValue = null;
	CheckBox vibrate = null;
	Button clearButton = null;
	Button exitButton = null;
	Button editButton = null;
	Button deleteButton = null;
	ArrayList<Profile>list = new ArrayList<Profile>();
	
	//Here You should first load on all created profiles, or how can you delete it
	
	private static EditProfileUI _instance = null;
	
	private EditProfileUI() {
		super("EditProfile");
		// TODO Auto-generated constructor stub
	}
	
	public static void contextSwitch(){
		if(_instance == null){
			_instance = new EditProfileUI();
			owner.setContentView(R.layout.editprofile);
			_instance.init();
		}else{
			owner.setContentView(R.layout.editprofile);
			_instance.init();
		}
	}

	@Override
	public void registeListener() {
		// TODO Auto-generated method stub
		
		name.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
		    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) 
		    {
		    	String spinnerSelection = (String)name.getItemAtPosition(pos);
				volume.setProgress(getProfileVolume(spinnerSelection));
				volumeValue.setText(" " + getProfileVolume(spinnerSelection));
				vibrate.setChecked(getProfileVibrate(spinnerSelection));
		    }
		    public void onNothingSelected(AdapterView<?> parent) { }
		});
		
		// Create Button
		deleteButton.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View arg0) {
				// TODO!!!
				int spinnerPosition = name.getSelectedItemPosition();
				String spinnerSelection = (String)name.getItemAtPosition(spinnerPosition);
				int res = ProfileManager.getInstance().deleteProfile(getProfileID(spinnerSelection));
				if (res != ErrorCode.SUCCESS){
					Log.e(field, "Delete Profile Error");
					return;
				}
				Log.i(field,"trigger create button");
				ProfileUI.contextSwitch();
			}
		});
		
		// Clear Button
		volume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
			
			public void onProgressChanged(SeekBar volume, int progress, boolean fromUser) {
				    // TODO Auto-generated method stub
				    volumeValue.setText(" " + String.valueOf(progress));
		    }
		
			public void onStartTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void onStopTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		// Clear Button
		clearButton.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View arg0) {
				name.setSelection(0);
				volume.setProgress(50);
				volumeValue.setText(" 50");
				vibrate.setChecked(true);
				Log.i(field,"trigger clear button");
				EditProfileUI.contextSwitch();
				
			}
		});
		
		// Create Button
		editButton.setOnClickListener(new OnClickListener(){
			//DO YOU MEAN EDITButton a save button here?? I don't know what to do here
			@Override
			public void onClick(View arg0) {
				// TODO!!!
				int spinnerPosition = name.getSelectedItemPosition();
				String spinnerSelection = (String)name.getItemAtPosition(spinnerPosition);
				Profile profile = new Profile(spinnerSelection, vibrate.isChecked(), volume.getProgress());
				int res = ProfileManager.getInstance().updateProfile(profile, getProfileID(spinnerSelection));
				if(res != ErrorCode.SUCCESS){
					//TODO: ADD AN NOTIFICATION TO THE USER
					Log.e(field,"Add Event Error: " + res);
					return;
				}
				Log.i(field,"trigger create button");
				ProfileUI.contextSwitch();
			}
		});
		
		// Exit Button
		exitButton.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Log.i(field,"trigger exit button");
				ProfileUI.contextSwitch();
			}
		});
	}

	@Override
	public void registeComponent() {
		this.name = (Spinner) owner.findViewById(R.editprofile.name);
		this.volume = (SeekBar) owner.findViewById(R.editprofile.volume);
		this.volumeValue = (TextView) owner.findViewById(R.editprofile.volumevalue);
		this.vibrate = (CheckBox) owner.findViewById(R.editprofile.vibrate);
		this.clearButton = (Button) owner.findViewById(R.editprofile.clearButton);
		this.editButton = (Button) owner.findViewById(R.editprofile.editButton);
		this.exitButton = (Button) owner.findViewById(R.editprofile.exitButton);
		this.deleteButton = (Button) owner.findViewById(R.editprofile.deleteButton);
		loadProfile();
	}
	
	private void loadProfile()
	{
		list.clear();
		ProfileManager.getInstance().listProfile(list);
		String profileNames[] = new String[list.size()];
		Log.d(field, "Load Profiles: ");
		for(int i = 0; i < list.size(); i++)
		{
			profileNames[i] = (list.get(i)).getDescription();
			Log.d(field, "- Profile Name: " + (list.get(i)).getDescription());
			Log.d(field, "- Profile ID: " + (list.get(i)).getId());
		}
		ArrayAdapter<CharSequence> profileadapter = new ArrayAdapter<CharSequence>(owner, android.R.layout.simple_spinner_item, profileNames);
		name.setAdapter(null);
		name.setAdapter(profileadapter);
	}
	
	private int getProfileID(String profileName)
	{
		for(int i = 0; i < list.size(); i++)
		{
			if(profileName.equals((list.get(i)).getDescription()))
			{
				return (list.get(i)).getId();
			}
		}
		return -1;
	}
	
	private int getProfileVolume(String profileName)
	{
		for(int i = 0; i < list.size(); i++)
		{
			if(profileName.equals((list.get(i)).getDescription()))
			{
				return (list.get(i)).getVolume();
			}
		}
		return -1;
	}
		
	private boolean getProfileVibrate(String profileName)
	{
		for(int i = 0; i < list.size(); i++)
		{
			if(profileName.equals((list.get(i)).getDescription()))
			{
				return (list.get(i)).getVibrate();
			}
		}
		return false;
	}
}

