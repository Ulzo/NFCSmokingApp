package cs340.nfc.smoking.survey;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Calendar;

import android.content.Intent;
import android.content.res.Resources;
import android.nfc.FormatException;
import android.nfc.NdefMessage;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import cs340.nfc.smoking.survey.R;

import cs340.nfc.creator.NdefWriter;
import cs340.nfc.creator.NdefWriter.NdefWriterListener;


public class NfcWriterActivity extends NfcDetectorActivity implements NdefWriterListener {

	protected NdefWriter writer;
	private int mRowCount;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.writer);
	}

	
	@Override
	protected void onNfcFeatureFound() {
		writer = new NdefWriter(this);
		writer.setListener(this);
		
        toast(getString(R.string.nfcMessage));
	}

	
	@Override
	protected void onNfcFeatureNotFound() {
        toast(getString(R.string.noNfcMessage));
	}
	
	@Override
	public void nfcIntentDetected(Intent intent, String action) {
		byte[] messagePayload; 

		Calendar ci = Calendar.getInstance();
		mRowCount++;
		TableLayout date_table = (TableLayout)findViewById(R.id.date_table);
		TableRow date_row = new TableRow(this);
		TextView date_tv = new TextView(this);
		TableLayout time_table = (TableLayout)findViewById(R.id.time_table);
		TableRow time_row = new TableRow(this);
		TextView time_tv = new TextView(this);
		
		String CiDate = "" + ci.get(Calendar.YEAR) + "-" + 
				(ci.get(Calendar.MONTH) + 1) + "-" +
				ci.get(Calendar.DAY_OF_MONTH);
		String CiTime = " " +
				(ci.get(Calendar.HOUR)+12 )+ ":" +
				ci.get(Calendar.MINUTE) +  ":" +
				ci.get(Calendar.SECOND);
		date_tv.setText(CiDate);
		date_row.addView(date_tv);
		date_table.addView(date_row);
		time_tv.setText(CiTime);
		time_row.addView(time_tv);
		time_table.addView(time_row);

		try {
	        Resources res = getResources();
	        
	        InputStream in = res.openRawResource(R.raw.aar);

	        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

	        byte[] buffer = new byte[1024];
	        int read;
	        do {
	        	read = in.read(buffer, 0, buffer.length);
	        	
	        	if(read == -1) {
	        		break;
	        	}
	        	
	        	byteArrayOutputStream.write(buffer, 0, read);
	        } while(true);

	        messagePayload = byteArrayOutputStream.toByteArray();
	    } catch (Exception e) {
	    	throw new RuntimeException("Cannot access resource", e);
	    }
		
		NdefMessage message;
		try {
			message = new NdefMessage(messagePayload);
		} catch (FormatException e) {
			// ups, illegal ndef message payload
			
			return;
		}

		// then write
		if(writer.write(message, intent)) {
			// do something
		} else {
			// do nothing(?)
		}
	}
	
	@Override
	public void writeNdefFormattedFailed(Exception e) {
        toast(getString(R.string.ndefFormattedWriteFailed) + ": " + e.toString());
	}

	@Override
	public void writeNdefUnformattedFailed(Exception e) {
        toast(getString(R.string.ndefUnformattedWriteFailed, e.toString()));
	}

	@Override
	public void writeNdefNotWritable() {
        toast(getString(R.string.tagNotWritable));
	}

	@Override
	public void writeNdefTooSmall(int required, int capacity) {
		toast(getString(R.string.tagTooSmallMessage,  required, capacity));
	}

	@Override
	public void writeNdefCannotWriteTech() {
        toast(getString(R.string.cannotWriteTechMessage));
	}

	@Override
	public void wroteNdefFormatted() {
	    toast(getString(R.string.wroteFormattedTag));
	}

	@Override
	public void wroteNdefUnformatted() {
	    toast(getString(R.string.wroteUnformattedTag));
	}
	
	
	public void toast(String message) {
		Toast toast = Toast.makeText(this, message, Toast.LENGTH_LONG);
		toast.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL, 0, 0);
		toast.show();
	}
}
