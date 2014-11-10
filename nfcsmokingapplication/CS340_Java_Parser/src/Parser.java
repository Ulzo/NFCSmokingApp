


import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;

public class Parser {
	
	public String[] questionTypes = {"Slider","Button","Check"};
	
	public class Question {
		private String type;
		public String text;
		private ArrayList<String> answers = new ArrayList<String>();
		private int numberOfAnswers;
		
	} // end of class Question
	

	// chain off to avoid static errors
	public static void main(String args[])
	{
		Parser test = new Parser();
		
		System.out.println("Hello World!");
		System.out.println("Reading questions from file now ... \n");
		
		test.readQuestionsFromFile();
		
	} // end of main()
	
	// reads question info from specified input file
	void readQuestionsFromFile()
	{
		try 
		{
			File inputFile  = new File( "testQuestions1.txt");	// create a file object
			Scanner current = new Scanner( inputFile );	// create a Scanner of the file object
			String line = "";
			
  	    // Set delimiters for separating input lines and fields to be 
  	    //    new line     \n
  	    //    carriage return \r
  	    //    tab    \t
			
			//current.useDelimiter("\n|\r");
			// Set delimiters for separating input lines
			current.useDelimiter("[|]");
			
			int lineLength = -1;
  	    
			while(current.hasNext())
			{
				line = current.next();
				if(line.charAt(0) == '[')
				{
					lineLength = line.length();
					System.out.println(line.substring(3,lineLength-1));
					//System.out.println(line.substring(1,line.length()-2));
				}
			}
			
			/*
  	    // read the desired URL from the file
  	    theURL = settings.next();
  	    System.out.println("Using URL: " + theURL);
  	    
  	    numberOfLinks = settings.nextInt();
  	    System.out.println("Number of links to include is: " + numberOfLinks);
  	    
  	    if(settings.next().equals("true"))
  	    	webPageLimit = true;
  	    System.out.println("Current webPageLimit is at: " + webPageLimit);
  	    
  	    outputDir = settings.next();
  	    System.out.println("Current output directory is: "+outputDir+"\n");
	    */
			
		// close the input streams
		current.close();
			
			
		}
    	catch (Exception e)
    	{
			// Catch exception if any and display the stack trace 
			System.err.println("Error: " + e.getMessage());
			e.printStackTrace();
    	}
		
	} // end of readQuestionsFromFile()
	
	
	
} // end of class Parser
