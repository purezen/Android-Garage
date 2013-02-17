package com.example.marakanatutorial;

import winterwell.jtwitter.Twitter;
import winterwell.jtwitter.TwitterException;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	static final String TAG = "Start_App";
	EditText edit_message;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		// Adding the Debug trace
		Debug.startMethodTracing("Yamba.trace");
		
		Log.d(TAG, "The app. has started...!!"); //Indicates the starting of the application
		
		setContentView(R.layout.activity_main);
		
		edit_message = (EditText) findViewById(R.id.edit_message);
	}

	
	
	@Override
	protected void onStop() {
		super.onStop();
		
		Debug.stopMethodTracing();
	}



	//Making the button to tweet the desired text
	
	public void post_tweet(View v) {
		
		final String status_text = edit_message.getText().toString();
		
		new PostToTwitter().execute(status_text);
		
		Log.d(TAG, "Now clicking the button..!!");
				
	}
	
	class PostToTwitter extends AsyncTask<String, Void, String> {

		/* New thread */
		@Override
		protected String doInBackground(String... params) {
			try {
				Twitter twitter = new Twitter("student", "password");
				twitter.setAPIRootUrl("http://yamba.marakana.com/api/");
				twitter.setStatus(params[0]);
				
				Log.d(TAG, "Successfully posted: "+ params[0]);
				
				//Tweet successfully posted message
				return "Successfully posted: " + params[0];
				
			} catch (TwitterException e) {
				Log.e(TAG, "Died while tweeting", e);
				e.printStackTrace();
				
				//Tweet failed message
				return "Failed to post: " + params[0];
		}
	}

	
	/* UI thread */	
	@Override
	public void onPostExecute(String result) {
		super.onPostExecute(result);
		Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
	}
	
	
	/* Not needed now
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	 */
	}
}
