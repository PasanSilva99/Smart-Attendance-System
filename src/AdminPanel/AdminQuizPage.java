package AdminPanel;

import Common.Quiz;
import Common.QuizDAO;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

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

    public void btn_createQuiz_Click(ActionEvent actionEvent) {
    }
}
