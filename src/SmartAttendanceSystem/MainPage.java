package SmartAttendanceSystem;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.SnapshotParameters;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import javafx.scene.input.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainPage implements Initializable {

    @FXML
    AnchorPane topBar;
    @FXML
    public AnchorPane base;
    @FXML
    ImageView img_Pic;
    @FXML
    public AnchorPane ap_home;
    @FXML
    public AnchorPane ap_leaderBoard;
    @FXML
    public AnchorPane ap_modules;
    @FXML
    public AnchorPane ap_analytics;
    @FXML
    public ImageView img_home;
    @FXML
    ImageView img_modules;
    @FXML
    ImageView img_analytics;
    @FXML
    javafx.scene.control.Label lbl_home;
    @FXML
    javafx.scene.control.Label lbl_leaderBoard;
    @FXML
    javafx.scene.control.Label lbl_modules;
    @FXML
    javafx.scene.control.Label lbl_analytics;

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

    @FXML
    public void NavHomeEnter(MouseEvent mouseEvent)
    {
        ap_home.setStyle("-fx-background-color : #6B6B6B; -fx-background-radius: 20;");
    }
    @FXML
    public void NavHomeExit(MouseEvent mouseEvent)
    {
        ap_home.setStyle("-fx-background-color : #636363; ");
    }


    public void NavHomeClick(MouseEvent mouseEvent)
    {

    }


    public void NavLeaderBoardEnter(MouseEvent mouseEvent)
    {
        ap_leaderBoard.setStyle("-fx-background-color : #6B6B6B; -fx-background-radius: 20;");
    }


    public void NavLeaderBoardExit(MouseEvent mouseEvent) { ap_leaderBoard.setStyle("-fx-background-color : #636363;"); }
    @FXML
    public void NavLeaderBoardClick(MouseEvent mouseEvent) throws IOException{
        AnchorPane page = FXMLLoader.load(getClass().getResource("LeaderBoardMain.fxml"));
        base.getChildren().clear();
        base.getChildren().setAll(page);
    }

    public void NavModulesEnter(MouseEvent mouseEvent)
    {
        ap_modules.setStyle("-fx-background-color : #6B6B6B; -fx-background-radius: 20;");
    }

    public void NavModulesExit(MouseEvent mouseEvent)
    {
        ap_modules.setStyle("-fx-background-color : #636363;");
    }
    @FXML
    public void NavModulesClick(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loder = new FXMLLoader();
        loder.setLocation(getClass().getResource("Modules.fxml"));
        AnchorPane page = loder.load();
        base.getChildren().clear();
        base.getChildren().setAll(page);
        Modules Mpage = loder.getController();
        Mpage.setMainPage(this);
    }

    public void NavAnalyticsEnter(MouseEvent mouseEvent)
    {
        ap_analytics.setStyle("-fx-background-color : #6B6B6B; -fx-background-radius: 20;");
    }

    public void NavAnalyticsExit(MouseEvent mouseEvent)
    {
        ap_analytics.setStyle("-fx-background-color : #636363;");
    }

    public void NavAnalyticsClick(MouseEvent mouseEvent)
    {

    }
}
