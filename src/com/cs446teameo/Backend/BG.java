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
	EventManager EM = EventManager.getInstance();
	HashMap<Integer ,Event> emap = null;
	Timer schEvent = new Timer();
	
	public class LocalBinder extends Binder {
        public BG getService() {
            return BG.this;
        }
    }
	
	private void gatherEvents() {
		ArrayList<Event> elist = new ArrayList();
		EM.listEvent(elist);
		emap = new HashMap();
		if (elist.size() != 0) {
			Log.i("bg", "got data");
		} else {
			Log.i("bg", "no data");
		}
		for (int i=0; i < elist.size(); i++) {
			emap.put(elist.get(i).getEid(), elist.get(i));
		}
	}
	
	private void setEvents() {
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
	
	public void notifyME() {
		Log.i("bg", "notify recived");
		gatherEvents();
		setEvents();
	}
	
	@Override
	public void onCreate() {
		//pull the data from the database and store them into some global array
		gatherEvents();
		Log.i("bg", "end create");
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		//schedule what ever is pulled out of the database
		Log.i("bg", "start onStartCommand");
		Log.i("bg", "NO. Events " + emap.size());
		setEvents();
		return START_STICKY;
	}
	
	private final IBinder mBinder = new LocalBinder();
	
	@Override
	public IBinder onBind(Intent intent) {
		//binding only used for notification
		Log.i("bg", "binding done: "+intent.getDataString());
		notifyME();
		return mBinder;
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
			manager.setRingerMode(src.getStatus());
		}
	}
	
}