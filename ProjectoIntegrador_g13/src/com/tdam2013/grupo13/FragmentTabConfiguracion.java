package com.tdam2013.grupo13;

import android.os.Bundle;
import android.app.ActionBar.Tab;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ActionBar;
 
public class FragmentTabConfiguracion extends Fragment implements ActionBar.TabListener {
 
    private Fragment mFragment;
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setContentView(R.layout.fragmenttab_configuracion);
    }
 
    public void onTabSelected(Tab tab, FragmentTransaction ft) {
        mFragment = new FragmentTabConfiguracion();
        ft.add(android.R.id.content, mFragment);
        ft.attach(mFragment);
    }
 
    public void onTabUnselected(Tab tab, FragmentTransaction ft) {
    	ft.detach(mFragment);
    }
 
    public void onTabReselected(Tab tab, FragmentTransaction ft) {
 
    }
 
}
