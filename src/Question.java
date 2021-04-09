public class Question {
    
    private String statement;
    private String correctAnswer;

    public Question(String statement, String correctAnswer) {
        this.statement = statement;
        this.correctAnswer = correctAnswer;
    }

    public String getStatement() {
        return statement;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

}
