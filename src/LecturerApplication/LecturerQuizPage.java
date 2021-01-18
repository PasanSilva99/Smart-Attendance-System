package LecturerApplication;

import AdminPanel.QuestionDAO;
import Common.Quiz;
import Common.QuizDAO;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

public class LecturerQuizPage implements Initializable {
    public GridPane grid_quizView;
    public Button btn_createQuiz;

    List<Quiz> quizList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        quizList = new ArrayList<>();
        quizList = new QuizDAO().getQuizList();

        int row=0,col=0;
        for (Quiz quiz:quizList) {
            col++;
            AnchorPane item = quiz.getQuizView();
            grid_quizView.add(item, col, row);
            GridPane.setMargin(item, new Insets(10,10,10,10));

            EventHandler<MouseEvent> OnMouseCLick = new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    Platform.runLater(()-> quizViewClick(quiz));
                }
            };


            item.addEventFilter(MouseEvent.MOUSE_CLICKED, OnMouseCLick);
            if(col>1){
                col=0;
                row++;
            }
        }
    }

    private void quizViewClick(Quiz quiz) {
        try {
            FXMLLoader loder = new FXMLLoader();
            loder.setLocation(getClass().getResource("LecturerCreateQuiz.fxml"));
            AnchorPane root = loder.load();
            LecturerCreateQuiz controller = loder.getController();
            controller.QuizID = quiz.getQuizID();
            controller.tb_quizName.setText(quiz.getQuizName());
            controller.tb_eventID.setText(quiz.getEventID());
            controller.reloadQuestions();
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Student");
            primaryStage.setScene(new Scene(root, 600, 600));
            primaryStage.show();

            Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
            primaryStage.setX((primScreenBounds.getWidth() - primaryStage.getWidth()) / 2);
            primaryStage.setY((primScreenBounds.getHeight() - primaryStage.getHeight()) / 2);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * This function returns a generated quiz id if it is not duplicated. (Recursive until finds a unique)
     * @return
     */
    public String getQuizID(){
        String newQuizID = generateNewID();
        boolean isDuplicated = new QuestionDAO().isDuplicated(newQuizID);
        if(!isDuplicated){
            // if the new is is not duplicated return it
            return newQuizID;
        } else {
            // if teh new id is already in the table, re run generation (Recursive)
            return getQuizID();
        }
    }

    /** This function Generates a Quiz ID
     *
     * @return
     */
    public String generateNewID() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        System.out.println(generatedString);
        return generatedString;
    }


    public void btn_createQuiz_Click(ActionEvent actionEvent) throws IOException {
        FXMLLoader loder = new FXMLLoader();
        loder.setLocation(getClass().getResource("LecturerCreateQuiz.fxml"));
        AnchorPane root = loder.load();
        LecturerCreateQuiz controller = loder.getController();
        controller.QuizID = getQuizID();
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Student");
        primaryStage.setScene(new Scene(root, 600, 600));
        primaryStage.show();

        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setX((primScreenBounds.getWidth() - primaryStage.getWidth()) / 2);
        primaryStage.setY((primScreenBounds.getHeight() - primaryStage.getHeight()) / 2);
    }
}
