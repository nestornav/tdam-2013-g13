package com.tdam2013.grupo13;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;

import com.tdam2013.grupo13.adapters.EventHistoryAdapter;
import com.tdam2013.grupo13.adapters.WebMessageAdapter;
import com.tdam2013.grupo13.dataBase.DataBaseManager;
import com.tdam2013.grupo13.model.EventHistory;
import com.tdam2013.grupo13.model.EventHistory.HistoryEventType;
import com.tdam2013.grupo13.model.WebMessage;

import android.os.Bundle;
import android.renderscript.Sampler.Value;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.app.ActionBar.Tab;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ActionBar;
import android.app.ListFragment;
import android.content.Intent;
import android.database.Cursor;

public class FragmentTabHistorial extends ListFragment  {
	private Fragment mFragment;

	// Mock de eventos
	private static ArrayList<WebMessage> messages;
	private DataBaseManager db;

	private WebMessageAdapter adapter;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragmenttab_historial, container,
				false);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		db = new DataBaseManager(getActivity().getApplicationContext());
		adapter = new WebMessageAdapter(getActivity(), getWebMessages());
		setListAdapter(adapter);
	}
	
	public void onResume(){
    	super.onResume();
    	adapter.update(getWebMessages());
    }
	
	public ArrayList<WebMessage> getWebMessages(){
		messages = new ArrayList<WebMessage>();
		WebMessage msg;
		Cursor c = db.getAllMessage();    	
    	if(c.moveToFirst()){
        	do {
        		String message = c.getString(0);
        		String date = c.getString(1);
        		String receiverName = c.getString(2);
        		msg = new WebMessage(date, message,receiverName);
        		messages.add(msg); 
			} while (c.moveToNext());    		    		
    	};
		return messages;
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);

		// Intent intent = new Intent(getActivity(),
		// ActivityContactProfile.class);
		// intent.putExtra("contactName", contactos[position]);
		// intent.putStringArrayListExtra("phones", events);
		// intent.putStringArrayListExtra("mails", mails);
		// startActivity(intent);
	}

}
