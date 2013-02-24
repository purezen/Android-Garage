package com.example.theapp;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class DisplayTasks extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_tasks);

		// Displaying the added task in the list
		
		Cursor cursor = new TaskEntry(this).readDb();
		
		cursor.moveToFirst();
		
		//long taskID = cursor.getLong(cursor.getColumnIndexOrThrow(TaskEntryDetails._ID));
		String task_summary = cursor.getString(cursor.getColumnIndexOrThrow(TaskEntryDetails.TASK_SUMMARY));
		
		while(cursor.moveToNext()) {
			task_summary += "\n" + cursor.getString(cursor.getColumnIndexOrThrow(TaskEntryDetails.TASK_SUMMARY));
		}
		
		TextView textView = (TextView)findViewById(R.id.temp_task_list);
		textView.setTextSize(40);
		textView.setText(task_summary);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_display_tasks, menu);
		return true;
	}

}
