package com.cs446teameo.Storage;


import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.*;
import android.util.Log;

public class EventDatabase {
	//Here we have to design our database considering concurrency
	public static final String KEY_TITLE = "title";
	public static final String KEY_BODY = "body";
	public static final String KEY_ROWID = "_id"; 
	private static final String TAG = "EventDatabase";
	
	private DatabaseHelper mDbHelper;
	private SQLiteDatabase mDb;
	
	private final Context mCtx;
	    
	private static final String DATABASE_NAME = "EO";
	private static final int DATABASE_VERSION = 2;
    private static final String Event_TABLE_NAME = "Events";
    private static final String Event_TABLE_CREATE =
    	"create table events (_id integer primary key autoincrement, "
        + "name text not null, start_time integer not null, end_time integer not null);";
    
    /**
     * Constructor - takes the context to allow the database to be
     * opened/created
     * 
     * @param ctx the Context within which to work
     */
    public EventDatabase(Context ctx) {
        this.mCtx = ctx;
    }
    
    private static class DatabaseHelper extends SQLiteOpenHelper {

        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(Event_TABLE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS notes");
            onCreate(db);
        }
    }

    /**
     * Open the notes database. If it cannot be opened, try to create a new
     * instance of the database. If it cannot be created, throw an exception to
     * signal the failure
     * 
     * @return this (self reference, allowing this to be chained in an
     *         initialization call)
     * @throws SQLException if the database could be neither opened or created
     */
    public EventDatabase open() throws SQLException {
        mDbHelper = new DatabaseHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        mDbHelper.close();
    }
    
    public Cursor query(String q) throws SQLException {

        Cursor mCursor =
            mDb.rawQuery(q, new String[] {"_id", "start_time", "end_time", "name"});
        Log.d("sd", "here");
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }
	/*
	private int currentId;
	
	private ObjectInputStream reader = null;
	private ObjectOutputStream writer = null;
	
	private static EventDatabase _instance = null;
	
	
	
	private EventDatabase(){
	}
	
	
	public static EventDatabase getInstance(){
		if(_instance == null)
			_instance = new EventDatabase();
		return _instance;
	}
	
	
	int getCurrentId(){
		return 0;
	}
	
	
	int createNew(Event event){
		return 0;
	}
	
	int getReadable(Event event){
		return 0;
	}
	
	int getReadable(Vector<Event> list){
		return 0;
	}
	
	int getEditable(Event event){
		return 0;
	}
	
	int getEditable(Vector<Event> list){
		return 0;
	}
	
	int write(Event event){
		return 0;
	}
	
	int write(Vector<Event> list){
		return 0;
	}
	*/
}
