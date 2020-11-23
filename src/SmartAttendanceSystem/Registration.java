package SmartAttendanceSystem;

import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;

import java.awt.*;

public class Registration {

    @FXML
    TextField TextSID;
    @FXML
    TextField TextFirstName;
    @FXML
    TextField TextSurname;
    @FXML
    TextField TextEmail;
    @FXML
    MenuButton  MenuDegree;
    @FXML
    MenuButton MenuButton;
    @FXML
    Button ButtonCheckDevice;
    @FXML
    Label LablelCorrect;
    @FXML
    Button ButtonRegister;

    String StudentID;
    String Firstname;
    String Surname;
    String Email;
    String Degree;
    String Batch;


    public TextField getTextSID() {
        return TextSID;
    }

    public void setTextSID(TextField textSID) {
        TextSID = textSID;
    }

    public TextField getTextFirstName() {
        return TextFirstName;
    }

    public void setTextFirstName(TextField textFirstName) {
        TextFirstName = textFirstName;
    }

    public TextField getTextSurname() {
        return TextSurname;
    }

    public void setTextSurname(TextField textSurname) {
        TextSurname = textSurname;
    }

    public TextField getTextEmail() {
        return TextEmail;
    }

    public void setTextEmail(TextField textEmail) {
        TextEmail = textEmail;
    }

    public javafx.scene.control.MenuButton getMenuDegree() {
        return MenuDegree;
    }

    public void setMenuDegree(javafx.scene.control.MenuButton menuDegree) {
        MenuDegree = menuDegree;
    }

    public javafx.scene.control.MenuButton getMenuButton() {
        return MenuButton;
    }

    public void setMenuButton(javafx.scene.control.MenuButton menuButton) {
        MenuButton = menuButton;
    }

    public Button getButtonCheckDevice() {
        return ButtonCheckDevice;
    }

    public void setButtonCheckDevice(Button buttonCheckDevice) {
        ButtonCheckDevice = buttonCheckDevice;
    }

    public Label getLablelCorrect() {
        return LablelCorrect;
    }

    public void setLablelCorrect(Label lablelCorrect) {
        LablelCorrect = lablelCorrect;
    }

    public Button getButtonRegister() {
        return ButtonRegister;
    }

    public void setButtonRegister(Button buttonRegister) {
        ButtonRegister = buttonRegister;
    }

    public String getStudentID() {
        return StudentID;
    }

    public void setStudentID(String studentID) {
        StudentID = studentID;
    }

    public String getFirstname() {
        return Firstname;
    }

    public void setFirstname(String firstname) {
        Firstname = firstname;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getDegree() {
        return Degree;
    }

    public void setDegree(String degree) {
        Degree = degree;
    }

    public String getBatch() {
        return Batch;
    }

    public void setBatch(String batch) {
        Batch = batch;
    }
}
