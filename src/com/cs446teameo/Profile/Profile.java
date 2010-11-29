package com.cs446teameo.Profile;

import java.io.Serializable;

public class Profile{
        public static final short SHUTDOWN = 0;
       
        private int pId;
        private String description;
        private boolean vibrate;
        private int volume;
       
        public Profile(String desc, boolean vib, int vol)
        {
			this.description = desc;
			this.vibrate = vib;
			this.volume = vol;
        }
       
        public Profile(int id, String desc, boolean vib, int vol)
        {
        	this.pId = id;
			this.description = desc;
			this.vibrate = vib;
			this.volume = vol;
        }
        
        public int getId(){
                return pId;
        }
       
        public void setDescription(String desc){
                description = desc;
        }
       
        public String getDescription(){
                return description;
        }
       
        public void setVibrate(boolean vib){
                vibrate = vib;
        }
       
        public boolean getVibrate(){
                return vibrate;
        }
       
        public void setVolume(int vol){
                volume = vol;
        }
       
        public int getVolume(){
                return volume;
        }
        public String toString(){
        	return description;
        }
}
