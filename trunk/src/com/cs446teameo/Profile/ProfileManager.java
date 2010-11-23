package com.cs446teameo.Profile;

import java.util.ArrayList;

import com.cs446teameo.Parameter.ErrorCode;
import com.cs446teameo.Storage.Database;

import android.app.Activity;
import android.content.ContentValues;
import android.database.*;

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
		val.put(Database.PROFILE_VIB, p.getVibrate());
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
	
	public int updateProfile(Profile p, int pId){
		String cond = Database.PROFILE_ID + "=" + pId;
		ContentValues val = new ContentValues();
		
		val.put(Database.PROFILE_NAME, p.getDescription());
		val.put(Database.PROFILE_VIB, p.getVibrate());
		val.put(Database.PROFILE_VOL, p.getVolume());
		
		ebase.update(ebase.getProfileTable(), val, cond);
		
		/*if (ebase.update(ebase.getProfileTable(), val, cond) > 0)
			return ErrorCode.SUCCESS;*/
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
	public int listProfile(ArrayList<Profile> list){
		//list all profiles in the database into 'list'
		return 0;
	}
	
}
