package AdminPanel;

import AdminPanel.AdminLecturerPage;
import Common.Module;
import Common.ModulesDAO;
import Common.User;
import Common.UserDAO;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.SQLException;

public class RemoveModule {
        public TextField tb_moduleCode;
        public Button btn_confirm;
        public Button btn_cancel;
        public Button btn_fetch;
        public Text lbl_moduleName;

        public AdminModulesPage mainPage;

        //Can`t delete the module!
        public void btn_confirm_Click(ActionEvent actionEvent) throws SQLException {
            new ModulesDAO().RemoveModule(tb_moduleCode.getText());
            tb_moduleCode.setText(null);
            lbl_moduleName.setText("Type a Module Code to Show");
            System.out.println("Module Removed");
            mainPage.RefreshModuleList();

        }

        public void btn_cancel_Click(ActionEvent actionEvent) {
            mainPage.RefreshModuleList();
            Stage stage = (Stage) btn_cancel.getScene().getWindow();
            stage.close();
        }

        public void btn_fetch_Click(ActionEvent actionEvent) throws SQLException {
            Module module = new ModulesDAO().getModuleByID(tb_moduleCode.getText());

            if(module == null){
                lbl_moduleName.setText("Module Cannot be found");

            }
            else {
                lbl_moduleName.setText(module.getModuleName());
            }
    }

    public void setMainPage(AdminModulesPage main){
        mainPage = main;
    }
}
