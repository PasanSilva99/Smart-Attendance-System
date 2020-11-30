package Common;

import AdminPanel.AdminStudentPage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
    public TextField tb_Email;

    AdminStudentPage AdminStudentPage;
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            // Get all Batches
            ObservableList<String> BatchList = FXCollections.observableArrayList(BatchDAO.getBatchList());

            cmb_Batch.setItems(BatchList);

        } catch (Exception e) {
            System.out.println("Error loading batches" + e.getMessage());
        }

        try {
            // Get all Degree Programs
            ObservableList<String> DegreeList = FXCollections.observableArrayList(new DegreeDAO().getAllDegreePrograms());

            cmb_Degree.setItems(DegreeList);

        } catch (Exception e) {
            System.out.println("Error loading degree programs" + e.getMessage());
        }
        
    }

    public String getHash(String passwordToHash){
        String generatedPassword = null;
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            //Add password bytes to digest
            md.update(passwordToHash.getBytes());
            //Get the hash's bytes
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        System.out.println(generatedPassword);

        return generatedPassword;

    }

    public void setMainPage(AdminStudentPage adminStudentPage) {
        AdminStudentPage = adminStudentPage;
    }

    public void btn_Register_click(ActionEvent actionEvent) {
        User newStudent;

        boolean isErrorFree=true;

        String nsbm_id = "";
        String first_name = "";
        String last_name = "";
        String nsbm_email = "";
        String password = "";
        String confirm_password = "";
        String degree_program = "";
        String batch = "";
        String privilege_level = "student";

        String message = "";

        if(!tb_StudentID.getText().equals("")) {
            nsbm_id = tb_StudentID.getText();
        }else {
            message = message+" PLease enter the NSBM ID\n";
            isErrorFree=false;
        }
        if(!tb_FirstName.getText().equals("")) {
            first_name = tb_FirstName.getText();
        }else {
            message = message+" Please enter the First Name\n";
            isErrorFree=false;
        }
        if(!tb_LastName.getText().equals("")) {
            last_name = tb_LastName.getText();
        }else {
            message = message+" Please enter the Last Name\n";
            isErrorFree=false;
        }
        if(!tb_Email.getText().equals("")) {
            nsbm_email = tb_Email.getText();
        }else {
            message = message+" Please enter the Email Address\n";
            isErrorFree=false;
        }
        if(!tb_password.getText().equals("") && !tb_confirmPassword.getText().equals("") && (tb_password.getText().equals(tb_confirmPassword.getText()))) {
            password = getHash(tb_password.getText());
        }else {
            message = message+" Password and Confirm Password should match and cannot be empty\n";
            isErrorFree=false;
        }
        if(cmb_Degree.getSelectionModel().getSelectedItem()!=null) {
            degree_program = cmb_Degree.getSelectionModel().getSelectedItem().toString();
        }else {
            message = message+" Please Select a Degree Program\n";
            isErrorFree=false;
        }
        if(cmb_Batch.getSelectionModel().getSelectedItem()!=null) {
            batch = cmb_Batch.getSelectionModel().getSelectedItem().toString();
        }else {
            message = message+" Please select a batch\n";
            isErrorFree=false;
        }

        if(isErrorFree)
        {
            message = "Student "+nsbm_id+" Registration Successful";

            try{
                User user = new User(nsbm_id, first_name, last_name, nsbm_email, password, degree_program, batch, privilege_level);

                new UserDAO().AddUser(user);

                Alert.AlertType alertAlertType = AlertType.CONFIRMATION;
                Alert confirmation = new Alert(alertAlertType);
                confirmation.setTitle("Success");
                confirmation.setHeaderText("Registration Success");
                confirmation.setContentText("Student "+nsbm_id+" successfully registered");
                confirmation.show();
                clearAll();
                AdminStudentPage.RefreshStudentList();

            }catch (Exception e){
                e.printStackTrace();
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error !");
                alert.setContentText("Cannot Register User");
                alert.show();
            }
        }else {

            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Missing or incorrect values");
            alert.setContentText(message);
            alert.show();
        }

    }

    public void clearAll(){
        tb_StudentID.setText("");
        tb_FirstName.setText("");
        tb_LastName.setText("");
        tb_Email.setText("");
        tb_password.setText("");
        tb_confirmPassword.setText("");
        cmb_Degree.getSelectionModel().clearSelection();
        cmb_Batch.getSelectionModel().clearSelection();
    }

    public void btn_cancel_click(ActionEvent actionEvent) {
        Stage stage = (Stage) btn_cancel.getScene().getWindow();
        stage.close();
    }

    public void btn_reset_click(ActionEvent actionEvent) {
        clearAll();
    }
}
