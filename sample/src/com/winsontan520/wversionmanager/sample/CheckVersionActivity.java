package com.winsontan520.wversionmanager.sample;

import com.winsontan520.wversionmanager.sample.R;
import com.winsontan520.wversionmanager.library.WVersionManager;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class CheckVersionActivity extends Activity {
	
	// this is just a sample url to retrieve update content, the return content must follow the json format as stated in readme.md
	public final static String URL_VERSION_2 = "http://bit.ly/11c7Pnb"; // this link is refer to a static text file hosted using dropbox
	public final static String URL_VERSION_3 = "http://bit.ly/127Ug7e";
	
	private EditText versionContentUrl;
	private EditText updateNowLabel;
	private EditText remindMeLaterLabel;
	private EditText ignoreThisVersionLabel;
	private EditText reminderTimer;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.check_version);
		
		versionContentUrl = (EditText) findViewById(R.id.versionContentUrl);
		updateNowLabel = (EditText) findViewById(R.id.updateNowLabel);
		remindMeLaterLabel = (EditText) findViewById(R.id.remindMeLaterLabel);
		ignoreThisVersionLabel = (EditText) findViewById(R.id.ignoreThisVersionLabel);
		reminderTimer = (EditText) findViewById(R.id.reminderTimer);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	public void onClickHandler(View v){
		switch(v.getId()){
		case R.id.use_version_2_button:
			versionContentUrl.setText(URL_VERSION_2);
			break;
		case R.id.use_version_3_button:
			versionContentUrl.setText(URL_VERSION_3);
			break;
		case R.id.check_version_button:
			checkVersion();
			break;
		}
	}

	private void checkVersion() {
		WVersionManager versionManager = new WVersionManager(this);
		
		versionManager.setVersionContentUrl(versionContentUrl.getText().toString());
		versionManager.setUpdateNowLabel(updateNowLabel.getText().toString());
		versionManager.setRemindMeLaterLabel(remindMeLaterLabel.getText().toString());
		versionManager.setIgnoreThisVersionLabel(ignoreThisVersionLabel.getText().toString());
		versionManager.setReminderTimer(Integer.valueOf(reminderTimer.getText().toString()));
	
		versionManager.checkVersion();
	}
	
}
