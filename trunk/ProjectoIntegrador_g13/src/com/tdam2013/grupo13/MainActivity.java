package com.tdam2013.grupo13;

import com.tdam2013.grupo13.dataBase.DataBaseManager;
import com.tdam2013.grupo13.utils.ActiveService;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

public class MainActivity extends Activity {
	// Declare Tab Variable
//	Tab tab;
//	private FragmentTabContactos tabContactos;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		DataBaseManager db = new DataBaseManager(MainActivity.this);
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayShowHomeEnabled(false);
		actionBar.setDisplayShowTitleEnabled(false);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		actionBar.addTab(actionBar
				.newTab()
				.setText("Contactos")
				.setTabListener(
						new TabListener<FragmentTabContactos>(this,
								"Contactos", FragmentTabContactos.class)));

		actionBar.addTab(actionBar
				.newTab()
				.setText("Historial")
				.setTabListener(
						new TabListener<FragmentTabHistorial>(this,
								"Historial", FragmentTabHistorial.class)));

		actionBar
				.addTab(actionBar
						.newTab()
						.setText("Conectividad")
						.setTabListener(
								new TabListener<FragmentTabConectividad>(this,
										"Conectividad",
										FragmentTabConectividad.class)));

		actionBar
				.addTab(actionBar
						.newTab()
						.setText("Configuracion")
						.setTabListener(
								new TabListener<FragmentTabConfiguracion>(this,
										"Configuracion",
										FragmentTabConfiguracion.class)));
		
		startService(new Intent(this,ActiveService.class));	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		// getMenuInflater().inflate(R.menu.main, msenu);
		return true;
	}

	public static class TabListener<T extends Fragment> implements
			ActionBar.TabListener {
		private final Activity mActivity;
		private final String mTag;
		private final Class<T> mClass;
		private final Bundle mArgs;
		private Fragment mFragment;

		public TabListener(Activity activity, String tag, Class<T> clz) {
			this(activity, tag, clz, null);
		}

		public TabListener(Activity activity, String tag, Class<T> clz,
				Bundle args) {
			mActivity = activity;
			mTag = tag;
			mClass = clz;
			mArgs = args;

			// Check to see if we already have a fragment for this tab, probably
			// from a previously saved state. If so, deactivate it, because our
			// initial state is that a tab isn't shown.
			mFragment = mActivity.getFragmentManager().findFragmentByTag(mTag);
			if (mFragment != null && !mFragment.isDetached()) {
				FragmentTransaction ft = mActivity.getFragmentManager()
						.beginTransaction();
				ft.detach(mFragment);
				ft.commit();
			}
		}

		public void onTabSelected(Tab tab, FragmentTransaction ft) {
			if (mFragment == null) {
				mFragment = Fragment.instantiate(mActivity, mClass.getName(),
						mArgs);
				ft.add(android.R.id.content, mFragment, mTag);
			} else {
				ft.attach(mFragment);
			}
		}

		public void onTabUnselected(Tab tab, FragmentTransaction ft) {
			if (mFragment != null) {
				ft.detach(mFragment);
			}
		}

		public void onTabReselected(Tab tab, FragmentTransaction ft) {
			Toast.makeText(mActivity, "Reselected!", Toast.LENGTH_SHORT).show();
		}
	}

	// protected void showFragmentContactos(){
	// if(tabContactos == null){
	// this.tabContactos = new FragmentTabContactos();
	// }
	// showFragment(tabContactos);
	// }
	//
	// protected void showFragment(Fragment fragment) {
	// FragmentTransaction ft = getFragmentManager().beginTransaction();
	// ft.replace(R.id.layout_container, fragment);
	// ft.addToBackStack(fragment.getClass().getName());
	// ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
	// ft.commit();
	// }
}
