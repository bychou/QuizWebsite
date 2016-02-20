package web;

public class QuestionResponse {
	
	private String question;
	private String answer;

	public QuestionResponse() {
		/* Randomly select a question */
		
		/* Fetch question & answer from database. */
	}
	
	
	/* Check if provided answer is correct. */
	
	public String printQuestion() {
		return question;
	}
	public boolean checkAnswer(String str) {
		return str.equals(answer);
	}
	
}
