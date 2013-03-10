package com.example.theapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class TaskEntry {

	public static final String TAG = "TaskEntry";
	private DbHelper dbHelper;
	 
	public TaskEntry(Context context){
	    dbHelper = new DbHelper(context);
	}
	public DbHelper DbHelpermethod() {
	    return this.dbHelper;
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
