package com.tdam2013.grupo13;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

import com.tdam2013.grupo13.adapters.ContactsAdapter;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class FragmentTabContactos extends ListFragment implements
		ActionBar.TabListener {

	private Fragment mFragment;

	// Mock de nombres
	public static String[] contactos = { "Alonzo Tavernier", "Julieta Stockstill",
			"Shona Blessing", "Bruno Paneto", "Fonda Zilnicki",
			"Oneida Flickinger", "Obdulia Blunt", "Jona Swiney",
			"Clinton Decamp", "Patricia Hunley", "Brenda Owens", "Lue Wind",
			"Odessa Moline", "Lou Dargan", "Aron Redfern", "Pa Tierney",
			"Maryanna Lone", "Shannan Seiber", "Velia Cao", "Gwendolyn Devens", };
	// Mock teléfonos
	private static ArrayList<String> phones;
	static {
		phones = new ArrayList<String>();
		phones.add("+5491234567890");
		phones.add("+1234567890123");
	}
	// Mock mails
		private static ArrayList<String> mails;
		static {
			mails = new ArrayList<String>();
			mails.add("mail1@mail.com");
			mails.add("another@mail.com");
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

		setListAdapter(new ContactsAdapter(getActivity(), Arrays.asList(contactos)));
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);

		Intent intent = new Intent(getActivity(), ActivityContactProfile.class);
		intent.putExtra("contactName", contactos[position]);
		intent.putStringArrayListExtra("phones", phones);
		intent.putStringArrayListExtra("mails", mails);
		startActivity(intent);
	}

	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		mFragment = new FragmentTabContactos();
		ft.add(android.R.id.content, mFragment);
		ft.attach(mFragment);
	}

	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		ft.detach(mFragment);
	}

	public void onTabReselected(Tab tab, FragmentTransaction ft) {

	}

}
