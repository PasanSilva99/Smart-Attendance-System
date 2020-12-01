package StudentApplication;

import Common.*;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class MainScene  implements Initializable {

    public User user;

    public Label username;
    @FXML
    GridPane grid_ModuleView;
    @FXML
    ImageView testImage;
    @FXML
    Label hello;
    @FXML
    AnchorPane base2;
    @FXML
    ImageView TopInfoImage;

    List<UniEvent> uniEventsList = new ArrayList<>();

    public void setUser(User user){
        this.user = user;

        uniEventsList = new UniEventDAO().getEventList();

        username.setText(user.getName());

        int r = 0, c = 0;
        for (UniEvent event_: uniEventsList) {

            AnchorPane eventView = event_.generateEventView();
            EventHandler<MouseEvent> OnMouseClick = new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    try {
                        module_Click(event_);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };
            eventView.addEventFilter(MouseEvent.MOUSE_CLICKED, OnMouseClick);
            c++;

            grid_ModuleView.add(eventView, c, r);
            grid_ModuleView.setMargin(eventView, new Insets(20, 0, 0, 10));
            if (c > 1) {
                r++;
                c = 0;
            }

        }

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



    }

    public void module_Click(UniEvent ClickedItem) throws IOException {

        try{
            FXMLLoader loder = new FXMLLoader();
            loder.setLocation(getClass().getResource("MarkAttendance.fxml"));
            AnchorPane scene = loder.load();
            MarkAttendance controller = loder.getController();
            controller.setUser(user);
            controller.setEvent(ClickedItem);
            base2.getChildren().clear();
            base2.getChildren().add(scene);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}



