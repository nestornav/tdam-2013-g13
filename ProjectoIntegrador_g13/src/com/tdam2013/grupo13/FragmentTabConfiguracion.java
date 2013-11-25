package com.tdam2013.grupo13;

import android.app.Fragment;
import android.os.Bundle;
import android.preference.PreferenceFragment;

public class FragmentTabConfiguracion extends PreferenceFragment {

	private Fragment mFragment;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Load the preferences from an XML resource
		addPreferencesFromResource(R.xml.preferences);
	}
}
