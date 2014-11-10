package com.example.service_test;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class Reciever extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// bring app to the foreground
		
		Intent myIntent = new Intent(context, MainActivity.class);
		myIntent.addCategory(Intent.CATEGORY_LAUNCHER);
		myIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(myIntent);
		
	}
}
