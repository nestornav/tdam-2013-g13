package com.tdam2013.grupo13.notification;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.tdam2013.grupo13.MainActivity;
import com.tdam2013.grupo13.R;

public class MyNotificationManager {
	
	public static void notify(Activity act, String msg, String time){
		int icon = R.drawable.sms_ico;
		CharSequence tickerText = "Grupo 13";
		long when = System.currentTimeMillis();
		
		Notification notification = new Notification(icon, tickerText, when);
		
		Intent intent = new Intent(act, MainActivity.class);
		
		PendingIntent pending = PendingIntent.getActivity(act, 0, intent, 0);
		
		notification.setLatestEventInfo(act, msg, time, pending);
		
		NotificationManager nm = (NotificationManager) act.getSystemService(Context.NOTIFICATION_SERVICE);
		nm.notify(1, notification);
	}
}