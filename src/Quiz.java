import java.util.ArrayList;

public class Quiz {
    
    private ArrayList <Question> questions;
    private static int quizID = 0;
    private int myQuizID;

    public Quiz() {
        questions = new ArrayList<Question>();
        myQuizID = quizID++;
    }

    public ArrayList<String> getQuestions() {
        return this.questions;
    }

    public int getQuizID() {
        return this.myQuizID;
    }

    public void addQuestion(Question question) {
        this.questions.add(question);
    }

}
