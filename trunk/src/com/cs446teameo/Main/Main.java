package com.cs446teameo.Main;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.content.*;
import android.database.Cursor;

import com.cs446teameo.Backend.*;
import com.cs446teameo.Event.Event;
import com.cs446teameo.Evfac.EventManager;
import com.cs446teameo.Profile.Profile;
import com.cs446teameo.Profile.ProfileManager;
import com.cs446teameo.UI.EventAdderUI;
import com.cs446teameo.UI.Frame;
import com.cs446teameo.UI.MenuUI;
import com.cs446teameo.Event.*;

public class Main extends Activity{
	public static ArrayList<Event> sharing = new ArrayList<Event>(); 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("www","10");
        Frame.setActivity(this);
        Log.e("www","2");
        Intent bgtimer = new Intent(this, BG.class);
        EventManager.setActivity(this);
        ProfileManager.setActivity(this);
        this.startService(bgtimer);
        MenuUI.contextSwitch();
    }
}
