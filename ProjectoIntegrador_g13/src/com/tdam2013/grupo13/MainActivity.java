package com.tdam2013.grupo13;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
 
public class MainActivity extends Activity {
    // Declare Tab Variable
    Tab tab;
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Create an actionbar
        ActionBar actionBar = getActionBar();
 
        // Hide Actionbar Icon
        actionBar.setDisplayShowHomeEnabled(false);
 
        // Hide Actionbar Title
        actionBar.setDisplayShowTitleEnabled(false);
 
        // Create Actionbar Tabs
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
 
        // Create first Tab
        tab = actionBar.newTab().setTabListener(new FragmentTabContactos());
        // Create your own custom icon
//        tab.setIcon(R.drawable.tab1);
        tab.setText("Contactos");
        actionBar.addTab(tab);
 
        // Create Second Tab
        tab = actionBar.newTab().setTabListener(new FragmentTabHistorial());
        // Set Tab Title
        tab.setText("Historial");
        actionBar.addTab(tab);
 
        // Create Third Tab
        tab = actionBar.newTab().setTabListener(new FragmentTabConectividad());
        // Set Tab Title
        tab.setText("Conectividad");
        actionBar.addTab(tab);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
