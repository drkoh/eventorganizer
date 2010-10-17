package Profile;

import java.io.Serializable;

public class Profile implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2789089601153968331L;
	
	public static final short SHUTDOWN = 0;
	
	
	String description;
	short serial;
	
	//to add in more features here
	public Profile(String description,int serial){
		
	}
	
	public Profile(){
		this.description = "debug";
		this.serial = -1;
	}
}
