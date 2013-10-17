package com.tdam2013.grupo13;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.app.ActionBar.Tab;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ActionBar;
 
public class FragmentTabConfiguracion extends Fragment{
 
    private Fragment mFragment;
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getActivity().setContentView(R.layout.fragmenttab_configuracion);
        
    }
    
    @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragmenttab_configuracion, container,
				false);
	}
 
}
