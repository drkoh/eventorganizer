package com.cs446teameo.Main;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.media.AudioManager;

public class Main extends Activity {
	private AudioManager manager;
	private Context context;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getApplicationContext();
        manager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        manager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
        setContentView(R.layout.main);
    }

	public static void main(String[] args){
		return;
	}
}