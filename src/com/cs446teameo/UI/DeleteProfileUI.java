package com.cs446teameo.UI;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

import com.cs446teameo.Main.R;

public class DeleteProfileUI extends Frame{

	Spinner profile = null;
	Button deleteButton = null;
	Button exitButton = null;
	
	private static DeleteProfileUI _instance = null;
	
	private DeleteProfileUI() {
		super("DeleteProfile");
		// TODO Auto-generated constructor stub
	}
	
	public static void contextSwitch(){
		if(_instance == null){
			_instance = new DeleteProfileUI();
		}
		owner.setContentView(R.layout.deleteprofile);
		_instance.init();
	}

	@Override
	public void registeListener() {
		// TODO Auto-generated method stub
		
		// Create Button
		deleteButton.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View arg0) {
				// TODO!!!
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
		
		this.profile = (Spinner) owner.findViewById(R.deleteprofile.profile);
		this.deleteButton = (Button) owner.findViewById(R.deleteprofile.deleteButton);
		this.exitButton = (Button) owner.findViewById(R.deleteprofile.exitButton);		
	}
}
