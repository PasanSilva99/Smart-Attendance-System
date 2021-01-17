package Common;

import AdminPanel.AdminCreateQuiz;
import AdminPanel.QuestionDAO;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Question implements Initializable {
    private int QuestionNumber;
    private String QuizID;
    private String Question;
    private List<String> AnswerList = new ArrayList<>();
    private int CorrectAnswerIndex;
    private boolean Editable = true;

    public String Q_Number;

    TextArea tb_question = new TextArea("Question");
    TextField tb_answer1 = new TextField("Answer 1");
    TextField tb_answer2 = new TextField("Answer 2");
    TextField tb_answer3 = new TextField("Answer 3");
    TextField tb_answer4 = new TextField("Answer 4");
    Label lbl_answerLabel = new Label("Answers");
    CheckBox chb_answerCheck1;
    CheckBox chb_answerCheck2;
    CheckBox chb_answerCheck3;
    CheckBox chb_answerCheck4;

    Button btn_save;
    Button btn_cancel;

    AdminCreateQuiz parent_admin;

    public Question(int questionNumber, String quizID, String question, List<String> answerList) {
        QuestionNumber = questionNumber;
        QuizID = quizID;
        AnswerList = answerList;
    }

    public Question() {

    }

    public Question(String q_number, String question, String answerList, String quizID) {
        Q_Number = q_number;
        System.out.println("Received:: " + Q_Number);
        Question = question;
        System.out.println("Received:: " + Question);
        String[] RAWAnswerList = answerList.split("`");

        for (String _answer:RAWAnswerList){
            AnswerList.add(_answer);
            System.out.println("Received Answer:: " + _answer);
        }

        tb_question.setText(Question);
        tb_answer1.setText(AnswerList.get(0));
        tb_answer2.setText(AnswerList.get(1));
        tb_answer3.setText(AnswerList.get(2));
        tb_answer4.setText(AnswerList.get(3));

        QuizID = quizID;

    }

    /**
     * This function gets the parent to save the settings
     * @param parent
     */
    public void setParent(AdminCreateQuiz parent){
        parent_admin = parent;
    }

    /**
     * Is the question editable/ Unlocked?
     * @return Locked State
     */
    public boolean isEditable() {
        return Editable;
    }

    /**
     * Set the Questions Lock state
     * @param editable
     */
    public void setEditable(boolean editable) {
        Editable = editable;

        if (Editable) {
            btn_save.setVisible(true);
            btn_cancel.setVisible(true);
        } else {
            btn_save.setVisible(false);
            btn_cancel.setVisible(false);
        }

    }

    /**
     * Get the Question number for the view
     * @return
     */
    public int getQuestionNumber() {
        return QuestionNumber;
    }

    /**
     * Set The Question Number for the view
     * @param questionNumber
     */
    public void setQuestionNumber(int questionNumber) {
        QuestionNumber = questionNumber;
    }

    /**
     *
     * @return
     */
    public String getQuizID() {
        return QuizID;
    }

    public void setQuizID(String quizID) {
        QuizID = quizID;
    }

    public String getquestion() {
        return Question;
    }

    public void setquestion(String question) {
        Question = question;
    }

    public List<String> getAnswerList() {
        return AnswerList;
    }

    public AnchorPane generateView(){
        System.out.println("Generating View For "+Q_Number);
        AnchorPane base = new AnchorPane();
        base.setPrefSize(500, 265);
        base.setStyle("-fx-border-width: 0px 0px 2px 0px; -fx-border-color: #3C3F41;");

        tb_question.setLayoutX(13.0);
        tb_question.setLayoutY(14);
        tb_question.setPrefHeight(53.0);
        tb_question.setPrefWidth(493.0);

        tb_answer1.setPrefWidth(304.0);
        tb_answer1.setLayoutX(31.0);
        tb_answer1.setLayoutY(101.0);
        tb_answer1.setFont(new Font("Century", 12));

        tb_answer2.setPrefWidth(304.0);
        tb_answer2.setLayoutX(31.0);
        tb_answer2.setLayoutY(137.0);
        tb_answer2.setFont(new Font("Century", 12));

        tb_answer3.setPrefWidth(304.0);
        tb_answer3.setLayoutX(31.0);
        tb_answer3.setLayoutY(173.0);
        tb_answer3.setFont(new Font("Century", 12));

        tb_answer4.setPrefWidth(304.0);
        tb_answer4.setLayoutX(31.0);
        tb_answer4.setLayoutY(209.0);
        tb_answer4.setFont(new Font("Century", 12));

        lbl_answerLabel.setFont(new Font("Century", 12));
        lbl_answerLabel.setLayoutX(31);
        lbl_answerLabel.setLayoutY(72);

        chb_answerCheck1 = new CheckBox("Correct Answer");
        chb_answerCheck1.setLayoutX(357);
        chb_answerCheck1.setLayoutY(103.0);

        chb_answerCheck2 = new CheckBox("Correct Answer");
        chb_answerCheck2.setLayoutX(357);
        chb_answerCheck2.setLayoutY(141.0);

        chb_answerCheck3 = new CheckBox("Correct Answer");
        chb_answerCheck3.setLayoutX(357);
        chb_answerCheck3.setLayoutY(177.0);

        chb_answerCheck4 = new CheckBox("Correct Answer");
        chb_answerCheck4.setLayoutX(357);
        chb_answerCheck4.setLayoutY(213.0);

        btn_save = new Button();
        btn_save.setLayoutX(365.0);
        btn_save.setLayoutY(253.0);
        btn_save.setStyle("-fx-background-color: #DEDEDE;");
        btn_save.setText("Save");
        File file = new File("Images/save.png");
        Image img = new Image(file.toURI().toString());
        ImageView img_save = new ImageView(img);
        img_save.setFitWidth(17);
        img_save.setFitHeight(17);
        btn_save.setGraphic(img_save);
        if (Editable) {
            btn_save.setVisible(true);
        } else {
            btn_save.setVisible(false);
        }

        EventHandler<ActionEvent> on_btn_save_Click = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                btn_save_Click(btn_save, tb_question, tb_answer1, tb_answer2, tb_answer3, tb_answer4, chb_answerCheck1, chb_answerCheck2, chb_answerCheck3, chb_answerCheck4);
            }
        };

        btn_save.setOnAction(on_btn_save_Click);

        btn_cancel = new Button();
        btn_cancel.setLayoutX(445.0);
        btn_cancel.setLayoutY(253.0);
        btn_cancel.setStyle("-fx-background-color: #DEDEDE;");
        btn_cancel.setText("Delete");
        File file2 = new File("Images/cancel.png");
        Image img_c = new Image(file2.toURI().toString());
        ImageView img_cancel = new ImageView(img_c);
        img_cancel.setFitWidth(17);
        img_cancel.setFitHeight(17);
        btn_cancel.setGraphic(img_cancel);

        if(Editable){
            btn_cancel.setVisible(true);
        } else {
            btn_cancel.setVisible(false);
        }



        EventHandler<ActionEvent> on_btn_cancel_Click = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                btn_cancel_Click(btn_cancel);
            }
        };
        btn_cancel.setOnAction(on_btn_cancel_Click);


        base.getChildren().addAll(tb_question, tb_answer1, tb_answer2, tb_answer3, tb_answer4, chb_answerCheck1, chb_answerCheck2, chb_answerCheck3, chb_answerCheck4, lbl_answerLabel, btn_save, btn_cancel);

        return base;
    }

    public AnchorPane generateViewStudent(){

        AnchorPane base = new AnchorPane();
        base.prefHeight(192.0);
        base.setPrefWidth(600.0);

        Label lbl_question = new Label("");
        lbl_question.setLayoutX(26.0);
        lbl_question.setLayoutY(24.0);
        lbl_question.setPrefWidth(552.0);
        lbl_question.setWrapText(true);
        lbl_question.setAlignment(Pos.TOP_LEFT);
        lbl_question.setFont(new Font("Century", 14));

        RadioButton answer1 = new RadioButton("");
        RadioButton answer2 = new RadioButton("");
        return null;
    }

    private void btn_cancel_Click(Button btn_cancel) {
        parent_admin.vbox_questionView.getChildren().remove(this);
    }

    private void btn_save_Click(Button btn_save, TextArea question, TextField answer1, TextField answer2, TextField answer3, TextField answer4, CheckBox answerCheck1, CheckBox answerCheck2, CheckBox answerCheck3, CheckBox answerCheck4) {

        if(tb_question.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Empty Question");
            alert.setContentText("Your question is empty");
            alert.show();
        } else {
            Question = tb_question.getText(); // Save the Question to the Local Variable
            System.out.println(Question);
        }

        // Marking the correct and wrong answers
        String Answer1 = answer1.getText();
        if(answerCheck1.isSelected()){
            Answer1 = Answer1+"<<X>>";
        }else {
            Answer1 = Answer1+"<<O>>";
        }

        AnswerList.add(Answer1);
        System.out.println(Answer1);

        String Answer2 = answer2.getText();
        if(answerCheck2.isSelected()){
            Answer2 = Answer2+"<<X>>";
        }else {
            Answer2 = Answer2+"<<O>>";
        }

        AnswerList.add(Answer2);
        System.out.println(Answer2);

        String Answer3 = answer3.getText();
        if(answerCheck3.isSelected()){
            Answer3 = Answer3+"<<X>>";
        }else {
            Answer3 = Answer3+"<<O>>";
        }

        AnswerList.add(Answer3);
        System.out.println(Answer3);

        String Answer4 = answer4.getText();
        if(answerCheck4.isSelected()){
            Answer4 = Answer4+"<<X>>";
        }else {
            Answer4 = Answer4+"<<O>>";
        }

        Q_Number = QuizID+"_"+QuestionNumber;
        System.out.println("Question Number created : "+ Q_Number);

        AnswerList.add(Answer4);
        System.out.println(Answer4);

        new QuestionDAO().SaveNewQuestion(Q_Number, Question, joinAnswer(), QuizID);

        parent_admin.reloadQuestions();

    }

    public String joinAnswer(){
        String joinedAnswer= "";
        for (String answer:AnswerList) {

            if(joinedAnswer!="")
                joinedAnswer = joinedAnswer+"`"+answer;
            else
                joinedAnswer = answer;

        }
        return joinedAnswer;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
