package com.example.theapp;

import android.annotation.SuppressLint;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.app.ListActivity;
import android.app.LoaderManager;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.content.CursorLoader;
import android.view.Gravity;
import android.view.Menu;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class DisplayTasks extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_tasks);

		// Displaying the added task in the list
		
		final Cursor cursor = new TaskEntry(this).readDb();
		
		cursor.moveToFirst();
		
		//long taskID = cursor.getLong(cursor.getColumnIndexOrThrow(TaskEntryDetails._ID));
		String task_summary = cursor.getString(cursor.getColumnIndexOrThrow(TaskEntryDetails.TASK_SUMMARY));
		
		while(cursor.moveToNext()) {
			task_summary += "\n" + cursor.getString(cursor.getColumnIndexOrThrow(TaskEntryDetails.TASK_SUMMARY));
		}
		
		TextView textView = (TextView)findViewById(R.id.temp_task_list);
		textView.setTextSize(40);
		textView.setText(task_summary);
		
		@SuppressLint("NewApi")
		class ListViewLoader extends ListActivity implements LoaderManager.LoaderCallbacks<Cursor>{
			
			@Override
			protected void onCreate(Bundle savedInstanceState) {
				super.onCreate(savedInstanceState);
				
				String fromColumns[] = {TaskEntryDetails.TASK_SUMMARY};
				int toViews[] = {R.id.textView1};
				
				SimpleCursorAdapter adapter;
				
				// Create a progress bar to display while the list loads
				ProgressBar progressBar = new ProgressBar(this);
				progressBar.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
						LayoutParams.WRAP_CONTENT, Gravity.CENTER));
				progressBar.setIndeterminate(true);
				getListView().setEmptyView(progressBar);
				
				// Must add the progress bar to the root of the layout
				ViewGroup root = (ViewGroup) findViewById(android.R.id.content);
				root.addView(progressBar);
				
				adapter = new SimpleCursorAdapter(this, R.id.listView1, cursor, fromColumns, toViews);
				setListAdapter(adapter);
				
				// Prepare the loader.  Either re-connect with an existing one,
		        // or start a new one.
		        getLoaderManager().initLoader(0, null, this);
			}

			@Override
			public Loader<Cursor> onCreateLoader(int id, Bundle args) {
				
				return new ;
			}

			@Override
			public void onLoadFinished(Loader<Cursor> arg0, Cursor arg1) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onLoaderReset(Loader<Cursor> arg0) {
				// TODO Auto-generated method stub
				
			}
			
		}
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_display_tasks, menu);
		return true;
	}

}
