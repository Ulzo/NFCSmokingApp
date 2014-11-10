package cs340.nfc.smoking.survey;

import java.io.Writer;

import cs340.nfc.smoking.survey.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Start extends Activity {

	SharedVars shared;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);

		//Edit global variables
		shared = (SharedVars)getApplicationContext();
	
		/*Button nfc_button = (Button) findViewById(R.id.nfc);
		nfc_button.setOnClickListener(new View.OnClickListener()
		{
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent nfc_page = new Intent(getApplicationContext(), NFC_Settings.class);
				
				//Call nfc page with retrieval of data (IMPLEMENT LATER)
				startActivity(nfc_page);
			}
			
		});
		*/
		Button survey_btn = (Button) findViewById(R.id.view_survey);
		survey_btn.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View arg0) {
				Intent view_survey = new Intent(getApplicationContext(), SurveyPop.class);
				startActivity(view_survey);			
			}
		});
		Button app_button = (Button) findViewById(R.id.proceed);
		app_button.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				Intent homePage = new Intent(getApplicationContext(), Home.class);		
				startActivity(homePage);
			}
		});
		
		Button history_button = (Button) findViewById(R.id.history_btn);
		history_button.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				Intent homePage = new Intent(getApplicationContext(), NfcWriterActivity.class);		
				startActivity(homePage);
			}
		});
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_start, menu);
		return true;
	}
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId())
		{
			case R.id.menu_settings:
				//Call activity to choose survey
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	    //respond to menu item selection
	}
}