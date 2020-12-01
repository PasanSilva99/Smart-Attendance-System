package Common;

import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

import java.io.File;
import java.util.List;

public class Question {
    private int QuestionNumber;
    private String QuizID;
    private String Question;
    private List<String> AnswerList;
    private int CorrectAnswerIndex;

    public Question() {

    }

    public int getQuestionNumber() {
        return QuestionNumber;
    }

    public void setQuestionNumber(int questionNumber) {
        QuestionNumber = questionNumber;
    }

    public String getQuizID() {
        return QuizID;
    }

    public void setQuizID(String quizID) {
        QuizID = quizID;
    }

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

    public AnchorPane generateView(){
        AnchorPane base = new AnchorPane();
        base.setPrefSize(265, 640);

        TextArea question = new TextArea();
        question.setPromptText("Type Your Question Here");
        question.setLayoutX(13.0);
        question.setLayoutY(14);
        question.setPrefHeight(53.0);
        question.setPrefWidth(579.0);

        TextField answer1 = new TextField();
        answer1.setPromptText("Answer 1");
        answer1.setPrefWidth(304.0);
        answer1.setLayoutX(31.0);
        answer1.setLayoutY(101.0);
        answer1.setFont(new Font("Century", 12));

        TextField answer2 = new TextField();
        answer2.setPromptText("Answer 2");
        answer2.setPrefWidth(304.0);
        answer2.setLayoutX(31.0);
        answer2.setLayoutY(137.0);
        answer2.setFont(new Font("Century", 12));

        TextField answer3 = new TextField();
        answer3.setPromptText("Answer 3");
        answer3.setPrefWidth(304.0);
        answer3.setLayoutX(31.0);
        answer3.setLayoutY(173.0);
        answer3.setFont(new Font("Century", 12));

        TextField answer4 = new TextField();
        answer4.setPromptText("Answer 4");
        answer4.setPrefWidth(304.0);
        answer4.setLayoutX(31.0);
        answer4.setLayoutY(209.0);
        answer4.setFont(new Font("Century", 12));

        Label answerLabel = new Label("Multi Choice Answers");
        answerLabel.setFont(new Font("Century", 12));
        answerLabel.setLayoutX(31);
        answerLabel.setLayoutY(72);

        CheckBox answerCheck1 = new CheckBox("Correct Answer");
        answerCheck1.setLayoutX(357);
        answerCheck1.setLayoutY(103.0);

        CheckBox answerCheck2 = new CheckBox("Correct Answer");
        answerCheck2.setLayoutX(357);
        answerCheck2.setLayoutY(141.0);

        CheckBox answerCheck3 = new CheckBox("Correct Answer");
        answerCheck3.setLayoutX(357);
        answerCheck3.setLayoutY(177.0);

        CheckBox answerCheck4 = new CheckBox("Correct Answer");
        answerCheck4.setLayoutX(357);
        answerCheck4.setLayoutY(213.0);

        Button btn_save = new Button();
        btn_save.setLayoutX(532.0);
        btn_save.setLayoutY(253.0);
        btn_save.setStyle("-fx-background-color: #DEDEDE;");
        btn_save.setText("Save");
        File file = new File("Images/save.png");
        Image img = new Image(file.toURI().toString());
        ImageView img_save = new ImageView(img);
        btn_save.setGraphic(img_save);

        Button btn_cancel = new Button();
        btn_cancel.setLayoutX(452.0);
        btn_save.setLayoutY(253.0);
        btn_save.setStyle("-fx-background-color: #DEDEDE;");
        btn_save.setText("Cancel");
        File file2 = new File("Images/cancel.png");
        Image img_c = new Image(file2.toURI().toString());
        ImageView img_cancel = new ImageView(img_c);
        btn_save.setGraphic(img_cancel);

        base.getChildren().addAll(question, answer1, answer2, answer3, answer4, answerCheck1, answerCheck2, answerCheck3, answerCheck4, answerLabel, btn_save, btn_cancel);

        return base;
    }

    public String joinAnswer(){
        String joinedAnswer= "";
        for (String answer:AnswerList) {

            if(joinedAnswer!="")
                joinedAnswer = joinedAnswer+","+answer;
            else
                joinedAnswer = answer;

        }
        return joinedAnswer;
    }

}
