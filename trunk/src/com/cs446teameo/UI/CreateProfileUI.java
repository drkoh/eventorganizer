package com.cs446teameo.UI;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

import com.cs446teameo.Main.R;
import com.cs446teameo.Parameter.ErrorCode;
import com.cs446teameo.Profile.Profile;
import com.cs446teameo.Profile.ProfileManager;

public class CreateProfileUI extends Frame{

	EditText name = null;
	SeekBar volume = null;
	TextView volumeValue = null;
	CheckBox vibrate = null;
	Button clearButton = null;
	Button exitButton = null;
	Button createButton = null;
	
	private static CreateProfileUI _instance = null;
	
	private CreateProfileUI() {
		super("CreateProfile");
		// TODO Auto-generated constructor stub
	}
	
	public static void contextSwitch(){
		if(_instance == null){
			_instance = new CreateProfileUI();
		}
		owner.setContentView(R.layout.createprofile);
		_instance.init();
	}

	@Override
	public void registeListener() {
		// TODO Auto-generated method stub
		
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
				name.setText("");
				volume.setProgress(50);
				volumeValue.setText(" 50");
				vibrate.setChecked(true);
				Log.i(field,"trigger clear button");
				CreateProfileUI.contextSwitch();
				
			}
		});
		
		// Create Button
		createButton.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View arg0) {
				Profile profile = new Profile(name.getText().toString(),vibrate.isChecked(),volume.getProgress());
				int res = ProfileManager.getInstance().createNewProfile(profile);
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
		this.name = (EditText) owner.findViewById(R.createprofile.name);
		this.name.setSingleLine();
		this.volume = (SeekBar) owner.findViewById(R.createprofile.volume);
		this.volumeValue = (TextView) owner.findViewById(R.createprofile.volumevalue);
		this.vibrate = (CheckBox) owner.findViewById(R.createprofile.vibrate);
		this.clearButton = (Button) owner.findViewById(R.createprofile.clearButton);
		this.createButton = (Button) owner.findViewById(R.createprofile.createButton);
		this.exitButton = (Button) owner.findViewById(R.createprofile.exitButton);	
	}

}
