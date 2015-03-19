package com.winsontan520.wversionmanager.sample;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.winsontan520.wversionmanager.library.OnReceiveListener;
import com.winsontan520.wversionmanager.library.WVersionManager;

public class CheckVersionActivity extends Activity {

	// this is just a sample url to retrieve update content, the return content
	// must follow the json format as stated in readme.md
	// please use proper rest api for your project, this is just for demo
	public final static String URL_VERSION_2 = "http://bit.ly/wversionmanager-v2"; 
	public final static String URL_VERSION_3 = "http://bit.ly/wversionmanager-v3";

	private EditText versionContentUrl;
	private EditText updateNowLabel;
	private EditText remindMeLaterLabel;
	private EditText ignoreThisVersionLabel;
	private EditText reminderTimer;
	private CheckBox checkBoxCallback;
	private CheckBox checkBoxUseDefaultDialog;
	private ProgressDialog mProgressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.check_version);

		versionContentUrl = (EditText) findViewById(R.id.versionContentUrl);
		updateNowLabel = (EditText) findViewById(R.id.updateNowLabel);
		remindMeLaterLabel = (EditText) findViewById(R.id.remindMeLaterLabel);
		ignoreThisVersionLabel = (EditText) findViewById(R.id.ignoreThisVersionLabel);
		reminderTimer = (EditText) findViewById(R.id.reminderTimer);
		checkBoxCallback = (CheckBox) findViewById(R.id.checkBoxWithCallback);
		checkBoxUseDefaultDialog = (CheckBox) findViewById(R.id.checkBoxUseDefaultDialog);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	public void onClickHandler(View v) {
		switch (v.getId()) {
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

		// implement own listener callback handler if is checked
		if (checkBoxCallback.isChecked()) {
			// set own listener
			versionManager.setOnReceiveListener(new OnReceiveListener() {

				@Override
				public boolean onReceive(int status, String result) {
					if(mProgressDialog != null){
						mProgressDialog.cancel();
					}
					
					if(!checkBoxUseDefaultDialog.isChecked()){
						showMyOwnDialog(status, result);
					}

					// return true to use default dialog
					return checkBoxUseDefaultDialog.isChecked(); 
				}
			});

			mProgressDialog = ProgressDialog.show(this, "", "Loading. Please wait...", true, false);
		}

		versionManager.checkVersion();
	}

	protected void showMyOwnDialog(int status, String result) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Status " + status);
		builder.setMessage("Result: \n" + result);
		builder.create().show();
	}

}
