PLEASE NOTE, THIS PROJECT IS NO LONGER BEING MAINTAINED
====================
## PLEASE INFORM ME THE IMPROVED VERSION SO I CAN LINK IT FOR OTHER USERS

Following links are the improved version by others:
- [revanmj](https://github.com/revanmj/Android-WVersionManager)

## Objective
- Save time to implement check new update available since Google play havent provide any API to check (written at Jan 2013)
- Ask user for rate

## Features
- Show Alert Dialog with new update content and 3 options button: update now, remind me later and ignore this version.
- Callback to implement your own custom logic
- Show Alert Dialog to prompt user ask for rating
- Few lines of code to implement.

## Changelog
- v1.1 Ask for rating
- v1.2 Add OnReceive callback when response
- v1.3 Fix error handling callback

## Screenshots
![Screenshot](https://github.com/winsontan520/Android-WVersionManager/raw/master/image.png)

## Demo
[![Get it on Google Play](http://www.android.com/images/brand/get_it_on_play_logo_small.png)](http://play.google.com/store/apps/details?id=com.winsontan520.wversionmanager.sample)

## Usage - Check Version for latest update
1. Copy latest version of jarfile in folder JAR to your project libs folder. You may also check out src used as project library.
2. For check version, in your Activity, 

    	protected void onCreate(Bundle savedInstanceState) {
    		super.onCreate(savedInstanceState);
    		setContentView(R.layout.activity_main);
    		
    		WVersionManager versionManager = new WVersionManager(this);
    		versionManager.setVersionContentUrl("http://bit.ly/11c7Pnb"); // your update content url, see the response format below
    		versionManager.checkVersion();
    	}

3. The Version Content Url must return response with json format which follow this format. To save time for testing you may just put content in static text file.

		{
		"version_code": 2,
		"content": "Version 2.0 <p>New features:</p><li>Added feature A</li><li>Added feature B</li><li>Added feature C</li><li>Added feature D</li><li>Added feature E</li><li>Added feature F</li><li>Added feature G</li>"
		}

4. You can also customize label and reminder time eg:

    	versionManager.setUpdateNowLabel("Custom update now label");
    	versionManager.setRemindMeLaterLabel("Custom remind me later label");
    	versionManager.setIgnoreThisVersionLabel("Custom ignore this version");
    	versionManager.setUpdateUrl("http://your_app_url"); // this is the link will execute when update now clicked. default will go to google play based on your package name.
    	versionManager.setReminderTimer(10); // this mean checkVersion() will not take effect within 10 minutes

5. Set callback for your own implementation:
	```
	versionManager.setOnReceiveListener(new OnReceiveListener() {
		@Override
		public boolean onReceive(int status, String result) {
			// implement your own compare logic here
			return false; // return true if you want to use library's default logic & dialog
		}
	});
	```
## Usage - Ask user for rate
1. Copy latest version of jarfile in folder JAR to your project libs folder. You may also check out src used as project library.
2. Add following lines to prompt user dialog for rating

		WVersionManager versionManager = new WVersionManager(this);
		versionManager.askForRate();
		
3. Customize label for ask for rate:

		versionManager.setTitle("Please rate us"); // optional
		versionManager.setMessage("We need your help to rate this app!"); // optional
		versionManager.setAskForRatePositiveLabel("OK"); // optional
		versionManager.setAskForRateNegativeLabel("Not now"); // optional
		
## License - Free to use
    Copyright 2013 Winson Tan
    
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
    
       http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
