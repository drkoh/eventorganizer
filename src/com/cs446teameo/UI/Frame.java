package com.cs446teameo.UI;

import android.app.Activity;
import android.content.Context;
import android.view.View;

public abstract class Frame{

	Activity owner = null;
	View frameView = null;
	
	String field = null;
	
	public Frame(Activity owner,View parent,String field) {
		this.owner = owner;
		this.frameView = parent;
		this.field = field;
		// TODO Auto-generated constructor stub
	}
	
	public abstract void registeListener();
	public abstract void registeComponent();
}
