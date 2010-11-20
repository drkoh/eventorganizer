package com.cs446teameo.Main;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.content.*;

import com.cs446teameo.Backend.BG;
import com.cs446teameo.Event.Event;
import com.cs446teameo.UI.EventAdderUI;
import com.cs446teameo.UI.Frame;
import com.cs446teameo.UI.MenuUI;

public class Main extends Activity{
	public static ArrayList<Event> sharing = new ArrayList<Event>(); 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("www","10");
        Frame.setActivity(this);
        MenuUI.contextSwitch();
        Intent intent = new Intent(this,BG.class);
        super.startService(intent);
        super.stopService(intent);
        Log.e("www","2");
        Intent bgtimer = new Intent(this, BG.class);
        startService(bgtimer);
        stopService(bgtimer);
    }
}
