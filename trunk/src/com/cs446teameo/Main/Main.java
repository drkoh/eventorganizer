package com.cs446teameo.Main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.cs446teameo.Backend.*;
import com.cs446teameo.Storage.*;
import com.cs446teameo.UI.Frame;
import com.cs446teameo.UI.MenuUI;

public class Main extends Activity {
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Frame frame = Frame.getInstance();
        frame.setActivity(this);
        frame.ContextSwitch(R.layout.menu);
        MenuUI.cast();
        frame.init();
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