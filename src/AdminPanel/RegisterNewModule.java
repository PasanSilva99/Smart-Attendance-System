package AdminPanel;

import AdminPanel.AdminModulesPage;
import Common.*;
import Common.User;
import Common.UserDAO;
import Common.Module;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class RegisterNewModule implements Initializable {

    public TextField tb_ModuleCode;
    public TextField tb_ModuleName;
    public ComboBox cmb_ModuleLeader;
    public ComboBox cmb_DegreeProgram;
    public Button btn_Register;
    public Button btn_cancel;
    public Button btn_reset;


    AdminModulesPage AdminModulesPage;
    private String SelectedDegreeCode;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

}

    public void setMainPage (AdminModulesPage adminModulesPage) {

        AdminModulesPage = adminModulesPage;
    }


    public void btn_Register_click(ActionEvent actionEvent) {

        Module newModule;

        boolean isErrorFree=true;

        String module_code = "";
        String module_name = "";
        String lecturer_name = "";
        String degree_program = "";

        String message = "";

        if(!tb_ModuleCode.getText().equals("")) {
            module_code = tb_ModuleCode.getText();
        }else {
            message = message+" PLease enter the Module Code \n";
            isErrorFree=false;
        }

        if(!tb_ModuleName.getText().equals("")) {
            module_name = tb_ModuleName.getText();
        }else {
            message = message+" Please enter the Module Name\n";
            isErrorFree=false;
        }

        //There need to add codes for  the cmb_ModuleLeader





        //There need to aadd codes for the cmb_DegreeProgram







        if(isErrorFree)
        {
            message = " "+module_code+" Added Successful";

            try{
                Module module = new Module(module_code,module_name,lecturer_name,degree_program);


                new ModulesDAO().addModule(module);

                AlertType alertAlertType = AlertType.CONFIRMATION;
                Alert confirmation = new Alert(alertAlertType);
                confirmation.setTitle("Success");
                confirmation.setHeaderText("Module Added");
                confirmation.setContentText("Module "+module_code+" added successfully!");
                confirmation.show();
                clearAll();
                AdminModulesPage.RefreshModuleList();

            }catch (Exception e){
                e.printStackTrace();
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error !");
                alert.setContentText("Cannot Add New Module");
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
        tb_ModuleCode.setText("");
        tb_ModuleName.setText("");
    }
    }
