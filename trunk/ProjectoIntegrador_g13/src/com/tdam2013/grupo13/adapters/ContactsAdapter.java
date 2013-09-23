package com.tdam2013.grupo13.adapters;

import java.util.List;

import com.tdam2013.grupo13.R;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.provider.ContactsContract;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.QuickContactBadge;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class ContactsAdapter extends BaseAdapter{
	
	private List<String> contacts;
	private Activity activity;

	public ContactsAdapter(Activity activity, List<String> contacts){
		super();
		this.activity = activity;
		this.contacts = contacts;
	}

	@Override
	public int getCount() {
		return contacts.size();
	}

	@Override
	public Object getItem(int position) {
		return contacts.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		String contact = contacts.get(position);
		if (convertView == null){
			convertView = activity.getLayoutInflater().inflate(R.layout.listview_contacts, null);
		}
		TextView contactName = (TextView) convertView.findViewById(R.id.contact_name);
		contactName.setText(contact);
		
		QuickContactBadge badgeSmall = (QuickContactBadge) convertView.findViewById(R.id.badge_small);  
		badgeSmall.assignContactFromEmail("any@gmail.com", true);  
		badgeSmall.setMode(ContactsContract.QuickContact.MODE_SMALL);
		
		return convertView;
	}

}
