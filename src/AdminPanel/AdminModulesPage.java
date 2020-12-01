package AdminPanel;

import Common.Module;
import Common.ModulesDAO;
import Common.User;
import Common.UserDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AdminModulesPage implements Initializable {

    public AnchorPane ap_baseModules;
    public GridPane grid_moduleView;
    public Button btn_addModule;

    List<Module> ModulesList = new ArrayList<>();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        RefreshModuleList();

    }

    public void RefreshModuleList() {
        ModulesList = new ModulesDAO().getModulesList();

        int c=0, r=0;
        for (Module module:ModulesList) {
            r++;

            AnchorPane moduleView = module.getModuleView();

            grid_moduleView.add(moduleView, 0,r);
            GridPane.setMargin(moduleView, new Insets(10,10,10,10));

        }
    }

    public void btn_addModule_Click(ActionEvent actionEvent) {

        try {
            FXMLLoader loder = new FXMLLoader();
            loder.setLocation(getClass().getResource("../AdminPanel/RegisterNewModule.fxml"));
            Parent root = loder.load();
            RegisterNewModule controller = loder.getController();
            controller.setMainPage(this);
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Add New Module");
            primaryStage.setScene(new Scene(root, 599, 697));
            primaryStage.show();

            Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
            primaryStage.setX((primScreenBounds.getWidth() - primaryStage.getWidth()) / 2);
            primaryStage.setY((primScreenBounds.getHeight() - primaryStage.getHeight()) / 2);
        }catch (Exception e){
            e.printStackTrace();
        }

    }


}
