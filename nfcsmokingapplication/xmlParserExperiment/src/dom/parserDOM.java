package dom;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

// added radio to qType
enum qType {button, check, slider, radio};

public class parserDOM {
	

	public void getAllQuestions(String fileName) {
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			File file = new File(fileName);
			if (file.exists()) {
				Document doc = db.parse(file);
				Element docEle = doc.getDocumentElement();
				
				int answerCounter = 0;
				int numOfChoices = 0;
				int numOfDigits = 0;
				int lengthOfDigits = 0;
				
				// Print root element of the document
				System.out.println("Root element of the document: "
						+ docEle.getNodeName());
				
				NodeList quesList = docEle.getElementsByTagName("Question");
				
				/*// Print total question elements in document
				System.out.println("Total number of questions: " + quesList.getLength());*/
				
				if (quesList != null && quesList.getLength() > 0) {
					for (int i = 0; i < quesList.getLength(); i++) {
				
						Node node = quesList.item(i);
						NodeList choices;
						
						if (node.getNodeType() == Node.ELEMENT_NODE) {
							
							System.out.println("=====================");
		
							Element e = (Element) node;
							NodeList nodeList = e.getElementsByTagName("QuesID");
							
							// create a new Question
							Question thisQues = new Question();
							
							//printElementText(e,"QuesID");
							thisQues.setQuesID(extractElementText(e,"QuesID"));
							System.out.println("QuesID is: "+thisQues.getQuesID());
							
							// note: extraction of <SubID> </SubID> skipped for all questions
							
							//printElementText(e,"QuestionString");
							thisQues.setQuestion(extractElementText(e,"QuestionString"));
							System.out.println("QuesString is: "+thisQues.getQuestion());
							
							//printElementText(e,"QuesType");
							thisQues.setQuesType(extractElementText(e,"QuesType"));
							System.out.println("QuesType is: "+thisQues.getQuesType());
							
							String quesType = ""+thisQues.getQuesType();
							
							// only non-slider questions have a NumOfChoices
							if(!quesType.equalsIgnoreCase("slider"))	
							{
								nodeList = e.getElementsByTagName("NumOfChoices");
								
								try {
									//String[] choiceElements = nodeList.item(0).getChildNodes().item(0).getNodeValue().split(" ");
									ArrayList<String> choiceList = new ArrayList<String>(Arrays.asList(nodeList.item(0).getChildNodes().item(0).getNodeValue().split(" ")));
									
									// numOfChoices is the 0th element of choiceElements[]
									//numOfChoices = Integer.parseInt(choiceElements[0].trim());
									numOfChoices = Integer.parseInt(choiceList.get(0).trim());
								}
								
								catch (Exception nc)
								{
									lengthOfDigits = (nodeList.item(0).getChildNodes().item(0)
											.getNodeValue() ).length();
									//System.out.println("the length of the NumOfChoices string is: "+lengthOfDigits);
									
									numOfDigits = lengthOfDigits - 4;
									numOfChoices = Integer.parseInt(nodeList.item(0).getChildNodes().item(0).getNodeValue().substring(1,2));			
								}						
								
								//System.out.println("NumOfChoices: "+numOfChoices);
								thisQues.setNumChoices(numOfChoices);
								System.out.println("numChoices is: "+thisQues.getNumChoices());
								
								choices = docEle.getElementsByTagName("choice");
								
								/* Need to only print/store the relevant answers for a particular question, but 
								 * the answers for all of the questions are all stored in choices in 
								 * sequential order.
								 * 
								 * Use an answerCounter to track the starting index of the answers 
								 * for each question. 
								 */
								
								System.out.println("Available choices: ");
								for(int k = answerCounter; k < numOfChoices+answerCounter; k++)
								{
									thisQues.addToChoices(choices.item(k).getChildNodes().item(0).getNodeValue());
									
									// double-check: print the item just added to ArrayList<String> choices 
									System.out.println(thisQues.getChoices().get(thisQues.getChoicesSize()-1));
								}
								
								answerCounter += numOfChoices;
								
								
								/*for (int j = 0; j< choices.getLength(); j++) 
								{	
									//System.out.println("Choice: " + choices.item(j).toString());
	
									Node nodech = choices.item(i);
									NodeList nodeListch = e.getElementsByTagName("choice");
	
									if (nodech.getNodeType() == Node.ELEMENT_NODE) {
										System.out.println("Choice: "
												+ nodeListch.item(0).getChildNodes().item(0)
												.getNodeValue());
									}
								}*/
							
							} // end of if(notSlider)
							
							else 
							{			
							// if QuesType == slider, need to extract min & max value fields and increment (numDiv) for slider
															
								//nodeList = e.getElementsByTagName("QuesType");
								//if(nodeList.item(0).getChildNodes().item(0).getNodeValue().equalsIgnoreCase("slider")){
								if(thisQues.getQuesType()==qType.slider) {
									
									//printElementText(e,"StartNum");
									
									thisQues.getSlider().setStart(Integer.parseInt(extractElementText(e,"StartNum")));
									System.out.println("starting number: "+ thisQues.getSlider().getStart());
									
									//printElementText(e,"StartLabel");
									
									thisQues.getSlider().setStartLabel(extractElementText(e,"StartLabel"));
									System.out.println("starting label: "+ thisQues.getSlider().getStartLabel());
									
									try {
										//printElementText(e,"MiddleLabel");
										
										thisQues.getSlider().setMiddleLabel(extractElementText(e,"MiddleLabel"));
										System.out.println("middle label: "+ thisQues.getSlider().getMiddleLabel());
									}
									catch (Exception ml)
									{
										;
									}

									//printElementText(e,"EndNum");
									
									thisQues.getSlider().setEnd(Integer.parseInt(extractElementText(e,"EndNum")));
									System.out.println("end num: "+ thisQues.getSlider().getEnd());

									//printElementText(e,"EndLabel");
									
									thisQues.getSlider().setEndLabel(extractElementText(e,"EndLabel"));
									System.out.println("end label: "+ thisQues.getSlider().getEndLabel());
									
									try {
										//printElementText(e,"NumOfDivisions");
										
										thisQues.getSlider().setNumDiv(Integer.parseInt(extractElementText(e,"NumOfDivisions")));
										System.out.println("number of divisions: "+thisQues.getSlider().getNumDiv());			
									}
									catch (Exception nd)
									{
										;
									}
								}						
							}
							
							// for all questions, need to extract DefaultNext, Condition, and NextQuestion fields 
							
							// DefaultNext is specified for all questions
							//printElementText(e,"DefaultNext");
							
							thisQues.setDefaultNext(extractElementText(e,"DefaultNext"));
							System.out.println("default next question is: "+thisQues.getDefaultNext());
							                                   							                             
							// not all questions have a Condition and corresponding NextQuestion
							// (Radio) Buttons Condition, CheckBoxes Condition, Slider Condition
							nodeList = e.getElementsByTagName("Condition");
							
							//String firstCond = nodeList.item(0).getFirstChild().getNodeValue();
							String condLine = "";
							String condLineTrim = "";
																						
							try {
								//Integer firstCondNum = Integer.parseInt(firstCond.substring(0,1));
								
								String condString = "";
								String nextQuesString = "";
								qType currentQues = thisQues.getQuesType();
								
								for(int y = 0; y < nodeList.getLength(); y++)
								{												
									// extract each condition line
									condLine = nodeList.item(y).getChildNodes().item(0).getNodeValue();
									condLineTrim = condLine.trim();
									
									if(condLine.length() > 4) {
										System.out.println("Condition code: "+condLine);
										
										//get NextQuestion that corresponds to the condition
										// RadioButtons NextQuestion, CheckBoxes NextQuestion, Slider NextQuestion
										nodeList = e.getElementsByTagName("NextQuestion");
										nextQuesString = nodeList.item(y).getChildNodes().item(0).getNodeValue();
										
										// parse condition string according to appropriate parsing scheme

										if(currentQues==qType.radio | currentQues==qType.button)
										{
											System.out.println("button condition");
											thisQues.parseCondition("button", condLine, nextQuesString);
											
											for(int d = 0; d < thisQues.getButtonCondition().size(); d++)
											{
												System.out.println("these conditions are stored in buttonCondition: "+thisQues.getButtonCondition().get(d).getCond());
												System.out.println("and the next question is: "+thisQues.getButtonCondition().get(d).getNextQues());
											}
										}
										else if(currentQues==qType.check)
										{
											System.out.println("check condition");
											thisQues.parseCondition("check", condLine, nextQuesString);
											
											for(int j = 0; j < thisQues.getCheckConditions().size(); j++)
											{
												System.out.println("these conditions are stored in checkCondition: "+thisQues.getCheckConditions().get(j).getCond());
												System.out.println("and the next question is: "+thisQues.getCheckConditions().get(j).getNextQues());
											}
										}
										else if(currentQues==qType.slider)
										{
											System.out.println("slider condition");
											thisQues.parseCondition("slider", condLine, nextQuesString);
											System.out.println("these conditions are stored in sliderCondition: ");
											System.out.println("lowerLimit: "+thisQues.getSliderConditions().getLowerLim());
											System.out.println("upperLimit: "+thisQues.getSliderConditions().getUpperLim());
											System.out.println("next question: "+thisQues.getSliderConditions().getNextQues());
											
										}
										
										//System.out.println("next question is: "+nextQuesString);
										
										break;
										
									} // end of if statement
									
	
								} // end of for loop
																
							} // end of try block
							catch (Exception cc)
							{
								System.out.println("this question has no condition codes and no NextQuestion");
								// set values for Conditions to null
							}
							
						}
						
					}
				} else {
					System.exit(1);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	
	// given an Element e and a String tagName, prints the text
	// of the tag from the Element.
	public void printElementText(Element e, String tagName)
	{
		NodeList nodeList = e.getElementsByTagName(tagName);
		System.out.println(tagName + ": "
				+ nodeList.item(0).getChildNodes().item(0)
				.getNodeValue());
	}
	
	// given an Element e and a String tagName, returns the text
	// of the tag from the Element as a String.
	public String extractElementText(Element e, String tagName)
	{
		NodeList nodeList = e.getElementsByTagName(tagName);
		return ""+nodeList.item(0).getChildNodes().item(0)
		.getNodeValue();
	}
	
	
	public static void main(String[] args) throws Exception {
		parserDOM parser = new parserDOM();
		parser.getAllQuestions("sampleSurvey1.xml");
		//parser.getAllQuestions("John_survey.xml");
	} // end of main()

} // end of parserDOM class

class Question
{
	String QuesID;
	qType QuesType;
	String question;
	int numChoices;
	ArrayList<String> choices = new ArrayList<String>();
	
	// getters and setters
	
	public String getQuesID() {
		return QuesID;
	}
	public void setQuesID(String quesID) {
		QuesID = quesID;
	}
	public qType getQuesType() {
		return QuesType;
	}
	public void setQuesType(qType quesType) {
		QuesType = quesType;
	}
	
	public void setQuesType(String quesType) {
		if(quesType.equalsIgnoreCase("radio"))
			QuesType = qType.radio;
		else if(quesType.equalsIgnoreCase("button"))
			QuesType = qType.button;
		else if(quesType.equalsIgnoreCase("check"))
			QuesType = qType.check;
		else if(quesType.equalsIgnoreCase("slider"))
			QuesType = qType.slider;
	}
	
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public int getNumChoices() {
		return numChoices;
	}
	public void setNumChoices(int numChoices) {
		this.numChoices = numChoices;
	}
	
	// note: changed data member this.choices from String[] to ArrayList<String>
	public ArrayList<String> getChoices() {
		return choices;
	}
	public void setChoices(ArrayList<String> choices) {
		this.choices = choices;
	}
	
	public int getChoicesSize() {
		return this.choices.size();
	}
	
	public void addToChoices(String myChoice)
	{
		this.choices.add(myChoice);
	}
	
	
	class sliderInfo
	{
		int start;
		int end;
		int numDiv;
		String startLabel;
		String middleLabel;
		String endLabel;
		
		public int getStart() {
			return start;
		}
		public void setStart(int start) {
			this.start = start;
		}
		public int getEnd() {
			return end;
		}
		public void setEnd(int end) {
			this.end = end;
		}
		public int getNumDiv() {
			return numDiv;
		}
		public void setNumDiv(int numDiv) {
			this.numDiv = numDiv;
		}
		public String getStartLabel() {
			return startLabel;
		}
		public void setStartLabel(String startLabel) {
			this.startLabel = startLabel;
		}
		public String getMiddleLabel() {
			return middleLabel;
		}
		public void setMiddleLabel(String middleLabel) {
			this.middleLabel = middleLabel;
		}
		public String getEndLabel() {
			return endLabel;
		}
		public void setEndLabel(String endLabel) {
			this.endLabel = endLabel;
		}

	} 
	
	//sliderInfo slider;
	sliderInfo slider = new sliderInfo();
	
	public sliderInfo getSlider() {
		return this.slider;
	}
	
	class buttonCond
	{
		String cond;
		String nextQues;
		
		public String getCond() {
			return cond;
		}
		public void setCond(String cond) {
			this.cond = cond;
		}
		public String getNextQues() {
			return nextQues;
		}
		public void setNextQues(String nextQues) {
			this.nextQues = nextQues;
		}	
	}
	ArrayList<buttonCond> buttonCondition;
	
	class checkCond
	{
		String cond;
		String nextQues;	
		
		public String getCond() {
			return cond;
		}
		public void setCond(String cond) {
			this.cond = cond;
		}
		public String getNextQues() {
			return nextQues;
		}
		public void setNextQues(String nextQues) {
			this.nextQues = nextQues;
		}
	}
	ArrayList<checkCond> checkConditions;
	
	
	
	public ArrayList<buttonCond> getButtonCondition() {
		return buttonCondition;
	}
	public void setButtonCondition(ArrayList<buttonCond> buttonCondition) {
		this.buttonCondition = buttonCondition;
	}
	public ArrayList<checkCond> getCheckConditions() {
		return checkConditions;
	}
	public void setCheckConditions(ArrayList<checkCond> checkConditions) {
		this.checkConditions = checkConditions;
	}
	public sliderCond getSliderConditions() {
		return sliderConditions;
	}
	public void setSliderConditions(sliderCond sliderConditions) {
		this.sliderConditions = sliderConditions;
	}


	class sliderCond
	{
		int upperLim;     //-1 for no upper limit
		int lowerLim;     //-1 for no lower limit
		String nextQues;		
		
		public int getUpperLim() {
			return upperLim;
		}
		public void setUpperLim(int u) {
			this.upperLim = u;
		}
		
		public int getLowerLim() {
			return lowerLim;
		}
		public void setLowerLim(int w) {
			this.lowerLim = w;
		}
		
		public String getNextQues() {
			return nextQues;
		}
		public void setNextQues(String nextQues) {
			this.nextQues = nextQues;
		}
	}
	sliderCond sliderConditions;
	String defaultNext;
	
	public String getDefaultNext() {
		return this.defaultNext;
	}
	
	public void setDefaultNext(String n1) {
		this.defaultNext = n1;
	}
	
	/* Given a condition type, condition string, and nextQuestion string 
	 * associated with the condition, extracts and stores the conditions 
	 * and next questions based on the type (button, check, or slider).
	 */

	public void parseCondition(String condType, String bCond, String nextQ) {	
		char div = '?';
		
		//button or slider question: split condition string by "|"
		if(condType.equals("button")|| condType.equals("radio")|| condType.equals("slider"))
		{
			div = '|';
		}
		// check question: split condition string by "*"
		else if(condType.equals("check"))
		{
			div = '*';
		}
		
		bCond.trim();
		ArrayList<String> conditionList = new ArrayList<String>(Arrays.asList(bCond.split("|")));
		
		ArrayList<String> buttonCondCodes = new ArrayList<String>();
		int startIndex = -1;
		int endIndex = -2;
		String currentCond = "";

		// parse out individual condition codes from the condition code string
		for(int r = 0; r < conditionList.size(); r++)
		{				
			if(conditionList.get(r).length() > 0)
			{
				char c = conditionList.get(r).charAt(0);
				
				if(!(c != div && c != '0' && c != '1' && c != '2' && c != '3' && c != '4'
					&& c != '5' && c != '6' && c != '7' && c != '8' && c != '9' && c != '+' && c!= '-'))
					{
						//System.out.println("The "+r+"th element is: "+conditionList.get(r));
						
						// first condition code
						if( c != div && startIndex < 0)
						{
							startIndex = r;
							endIndex = r;
							currentCond += conditionList.get(r);
						}
						// continue tracking the length of the current condition code
						else if(c != div)
						{
							endIndex = endIndex + 1;
							currentCond += conditionList.get(r);
						}
						// current condition code is ended; need to reset for the next one
						else if(c == div)
						{
							// store the current completed condition code
							buttonCondCodes.add(currentCond);
							currentCond = "";
							
							startIndex = r+1;
							endIndex = r+1;
						}
											
					} // end of if char statement
				
					// add the very last condition code
					else if(r == conditionList.size()-1)
					{
						buttonCondCodes.add(currentCond);
					}
			
			} // end of if statement	
			
		} // end of for statement
		
		
		// add the individual condition codes to the appropriate data structures 
		ArrayList<buttonCond> bc = new ArrayList<buttonCond>();
		ArrayList<checkCond> chc = new ArrayList<checkCond>();		
		sliderCond s1 = new sliderCond();
		
		char condSign  = '|';
		
		//System.out.println("here are all of the stored condition codes:");
		for(int z = 0; z < buttonCondCodes.size(); z++)
		{
			//System.out.println("Condition code "+z+": "+buttonCondCodes.get(z));
			
			if(condType.equals("radio")|| condType.equals("button"))
			{	
				buttonCond b1 = new buttonCond();
				b1.setCond(buttonCondCodes.get(z));
				b1.setNextQues(nextQ);		
				bc.add(b1);
			}
			else if(condType.equals("check"))
			{
				checkCond c1 = new checkCond();
				c1.setCond(buttonCondCodes.get(z));
				c1.setNextQues(nextQ);
				chc.add(c1);
			}
			else if(condType.equals("slider"))
			{		
				//System.out.println("for slider, button code "+z+" is: "+buttonCondCodes.get(z))
				condSign = buttonCondCodes.get(0).charAt(0);
				
				if(buttonCondCodes.size()==1)
				{
					if(condSign == '+')
					{
						s1.setLowerLim(Integer.parseInt(buttonCondCodes.get(0).substring(1,2)));
						s1.setUpperLim(-1);
					}
					else if(condSign == '-')
					{				
						s1.setUpperLim(Integer.parseInt(buttonCondCodes.get(0).substring(1,2)));
						s1.setLowerLim(-1);
					}
				}
				else if(buttonCondCodes.size()==2)
				{
					if(condSign == '+')
					{
						s1.setLowerLim(Integer.parseInt(buttonCondCodes.get(0).substring(1,2)));
						s1.setUpperLim(Integer.parseInt(buttonCondCodes.get(1).substring(1,2)));
					}
					else if(condSign == '-')
					{				
						s1.setUpperLim(Integer.parseInt(buttonCondCodes.get(0).substring(1,2)));
						s1.setLowerLim(Integer.parseInt(buttonCondCodes.get(1).substring(1,2)));
					}
				}
				
				s1.setNextQues(nextQ);					
				
			} // end of if(slider)
			
		} // end of for loop
		
		// store condition codes & related info into data structures (ArrayList, etc.)
		setButtonCondition(bc);
		setCheckConditions(chc);
		setSliderConditions(s1);

	} // end of parseCondition()
	
} // end of Question class