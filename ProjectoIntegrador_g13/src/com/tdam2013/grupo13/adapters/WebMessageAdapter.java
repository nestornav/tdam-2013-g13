package com.tdam2013.grupo13.adapters;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tdam2013.grupo13.R;
import com.tdam2013.grupo13.model.WebMessage;

import java.util.ArrayList;
import java.util.List;

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
		ImageView locationContent = (ImageView) convertView.findViewById(R.id.localitation_content);
		String [] msg = messageType(message.getMessage());
		if(msg[0].equals("MSG")){
			messageContent.setText(msg[1]);
			messageContent.setVisibility(View.VISIBLE);
			locationContent.setVisibility(View.GONE);
		}else{
			locationContent.setVisibility(View.VISIBLE);
			messageContent.setVisibility(View.GONE);
			locationContent.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:"+msg[1]));
					intent.setClassName("com.google.android.apps.maps","com.google.android.maps.MapsActivity");
					activity.startActivity(intent);
				}
			});
		}

		return convertView;
	}

	public void update(ArrayList<WebMessage> webMessages) {
		this.messages = webMessages;
		notifyDataSetChanged();
	}

	public String[] messageType(String message){
		String[] messageParsed = new String[2];

		messageParsed[0] = message.substring(0,3);
		messageParsed[1] = message.substring(3);

		return messageParsed;
	}
}
