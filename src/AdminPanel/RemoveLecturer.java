package AdminPanel;

import AdminPanel.AdminStudentPage;
import Common.User;
import Common.UserDAO;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.SQLException;

public class RemoveLecturer {
        public TextField tb_lecturerNumber;
        public Button btn_confirm;
        public Button btn_cancel;
        public Button btn_fetch;
        public Text lbl_lecturerName;
        public Text lbl_lecturerEmail;

        public AdminLecturerPage mainPage;

        public void btn_confirm_Click(ActionEvent actionEvent) {
            new UserDAO().removeUser(tb_lecturerNumber.getText());
            tb_lecturerNumber.setText(null);
            lbl_lecturerName.setText("Type a Lecturer Number to Show");
            lbl_lecturerEmail.setText("Type a Lecturer Number to Show");
            System.out.println("Lecturer Removed");
            mainPage.RefreshLecturerList();

        }

        public void btn_cancel_Click(ActionEvent actionEvent) {
            mainPage.RefreshLecturerList();
            Stage stage = (Stage) btn_cancel.getScene().getWindow();
            stage.close();
        }

        public void btn_fetch_Click(ActionEvent actionEvent) throws SQLException {
            User student = new UserDAO().getStudent(tb_lecturerNumber.getText());

            if(student == null){
                lbl_lecturerEmail.setText("Lecturer Cannot be found");
                lbl_lecturerName.setText("Lecturer cannot be found");

            }
            else {
                lbl_lecturerName.setText(student.getName());
                lbl_lecturerEmail.setText(student.getNsbm_email());
            }
    }

    public void setMainPage(AdminLecturerPage main){
        mainPage = main;
    }
}
