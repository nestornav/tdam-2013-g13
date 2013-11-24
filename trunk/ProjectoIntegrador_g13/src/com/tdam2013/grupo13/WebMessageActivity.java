package com.tdam2013.grupo13;

import java.util.ArrayList;
import java.util.Date;

import com.tdam2013.grupo13.adapters.Contact;
import com.tdam2013.grupo13.adapters.WebMessage;
import com.tdam2013.grupo13.adapters.WebMessageAdapter;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class WebMessageActivity extends Activity {

	private Contact contact;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_web_message);
		
		// Loading contact
		contact = (Contact) getIntent().getSerializableExtra("contact");
		ListView userNameView = (ListView) findViewById(R.id.msgs_list);
		ArrayList<WebMessage> messages = new ArrayList<WebMessage>();
		for (int i = 0; i < 100; i++) {
			messages.add(new WebMessage(new Date().toLocaleString(), contact.getName()));	
		}
		ListAdapter webMessageAdapter = new WebMessageAdapter(this, messages);
		
		
		userNameView.setAdapter(webMessageAdapter);		
		
		// Show the Up button in the action bar.
		setupActionBar();
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setTitle(contact.getName());

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
