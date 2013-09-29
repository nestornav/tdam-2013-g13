package com.tdam2013.grupo13.adapters;

import java.util.List;

import com.tdam2013.grupo13.R;
import com.tdam2013.grupo13.adapters.ConnectionHistory.ConnectionHistoryStatus;
import com.tdam2013.grupo13.adapters.EventHistory.HistoryEventType;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

public class ConnectionHistoryAdapter extends BaseAdapter {

	private List<ConnectionHistory> connections;
	private Activity activity;
	
	public ConnectionHistoryAdapter(Activity activity,List<ConnectionHistory> connections){
		super();
		this.activity = activity;
		this.connections = connections;		
	}
	
	@Override
	public int getCount() {
		return connections.size();
	}

	@Override
	public Object getItem(int index) {		
		return connections.get(index);
	}

	@Override
	public long getItemId(int index) {		
		return index;
	}

	@Override
	public View getView(int index, View connectioView, ViewGroup parent) {
		ConnectionHistory connection = connections.get(index);
		if(connectioView == null){
			connectioView = activity.getLayoutInflater().inflate(R.layout.listview_connectivity, null);			
		}			
		
		ImageView imageStatus = (ImageView) connectioView.findViewById(R.id.image_connection);
		TextView date = (TextView) connectioView.findViewById(R.id.connection_date);
		
		ConnectionHistoryStatus status = connection.getConnectionStatus();		
		switch (status) {
		case CONNECTED: imageStatus.setImageResource(R.drawable.connection); 			
						break;
		case LOST : imageStatus.setImageResource(R.drawable.connection);
					break;
		}
				
		date.setText(connection.getDate());
		return connectioView;
	}

}
