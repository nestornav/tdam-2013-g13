package com.tdam2013.grupo13;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;

import com.tdam2013.grupo13.adapters.EventHistoryAdapter;
import com.tdam2013.grupo13.adapters.EventHistory;
import com.tdam2013.grupo13.adapters.EventHistory.HistoryEventType;

import android.os.Bundle;
import android.renderscript.Sampler.Value;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.app.ActionBar.Tab;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ActionBar;
import android.app.ListFragment;
import android.content.Intent;

public class FragmentTabHistorial extends ListFragment  {
	private Fragment mFragment;

	// Mock de eventos
	private static ArrayList<EventHistory> events;
	static {
		events = new ArrayList<EventHistory>();
		Random ran = new Random();
		HistoryEventType[] types = HistoryEventType.values();
		int n = types.length;
		/*HashSet<String> contactos = new HashSet<String>(
				Arrays.asList(FragmentTabContactos.contactos));

		for (int i = 0; i < 3; i++) {
			for (String contactName : contactos) {
				HistoryEventType type = types[ran.nextInt(n)];
				events.add(new EventHistory(new Date().toLocaleString(),
						contactName, type));
			}
		}*/
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragmenttab_historial, container,
				false);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setListAdapter(new EventHistoryAdapter(getActivity(), events));
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
