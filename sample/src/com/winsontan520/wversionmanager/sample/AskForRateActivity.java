package com.winsontan520.wversionmanager.sample;

import com.winsontan520.wversionmanager.sample.R;
import com.winsontan520.wversionmanager.library.WVersionManager;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AskForRateActivity extends Activity {
	private Button askForRateButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ask_for_rate);
		
		askForRateButton = (Button) findViewById(R.id.ask_for_rate);
		
		askForRateButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				askForRate();	
			}
		});
	}

	protected void askForRate() {
		WVersionManager versionManager = new WVersionManager(this);
		versionManager.setTitle("Please rate us"); // optional
		versionManager.setMessage("We need your help to rate this app!"); // optional
		versionManager.setAskForRatePositiveLabel("OK"); // optional
		versionManager.setAskForRateNegativeLabel("Not now"); // optional
		versionManager.askForRate();
		
	}
	
	
	
}
