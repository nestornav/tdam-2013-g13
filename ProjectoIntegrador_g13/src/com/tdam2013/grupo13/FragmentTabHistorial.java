package com.tdam2013.grupo13;

import android.os.Bundle;
import android.app.ActionBar.Tab;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ActionBar;
import android.app.ListFragment;
 
public class FragmentTabHistorial extends ListFragment implements ActionBar.TabListener {
 
    private Fragment mFragment;
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setContentView(R.layout.fragmenttab_historial);
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
    	ft.attach(mFragment);
    }
 
}