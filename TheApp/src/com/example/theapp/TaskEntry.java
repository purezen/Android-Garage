package com.example.theapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

class TaskEntryDetails implements BaseColumns {
	
	public static final String DB_NAME = "tasks_list.db";
	public static final int DB_VERSION = 1;
	
	public static final String TABLE_NAME = "tasks_list";
	public static final String TASK_SUMMARY = "task_details";

}

public class TaskEntry {

	public static final String TAG = "TaskEntry";
	private Context context;
	private DbHelper dbHelper;

	public TaskEntry(Context context){
		this.context = context;
		dbHelper = new DbHelper();
	}

	class DbHelper extends SQLiteOpenHelper{

		public DbHelper() {
			super(context, TaskEntryDetails.DB_NAME, null, TaskEntryDetails.DB_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			String sql = String.format("CREATE TABLE %s" + "(%s INT PRIMARY KEY, %s TEXT)", TaskEntryDetails.TABLE_NAME, TaskEntryDetails._ID,TaskEntryDetails.TASK_SUMMARY);
			
			Log.d(TAG, "Created database by name - " + TaskEntryDetails.DB_NAME);
			db.execSQL(sql);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP IF EXISTS " + TaskEntryDetails.TABLE_NAME);
			onCreate(db);
		}
	
	}
	
	// Inserting into Database
	
	public void insertIntoDb(String summary) {
		
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		
		// Create a new map of values, where column names are the keys
		ContentValues values = new ContentValues();
		values.put(TaskEntryDetails.TASK_SUMMARY, summary);
	
		@SuppressWarnings("unused")
		long newRowId;
		newRowId = db.insert(TaskEntryDetails.TABLE_NAME, TaskEntryDetails._ID, values);
	
	}
	
	// Read the database via a Cursor
	
	public Cursor readDb() {
		
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		
		String[] projection = {
				TaskEntryDetails._ID, TaskEntryDetails.TASK_SUMMARY
		};
		
		String sortOrder = TaskEntryDetails._ID + " ASC";
		
		Cursor c = db.query(TaskEntryDetails.TABLE_NAME, projection, null, null, null, null, sortOrder);
		
		return c;
	}
}
