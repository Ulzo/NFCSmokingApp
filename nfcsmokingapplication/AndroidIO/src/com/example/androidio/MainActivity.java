package com.example.androidio;

import java.io.*;
import java.lang.Thread;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.widget.Toast;
 

public class MainActivity extends Activity {
	private static final String filename = "nfctext";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        
        String r;
        
        //sleep(5000);
        try {
        	
        	//Toast.makeText(getApplicationContext(), "write", Toast.LENGTH_LONG).show();
			write("dookie");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Toast.makeText(getApplicationContext(), "don guufed", Toast.LENGTH_LONG).show();
			e.printStackTrace();
		}
        
       
        try {
			r = read();
			
			Toast.makeText(getApplicationContext(), r, Toast.LENGTH_LONG).show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
    }

    
    public void sleep(int secs) {
    	try {
			Thread.sleep(secs);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    
public void write(String s) throws IOException {
    	FileOutputStream fos = openFileOutput(filename, Context.MODE_PRIVATE);
    	fos.write(s.getBytes());
    	fos.close();
    	Toast.makeText(getApplicationContext(), "wrote", Toast.LENGTH_LONG).show();
	}





    public String read() throws IOException {
    	    	
    	File f = new File(filename);
    	if (!f.exists()){
    		Toast.makeText(getApplicationContext(), "read: file does not exist", Toast.LENGTH_LONG).show();
    	} else Toast.makeText(getApplicationContext(), "read: file exists", Toast.LENGTH_LONG).show();
    	
    	
    	InputStream is = new FileInputStream(filename);
    	InputStreamReader isr = new InputStreamReader(is);
    	BufferedReader br = new BufferedReader(isr);
    	
    	String everything = "";
    	String line;
    	
    	while ( (line = br.readLine()) != null)
    		everything += line + '\n';
    	br.close();
    	return everything;
    }
    
}
