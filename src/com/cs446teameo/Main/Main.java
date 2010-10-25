package com.cs446teameo.Main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.style.SuperscriptSpan;
import android.util.Log;

import com.cs446teameo.Backend.*;
import com.cs446teameo.Parameter.ErrorCode;
import com.cs446teameo.Storage.*;
import com.cs446teameo.UI.EventAdderUI;
import com.cs446teameo.UI.Frame;
import com.cs446teameo.UI.MenuUI;

public class Main extends Activity {
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("www","10");
        Frame.setActivity(this);
        MenuUI.contextSwitch();
        Log.e("www","2");
    }
    
    
	public static void main(String[] args){
		return;
	}
}