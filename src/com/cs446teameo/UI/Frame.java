package com.cs446teameo.UI;

import com.cs446teameo.Parameter.ErrorCode;

import android.app.Activity;
import android.content.Context;
import android.view.View;

public class Frame{
	
	protected static Frame _instance= null;
	

	Activity owner = null;
	View frameView = null;
	
	String field = null;
	
	protected Frame(String field) {
		this.field = field;
		// TODO Auto-generated constructor stub
	}
	
	public static Frame getInstance(){
		if(_instance == null)
			_instance = new Frame("abstract frame");
		return _instance;
	}
	public void setActivity(Activity owner){
		this.owner = owner;
	}
	protected void registeListener(){}
	protected void registeComponent(){}
	public void init(){
		registeListener();
		registeComponent();
	}
	
	public int ContextSwitch(int serial){
		frameView = owner.findViewById(serial);
		if(frameView == null)
			return ErrorCode.CONTEXT_SWITCH_ERROR;
		owner.setContentView(serial);
		return ErrorCode.SUCCESS;
	}
	public static void cast(){}
}
