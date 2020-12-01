package AdminPanel;

import Common.Question;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.net.URL;
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

    public void btn_addQuestion_Click(ActionEvent actionEvent) {

        Question question = new Question();

        vbox_questionView.getChildren().add(new Question().generateView());

    }

    public void btn_save_Click(ActionEvent actionEvent) {

    }

    public void btn_close_Click(ActionEvent actionEvent) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
