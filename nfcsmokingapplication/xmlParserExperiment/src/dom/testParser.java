package dom;

//import java.lang.String;

public class testParser {
	
	public static void main(String args[])
	{
		parserDOM test1 = new parserDOM();
		System.out.println("Attempting to extract questions now...\n");
		test1.getAllQuestions("sampleSurvey1.xml");
		//System.out.println("\n Now attempting to parse John's questions...\n");
		//test1.getAllQuestions("John_survey.xml");
		System.out.println("\n All Questions extracted!");
	}

}
