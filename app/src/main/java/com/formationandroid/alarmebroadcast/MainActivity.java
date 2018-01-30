package com.formationandroid.alarmebroadcast;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity
{
	
	// Vues :
	private TimePicker timePicker = null;
	

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// init :
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// vues :
		timePicker = findViewById(R.id.timepicker);
		timePicker.setIs24HourView(true);
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
	
}
