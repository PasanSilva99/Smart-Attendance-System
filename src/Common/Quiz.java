package Common;

import java.util.List;

public class Quiz {
    private String QuizID;
    private List<Question> QuestionsList;

    public String getQuizID() {
        return QuizID;
    }

    public void setQuizID(String quizID) {
        QuizID = quizID;
    }

    public List<Question> getQuestionsList() {
        return QuestionsList;
    }

    public void addQuestion(Question question) {
        QuestionsList.add(question);
    }

    public void removeQuestion(Question question) {
        QuestionsList.remove(question);
    }
}
