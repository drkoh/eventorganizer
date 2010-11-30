package com.cs446teameo.Profile;

import java.util.ArrayList;
import java.util.List;

import com.cs446teameo.Event.Event;
import com.cs446teameo.Evfac.EventManager;
import com.cs446teameo.Parameter.ErrorCode;
import com.cs446teameo.Storage.Database;

import android.app.Activity;
import android.content.ContentValues;
import android.database.*;
import android.util.Log;
import android.widget.Button;

public class ProfileManager {
	private static ProfileManager _instance = null;
	private static Activity owner = null;
	private Database ebase = null;
	
	public static void setActivity(Activity ownergiven) {
		owner = ownergiven;
	}
	
	private ProfileManager(){
		ebase = Database.open(owner);
	}
	
	public static ProfileManager getInstance(){
		if(_instance == null)
			_instance = new ProfileManager();
		return _instance;
	}
	
	public int createNewProfile(Profile p){
		ContentValues val = new ContentValues();
		val.put(Database.PROFILE_NAME, p.getDescription());
		val.put(Database.PROFILE_VIB, booleanToInt(p.getVibrate()));
		val.put(Database.PROFILE_VOL, p.getVolume());
		ebase.insert(ebase.getProfileTable(), val);
		return 0;
	}
	
	public int deleteProfile(int pId){
		String cond = Database.PROFILE_ID + "=" + pId;
		
		ebase.delete(ebase.getProfileTable(), cond);
		
		/*if (ebase.delete(ebase.getProfileTable(), cond) > 0)
			return ErrorCode.SUCCESS;*/
		return 0;
	}
	
	public Profile getProfile(int pid) {
		Cursor tempCursor = selectProfile("");
		if (tempCursor.getCount() > 0) {
			int id = tempCursor.getInt(tempCursor.getColumnIndex(Database.PROFILE_ID));
			String name = tempCursor.getString(tempCursor.getColumnIndex(Database.PROFILE_NAME));
			int volume = tempCursor.getInt(tempCursor.getColumnIndex(Database.PROFILE_VOL));
			boolean vibrate = intToBoolean(tempCursor.getInt(tempCursor.getColumnIndex(Database.PROFILE_VIB)));
			return new Profile(id, name, vibrate, volume);
		} else {
			return null;
		}
	}
	
	public int updateProfile(Profile p, int pId){
		Log.d("UpdateProfile", "ID: " + p.getId());
		Log.d("UpdateProfile", "Name: " + p.getDescription());
		Log.d("UpdateProfile", "Vibrate: " +  booleanToInt(p.getVibrate()));
		Log.d("UpdateProfile", "Volume: " + p.getVolume());
		String cond = Database.PROFILE_ID + "=" + pId;
		ContentValues val = new ContentValues();	
		val.put(Database.PROFILE_NAME, p.getDescription());
		val.put(Database.PROFILE_VIB, booleanToInt(p.getVibrate()));
		val.put(Database.PROFILE_VOL, p.getVolume());		
		ebase.update(ebase.getProfileTable(), val, cond);
		
		return 0;
	}
	
	public Cursor selectProfile(String cond){
		String sel = "select * from " + ebase.getProfileTable();
		
		if (cond.length() > 0 || cond == null){
			sel = sel + " " + cond;
		}
		
		return ebase.select(sel);
	}
	
	//TODO add a listProfile function
	public int listProfile(ArrayList<Profile> profileList)
	{
		Cursor tempCursor = selectProfile("");
		int cursorSize = tempCursor.getCount();
        if(cursorSize != 0)
        {
    		Log.i("ProfileManager","Cursor size != 0");
    		while (tempCursor.moveToNext())
    		{
	            // Get the field values of each profile
	            int pid = tempCursor.getInt(tempCursor.getColumnIndex(Database.PROFILE_ID));
	            String name = tempCursor.getString(tempCursor.getColumnIndex(Database.PROFILE_NAME));
	            int volume = tempCursor.getInt(tempCursor.getColumnIndex(Database.PROFILE_VOL));
	            boolean vibrate = intToBoolean(tempCursor.getInt(tempCursor.getColumnIndex(Database.PROFILE_VIB)));
	            Profile tempProfile = new Profile(pid, name, vibrate, volume);
	            profileList.add(tempProfile);
	        } 
    		return 0;
        }
		return -1;
	}

	
	// Integer to boolean converter (SQLLite doesn't have BOOLEAN!)
	public static boolean intToBoolean(int value)
	{
		  return (value != 0);
	}
	
	// Boolean to integer converter (SQLLite doesn't have BOOLEAN!)
	public static int booleanToInt(boolean value)
	{
		if(value == true)
		{
			return 1;
		}
		return 0;
	}
	
}
