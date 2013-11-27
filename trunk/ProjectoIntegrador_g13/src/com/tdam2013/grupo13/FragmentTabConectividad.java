package com.tdam2013.grupo13;

import java.util.ArrayList;

import com.tdam2013.grupo13.adapters.ConnectionHistoryAdapter;
import com.tdam2013.grupo13.dataBase.DataBaseManager;
import com.tdam2013.grupo13.model.ConnectionHistory;
import com.tdam2013.grupo13.model.ConnectionHistory.ConnectionHistoryStatus;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.app.ActionBar.Tab;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ActionBar;
import android.app.ListFragment;
import android.database.Cursor;
 
public class FragmentTabConectividad extends ListFragment {
 
    private Fragment mFragment;
    private DataBaseManager db;
    
    private static ArrayList<ConnectionHistory> connections;
	private ConnectionHistoryAdapter adapter;
//    static{
//    	connections = new ArrayList<ConnectionHistory>();
//    	connections.add(new ConnectionHistory( ConnectionHistoryStatus.CONNECTED,"12/09/12 15:55"));
//    	connections.add(new ConnectionHistory( ConnectionHistoryStatus.CONNECTED,"22/09/12 23:15"));
//    }
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new DataBaseManager(getActivity().getApplicationContext());
        adapter = new ConnectionHistoryAdapter(getActivity(), getConnectivityLog());
        setListAdapter(adapter);
    }
    
    public void onResume(){
    	super.onResume();
    	adapter.update(getConnectivityLog());
    }
 
    @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState){
		return inflater.inflate(R.layout.fragmenttab_conectividad, container,
				false);
	}
    
    public ArrayList<ConnectionHistory> getConnectivityLog(){
    	ArrayList<ConnectionHistory> logs = new ArrayList<ConnectionHistory>();     	
    	ConnectionHistory ch;
    	Cursor c = db.getConnectivityLog();
//    	Toast.makeText(getActivity().getApplicationContext(), "Cant Regostrops"+c.getColumnCount(), Toast.LENGTH_SHORT).show();
    	if(c.moveToFirst()){
	    	do{
	    		String status = c.getString(2);    		
	    		String date = c.getString(0);
	    		if(status.equals(ConnectionHistoryStatus.CONNECTED.toString())){
	    			ch = new ConnectionHistory(ConnectionHistoryStatus.CONNECTED, date);
	    		}else{
	    			ch = new ConnectionHistory(ConnectionHistoryStatus.LOST, date);
	    		}    		
	    		logs.add(ch);
	    	}while(c.moveToNext());
    	}
    	return logs;
    }
 
}