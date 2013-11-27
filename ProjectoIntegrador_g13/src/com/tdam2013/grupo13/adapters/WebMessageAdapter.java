package com.tdam2013.grupo13.adapters;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.ReceiverCallNotAllowedException;
import android.location.Address;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tdam2013.grupo13.R;
import com.tdam2013.grupo13.model.ConnectionHistory;
import com.tdam2013.grupo13.model.WebMessage;

public class WebMessageAdapter extends BaseAdapter{
	
	private List<WebMessage> messages;
	private Activity activity;

	public WebMessageAdapter(Activity activity, List<WebMessage> messages){
		super();
		this.activity = activity;
		this.messages = messages;
	}
	
	public void addItem(WebMessage item){
		this.messages.add(item);
		notifyDataSetChanged();
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
		
		TextView reciverName = (TextView) convertView.findViewById(R.id.reciver_name);
		reciverName.setText(message.getReceiverName());
		
		TextView dateTime = (TextView) convertView.findViewById(R.id.message_date_time);
		dateTime.setText(message.getDateTime());
		
		TextView messageContent = (TextView) convertView.findViewById(R.id.message_content);
		messageContent.setText(message.getMessage());
		
		return convertView;
	}

	public void update(ArrayList<WebMessage> webMessages) {
		this.messages = webMessages;
		notifyDataSetChanged();
	}

}
