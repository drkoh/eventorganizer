package com.cs446teameo.Storage;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.*;
import android.util.Log;

public class Database {
	private static final Database INSTANCE = new Database();
	//Here we have to design our database considering concurrency
	public static final String EVENT_ID = "_id"; 
	public static final String EVENT_NAME = "name";
	public static final String EVENT_START = "start_time";
	public static final String EVENT_END = "end_time";
	public static final String EVENT_LOCATION = "location";
	public static final String EVENT_PROFILE_ID = "profileID";
	public static final String PROFILE_ID = "_id";
	public static final String PROFILE_NAME = "name";
	public static final String PROFILE_VOL = "volume";
	public static final String PROFILE_VIB = "vibrate";
	private static final String TAG = "Database";
	
	private static DatabaseHelper mDbHelper;
	private static SQLiteDatabase mDb;
	
	private static Context mCtx;
	    
	private static final String DATABASE_NAME = "EO";
	private static final int DATABASE_VERSION = 2;
    private static final String Event_TABLE_NAME = "Events";
    private static final String Profile_TABLE_NAME = "Profile";
    private static final String Event_TABLE_CREATE =
    	"create table events (_id integer primary key autoincrement, "
        + "name text not null, start_time integer not null, end_time integer not null, "
        + "location text not null, profileID);";
    private static final String Profile_TABLE_CREATE =
    	"create table profile (_id integer primary key autoincrement, "
    	+ "name text not null, volume integer not null, vibrate boolean not null);";
    
    /**
     * Constructor - takes the context to allow the database to be
     * opened/created
     * 
     * @param ctx the Context within which to work
     */
    private Database() {
    }
    
    private static class DatabaseHelper extends SQLiteOpenHelper {

        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(Event_TABLE_CREATE);
            db.execSQL(Profile_TABLE_CREATE);
            Log.i("testdb", "here creat");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS table");
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
    public static Database open(Context ctx) throws SQLException {
    	if (mCtx == null) {
    		mCtx = ctx;
    		mDbHelper = new DatabaseHelper(mCtx);
    		mDb = mDbHelper.getWritableDatabase();
    	}
        return INSTANCE;
    }

    public static void close() {
        mDbHelper.close();
    }
    
    public long insert(String table, ContentValues val){
    	return mDb.insert(table, null, val);
    }
    
    public int delete(String table, String cond){
    	return mDb.delete(table, cond, null);
    }
    
    public int update(String table, ContentValues val, String cond){	
    	return mDb.update(table, val, cond, null);
    }
    
    public Cursor select(String sel){
    	return mDb.rawQuery(sel, null);
    }
    
    public String getEventTable(){
    	return Event_TABLE_NAME;
    }
    
    public String getProfileTable(){
    	return Profile_TABLE_NAME;
    }
    
    public int getNewestID(){
    	Cursor c = mDb.rawQuery("select max(" + PROFILE_ID + ") from " + Event_TABLE_NAME+";", null);
    	if (c.moveToFirst()) {
    		//Since there could be only a single row
    		return c.getInt(1);
    	}
    	return -1;
    }
}
