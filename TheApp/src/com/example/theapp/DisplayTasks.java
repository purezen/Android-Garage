package com.example.theapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class DisplayTasks extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_tasks);

		// Receiving the intent	
		Intent intent = getIntent();
		String new_task = intent.getStringExtra(MainActivity.NEW_TASK);
		
		// Displaying the added task in the list
		TextView textView = (TextView)findViewById(R.id.temp_task_list);
		textView.setTextSize(40);
		textView.setText(new_task);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_display_tasks, menu);
		return true;
	}

}
