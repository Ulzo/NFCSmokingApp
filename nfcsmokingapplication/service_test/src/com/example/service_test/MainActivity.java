package com.example.service_test;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

//import java.lang.Thread;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // keep this, to hide the app in the bg
        Button hideApp = (Button) findViewById(R.id.hide);
        
        hideApp.setOnClickListener(new Button.OnClickListener() {

			public void onClick(View arg0) {
				// send app to the background
				Intent myIntent = new Intent(Intent.ACTION_MAIN);
				myIntent.addCategory(Intent.CATEGORY_HOME);
				startActivity(myIntent);
				
			}
        });

        
        
        /*        REMOVE FOR NFC INTENT        */
        // send signal test
        // Will be removed once NFC intent is setup
        Button sendSignalButton = (Button) findViewById(R.id.signalSend);
        sendSignalButton.setOnClickListener(new Button.OnClickListener() {

			public void onClick(View arg0) {
				// this part of the code just tests 
				// 1: throwing to BG
				// wait a few seconds, just because
				// 2 coming back to the fg
				
				
				// send app to the background
				Intent myIntent = new Intent(Intent.ACTION_MAIN);
				myIntent.addCategory(Intent.CATEGORY_HOME);
				startActivity(myIntent);
				
				
				// wait a few seconds 
				try {
					Thread.sleep(4000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				// bring app back to foreground
				// change Intent.ACTION_CALL to NFC intent in AndroidManifest.xml
				Intent i = new Intent(Intent.ACTION_CALL);
				// send broadcast not needed for NFC, but I needed a way to test the receiver
				sendBroadcast(i);
				
			}
        });
        
        /*    END    REMOVE FOR NFC INTENT     */        
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    

}
