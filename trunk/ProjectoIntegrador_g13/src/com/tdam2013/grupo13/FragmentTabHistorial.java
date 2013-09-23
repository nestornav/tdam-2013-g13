package com.tdam2013.grupo13;

import java.util.ArrayList;

import com.tdam2013.grupo13.adapters.AdapterHistoryList;
import com.tdam2013.grupo13.adapters.HistoryEvent;
import com.tdam2013.grupo13.adapters.HistoryEvent.HistoryEventType;

import android.os.Bundle;
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
 
public class FragmentTabHistorial extends ListFragment implements ActionBar.TabListener {
	private Fragment mFragment;

	// Mock de eventos
	private static ArrayList<HistoryEvent> events;
	static {
		events = new ArrayList<HistoryEvent>();
		events.add(new HistoryEvent("13/03/99 22:11", "Jhon Smith", HistoryEventType.CALL));
		events.add(new HistoryEvent("23/11/86 05:55", "Albert Einstein", HistoryEventType.SMS));
		events.add(new HistoryEvent("13/03/99 22:11", "Jhon Smith", HistoryEventType.CALL));
		events.add(new HistoryEvent("23/11/86 05:55", "Albert Einstein", HistoryEventType.SMS));
		events.add(new HistoryEvent("13/03/99 22:11", "Jhon Smith", HistoryEventType.CALL));
		events.add(new HistoryEvent("23/11/86 05:55", "Albert Einstein", HistoryEventType.SMS));
		events.add(new HistoryEvent("13/03/99 22:11", "Jhon Smith", HistoryEventType.CALL));
		events.add(new HistoryEvent("23/11/86 05:55", "Albert Einstein", HistoryEventType.SMS));
		events.add(new HistoryEvent("13/03/99 22:11", "Jhon Smith", HistoryEventType.CALL));
		events.add(new HistoryEvent("23/11/86 05:55", "Albert Einstein", HistoryEventType.SMS));
		events.add(new HistoryEvent("23/11/86 05:55", "Albert Einstein", HistoryEventType.SMS));
		events.add(new HistoryEvent("13/03/99 22:11", "Jhon Smith", HistoryEventType.CALL));
		events.add(new HistoryEvent("23/11/86 05:55", "Albert Einstein", HistoryEventType.SMS));
		events.add(new HistoryEvent("13/03/99 22:11", "Jhon Smith", HistoryEventType.CALL));
		events.add(new HistoryEvent("23/11/86 05:55", "Albert Einstein", HistoryEventType.SMS));
		events.add(new HistoryEvent("23/11/86 05:55", "Albert Einstein", HistoryEventType.SMS));
		events.add(new HistoryEvent("13/03/99 22:11", "Jhon Smith", HistoryEventType.CALL));
		events.add(new HistoryEvent("23/11/86 05:55", "Albert Einstein", HistoryEventType.SMS));
		events.add(new HistoryEvent("13/03/99 22:11", "Jhon Smith", HistoryEventType.CALL));
		events.add(new HistoryEvent("23/11/86 05:55", "Albert Einstein", HistoryEventType.SMS));
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

		setListAdapter(new AdapterHistoryList(getActivity(), events));
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);

//		Intent intent = new Intent(getActivity(), ActivityContactProfile.class);
//		intent.putExtra("contactName", contactos[position]);
//		intent.putStringArrayListExtra("phones", events);
//		intent.putStringArrayListExtra("mails", mails);
//		startActivity(intent);
	}

	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		mFragment = new FragmentTabHistorial();
		ft.add(android.R.id.content, mFragment);
		ft.attach(mFragment);
	}

	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		ft.detach(mFragment);
	}

	public void onTabReselected(Tab tab, FragmentTransaction ft) {

	}

}
