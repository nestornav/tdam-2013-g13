package com.tdam2013.grupo13.adapters;

import java.util.List;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tdam2013.grupo13.R;

public class WebMessageAdapter extends BaseAdapter{
	
	private List<WebMessage> messages;
	private Activity activity;

	public WebMessageAdapter(Activity activity, List<WebMessage> messages){
		super();
		this.activity = activity;
		this.messages = messages;
	}

	@Override
	public int getCount() {
		return messages.size();
	}

	@Override
	public Object getItem(int position) {
		return messages.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		WebMessage message = messages.get(position);
		
		if (convertView == null){
			convertView = activity.getLayoutInflater().inflate(R.layout.listview_message, null);
		}
		
		TextView dateTime = (TextView) convertView.findViewById(R.id.message_date_time);
		dateTime.setText(message.getDateTime());
		
		TextView messageContent = (TextView) convertView.findViewById(R.id.message_content);
		messageContent.setText(message.getMessage());
		
		return convertView;
	}

}
