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
        Intent bgtimer = new Intent(this, BG.class);
        EventManager.setActivity(this);
        this.startService(bgtimer);
        
        Log.e("www","10");
        Frame.setActivity(this);
        MenuUI.contextSwitch();
        Log.e("www","2");
        
        /* This crashes and I don't know why? */
        // Initialize a few profiles with initial values
		/*
		Profile profile1 = new Profile("Normal", false, 50);
		ProfileManager.getInstance().createNewProfile(profile1);
		Profile profile2 = new Profile("Silent", false, 0);
		ProfileManager.getInstance().createNewProfile(profile2);
		Profile profile3 = new Profile("Vibrate", true, 0);
		ProfileManager.getInstance().createNewProfile(profile3);
		Profile profile4 = new Profile("Low", false, 25);
		ProfileManager.getInstance().createNewProfile(profile4);
		Profile profile5 = new Profile("High", false, 75);
		ProfileManager.getInstance().createNewProfile(profile5);
		*/
    }
}
