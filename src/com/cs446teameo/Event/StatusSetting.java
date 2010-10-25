package com.cs446teameo.Event;

public class StatusSetting extends EField{
	String status;
	
	public StatusSetting(String status){
		this.status = status;
	}

	public StatusSetting(boolean s) {
		if(s)
			status = "silent";
		else
			status = "default";
		// TODO Auto-generated constructor stub
	}
}
