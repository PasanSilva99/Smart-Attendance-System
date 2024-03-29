package AdminPanel;

import Common.DegreeDAO;
import Common.DegreeProgram;
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

public class AdminDegreePage implements Initializable {

    public AnchorPane ap_baseDegree;
    public GridPane grid_degreeView;
    public Button btn_registerDegree;

    List<DegreeProgram> DegreeList = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        RefreshDegreeList();

    }

    public void RefreshDegreeList() {
        DegreeList = new DegreeDAO().getDegreeList();

        int c=0, r=0;
        for (DegreeProgram degree:DegreeList) {
            r++;

            AnchorPane degreeView = degree.getDegreeView();

            grid_degreeView.add(degreeView, 0,r);
            GridPane.setMargin(degreeView, new Insets(10,10,10,10));
            System.out.println("Degree List Updated");
        }
    }

    public void btn_registerDegree_Click(ActionEvent actionEvent) {

        try {
            FXMLLoader loder = new FXMLLoader();
            loder.setLocation(getClass().getResource("../AdminPanel/RegisterNewDegree.fxml"));
            Parent root = loder.load();
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Add New Degree");
            primaryStage.setScene(new Scene(root, 600, 480));
            primaryStage.show();
            RegisterNewDegree controller = loder.getController();
            controller.setMainPage(this);

            Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
            primaryStage.setX((primScreenBounds.getWidth() - primaryStage.getWidth()) / 2);
            primaryStage.setY((primScreenBounds.getHeight() - primaryStage.getHeight()) / 2);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void btn_deleteDegree_Click(ActionEvent actionEvent) {
        try {
            FXMLLoader loder = new FXMLLoader();
            loder.setLocation(getClass().getResource("../AdminPanel/RemoveDegree.fxml"));
            Parent root = loder.load();
            RemoveDegree controller = loder.getController();
            controller.setMainPage(this);
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Remove Degree");
            primaryStage.setScene(new Scene(root, 434, 274));
            primaryStage.show();

            Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
            primaryStage.setX((primScreenBounds.getWidth() - primaryStage.getWidth()) / 2);
            primaryStage.setY((primScreenBounds.getHeight() - primaryStage.getHeight()) / 2);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
