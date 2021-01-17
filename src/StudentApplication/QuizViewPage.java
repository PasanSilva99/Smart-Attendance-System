package StudentApplication;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class QuizViewPage implements Initializable {
    public Label lbl_quizName;
    public Label lbl_moduleBatch;
    public Label lbl_deadline;
    public VBox vbox_questions;
    public Button btn_submit;

    public void btn_submit_Click(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
