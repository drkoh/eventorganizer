package com.cs446teameo.Backend;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.IBinder;
import android.util.Log;

import java.util.TimerTask;
import java.util.Timer;

public class BG extends Service {
	
	@Override
	public void onCreate() {
		TimerTask vibaret = new StatusChange();
		Timer timer = new Timer();
		timer.schedule(vibaret, 5000);
		Log.d("sd", "here wait 5sec");
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	class StatusChange extends TimerTask {
		private AudioManager manager;
		private Context context;
		
		@Override
		public void run(){
			context = getApplicationContext();
	        manager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
	        manager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
		}
	}
}