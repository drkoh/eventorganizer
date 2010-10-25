package com.cs446teameo.Main;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

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
}