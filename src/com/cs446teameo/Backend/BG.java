package com.cs446teameo.Backend;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Toast;

import java.util.TimerTask;
import java.util.Timer;

import com.cs446teameo.Event.Event;
import com.cs446teameo.Main.Main;
import com.cs446teameo.UI.Frame;

public class BG extends Service {
	
	Event currentEvent = null;
	boolean currentStatus = false;
	
	@Override
	public void onCreate() {
		TimerTask vibaret = new StatusChange();
		long delay = 60000 - System.currentTimeMillis() % 60000;
		Timer timer = new Timer();
		timer.schedule(vibaret,delay, 60000);
		Log.d("sd", "here wait 5sec");
	}
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Event toTrigger(){
		Log.d("error","checking in the database");
		for(int i = 0 ;i < Main.sharing.size();i++)
			if(Main.sharing.get(i).time.startTime()/60 == System.currentTimeMillis()/60000){
				Log.d("error","got one");
				return Main.sharing.get(i);
			}
		return null;
	}
	
	class StatusChange extends TimerTask {
		private AudioManager manager;
		private Context context;
		@Override
		public void run(){
			Log.d("error","run here");
			Log.d("error","the current time" + System.currentTimeMillis()/1000);
			Event src = toTrigger();
			boolean vibrate = false;
			if(src == null){
				if(currentEvent == null)
					return;
				Log.d("error","@3");
				if(currentEvent.time.endTime()/60 == System.currentTimeMillis() / 60000){
					Log.d("error","@4");
					vibrate = false;
				//	Toast toast = Toast.makeText(getApplicationContext(), "Event End\nEvent Description: "+currentEvent.description.toString() + " \nProfile: "+currentEvent.st_setting.status, Toast.LENGTH_LONG);
				//	toast.show();
				}
			}else{
				currentEvent = src;
				Log.d("error","@0");
				vibrate = src.st_setting.vibrate();
				Log.d("error","@1");
		//		Toast toast = Toast.makeText(getApplicationContext(), "Event Start\nEvent Description: "+src.description.toString() + "\nProfile: "+src.st_setting.status, Toast.LENGTH_LONG);
		//		toast.show();
			}
			if(vibrate == currentStatus)
				return;
			Log.d("error","@5");
			context = getApplicationContext();
	        manager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
	        Log.d("error","@6");
	        if(vibrate){
	        	manager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
	        	currentStatus = true;
	        }else{
	        	manager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
	        	currentStatus = false;
	        }
	        Log.d("error","@7");
		}
	}
}