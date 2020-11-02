package SmartAttendanceSystem;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ModuleViewItem extends VBox {

    @FXML
    Label Details;
    @FXML
    ImageView ModuleImage;

    public ModuleViewItem() {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "ModuleViewItem.fxml"));
        //fxmlLoader.setRoot(this);
        //fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        Details.setText("Testing 1\nTesting 2\nDone...");
        Image image = new Image("@Images/profile.jpg");
        ModuleImage.setImage(image);
    }
}
