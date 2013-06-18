package com.winsontan520.wversionmanager.sample;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class ExampleListActivity extends ListActivity {
	private static final String TAG = "ExampleListActivity";

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ArrayList<Map<String, Object>> list = buildData();
		String[] from = { "title" };
		int[] to = { android.R.id.text1 };

		SimpleAdapter adapter = new SimpleAdapter(this, list,
				android.R.layout.simple_list_item_1, from, to);
		setListAdapter(adapter);
	}

	private ArrayList<Map<String, Object>> buildData() {
		ArrayList<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		list.add(putData("Check version sample", CheckVersionActivity.class));
		list.add(putData("Ask for rate", AskForRateActivity.class));
		return list;
	}

	private <T> HashMap<String, Object> putData(String name, Class<T> className) {
		HashMap<String, Object> item = new HashMap<String, Object>();
		item.put("title", name);
		item.put("intent", new Intent(this, className));
		return item;
	}
	
    protected void addItem(List<Map<String, Object>> data, String name, Intent intent) {
        Map<String, Object> temp = new HashMap<String, Object>();
        temp.put("title", name);
        temp.put("intent", intent);
        data.add(temp);
    }

	@SuppressWarnings("unchecked")
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Map<String, Object> map = (Map<String, Object>)l.getItemAtPosition(position);

        Intent intent = (Intent) map.get("intent");
        startActivity(intent);
	}
	
	
}
