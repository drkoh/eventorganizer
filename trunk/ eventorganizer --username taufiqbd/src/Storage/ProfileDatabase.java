package Storage;

public class ProfileDatabase {
	//the profile database should also
	//take concurrency into account
	
	
	//here I don't know if the pre-read the database and manage 
	//all the profiles or read it real time from the database.
	
	private static ProfileDatabase _instance = null;
	
	
	private ProfileDatabase(){
		
	}
	
	
	public static ProfileDatabase getInstance(){
		if(_instance == null)
			_instance = new ProfileDatabase();
		return _instance;
	}
	
	
}
