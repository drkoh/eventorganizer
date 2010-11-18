package com.cs446teameo.Profile;

import java.io.Serializable;

public class Profile implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2789089601153968331L;
	
	public static final short SHUTDOWN = 0;
	
	private int pId;
	private String description;
	private boolean vibrate;
	private int volume;
	//short serial;
	
	//to add in more features here
	/*public Profile(String description,int serial){
		
	}*/
	
	public Profile(String desc, boolean vib, int vol){
		this.description = desc;
		this.vibrate = vib;
		this.volume = vol;
	}
	
	public int getId(){
		return pId;
	}
	
	public void setDescription(String desc){
		description = desc;
	}
	
	public String getDescription(){
		return description;
	}
	
	public void setVibrate(boolean vib){
		vibrate = vib;
	}
	
	public boolean getVibrate(){
		return vibrate;
	}
	
	public void setVolume(int vol){
		volume = vol;
	}
	
	public int getVolume(){
		return volume;
	}
}
