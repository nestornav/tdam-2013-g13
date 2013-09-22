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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contact_profile);
		// Show the Up button in the action bar.
		setupActionBar();
		
		// Loading contact name
		String contactName = getIntent().getStringExtra("contactName");
		((TextView) findViewById(R.id.contact_name)).setText(contactName);
		Toast.makeText(this, "Nombre del contacto " + contactName,
				Toast.LENGTH_SHORT).show();
		
		//Loading phones
		ArrayList<String> telefonos = getIntent().getStringArrayListExtra("telefonos");
		ListView phones = (ListView) findViewById(R.id.phones_list);
		ListAdapter phonesAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, telefonos);
		phones.setAdapter(phonesAdapter);
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

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
