package com.cs446teameo.UI;

import java.util.ArrayList;

import com.cs446teameo.Main.R;
import com.cs446teameo.Profile.Profile;
import com.cs446teameo.Profile.ProfileManager;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MenuUI extends Frame{

	Button AddEventButton = null;
	Button CalendarButton = null;
	Button ProfileButton = null;
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
		Log.i("field", "switch to menu");
		owner.setContentView(R.layout.menu);
		_instance.init();
	}
	
	
	@Override
	public void registeListener() {
		// TODO Auto-generated method stub
		this.AddEventButton.setOnClickListener((new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Log.i("field", "trigger the click listener");
				EventAdderUI.contextSwitch();
				return;
			}
		}));
		
		this.CalendarButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Log.i("field","trigger the calender button");
				DailyCalendarUI.contextSwitch();
				return;
			}
		});
		
		this.ProfileButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Log.i(field,"trigger the  profile button");
				ProfileUI.contextSwitch();
				return;
			}
		});
		
		this.ExitButton.setOnClickListener((new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Log.i("field", "trigger the exitButton");
				owner.finish();
			}
		}));
		
		this.SynchronizeButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Log.i("field","trigger the synchronize Button");
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
		this.ProfileButton = (Button)owner.findViewById(R.menuId.ProfileButton);
		this.ExitButton = (Button)owner.findViewById(R.menuId.ExitButton);
		this.SynchronizeButton = (Button)owner.findViewById(R.menuId.SynchronizeButton);
		SynchronizeButton.setVisibility(View.GONE);
		if(this.AddEventButton == null){
			Log.e("field", "reg error");
		}
		loadDefaultProfiles();
	}
	
	private void loadDefaultProfiles()
	{
		ArrayList<Profile> profileList = new ArrayList<Profile>();
		profileList.clear();
		ProfileManager tempPManager = ProfileManager.getInstance();
		int res = tempPManager.listProfile(profileList);
		if(res == -1)
		{
			Profile profile1 = new Profile("Default", false, 50);
			ProfileManager.getInstance().createNewProfile(profile1);
			Profile profile2 = new Profile("Silent", false, 0);
			ProfileManager.getInstance().createNewProfile(profile2);
			Profile profile3 = new Profile("Vibrate", true, 0);
			ProfileManager.getInstance().createNewProfile(profile3);
		}
	}
}
