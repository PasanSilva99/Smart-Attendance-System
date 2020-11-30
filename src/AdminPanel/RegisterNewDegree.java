package AdminPanel;

import AdminPanel.AdminDegreePage;
import Common.*;
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

public class RegisterNewDegree implements Initializable {
    public TextField tb_DegreeName;
    public TextField tb_DegreeCode;
    public Button btn_Register;
    public Button btn_cancel;
    public Button btn_reset;


    AdminDegreePage AdminDegreePage;
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

}
    public void setMainPage (AdminDegreePage adminDegreePage) {

        AdminDegreePage = adminDegreePage;
    }

    public void btn_Register_click(ActionEvent actionEvent) {

        DegreeProgram newDegree;

        boolean isErrorFree=true;

        String degree_code = "";
        String degree_name = "";

        String message = "";

        if(!tb_DegreeName.getText().equals("")) {
            degree_name = tb_DegreeName.getText();
        }else {
            message = message+" PLease enter the Degree Name \n";
            isErrorFree=false;
        }

        if(!tb_DegreeCode.getText().equals("")) {
            degree_code = tb_DegreeCode.getText();
        }else {
            message = message+" Please enter the Degree Code\n";
            isErrorFree=false;
        }

        if(isErrorFree)
        {
            message = " "+degree_code+" Registration Successful";

            try{
                DegreeProgram degree = new DegreeProgram(degree_code,degree_name);


                new DegreeDAO().AddDegree(degree);

                AlertType alertAlertType = AlertType.CONFIRMATION;
                Alert confirmation = new Alert(alertAlertType);
                confirmation.setTitle("Success");
                confirmation.setHeaderText("Degree Added");
                confirmation.setContentText("Degree "+degree_code+" successfully added!");
                confirmation.show();
                clearAll();
                AdminDegreePage.RefreshDegreeList();

            }catch (Exception e){
                e.printStackTrace();
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error !");
                alert.setContentText("Cannot Add New Degree");
                alert.show();
            }
        }else {

            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Missing or incorrect details");
            alert.setContentText(message);
            alert.show();
        }

    }
    public void btn_cancel_click(ActionEvent actionEvent) {
        Stage stage = (Stage) btn_cancel.getScene().getWindow();
        stage.close();
    }

    public void btn_reset_click(ActionEvent actionEvent) {
        clearAll();
    }

    private void clearAll() {
        tb_DegreeName.setText("");
        tb_DegreeCode.setText("");
    }
    }
