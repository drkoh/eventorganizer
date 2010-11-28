package com.cs446teameo.Backend;

import android.app.*;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.*;
import android.provider.SyncStateContract.Constants;
import android.util.Log;

import java.util.*;

import com.cs446teameo.Event.*;
import com.cs446teameo.UI.*;
import com.cs446teameo.Evfac.*;

public class BG extends Service {
	
	Event currentEvent = null;
	boolean currentStatus = false;
	EventManager EM;
	HashMap<Integer ,Event> emap = null;
	Timer schEvent = new Timer();
	
	public class LocalBinder extends Binder {
        public BG getService() {
            return BG.this;
        }
    }
	
	private void gatherEvents(Intent intent) {
		ArrayList<Event> elist = new ArrayList();
		if (intent == null) {
			EM.listEvent(elist);
			Log.i("bg", "gatherEvents intent is null");
		} else {
			//assuming there is only 1 id in intent
		}
		emap = new HashMap();
		if (elist.size() != 0) {
			Log.i("bg", "got data");
		} else {
			Log.i("bg", "no data");
		}
		for (int i=0; i < elist.size(); i++) {
			Log.i("bg", "saving eid: "+elist.get(i).getEid());
			emap.put(elist.get(i).getEid(), elist.get(i));
		}
	}
	
	private void setEvents(Intent intent) {
		Iterator<Integer> keys = emap.keySet().iterator();
		
		while(true) {
			if (!keys.hasNext()) break;
			int k = keys.next();
			Log.i("bg", "work "+k);
			
			TimerTask start = new StatusChange(k);
			schEvent.schedule(start, new Date(emap.get(k).getStartTime()));
			//TimerTask end = new StatusChange(k);
			//schEvent.schedule(end, new Date(emap.get(k).getStartTime()));
		}
	}
	
	public void notifyME(Intent intent) {
		Log.i("bg", "notify recived");
		gatherEvents(intent);
		setEvents(intent);
	}
	
	@Override
	public void onCreate() {
		//pull the data from the database and store them into some global array
		EM = EventManager.getInstance();
		gatherEvents(null);
		setEvents(null);
		Log.i("bg", "end create");
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		//schedule what ever is pulled out of the database
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
	
	class StatusChange extends TimerTask {
		private AudioManager manager;
		private Context context;
		private int eventid;

		StatusChange(int id){
			this.eventid = id;
		}
		
		public void run() {
			Event src = emap.get(eventid);
			context = getApplicationContext();
			manager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
			try {
				Log.i("bg", "status change run "+src.getStatus());
			//manager.setRingerMode(src.getStatus());
			} catch (Exception e) {
				Log.i("bg", e.toString());
			}
		}
	}
	
}