package com.cs446teameo.Backend;

import android.app.*;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.*;
import android.util.Log;

import java.util.*;

import com.cs446teameo.Event.*;
import com.cs446teameo.Evfac.*;
import com.cs446teameo.Profile.*;
import com.cs446teameo.repeater.*;

public class BG extends Service {
	
	Event currentEvent = null;
	boolean currentStatus = false;
	EventManager EM;
	ProfileManager PM;
	HashMap<Integer, Event> emap = null;
	HashMap<Integer, Timer> timerMap = null;
	ArrayList<Event> tmpelist = null;
	Driver RD = null;
	AudioManager audioManager;
	Context context;
	NotificationManager mNotificationManager;
	
	public class LocalBinder extends Binder {
        public BG getService() {
            return BG.this;
        }
    }
	
	private void gatherEvents(Intent intent) {
		if (intent == null) {
			tmpelist = EM.allEvents();
			Log.i("bg", "gatherEvents intent is null");
		} else {
			//assuming there is only 1 id in intent
			tmpelist = new ArrayList<Event>();
			tmpelist.add(EM.getEvent(Integer.parseInt(intent.getDataString())));
		}
		
		if (tmpelist.size() != 0) {
			Log.i("bg", "got data");
		} else {
			Log.i("bg", "no data");
		}
	}
	
	private void setEvents(Intent intent) {
		if (tmpelist == null) return;
		
		for (int i=0; i < tmpelist.size(); i++) {
			
			Event currEvt = tmpelist.get(i);
			int eid = currEvt.getEid();
					
			Log.i("bg", "working on "+eid);
			Log.i("bg", "repeat text of "+tmpelist.get(i).getRepeatText());
			RD.setString(tmpelist.get(i).getRepeatText());
			
			TimeSet tmp = null;
			try {
				tmp = RD.parse();
				//Log.i("trig", ""+tmp);
				//Log.i("trig", ((Boolean)(tmp == null)).toString());
				//Log.i("trig", "instance:" + ((Boolean)(tmp instanceof RepeatSet)).toString());
				//Log.i("trig","time:" + (((Boolean)(((RepeatSet)(tmp)).time == null)).toString()));
			} catch (Exception e) {
				Log.i("bg", "repeater error: "+e.toString());
			}
			
			if (tmp == null) {
				Log.i("bg", "tmp is null will quite function");
				continue; //return since the event does not exist anymore
			}
			Profile prof = PM.getProfile(1);
			
			GregorianCalendar cal = tmp.nextTrigger();
			if (cal == null) continue;
			Log.i("bg", "setting the events******************");
			
			Timer schEvent = new Timer();
			timerMap.put(eid, schEvent);
			emap.put(tmpelist.get(i).getEid(), tmpelist.get(i));
			//set the start event
			TimerTask start = new StatusChange(eid);
			Log.i("bg", "starttime: "+tmp.nextTrigger().getTime().toLocaleString());
			schEvent.schedule(start, tmp.nextTrigger().getTime());
			
			//Set the end time event
			TimerTask end = new changeDefault(1, eid, tmp);
			Log.i("bg", "endtime: "+tmp.nextEndtime().getTime().toLocaleString());
			schEvent.schedule(end, tmp.nextEndtime().getTime());
		}
	}
	
	private void removeEvent(Intent intent) {
		int id = Integer.parseInt(intent.getDataString());
		Timer t = timerMap.get(id);
		if (t != null) {
			t.cancel();
			timerMap.remove(id);
			emap.remove(id);
		}
	}
	
	private void modifyEvent(Intent intent) {
		removeEvent(intent);
		gatherEvents(intent);
		setEvents(intent);
	}
	
	public void notifyME(Intent intent) {
		Log.i("bg", "notify recived");
		if (intent.getAction().equals("ACTION_NEW_EVENT")) {
			gatherEvents(intent);
			setEvents(intent);
		} else if (intent.getAction().equals("ACTION_DELETE_EVENT")) {
			removeEvent(intent);
		} else if (intent.getAction().equals("ACTION_UPDATE_EVENT")) {
			modifyEvent(intent);
		}
	}
	
