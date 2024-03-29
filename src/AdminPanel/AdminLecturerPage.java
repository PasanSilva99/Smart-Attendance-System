package AdminPanel;

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

public class AdminLecturerPage implements Initializable {

    public AnchorPane ap_baseLecturer;
    public GridPane grid_lecturerView;
    public Button btn_registerLecturer;
    public Button btn_deleteLecturer;


    List<User> LecturerList = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        RefreshLecturerList();


    }

    public void RefreshLecturerList(){
        LecturerList = new UserDAO().getLecturerList();

        int c=0, r=0;
        for (User student:LecturerList) {
            r++;

            AnchorPane studentView = student.getUserView();

            grid_lecturerView.add(studentView, 0,r);
            GridPane.setMargin(studentView, new Insets(10,10,10,10));
            System.out.println("Lecturer List Updated");
        }

    }

    public void btn_registerLecturer_Click(ActionEvent actionEvent) {

        try {
            FXMLLoader loder = new FXMLLoader();
            loder.setLocation(getClass().getResource("../AdminPanel/RegisterNewLecturer.fxml"));
            Parent root = loder.load();
            RegisterNewLecturer controller = loder.getController();
            controller.setMainPage(this);
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Add New Lecturer");
            primaryStage.setScene(new Scene(root, 600, 752));
            primaryStage.show();

            Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
            primaryStage.setX((primScreenBounds.getWidth() - primaryStage.getWidth()) / 2);
            primaryStage.setY((primScreenBounds.getHeight() - primaryStage.getHeight()) / 2);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void btn_deleteLecturer_Click(ActionEvent actionEvent) {
        try {
            FXMLLoader loder = new FXMLLoader();
            loder.setLocation(getClass().getResource("../AdminPanel/RemoveLecturer.fxml"));
            Parent root = loder.load();
            RemoveLecturer controller = loder.getController();
            controller.setMainPage(this);
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Remove Lecturer");
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
