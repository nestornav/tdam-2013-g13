package com.tdam2013.grupo13;

import java.util.ArrayList;

import android.app.ListFragment;
import android.app.LoaderManager;
import android.content.ContentResolver;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.ContactsContract.Contacts;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.tdam2013.grupo13.adapters.ContactsAdapter;
import com.tdam2013.grupo13.model.Contact;

public class FragmentTabContactos extends ListFragment implements
		LoaderManager.LoaderCallbacks<Cursor> {

	// private Fragment mFragment;
	private ArrayList<Contact> contactos;
	private ContentResolver cr;

	// Defines a Cursor to contain the retrieved data
	private Cursor mCursor;
	/*
	 * Defines a projection based on platform version. This ensures that you
	 * retrieve the correct columns.
	 */
	private static final String[] PROJECTION = { 
		Contacts._ID,
		Contacts.LOOKUP_KEY,
		Contacts.DISPLAY_NAME,
		Contacts.PHOTO_THUMBNAIL_URI,
		Contacts.HAS_PHONE_NUMBER
	};
	/*
	 * As a shortcut, defines constants for the column indexes in the Cursor.
	 * The index is 0-based and always matches the column order in the
	 * projection.
	 */
	// Column index of the _ID column
	private int mIdIndex = 0;
	// Column index of the LOOKUP_KEY column
	private int mLookupKeyIndex = 1;
	// Column index of the display name column
	private int mDisplayNameIndex = 2;
	/* Column index of the photo data column. */
	private int mPhotoDataIndex = 3;
	
	private int mHasPhoneNumber = 4;

	private ListView mContactsList;

	private ContactsAdapter mContactsAdapter;
	
	private SharedPreferences sharedPreferences;

	public FragmentTabContactos() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragmenttab_contactos, container,
				false);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		/*
		 * Instantiates the subclass of CursorAdapter
		 */
		mContactsAdapter = new ContactsAdapter(getActivity());

		sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());

	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);

		Intent intent = new Intent(getActivity(), WebMessageActivity.class);
		intent.putExtra("contact", mContactsAdapter.getContactAt(position));
		startActivity(intent);
	}

	@Override
	public void onActivityCreated(Bundle savedState) {
		super.onActivityCreated(savedState);
		
		setListAdapter(mContactsAdapter);

		getLoaderManager().initLoader(0, null, this);
		
		getListView().setOnItemLongClickListener(
				new AdapterView.OnItemLongClickListener() {

					@Override
					public boolean onItemLongClick(AdapterView<?> arg0,
							View arg1, int pos, long id) {

						Intent intent = new Intent(getActivity(),
								ActivityContactProfile.class);
						/*
						 * intent.putExtra("contactName", contactos[pos]);
						 * intent.putStringArrayListExtra("phones", phones);
						 * intent.putStringArrayListExtra("mails", mails);
						 */
						startActivity(intent);

						return true;
					}
				});
	}

	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
		 /*
	     * Constructs search criteria from the search string
	     * and email MIME type
	     */
		boolean wPhone = sharedPreferences.getBoolean("contacts_pref_w_phone", true);
	    String selection = null;
	    String[] mSelectionArgs = null;
	    if(wPhone){
	    	selection = Contacts.HAS_PHONE_NUMBER + " = ?";
	    	mSelectionArgs = new String[]{ "1" };
	    }
	    String order = sharedPreferences.getString("contacts_pref_order", "ASC");
		return new CursorLoader(
				getActivity(),
				Contacts.CONTENT_URI,
				PROJECTION,
				selection,
				mSelectionArgs,
				Contacts.DISPLAY_NAME + " " + order
		);
	}

	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
		// When the loader has completed, swap the cursor into the adapter.
        mContactsAdapter.swapCursor(cursor);
	}

	@Override
	public void onLoaderReset(Loader<Cursor> loader) {
		// Removes remaining reference to the previous Cursor
        mContactsAdapter.swapCursor(null);
	}
}