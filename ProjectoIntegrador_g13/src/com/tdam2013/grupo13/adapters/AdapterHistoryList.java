package com.tdam2013.grupo13.adapters;

import java.util.List;

import com.tdam2013.grupo13.R;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class AdapterHistoryList extends BaseAdapter{
	
	private List<HistoryEvent> events;
	private Activity activity;

	public AdapterHistoryList(Activity activity, List<HistoryEvent> events){
		super();
		this.activity = activity;
		this.events = events;
	}

	@Override
	public int getCount() {
		return events.size();
	}

	@Override
	public Object getItem(int position) {
		return events.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		HistoryEvent event = events.get(position);
		if (convertView == null){
			convertView = activity.getLayoutInflater().inflate(R.layout.listview_history, null);
		}
		TextView contactName = (TextView) convertView.findViewById(R.id.contact_name);
		TextView dateTime = (TextView) convertView.findViewById(R.id.date_time);
		contactName.setText(event.getContact());
		dateTime.setText(event.getDateTime());
		return convertView;
	}

}
