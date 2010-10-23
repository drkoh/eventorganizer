package com.cs446teameo.UI;

import com.cs446teameo.Main.R;
import com.cs446teameo.Parameter.ErrorCode;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;

public abstract class Frame{
	
	protected Activity owner = null;
	protected String field = null;
	protected int serial;
	
	protected Frame(Activity owner,int serial,String field) {
		this.field = field;
		this.owner = owner;
		this.serial = serial;
		// TODO Auto-generated constructor stub
	}
	protected abstract void registeListener();
	protected abstract void registeComponent();
	
	public void init(){
		registeComponent();
		registeListener();
	}
}
