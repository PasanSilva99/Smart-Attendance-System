package AdminPanel;

import Common.Quiz;
import Common.QuizDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AdminQuizPage implements Initializable {
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

            if(col>1){
                col=0;
                row++;
            }
        }
    }

    public void btn_createQuiz_Click(ActionEvent actionEvent) throws IOException {
        FXMLLoader loder = new FXMLLoader();
        loder.setLocation(getClass().getResource("AdminCreateQuiz.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("AdminCreateQuiz.fxml"));
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Student");
        primaryStage.setScene(new Scene(root, 600, 600));
        primaryStage.show();

        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setX((primScreenBounds.getWidth() - primaryStage.getWidth()) / 2);
        primaryStage.setY((primScreenBounds.getHeight() - primaryStage.getHeight()) / 2);
    }
}
