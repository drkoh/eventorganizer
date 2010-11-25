package com.cs446teameo.Event;

import android.util.Log;
import android.media.AudioManager;

public class StatusSetting extends EField{
	private String status;
	private int statusCode;
	
	public StatusSetting(String status){
		this.status = status;
		setStatusCode();
	}
	
	public int getStatus(){
		return statusCode;
	}
	
	private void setStatusCode()
	{
		if(status.equals("Default"))
		{
			statusCode = AudioManager.RINGER_MODE_NORMAL;
		}
		else if (status.equals("Vibrate")) 
		{
			statusCode = AudioManager.RINGER_MODE_VIBRATE;
		} 
		else if (status.equals("Silent")) 
		{
			statusCode = AudioManager.RINGER_MODE_SILENT;
		} 
		else if (status.equals("Low")) 
		{
			statusCode = AudioManager.ADJUST_LOWER;
		} 
		else if (status.equals("Medium")) 
		{
			statusCode = AudioManager.RINGER_MODE_NORMAL;
		} 
		else if (status.equals("High")) 
		{
			statusCode = AudioManager.ADJUST_RAISE;
		} 
		else 
		{
			Log.e("Status", "Unspported status String");
		}
	}	
}
