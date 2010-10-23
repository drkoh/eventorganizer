package com.cs446teameo.Main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.cs446teameo.Backend.*;
import com.cs446teameo.Storage.*;
import com.cs446teameo.UI.Frame;
import com.cs446teameo.UI.MenuUI;

public class Main extends Activity {
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MenuUI.refreshInstance(this);
        Log.e("www","1");
     //   Frame.getInstance().ContextSwitch(R.layout.menu);
     //   Log.e("www","2");
     //   Frame.getInstance().init();
     //   Log.e("www","3");
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