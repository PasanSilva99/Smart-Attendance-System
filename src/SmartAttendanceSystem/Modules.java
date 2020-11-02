package SmartAttendanceSystem;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Modules implements Initializable {

    @FXML
    GridPane ModuleView;
    @FXML
    ImageView testImage;
    @FXML
    Label pathtxt;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {




        //Algorithm For Generating The Module Items
        int NoOfItems = 6;
        int c =0, r=0, i=0;
        while (c<NoOfItems) {

            if(r>1)
            {
                i++;
                r=0;
            }



            AnchorPane btn;

            btn = new AnchorPane();
            btn.setStyle("-fx-background-color:#DEDDDD; -fx-border-radius:20px; -fx-background-radius:20px;");
            btn.setPrefWidth(300);
            btn.setPrefHeight(100);

            ImageView ModuleImage = new ImageView();
            File file = new File("Images/profile.jpg");
            ModuleImage.setImage(new Image(file.toURI().toString()));
            ModuleImage.setFitWidth(82);
            ModuleImage.setFitHeight(82);
            ModuleImage.setLayoutX(8.0);
            ModuleImage.setLayoutY(6.0);
            ModuleImage.setPickOnBounds(false);
            btn.setTopAnchor(ModuleImage, 9.0);
            btn.setLeftAnchor(ModuleImage, 12.0);
            btn.setBottomAnchor(ModuleImage, 9.0);

            Label ModuleDetails = new Label();
            ModuleDetails.setPrefWidth(171);
            ModuleDetails.setPrefHeight(80);
            btn.setTopAnchor(ModuleDetails, 9.0);
            btn.setRightAnchor(ModuleDetails, 12.0);
            btn.setBottomAnchor(ModuleDetails, 9.0);

            ModuleDetails.setText("Test 1\nTest 2\nTest 3");

            btn.getChildren().addAll(ModuleImage, ModuleDetails);

            ModuleView.add(btn, r, i);


            r++;
            c++;
        }
    }
}
