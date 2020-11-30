package AdminPanel;

import Common.EventDetailView;
import Common.UniEvent;
import Common.UniEventDAO;
import Common.ViewItemWithBadge;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.net.URL;
import java.util.*;

public class MainSceneAdmin implements Initializable {
    public GridPane grid_lectureViewAdmin;

    public AnchorPane baseAdmin;

    List<UniEvent> LectureList = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        RefreshData();

    }

    public void setMainPage(AnchorPane mainPage){
        baseAdmin = mainPage;
    }

    public void RefreshData(){
        LectureList = new UniEventDAO().getLectureList();

        int c=0, r=0;
        for (UniEvent event:LectureList) {
            c++;
            ViewItemWithBadge item = new ViewItemWithBadge( ""+event.getModuleO(),""+event.getEventName(),""+event.getStartTime().substring(10)+" - "+ event.getEndTime().substring(10), event.getBatch(), event.getLocation());

            AnchorPane lectureItem = item.getControl();
            grid_lectureViewAdmin.add(lectureItem, c, r);

            EventHandler<MouseEvent> onMouseClick = new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    Platform.runLater(()-> itemClick(event));
                }
            };

            lectureItem.addEventFilter(MouseEvent.MOUSE_CLICKED, onMouseClick);

            GridPane.setMargin(lectureItem, new Insets(10,10,10,10));
            if(c>1){
                c=0;
                r++;
            }

        }
    }

    private void itemClick(UniEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../Common/EventDetailView.fxml"));
            AnchorPane page = loader.load();
            EventDetailView controler = loader.getController();
            controler.setEvent(event);
            // This Is the main Anchor pane.
            // To go back we need to send it to the page that we load
            controler.setBaseAdmin(baseAdmin);
            baseAdmin.getChildren().clear();
            baseAdmin.getChildren().setAll(page);

        }catch (Exception e){
            System.out.println("Couldn't Load the Page");
        }

    }

    public void btn_addNewEvent_click(ActionEvent actionEvent) {
        try {
            FXMLLoader loder = new FXMLLoader();
            loder.setLocation(getClass().getResource("AddNewEvent.fxml"));
            Parent root = loder.load();
            AddNewEvent controller = loder.getController();
            controller.setMainPage(this);
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Add New Event");
            primaryStage.setScene(new Scene(root, 380, 550));
            primaryStage.show();

            Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
            primaryStage.setX((primScreenBounds.getWidth() - primaryStage.getWidth()) / 2);
            primaryStage.setY((primScreenBounds.getHeight() - primaryStage.getHeight()) / 2);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
