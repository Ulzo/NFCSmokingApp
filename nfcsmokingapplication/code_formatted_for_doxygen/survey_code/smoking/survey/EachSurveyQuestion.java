package cs340.nfc.smoking.survey;

import java.util.ArrayList;


enum qType {button, check, slider};
public class EachSurveyQuestion {
	String QuesID;
	qType QuesType;
	String question;
	int numChoices;
	ArrayList<String>choices;	
	
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
	public ArrayList<String> getChoices() {
		return choices;
	}
	public void setChoices(ArrayList<String> choices) {
		this.choices = choices;
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
		String nextQues;
	}
	ArrayList<checkCond> checkConditions;
	
	class sliderCond
	{
		int upperLim;     //-1 for no upper limit
		int lowerLim;     //-1 for no lower limit
		String nextQues;
	}
	sliderCond sliderConditions;
	String defaultNext;
	public void setDefaultNext(String defaultNext)
	{
		this.defaultNext = defaultNext;
	}
	public String getDefaultNext()
	{
		return defaultNext;
	}
	
}