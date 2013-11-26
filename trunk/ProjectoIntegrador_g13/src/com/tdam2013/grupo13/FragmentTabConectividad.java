package com.tdam2013.grupo13;

import java.util.ArrayList;

import com.tdam2013.grupo13.adapters.ConnectionHistoryAdapter;
import com.tdam2013.grupo13.model.ConnectionHistory;
import com.tdam2013.grupo13.model.ConnectionHistory.ConnectionHistoryStatus;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.app.ActionBar.Tab;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ActionBar;
import android.app.ListFragment;
 
public class FragmentTabConectividad extends ListFragment {
 
    private Fragment mFragment;
 
    private static ArrayList<ConnectionHistory> connections;
    static{
    	connections = new ArrayList<ConnectionHistory>();
    	connections.add(new ConnectionHistory( ConnectionHistoryStatus.CONNECTED,"12/09/12 15:55"));
    	connections.add(new ConnectionHistory( ConnectionHistoryStatus.CONNECTED,"22/09/12 23:15"));
    }
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);                
        setListAdapter(new ConnectionHistoryAdapter(getActivity(), connections));
    }
 
    @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState){
		return inflater.inflate(R.layout.fragmenttab_conectividad, container,
				false);
	}
    
//    public void onTabSelected(Tab tab, FragmentTransaction ft) {
//        // TODO Auto-generated method stub
//        mFragment = new FragmentTabConectividad();
//        // Attach fragment3.xml layout
//        ft.add(android.R.id.content, mFragment);
//        ft.attach(mFragment);
//    }
// 
//    public void onTabUnselected(Tab tab, FragmentTransaction ft) {
//    	ft.detach(mFragment);
//    }
// 
//    public void onTabReselected(Tab tab, FragmentTransaction ft) {
//        // TODO Auto-generated method stub
// 
//    }
 
}