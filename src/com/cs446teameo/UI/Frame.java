package com.cs446teameo.UI;

import android.app.Activity;

public abstract class Frame{
	
	public static Activity owner = null;
	protected String field = null;
	
	
	public static void setActivity(Activity src){
		owner = src;
	}
	
	protected Frame(String field) {
		this.field = field;
		// TODO Auto-generated constructor stub
	}
	protected abstract void registeListener();
	protected abstract void registeComponent();
	
	public void init(){
		registeComponent();
		registeListener();
	}
}
