package SmartAttendanceSystem;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.ImageCursor;
import javafx.scene.SnapshotParameters;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainPage implements Initializable {

    @FXML
    AnchorPane topBar;
    @FXML
    ImageView img_Pic;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // set a clip to apply rounded border to the original image.
        Rectangle clip = new Rectangle(
                img_Pic.getFitWidth(), img_Pic.getFitHeight()
        );
        clip.setArcWidth(img_Pic.getFitHeight());
        clip.setArcHeight(img_Pic.getFitHeight());
        img_Pic.setClip(clip);

        // snapshot the rounded image.
        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);
        WritableImage image = img_Pic.snapshot(parameters, null);

        // remove the rounding clip so that our effect can show through.
        img_Pic.setClip(null);

        // apply a shadow effect.
        img_Pic.setEffect(new DropShadow(2, Color.BLACK));

        // store the rounded image in the imageView.
        img_Pic.setImage(image);

    }
}
