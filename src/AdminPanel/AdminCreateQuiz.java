package AdminPanel;

import Common.Question;
import Common.QuizDAO;
import Common.UniEventDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AdminCreateQuiz implements Initializable {
    public TextField tb_quizName;
    public Button btn_addQuestion;
    public VBox vbox_questionView;
    public Button btn_save;
    public Button btn_close;
    public TextField tb_eventID;
    public Button btn_check;
    public Label lbl_validation;
    public ImageView img_validation;

    // How many questions added
    public int questionCount;
    public String QuizID;  // Retrieved QuizID
    public List<Question> QuestionList = new ArrayList<>(); // List Of Question Classes
    boolean isEventIDValid;  // Is the Event ID valid

    public int getQuestionCount() {
        return questionCount;
    }

    public void setQuestionCount(int questionCount) {
        this.questionCount = questionCount;
    }

    public void reloadQuestions(){
        QuestionList.clear();
        vbox_questionView.getChildren().clear();

        System.out.println("Reloading Question");
        QuestionList = new QuestionDAO().getQuestionList(QuizID);

        System.out.println("Count QuestionList: "+ QuestionList.size());

        for (Question q:QuestionList) {
            AnchorPane item = q.generateView();
            q.setParent(this);
            System.out.println("Adding Question: "+q.Q_Number);
            vbox_questionView.getChildren().add(item);
        }
    }

    public void btn_addQuestion_Click(ActionEvent actionEvent) {

        btn_check_Click(new ActionEvent());

        if(!(tb_quizName.getText().equals("")||tb_eventID.getText().equals("") || !isEventIDValid)) {
            questionCount++;  // Increase the number of questions added
            Question questionControl = new Question();  // Initialize a new question
            questionControl.setQuizID(QuizID);  //  Send the current quiz id to the generated interface
            questionControl.setQuestionNumber(questionCount); // sets the question number
            questionControl.setParent(this);
            AnchorPane questionView = questionControl.generateView(); // Generate Editing interface for the question
            vbox_questionView.getChildren().add(questionView);  // Load the generated interface to the vbox.

            new QuizDAO().addQuiz(QuizID, tb_quizName.getText(), tb_eventID.getText());

        } else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setContentText("PLease enter a Quiz name and a valid event ID");
            alert.setHeaderText("Missing Values");
            alert.setTitle("Warning");
            alert.show();
        }
    }

    public void btn_save_Click(ActionEvent actionEvent) {
        Alert.AlertType alertAlertType=AlertType.ERROR;
        Alert alert = new Alert(alertAlertType);
        alert.setTitle("Error saving Quiz");
        alert.setHeaderText("Error occurred while saving the quiz");
        if(tb_eventID.getText().equals("") && isEventIDValid)
        {
            alert.setContentText("Please enter a valid EventID");
            alert.show();

            new QuizDAO().addQuiz(QuizID, tb_quizName.getText(), tb_eventID.getText());


        }else
        if(tb_quizName.getText().equals("")){
            alert.setContentText("Please enter a quiz name");
            alert.show();
        }else
        if(questionCount<1){
            alert.setAlertType(AlertType.INFORMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Confirm to save without questions");
            alert.setContentText("Are you sure to proceed with no questions");
        }

        for (Question q:QuestionList) {
            // Check questions
            System.out.println("Fetching Added Questions : "+q.getQuizID());

            String q_number = q.Q_Number;
            String Question = q.getquestion();
            System.out.println(q_number+": "+Question);
            List<String> AnswerList= q.getAnswerList();
            for (String answer:AnswerList){
                System.out.println("Fetching answers for " + q_number);
                System.out.println(answer.substring(0, answer.length()-5));
            }


        }


    }

    public void btn_close_Click(ActionEvent actionEvent) {
        Stage stage = (Stage) btn_close.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void btn_ciewEvents_Click(ActionEvent actionEvent) {
        try {
            FXMLLoader loder = new FXMLLoader();
            loder.setLocation(getClass().getResource("PortableEventView.fxml"));
            AnchorPane root = loder.load();
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Student");
            primaryStage.setScene(new Scene(root, 345, 400));
            primaryStage.show();

            Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
            primaryStage.setX((primScreenBounds.getWidth() - primaryStage.getWidth()) / 2);
            primaryStage.setY((primScreenBounds.getHeight() - primaryStage.getHeight()) / 2);


        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void btn_check_Click(ActionEvent actionEvent) {
        boolean isValid = new UniEventDAO().CheckDuplicateID(tb_eventID.getText());

        if(isValid){
            isEventIDValid = true;
            lbl_validation.setText("Event ID is Valid");
            lbl_validation.setTextFill(Paint.valueOf("#3E6556"));
            lbl_validation.setVisible(true);
        }else {
            isEventIDValid = false;
            lbl_validation.setText("Event ID is not Valid");
            lbl_validation.setTextFill(Paint.valueOf("#C75450"));
            lbl_validation.setVisible(true);
        }
    }
}
