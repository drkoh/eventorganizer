package com.cs446teameo.Event;

public class StatusSetting extends EField{
	public String status;
	
	public StatusSetting(String status){
		this.status = status;
	}

	public StatusSetting(boolean s) {
		if(!s)
			status = "silent";
		else
			status = "default";
		// TODO Auto-generated constructor stub
	}
	
	public boolean vibrate(){
		if(status.equals("silent"))
			return false;
		return true;
	}
}