	@Override
	public void onCreate() {
		emap = new HashMap<Integer, Event>();
		timerMap = new HashMap<Integer, Timer>();
		//pull the data from the database and store them into some global array
		EM = EventManager.getInstance();
		PM = ProfileManager.getInstance();
		try {
			RD = Driver.getInstance();
		} catch (Exception e) {
			Log.i("bg", "Error repeater: "+e.toString());
		}
		context = getApplicationContext();
		String ns = Context.NOTIFICATION_SERVICE;
		mNotificationManager = (NotificationManager) getSystemService(ns);
		
		audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
		Log.i("bg", "end create");
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		//schedule what ever is pulled out of the database
		gatherEvents(null);
		setEvents(null);
		Log.i("bg", "start onStartCommand");
		Log.i("bg", "NO. Events " + emap.size());
		return START_STICKY;
	}
	
	private final IBinder mBinder = new LocalBinder();
	
	@Override
	public IBinder onBind(Intent intent) {
		//binding only used for notification
		Log.i("bg", "binding done: "+intent.getDataString());
		notifyME(intent);
		return mBinder;
	}
	
	@Override
	public void onRebind(Intent intent) {
		//binding only used for notification
		Log.i("bg", "binding done: "+intent.getDataString());
		notifyME(intent);
	}
	
	public void onDestory() {
		Log.i("bg", "BG killed");
	}
	
	private int volscale(int vol, int stype) {
		return (vol * audioManager.getStreamMaxVolume(stype))/100;
	}
	
	private void setProfile(int volume, boolean vibrate, String profName)
	{
		Notification notification = new Notification(Notification.DEFAULT_ALL, "EO Notification", 0);
		CharSequence contentTitle = "EO Notification";
		CharSequence contentText = "Phone profile changed to "+profName;
		Intent notificationIntent = new Intent(this, BG.class);
		PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
		notification.setLatestEventInfo(context, contentTitle, contentText, contentIntent);
		
		mNotificationManager.notify(1, notification);
		
		Log.i("bg", "setProfile: volume is:"+volume+"; vibrate is:"+vibrate);
		// Set all of their volumes to be same (notify them with a vibration)
		audioManager.setStreamVolume (AudioManager.STREAM_VOICE_CALL, volscale(volume, AudioManager.STREAM_VOICE_CALL), AudioManager.FLAG_VIBRATE);
		audioManager.setStreamVolume (AudioManager.STREAM_SYSTEM, volscale(volume, AudioManager.STREAM_SYSTEM), AudioManager.FLAG_VIBRATE);
		audioManager.setStreamVolume (AudioManager.STREAM_RING, volscale(volume, AudioManager.STREAM_RING), AudioManager.FLAG_VIBRATE);
		audioManager.setStreamVolume (AudioManager.STREAM_ALARM, volscale(volume, AudioManager.STREAM_ALARM), AudioManager.FLAG_VIBRATE);

		// Set all things to be vibrate
		if(vibrate == true)
		{
			audioManager.setVibrateSetting (AudioManager.VIBRATE_TYPE_NOTIFICATION, AudioManager.VIBRATE_SETTING_ON);
			audioManager.setVibrateSetting (AudioManager.VIBRATE_TYPE_RINGER, AudioManager.VIBRATE_SETTING_ON);
		} else {
			audioManager.setVibrateSetting (AudioManager.VIBRATE_TYPE_NOTIFICATION, AudioManager.VIBRATE_SETTING_OFF);
			audioManager.setVibrateSetting (AudioManager.VIBRATE_TYPE_RINGER, AudioManager.VIBRATE_SETTING_OFF);
		}
	}
	
	class StatusChange extends TimerTask {
		private int eventid;

		StatusChange(int id){
			Log.i("bg", "statusChange created******************");
			this.eventid = id;
		}
		
		public void run() {
			Profile prof = PM.getProfile(emap.get(eventid).getPid());
			Log.i("bg", "statusChange running******************");
			setProfile(prof.getVolume(), prof.getVibrate(), prof.getDescription());
		}
	}
	
	class changeDefault extends TimerTask {
		int pid;
		int eid;
		TimeSet time;
		
		changeDefault(int defaultpid, int id, TimeSet time){
			Log.i("bg", "changeDefault created******************");
			this.pid = defaultpid;
			this.eid = id;
			this.time = time;
		}
		
		public void run() {
			Log.i("bg", "statusChange running******************");
			Profile prof = PM.getProfile(pid);
			setProfile(prof.getVolume(), prof.getVibrate(), prof.getDescription());
			GregorianCalendar tmp = time.nextTrigger();
			if (tmp != null) {
				EM.updateEventTime(eid, time.nextTrigger().getTimeInMillis(), time.nextEndtime().getTimeInMillis());
			} else {
				emap.remove(eid);
				timerMap.remove(eid);
			}
		}
	}
}