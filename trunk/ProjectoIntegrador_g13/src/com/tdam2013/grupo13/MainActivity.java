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
        ActionBar actionBar = getActionBar();
 
        actionBar.setDisplayShowHomeEnabled(false);
 
        actionBar.setDisplayShowTitleEnabled(false);
 
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
 
        tab = actionBar.newTab().setTabListener(new FragmentTabContactos());
        tab.setText("Contactos");
        actionBar.addTab(tab);
 
        tab = actionBar.newTab().setTabListener(new FragmentTabHistorial());
        tab.setText("Historial");
        actionBar.addTab(tab);
 
        tab = actionBar.newTab().setTabListener(new FragmentTabConectividad());
        tab.setText("Conectividad");
        actionBar.addTab(tab);
        
        tab = actionBar.newTab().setTabListener(new FragmentTabConfiguracion());
        tab.setText("Configuracion");
        actionBar.addTab(tab);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
