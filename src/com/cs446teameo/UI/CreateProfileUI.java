package com.cs446teameo.UI;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

import com.cs446teameo.Main.R;

public class CreateProfileUI extends Frame{

	EditText name = null;
	Spinner volume = null;
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
		clearButton.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View arg0) {
				// TODO!!!
				Log.i(field,"trigger clear button");
				CreateProfileUI.contextSwitch();
			}
		});
		
		// Create Button
		createButton.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View arg0) {
				// TODO!!!
				Log.i(field,"trigger create button");
				MenuUI.contextSwitch();
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
		this.name = (EditText) owner.findViewById(R.createprofile.name);
		this.volume = (Spinner) owner.findViewById(R.createprofile.volume);
		this.vibrate = (CheckBox) owner.findViewById(R.createprofile.vibrate);
		this.clearButton = (Button) owner.findViewById(R.createprofile.clearButton);
		this.createButton = (Button) owner.findViewById(R.createprofile.createButton);
		this.exitButton = (Button) owner.findViewById(R.createprofile.exitButton);	
	}

}
