package com.formationandroid.alarmebroadcast;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity
{
	
	// Vues :
	private TimePicker timePicker = null;
	
	// Broadcast receiver :
	private BatterieBroadcastReceiver batterieBroadcastReceiver = null;
	

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// init :
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// vues :
		timePicker = findViewById(R.id.timepicker);
		timePicker.setIs24HourView(true);
		
		// broadcast receiver :
		batterieBroadcastReceiver = new BatterieBroadcastReceiver();
		
		// connexion au broadcast receiver :
		registerReceiver(batterieBroadcastReceiver, new IntentFilter(Intent.ACTION_BATTERY_LOW));
	}
	
	/**
	 * Listener clic bouton ajouter réveil.
	 * @param view Bouton
	 */
	public void onClickAjouterReveil(View view)
	{
		// date future :
		Calendar calendar = Calendar.getInstance();
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), timePicker.getCurrentHour(), timePicker.getCurrentMinute(), 0);
		
		// action future à effectuer :
		Intent intent = new Intent(this, MainActivity.class);
		PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
		
		// alarme :
		AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
		if (manager != null)
		{
			manager.setRepeating(AlarmManager.RTC, calendar.getTimeInMillis(), 1000 * 3600 * 24, pendingIntent);
		}
		
		// message de fin :
		Toast.makeText(this, R.string.main_message_ajout, Toast.LENGTH_LONG).show();
	}
	
	@Override
	protected void onDestroy()
	{
		// init :
		super.onDestroy();
		
		// déconnexion du broadcast receiver :
		unregisterReceiver(batterieBroadcastReceiver);
	}
	
	
	/**
	 * Broadcast receiver pour capter l'événement "niveau de batterie bas".
	 */
	public class BatterieBroadcastReceiver extends BroadcastReceiver
	{
		
		@Override
		public void onReceive(Context context, Intent intent)
		{
			// affichage d'un message si la batterie est presque vide :
			Toast.makeText(context, R.string.main_message_batterie, Toast.LENGTH_LONG).show();
		}
		
	}
	
}
