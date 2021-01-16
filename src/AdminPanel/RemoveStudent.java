package AdminPanel;

import Common.User;
import Common.UserDAO;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.SQLException;

public class RemoveStudent {
    public TextField tb_studentNumber;
    public Button btn_confirm;
    public Button btn_cancel;
    public Button btn_fetch;
    public Text lbl_studentName;
    public Text lbl_studentEmail;

    public AdminStudentPage mainPage;

    public void btn_confirm_Click(ActionEvent actionEvent) {
        new UserDAO().removeUser(tb_studentNumber.getText());
        tb_studentNumber.setText(null);
        lbl_studentName.setText("Type a Student Number to Show");
        lbl_studentEmail.setText("Type a Student Number to Show");
        mainPage.RefreshStudentList();
    }

    public void btn_cancel_Click(ActionEvent actionEvent) {
        mainPage.RefreshStudentList();
        Stage stage = (Stage) btn_cancel.getScene().getWindow();
        stage.close();
    }

    public void btn_fetch_Click(ActionEvent actionEvent) throws SQLException {
        User student = new UserDAO().getStudent(tb_studentNumber.getText());

        if(student == null){
            lbl_studentEmail.setText("Student Cannot be found");
            lbl_studentName.setText("Student cannot be found");

        }
        else {
            lbl_studentName.setText(student.getName());
            lbl_studentEmail.setText(student.getNsbm_email());
        }
    }

    public void setMainPage(AdminStudentPage main){
        mainPage = main;
    }
}
