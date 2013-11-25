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

import com.tdam2013.grupo13.adapters.Contact;
import com.tdam2013.grupo13.adapters.ContactsAdapter;

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

//	public ArrayList<Contact> getContacts() {
//		Contact contact = null;
//		cr = getActivity().getContentResolver();
//		Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI, null,
//				null, null, null);
//
//		while (cur.moveToNext()) {
//			String id = cur.getString(cur
//					.getColumnIndex(ContactsContract.Contacts._ID));
//			String name = cur.getString(cur
//					.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
//			ArrayList<Integer> phoneList = new ArrayList<Integer>();
//
//			if (Integer
//					.parseInt(cur.getString(cur
//							.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
//				Cursor pnCur = getActivity()
//						.getContentResolver()
//						.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
//								null,
//								ContactsContract.CommonDataKinds.Phone.CONTACT_ID
//										+ " = "
//										+ cur.getString(cur
//												.getColumnIndex(ContactsContract.Contacts._ID)),
//								null, null);
//				while (pnCur.moveToNext()) {
//					phoneList.add(Integer.parseInt((pnCur.getString(pnCur
//							.getColumnIndex("DATA1")))));
//				}
//				pnCur.close();
//			}
//
//			cur.close();
//
//		}
//		return contactos;
//	}

	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
		 /*
	     * Constructs search criteria from the search string
	     * and email MIME type
	     */
		boolean wMail = sharedPreferences.getBoolean("contacts_pref_w_mail", true);
	    String SELECTION = null;
	    String[] mSelectionArgs = null;
//	    if(wMail){
//	    	SELECTION = Contacts.HAS_PHONE_NUMBER + " = ?";
//	    	mSelectionArgs = new String[]{ "1" };
//	    }
		return new CursorLoader(getActivity(), Contacts.CONTENT_URI, PROJECTION, SELECTION, mSelectionArgs, Contacts.DISPLAY_NAME + " ASC");
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