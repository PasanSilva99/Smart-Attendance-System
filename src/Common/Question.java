package Common;

import java.util.List;

public class Question {
    private String Question;
    private List<String> AnswerList;
    private int CorrectAnswerIndex;

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String question) {
        Question = question;
    }

    public List<String> getAnswerList() {
        return AnswerList;
    }

    public void addAnswer(String answer) {
        AnswerList.add(answer);
    }

    public void removeAnswer(String answer) {
        AnswerList.remove(answer);
    }

    public int getCorrectAnswerIndex() {
        return CorrectAnswerIndex;
    }

    public void setCorrectAnswerIndex(int correctAnswerIndex) {
        CorrectAnswerIndex = correctAnswerIndex;
    }

    public Question(String question, List<String> answerList, int correctAnswerIndex) {
        Question = question;
        AnswerList = answerList;
        CorrectAnswerIndex = correctAnswerIndex;
    }
}
