package cs340.nfc.smoking.survey;

import android.app.Application;

public class SharedVars extends Application{
	
	private String surveyFile = "John_survey.xml";
	
	public void setSurveyFile(String fileName)
	{
		surveyFile = fileName;
	}
	public String getSurveyFile()
	{
		return surveyFile;
	}
}