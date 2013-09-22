package com.tdam2013.grupo13;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.database.DataSetObserver;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.NavUtils;

public class ActivityContactProfile extends Activity {

	private String contactName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contact_profile);
		
		// Loading contact name
		contactName = getIntent().getStringExtra("contactName");
//		((TextView) findViewById(R.id.contact_name)).setText(contactName);
		ListView userNameView = (ListView) findViewById(R.id.user_name_list);
		ArrayList<String> userNames = new ArrayList<String>();
		userNames.add(contactName);
		ListAdapter userNameAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, userNames);
		userNameView.setAdapter(userNameAdapter);		
		
		// Show the Up button in the action bar.
		setupActionBar();
		
		//Loading phones
		ArrayList<String> phones = getIntent().getStringArrayListExtra("phones");
		ListView phonesView = (ListView) findViewById(R.id.phones_list);
		ListAdapter phonesAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1	, phones);
		phonesView.setAdapter(phonesAdapter);
		
		//Loading phones
		ArrayList<String> mails = getIntent().getStringArrayListExtra("mails");
		ListView mailsView = (ListView) findViewById(R.id.mails_list);
		ListAdapter mailsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mails);
		mailsView.setAdapter(mailsAdapter);
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setTitle(contactName);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_contact_profile, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
