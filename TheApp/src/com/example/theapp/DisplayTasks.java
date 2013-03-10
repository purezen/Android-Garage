package com.example.theapp;

import android.annotation.TargetApi;
import android.app.ListActivity;
import android.app.LoaderManager;
import android.content.Loader;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ProgressBar;
import android.widget.SimpleCursorAdapter;

import com.commonsware.cwac.loaderex.SQLiteCursorLoader;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class DisplayTasks extends ListActivity implements LoaderManager.LoaderCallbacks<Cursor>{
	
	private SQLiteCursorLoader loader;
	private DbHelper dbHelper;
	private SQLiteOpenHelper db=null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
			
		SimpleCursorAdapter adapter;
		String fromColumns[] = {TaskEntryDetails.TASK_SUMMARY};
		int toViews[] = {R.id.textView1};
		
		//Create a progress bar to display while the list loads
		ProgressBar progressBar = new ProgressBar(this);
		progressBar.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT));
		progressBar.setIndeterminate(true);
		getListView().setEmptyView(progressBar);
			
		// Must add the progress bar to the root of the layout
		ViewGroup root = (ViewGroup) findViewById(android.R.id.content);
		root.addView(progressBar);
		
		adapter = new SimpleCursorAdapter(this, R.layout.list_item, null, fromColumns, toViews, 0);
		setListAdapter(adapter);
		
		// Prepare the loader.  Either re-connect with an existing one, or start a new one.
	    getLoaderManager().initLoader(0, null, this);
	}
	
	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
	 
	    TaskEntry taskentry = new TaskEntry(DisplayTasks.this);
	    dbHelper=taskentry.DbHelpermethod();
	  
	    db = dbHelper;
	    String rawQuery = "SELECT " + TaskEntryDetails._ID + " , " + TaskEntryDetails.TASK_SUMMARY + " FROM " + TaskEntryDetails.TABLE_NAME;
	    loader = new SQLiteCursorLoader(this, db, rawQuery, null);
	    return(loader);
	}
			
	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
		((SimpleCursorAdapter)this.getListAdapter()).swapCursor(cursor);
	}
		
	@Override
	public void onLoaderReset(Loader<Cursor> arg0) {
		((SimpleCursorAdapter)this.getListAdapter()).swapCursor(null);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_display_tasks, menu);
		return true;
	}
}