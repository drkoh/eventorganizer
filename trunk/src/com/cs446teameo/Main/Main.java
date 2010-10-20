package com.cs446teameo.Main;

import android.app.Activity;
//import android.content.Context;
import android.os.Bundle;
//import com.cs446teameo.Backend.*;

public class Main extends Activity {
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //getApplicationContext().startService(getIntent(BG));
    }

	public static void main(String[] args){
		return;
	}
}