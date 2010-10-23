package com.cs446teameo.UI;

import com.cs446teameo.Main.R;
import com.cs446teameo.Parameter.ErrorCode;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;

public abstract class Frame{
	
	protected static Frame _instance = null;
	

	Activity owner = null;
	View frameView = null;
	String field = null;
	
	protected Frame(Activity activity,String field) {
		this.field = field;
		this.owner = activity;
		// TODO Auto-generated constructor stub
	}
	
	public static Frame getInstance(){
		return _instance;
	}
	
	public void cast(int serial){
		if(serial == R.layout.menu){
			MenuUI.refreshInstance(owner);
		}else if(serial == R.layout.eventadder){
			EventAdderUI.refreshInstance(owner);
		}else{
			Log.e(field, "error occur");
		}
	}
	
	public void setActivity(Activity owner){
		this.owner = owner;
	}
	
	protected abstract void registeListener();
	protected abstract void registeComponent();
	public void init(){
		registeListener();
		registeComponent();
	}
	
	public int ContextSwitch(int serial){
		cast(serial);
		frameView = owner.findViewById(serial);
		if(frameView == null){
			Log.e(field,"context switch error");
			return ErrorCode.CONTEXT_SWITCH_ERROR;
		}
		owner.setContentView(serial);
		return ErrorCode.SUCCESS;
	}
}
