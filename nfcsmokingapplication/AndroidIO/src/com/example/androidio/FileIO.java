package com.example.androidio;

import java.io.*;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;
 
public class FileIO extends Activity {
	
	
	private String filename;
	public FileIO(String f) {
		filename = f;
	}
	
public void write(String s) throws IOException {
    	
    	
    	
    	File mediaDir = new File(filename);
    	if (!mediaDir.exists()){
    		
    	   mediaDir.createNewFile();
    	   

    	}
    	if (!mediaDir.exists()){
    		Toast.makeText(getApplicationContext(), "no file bro", Toast.LENGTH_LONG).show();
    	} else Toast.makeText(getApplicationContext(), "awww yeaaahh", Toast.LENGTH_LONG).show();
    	
    	FileOutputStream fos = openFileOutput(filename, Context.MODE_PRIVATE);
    	fos.write(s.getBytes());
    	fos.close();
		
	}
    public String read() throws IOException {
    	    	
    	File f = new File(filename);
    	if (!f.exists()){
    		Toast.makeText(getApplicationContext(), "2no file bro", Toast.LENGTH_LONG).show();
    	} else Toast.makeText(getApplicationContext(), "2awww yeaaahh", Toast.LENGTH_LONG).show();
    	
    	
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
