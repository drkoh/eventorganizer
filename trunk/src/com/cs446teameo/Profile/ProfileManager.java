package com.cs446teameo.Profile;

import java.util.ArrayList;
import java.util.List;

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

	
	
	//TODO: Culvin, I set the id to be -1 now, coz I cannot get the id to be allocated.
	//I think It is done inside the database, YOu can check for the available id.
	
	
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
		
		/*if (ebase.insert(ebase.getProfileTable(), val) > 0)
			return ErrorCode.SUCCESS;*/
		return 0;
	}
	
	public int deleteProfile(int pId){
		String cond = Database.PROFILE_ID + "=" + pId;
		
		ebase.delete(ebase.getProfileTable(), cond);
		
		/*if (ebase.delete(ebase.getProfileTable(), cond) > 0)
			return ErrorCode.SUCCESS;*/
		return 0;
	}
	
	public Profile getProfie(String pname) {
		Cursor tempCursor = selectProfile("");
		if (tempCursor.getCount() > 0) {
			String name = tempCursor.getString(tempCursor.getColumnIndex(Database.PROFILE_NAME));
			int volume = tempCursor.getInt(tempCursor.getColumnIndex(Database.PROFILE_VOL));
			boolean vibrate = intToBoolean(tempCursor.getInt(tempCursor.getColumnIndex(Database.PROFILE_VIB)));
			return new Profile(name, vibrate, volume);
		} else {
			return null;
		}
	}
	
	public int updateProfile(Profile p, int pId){
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
			sel = sel + " where " + cond;
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
	            String name = tempCursor.getString(tempCursor.getColumnIndex(Database.PROFILE_NAME));
	            int volume = tempCursor.getInt(tempCursor.getColumnIndex(Database.PROFILE_VOL));
	            boolean vibrate = intToBoolean(tempCursor.getInt(tempCursor.getColumnIndex(Database.PROFILE_VIB)));
	            Profile tempProfile = new Profile(name, vibrate, volume);
	            profileList.add(tempProfile);
	        } 
    		return 0;
        }
		return -1;
	}
	
	// Simply list all the profile names into a string list
	public String[] listProfileNames()
	{
		ArrayList<Profile> list = new ArrayList<Profile>();
		listProfile(list);
		String profileNames[] = new String[list.size()];
		for(int i = 0; i < list.size(); i++)
		{
			profileNames[i] = (list.get(i)).getDescription();
		}
		return profileNames;
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
