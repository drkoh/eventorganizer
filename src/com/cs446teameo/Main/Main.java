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
       // MenuUI.refreshInstance();
       // Frame.setActivity(this);
       // Frame frame = Frame.getInstance();
       // Log.e("www","1");
      //  if(super.findViewById(R.layout.menu) == null)
      //  	Log.e("www","-1");
        Log.e("www","10");
        Frame.setActivity(this);
        MenuUI.contextSwitch();
      //  Log.e("www","9");
        //this.addContentView(R.layout.eventadder, params)
       // EventAdderUI eui = new EventAdderUI(this);
       // eui.init();
      //  Frame.getInstance().ContextSwitch(R.layout.menu);
        Log.e("www","2");
      //  Menu menu = new Menu(this,this.findViewById(R.layout.menu));
     //   menu.registeComponent();
      //  menu.registeListener();
   //     Intent bgtimer = new Intent(this, BG.class);
   //     startService(bgtimer);
   //     stopService(bgtimer);
   //     EventDatabase db = new EventDatabase(this);
   //     db.open();
    }
    
    
	public static void main(String[] args){
		return;
	}
}