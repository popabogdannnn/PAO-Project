import java.util.ArrayList;

public class Quiz {
    
    private ArrayList <String> questions;
    private static int quizID = 0;
    private int myQuizID;

    public Quiz() {
        questions = new ArrayList<String>();
        myQuizID = quizID++;
    }

    public ArrayList<String> getQuestions() {
        return this.questions;
    }

    public int getQuizID() {
        return this.myQuizID;
    }

    public void addQuestion(String question) {
        this.questions.add(question);
    }

}
