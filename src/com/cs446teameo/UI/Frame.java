package com.cs446teameo.UI;

import com.cs446teameo.Main.R;
import com.cs446teameo.Parameter.ErrorCode;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;

public abstract class Frame{
	
	protected static Activity owner = null;
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
