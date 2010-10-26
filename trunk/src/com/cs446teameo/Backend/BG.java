package com.cs446teameo.Backend;

import android.app.Notification;

import com.cs446teameo.Main.R;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.IBinder;
import android.os.SystemClock;
import android.provider.SyncStateContract.Constants;
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
		TimerTask vibrate = new StatusChange();
		long delay = 60000 - System.currentTimeMillis() % 60000;
		Timer timer = new Timer();
		timer.schedule(vibrate, delay, 60000);
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
	
	public void popup (String message, int duration)
	{
		Toast.makeText(Frame.owner, message, duration).show();
	}
	
	class StatusChange extends TimerTask {
		private AudioManager manager;
		private Context context;
		private CharSequence contentTitle = "";
		private CharSequence contentText = "";

		@Override
		public void run(){  
			Event src = toTrigger();
			boolean vibrate = false;
			context = getApplicationContext();
	        manager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
			if(src == null){
				if(currentEvent == null)
					return;
				if(currentEvent.time.endTime()/60 == System.currentTimeMillis() / 60000){
					vibrate = false;

					// Notification Starts
					NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
					int icon = R.drawable.icon;
					CharSequence text = "Event Ended, Profile: Default";
					long when = System.currentTimeMillis();
					Intent intent = new Intent(BG.this, StatusChange.class);
					PendingIntent contentIntent = PendingIntent.getActivity(BG.this, 0, intent, 0);
					Notification notification = new Notification(icon, text, when);
					notification.setLatestEventInfo(BG.this, contentTitle, contentText, contentIntent);
					notificationManager.notify(1, notification);
					// Notification Ends
				}
			}else{
				currentEvent = src;
				vibrate = src.st_setting.vibrate();

				// Notification Starts
				NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
				int icon = R.drawable.icon;
				CharSequence text = "Event Started, Profile: Vibrate";
				long when = System.currentTimeMillis();
				Intent intent = new Intent(BG.this, StatusChange.class);
				PendingIntent contentIntent = PendingIntent.getActivity(BG.this, 0, intent, 0);
				Notification notification = new Notification(icon, text, when);
				notification.setLatestEventInfo(BG.this, contentTitle, contentText, contentIntent);
				notificationManager.notify(1, notification);
				// Notification Ends
			}
			if(vibrate == currentStatus)
				return;
	        if(vibrate){
	        	manager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
	        	currentStatus = true;
	        }else{
	        	manager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
	        	currentStatus = false;
	        }
		}
	}
}