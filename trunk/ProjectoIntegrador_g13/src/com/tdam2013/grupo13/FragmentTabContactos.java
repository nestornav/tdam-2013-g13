package com.tdam2013.grupo13;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
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
	private String[] contactos = { "Alonzo Tavernier", "Julieta Stockstill",
			"Shona Blessing", "Bruno Paneto", "Fonda Zilnicki",
			"Oneida Flickinger", "Obdulia Blunt", "Jona Swiney",
			"Clinton Decamp", "Patricia Hunley", "Brenda Owens", "Lue Wind",
			"Odessa Moline", "Lou Dargan", "Aron Redfern", "Pa Tierney",
			"Maryanna Lone", "Shannan Seiber", "Velia Cao", "Gwendolyn Devens", };

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.fragmenttab_contactos, container,
				false);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		// Establecemos el Adapter a la Lista del Fragment
		setListAdapter(new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_1, contactos));
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);

		// Mostramos un mensaje con el elemento pulsado
		Toast.makeText(getActivity(), "Vamos a la ventana de mensajes con el contacto " + contactos[position],
				Toast.LENGTH_SHORT).show();
		Intent intent = new Intent(getActivity(), ActivityContactProfile.class);
	}

	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		mFragment = new FragmentTabContactos();
		ft.add(android.R.id.content, mFragment);
		ft.attach(mFragment);
	}

	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		ft.remove(mFragment);
	}

	public void onTabReselected(Tab tab, FragmentTransaction ft) {

	}

}
