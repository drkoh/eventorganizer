package com.cs446teameo.UI;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

import com.cs446teameo.Main.R;

public class ProfileUI extends Frame{

	Button createButton = null;
	Button editButton = null;
	Button exitButton = null;
	
	private static ProfileUI _instance = null;
	
	private ProfileUI() {
		super("Profile");
		// TODO Auto-generated constructor stub
	}
	
	public static void contextSwitch(){
		if(_instance == null){
			_instance = new ProfileUI();
		}
		owner.setContentView(R.layout.profile);
		_instance.init();
	}

	@Override
	public void registeListener() {
		// TODO Auto-generated method stub
		
		// Create Button
		createButton.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View arg0) {
				// TODO!!!
				Log.i(field,"trigger create button");
				CreateProfileUI.contextSwitch();
			}
		});
		
		// Create Button
		editButton.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View arg0) {
				// TODO!!!
				Log.i(field,"trigger create button");
				EditProfileUI.contextSwitch();
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

		this.createButton = (Button) owner.findViewById(R.profile.createButton);
		this.editButton = (Button) owner.findViewById(R.profile.editButton);
		this.exitButton = (Button) owner.findViewById(R.profile.exitButton);		
	}
}
