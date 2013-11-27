package com.tdam2013.grupo13;

import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.tdam2013.grupo13.adapters.WebMessageAdapter;
import com.tdam2013.grupo13.messaging.WebMessageServiceWrapper;
import com.tdam2013.grupo13.model.Contact;
import com.tdam2013.grupo13.model.WebMessage;

public class WebMessageActivity extends Activity {

	private Contact contact;
	private WebMessageServiceWrapper service;
	private EditText editTextMdg;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_web_message);
		
		service = new WebMessageServiceWrapper(this);
		editTextMdg = (EditText) findViewById(R.id.editTextMsg);
		Button sendButton = (Button) findViewById(R.id.buttonSendMsg);
		sendButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String text = editTextMdg.getText().toString();
				if(!text.equals("")){
					service.sendMessage("usuario", "123456", contact.getName(), text);
				}
			}
		});
		
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
