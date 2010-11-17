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
	
	private Context mCtx;
	    
	private static final String DATABASE_NAME = "EO";
	private static final int DATABASE_VERSION = 2;
    private static final String Event_TABLE_NAME = "Events";
    private static final String Profile_TABLE_NAME = "Profile";
    private static final String Event_TABLE_CREATE =
    	"create table events (_id integer primary key autoincrement, "
        + "name text not null, start_time integer not null, end_time integer not null);";
    private static final String Profile_TABLE_CREATE =
    	"create table profile (_id integer primary key autoincrement, "
    	+ "name text not null, volume integer not null, vibrate boolean not null);";
    
    /**
     * Constructor - takes the context to allow the database to be
     * opened/created
     * 
     * @param ctx the Context within which to work
     */
    public EventDatabase(Context ctx) {
    	this.mCtx = ctx;
    	mDbHelper = new DatabaseHelper(mCtx);
    }
    
    private static class DatabaseHelper extends SQLiteOpenHelper {

        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(Event_TABLE_CREATE);
            db.execSQL(Profile_TABLE_CREATE);
            Log.d("eosql", "here");
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
    public EventDatabase open() throws SQLException {
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        mDbHelper.close();
    }
    
    public Cursor queryEvent(String q) throws SQLException {
        Cursor mCursor = mDb.rawQuery(q, new String[] {"_id", "start_time", "end_time", "name"});
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }
}
