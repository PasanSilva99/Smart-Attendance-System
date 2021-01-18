package StudentApplication;

import AdminPanel.AdminCreateQuiz;
import AdminPanel.QuestionDAO;
import Common.*;
import com.sun.javafx.binding.StringFormatter;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.Module;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.List;
import java.util.ResourceBundle;

public class QuizViewPage implements Initializable {
    public Label lbl_quizName;
    public Label lbl_moduleBatch;
    public Label lbl_deadline;
    public VBox vbox_questions;
    public Button btn_submit;
    public Quiz _quiz;

    User user;
    UniEvent uniEvent;

    List<Question> questionList;

    public void setUser(User user) {
        this.user = user;
    }

    public void setEvent(UniEvent clickedItem) {
        this.uniEvent = clickedItem;
    }

    public void setQuiz(Quiz quiz){
        _quiz = quiz;
        questionList = new QuestionDAO().getQuestionList(_quiz.getQuizID());

        for (Question ques: questionList) {
            AnchorPane question_ = ques.generateViewStudent();
            vbox_questions.getChildren().add(question_);
        }

        lbl_quizName.setText(quiz.getQuizName());

        lbl_moduleBatch.setText(uniEvent.getModuleO() + "  " + uniEvent.getBatch());

        lbl_deadline.setText("Deadline: "+uniEvent.getEndTime());

    }

    private static DecimalFormat df2 = new DecimalFormat("#.##");

    public void btn_submit_Click(ActionEvent actionEvent) throws IOException {


        int numberOfQuestions = questionList.size();
        double indivudualMarks = 100.00/numberOfQuestions;

        // calculate the number of correct answers
        int correctAnswers = 0;

        for(int i=0; i<numberOfQuestions; i++){
            if(questionList.get(i).IsCorrect()) correctAnswers++;
            System.out.println("Answer "+i+" is correct");

        }

        System.out.println("Total count is "+correctAnswers);

        Double totalMarks = indivudualMarks*(double)correctAnswers;
        System.out.println("Total marks: "+ String.format("%.2f", totalMarks));

        // check weather the user is already submitted to this quiz

        boolean isSubmitted = new QuizDAO().checkSubmission(_quiz.getQuizID(), user.getNsbm_id());

        if(isSubmitted){
            System.out.println("You've Already Submitted this Quiz");
            Alert.AlertType alertAlertType = Alert.AlertType.WARNING;
            Alert confirmation = new Alert(alertAlertType);
            confirmation.setTitle("Duplicate Submission");
            confirmation.setHeaderText("You already Submitted");
            confirmation.setContentText("You cannot submit same quiz twice. If you think this is a mistake, please contact IT Administrator");
            confirmation.show();
        } else {
            System.out.println("Starting Submission");
            new UserLogin().markAttendance(user, uniEvent.getModuleO(), totalMarks, uniEvent.getEventID(), _quiz.getQuizID());
            System.out.println("Submission Success\nAttendance Recorded.");

            Alert.AlertType alertAlertType = Alert.AlertType.INFORMATION;
            Alert confirmation = new Alert(alertAlertType);
            confirmation.setTitle("Successfully Submitted");
            confirmation.setHeaderText("Submission Successful");
            confirmation.setContentText("Your submission is recorded successfully. Please do all quizzes available.");
            confirmation.show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }
}
