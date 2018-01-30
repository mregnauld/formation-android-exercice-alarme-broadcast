package com.formationandroid.alarmebroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;


public class BatterieBroadcastReceiver extends BroadcastReceiver
{
	
	@Override
	public void onReceive(Context context, Intent intent)
	{
		// affichage d'un message si la batterie est presque vide :
		Toast.makeText(context, R.string.main_message_batterie, Toast.LENGTH_LONG).show();
	}
	
}
