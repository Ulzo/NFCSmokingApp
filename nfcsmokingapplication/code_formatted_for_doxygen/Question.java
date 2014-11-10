/*! \class Question
   \brief A Question class to hold information about each survey question.

*/
class Question
{
	String QuesID;	/* !< String to hold the text of the QuestionID (1, 2b, etc.) */
	qType QuesType; /* !< The question type */
	String question; /* !< String to hold the text of the Question contents */
	int numChoices;		/* !< the number of possible answer choices for the Question */	
	/* !< ArrayList of Strings containing the text of each possible answer choice */
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


