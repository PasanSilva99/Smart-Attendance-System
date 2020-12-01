package AdminPanel;

import Common.*;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PortableEventView implements Initializable {
    public List<UniEvent> UniEventList = new ArrayList<>();
    public VBox vbox_eventView;
    public Button btn_close;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        UniEventList = new UniEventDAO().getEventList();
        for (UniEvent event:UniEventList) {
            AnchorPane item= event.getEventView();
            vbox_eventView.getChildren().add(item);
            VBox.setMargin(item, new Insets(10,10,10,10));
        }

    }

    public void closeWindow(ActionEvent actionEvent) {
        Stage stage = (Stage) btn_close.getScene().getWindow();
        stage.close();
    }
}
