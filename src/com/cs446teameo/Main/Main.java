package com.cs446teameo.Main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.cs446teameo.Backend.*;
import com.cs446teameo.Storage.*;

public class Main extends Activity {
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Intent bgtimer = new Intent(this, BG.class);
        startService(bgtimer);
        stopService(bgtimer);
        EventDatabase db = new EventDatabase(this);
        db.open();
    }

	public static void main(String[] args){
		return;
	}
}