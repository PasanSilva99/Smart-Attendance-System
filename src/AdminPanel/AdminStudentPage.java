package AdminPanel;

import Common.User;
import Common.UserDAO;
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

public class AdminStudentPage implements Initializable {
    public AnchorPane ap_baseStudent;
    public GridPane grid_studentView;
    public Button btn_registerStudent;

    List<User> StudentList = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        RefreshStudentList();
    }

    public void RefreshStudentList(){
        StudentList = new UserDAO().getStudentList();

        int c=0, r=0;
        for (User student:StudentList) {
            r++;

            AnchorPane studentView = student.getUserView();

            grid_studentView.add(studentView, 0,r);
            GridPane.setMargin(studentView, new Insets(10,10,10,10));

        }

    }

    public void btn_registerStudent_Click(ActionEvent actionEvent) {

    }
}
