package StudentApplication;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
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

    @FXML
    GridPane ModuleView;
    @FXML
    ImageView testImage;
    @FXML
    Label hello;
    @FXML
    AnchorPane base2;
    @FXML
    ImageView TopInfoImage;


    List<ViewItem2> Modules;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Modules = new ArrayList<ViewItem2>();
        Modules.add(new ViewItem2("Web development PlatformsL", "9AM - 11AM ", System.getProperty("user.dir") + "\\src\\SmartAttendanceSystem\\Images\\ss.png", "ISEN2002"));
        Modules.add(new ViewItem2("Network Security", "12PM - 2PM ", System.getProperty("user.dir") + "\\src\\SmartAttendanceSystem\\Images\\ss.png", "SOFT255SL"));
        Modules.add(new ViewItem2("Lab Session Java", "3PM - 4PM ", System.getProperty("user.dir") + "\\src\\SmartAttendanceSystem\\Images\\ss.png", "ISEN253"));
        Modules.add(new ViewItem2("It Leg And Ethics Tutorial", "4PM - 5PM ", System.getProperty("user.dir") + "\\src\\SmartAttendanceSystem\\Images\\ss.png", "PUSL233SL"));

        int r = 0, c = 0;
        for (ViewItem2 module : Modules) {
            Node crModule = module.CreateModuleItem();
            EventHandler<MouseEvent> OnMouseClick = new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    try {
                        module_Click(module);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };
            crModule.addEventFilter(MouseEvent.MOUSE_CLICKED, OnMouseClick);
            c++;

            ModuleView.add(crModule, c, r);
            ModuleView.setMargin(crModule, new Insets(20, 0, 0, 10));
            if (c > 1) {
                r++;
                c = 0;
            }

        }

    }

    public void module_Click(ViewItem2 ClickedItem) throws IOException {

        if (ClickedItem.id == "ISEN2002") {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("QuizView1.fxml"));
            AnchorPane pane =loader.load();
            SecondController controller = (SecondController) loader.getController();
            controller.setdate("ISEN2002","9AM-11PM","L102");
            base2.getChildren().clear();
            base2.getChildren().setAll(pane);
        }


        if (ClickedItem.id == "SOFT255SL") {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("QuizView1.fxml"));
            AnchorPane pane =loader.load();
            SecondController controller = (SecondController) loader.getController();
            controller.setdate("SOFT255","12PM-2PM","L101");
            base2.getChildren().clear();
            base2.getChildren().setAll(pane);


        }
        if (ClickedItem.id == "ISEN253") {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("QuizView1.fxml"));
            AnchorPane pane =loader.load();
            SecondController controller = (SecondController) loader.getController();
            controller.setdate("ISEN253","3PM-4PM","L001");
            base2.getChildren().clear();
            base2.getChildren().setAll(pane);


        }
        if (ClickedItem.id == "PUSL233SL") {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("QuizView1.fxml"));
            AnchorPane pane =loader.load();
            SecondController controller = (SecondController) loader.getController();
            controller.setdate("SOFT253SL","4PM-5PM","L009");
            base2.getChildren().clear();
            base2.getChildren().setAll(pane);
        }

    }


}



