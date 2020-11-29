package AdminPanel;

import Common.UniEvent;
import Common.UniEventDAO;
import Common.ViewItemWithBadge;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Formatter;
import java.util.List;
import java.util.ResourceBundle;

public class MainSceneAdmin implements Initializable {
    public GridPane grid_lectureViewAdmin;

    List<UniEvent> LectureList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        LectureList = new UniEventDAO().getLectureList();

        ViewItemWithBadge item = new ViewItemWithBadge("SOFT255SL", "9 AM - 12 PM", "L102");

        grid_lectureViewAdmin.add(item.getControl(), 0,0);
        grid_lectureViewAdmin.add(item.getControl(), 1,0);
        grid_lectureViewAdmin.add(item.getControl(), 0,1);
        grid_lectureViewAdmin.add(item.getControl(), 1,1);
    }

    public void btn_addNewEvent_click(ActionEvent actionEvent) {
        try {
            FXMLLoader loder = new FXMLLoader();
            loder.setLocation(getClass().getResource("AddNewEvent.fxml"));
            Parent root = FXMLLoader.load(getClass().getResource("AddNewEvent.fxml"));
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Add New Event");
            primaryStage.setScene(new Scene(root, 380, 415));
            primaryStage.show();

            Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
            primaryStage.setX((primScreenBounds.getWidth() - primaryStage.getWidth()) / 2);
            primaryStage.setY((primScreenBounds.getHeight() - primaryStage.getHeight()) / 2);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
