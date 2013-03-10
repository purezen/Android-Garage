package com.example.theapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	// Pressing the Add New Task button calls this function
	public void tryPosting(View view) {
		EditText editText = (EditText) findViewById(R.id.text_add_task);
		String task = editText.getText().toString();
		
		// Putting values in database
		TaskEntry taskEntry = new TaskEntry(this);
		taskEntry.insertIntoDb(task);
		
		// Starting new activity
		Intent intent = new Intent(this, DisplayTasks.class);
		startActivity(intent);
	}
}
