package com.tdam2013.grupo13;

import com.tdam2013.grupo13.messaging.WebMessageServiceWrapper;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;

public class FragmentTabConfiguracion extends PreferenceFragment {

	private SharedPreferences sharedPreferences;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// Load the preferences from an XML resource
		addPreferencesFromResource(R.xml.preferences);
		
		sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());

		Preference button = (Preference) findPreference("register_pref_button");
		button.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
			@Override
			public boolean onPreferenceClick(Preference arg0) {
				String userName = sharedPreferences.getString("user_name_pref", "");
				String password = sharedPreferences.getString("password_pref", "");
				new WebMessageServiceWrapper(getActivity()).registerUser(userName, password);
				return true;
			}
		});
	}
}
