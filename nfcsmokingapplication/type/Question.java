import java.util.LinkedList;

public class Question {
	
	private QType question_type;
	private String question;
	// dynamic size vs static debatable (discuss)
	private LinkedList<String>answers;
	
	/* 
	 make answer chosen variable
	 you can set the selected answer to the question object
	 access the answers that the user chose, directly from this question object instance
	*/ 
	
	public Question() {
		QType question_type = null;
		question = null;
		answers = new LinkedList<String>();
	}
	
	public void setQuestionType(QType q) {
		question_type = q;
	}
	public QType getQuestionType() {
		return question_type;
	}

	public void setQuestion(String s) {
		question = s;
	}
	public String getQuestion() {
		return question;
	}

	public void addAnswer(String a) {
		// sliders only have one answer
		if ( question_type != QType.slider  || answers.size() < 1 )
			answers.add(a);
		//else
			// throw custom exception
			// @TODO roll my own exception class
	}
	public String[] getAnswers() {
		return (String[]) answers.toArray();
	}
	
	// mostly just sanity test
	public void print() {
		System.out.println("Question: " + question);
		System.out.println("Question type: " + question_type);
		int len = answers.size();
		for (int i = 0; i < len; i++)
			System.out.println(answers.get(i));
	}
}	

