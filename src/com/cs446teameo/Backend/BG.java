package com.cs446teameo.Backend;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.IBinder;
import java.util.TimerTask;

public class BG extends Service {
	private AudioManager manager;
	private Context context;
	
	@Override
	public void onCreate() {
		context = getApplicationContext();
        manager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        manager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
}