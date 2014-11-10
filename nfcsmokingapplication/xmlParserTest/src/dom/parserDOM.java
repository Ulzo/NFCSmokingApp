package dom;
import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

enum qType {button, check, slider};

public class parserDOM {

	public void getAllQuestions(String fileName) {
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			File file = new File(fileName);
			if (file.exists()) {
				Document doc = db.parse(file);
				Element docEle = doc.getDocumentElement();
				
				// Print root element of the document
				System.out.println("Root element of the document: "
						+ docEle.getNodeName());
				
				NodeList quesList = docEle.getElementsByTagName("Question");
				
				/*// Print total question elements in document
				System.out
				.println("Total number of questions: " + quesList.getLength());*/
				
				if (quesList != null && quesList.getLength() > 0) {
					for (int i = 0; i < quesList.getLength(); i++) {
				
						Node node = quesList.item(i);
						NodeList choices;
						
						if (node.getNodeType() == Node.ELEMENT_NODE) {
							
							System.out
							.println("=====================");
		
							Element e = (Element) node;
							NodeList nodeList = e.getElementsByTagName("QuesID");
							System.out.println("QuesID: "
									+ nodeList.item(0).getChildNodes().item(0)
									.getNodeValue());

							nodeList = e.getElementsByTagName("SubID");
							System.out.println("SubID: "
									+ nodeList.item(0).getChildNodes().item(0)
									.getNodeValue());

							nodeList = e.getElementsByTagName("QuestionString");
							System.out.println("Question: "
									+ nodeList.item(0).getChildNodes().item(0)
									.getNodeValue());

							nodeList = e.getElementsByTagName("QuesType");
							System.out.println("QuestionType: "
									+ nodeList.item(0).getChildNodes().item(0)
									.getNodeValue());

							nodeList = e.getElementsByTagName("NumOfChoices");
							System.out.println("NumOfChoices: "
									+ nodeList.item(0).getChildNodes().item(0)
									.getNodeValue());

							choices = docEle.getElementsByTagName("choice");
							//System.out.println(choices.getLength());
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
	public static void main(String[] args) throws Exception {
		parserDOM parser = new parserDOM();
		parser.getAllQuestions("sampleSurvey1.xml");
	}
}
class question
{
	String QuesID;
	qType QuesType;
	String question;
	int numChoices;
	String []choices;
	class sliderInfo
	{
		int start;
		int end;
		int numDiv;
		String startLabel;
		String middleLabel;
		String endLabel;
	}
	sliderInfo slider;
	class buttonCond
	{
		String cond;
		int nextQues;
	}
	ArrayList<buttonCond>buttonCondition;
	class checkCond
	{
		String cond;
		int nextQues;
	}
	ArrayList<checkCond> checkConditions;
	class sliderCond
	{
		int upperLim;     //-1 for no upper limit
		int lowerLim;     //-1 for no lower limit
		int nextQues;
	}
	sliderCond sliderConditions;
	String defaultNext;
}