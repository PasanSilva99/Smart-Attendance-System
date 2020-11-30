package Common;

import AdminPanel.AdminStudentPage;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class RegisterNewStudent implements Initializable {
    public TextField tb_StudentID;
    public TextField tb_FirstName;
    public TextField tb_LastName;
    public TextField txtEmail;
    public ComboBox cmb_Degree;
    public ComboBox cmb_Batch;
    public Button btn_Register;
    public Button btn_cancel;
    public PasswordField tb_password;
    public PasswordField tb_confirmPassword;

    AdminStudentPage AdminStudentPage;
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        
    }

    public void setMainPage(AdminStudentPage adminStudentPage) {
        AdminStudentPage = adminStudentPage;
    }

    public void btn_Register_click(ActionEvent actionEvent) {
    }

    public void btn_cancel_click(ActionEvent actionEvent) {
    }
}
