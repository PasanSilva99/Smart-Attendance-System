package StudentApplication;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static javafx.fxml.FXMLLoader.load;

public class SecondController implements Initializable {
    @FXML
    javafx.scene.control.Button btnquiz1;
    @FXML
    javafx.scene.control.Button btnconfirm;
    @FXML
    javafx.scene.control.Button btnquiz2;
    @FXML
    javafx.scene.control.Button btnstatus1;
    @FXML
    javafx.scene.control.Button btnstatus2;
    @FXML
    javafx.scene.control.Label lblstatus1;
    @FXML
    javafx.scene.control.Label lblstatus2;
    @FXML
    javafx.scene.control.Button btnsub;
    @FXML
    javafx.scene.control.Button btnback;
    @FXML
    AnchorPane base3;
    @FXML
    ImageView backimage;
    @FXML
    javafx.scene.control.Label lblsub;
    public void setdate(String soft255, String s, String l1o2) {
        lblsub.setText(soft255 +"\n"+ s +"\n"+ l1o2);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblsub.setPrefWidth(200);
        lblsub.setPrefHeight(70);
        lblsub.setStyle("-fx-text-alignment: Center; -fx-background-color: #C0C0C0\t; -fx-border-radius: 10; -fx-background-radius: 10");
        lblsub.setFont(Font.font("fantasy", FontWeight.BOLD, 15));
        ImageView ModuleImage = new ImageView();
        File file = new File(System.getProperty("user.dir") + "\\src\\SmartAttendanceSystem\\Images\\ss.png");
        ModuleImage.setImage(new Image(file.toURI().toString()));
        ModuleImage.setFitWidth(60);
        ModuleImage.setFitHeight(60);
        ModuleImage.setLayoutX(6.0);
        ModuleImage.setLayoutY(4.0);
        ModuleImage.setPickOnBounds(false);
        lblsub.setGraphic(ModuleImage);

        backimage.setPickOnBounds(true);
        backimage.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                AnchorPane page = null;
                try {
                    page = load(getClass().getResource("MainScene.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                base3.getChildren().clear();
                base3.getChildren().setAll(page);
            }
        });

        btnquiz1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                btnquiz1.setStyle("-fx-background-color:  #D3D3D3; -fx-border-radius: 10; -fx-background-radius: 10");
                lblstatus1.setText("Not Completed");

            }
        });

        btnback.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                AnchorPane page = null;
                try {
                    page = load(getClass().getResource("MainScene.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                base3.getChildren().clear();
                base3.getChildren().setAll(page);
            }
        });
        btnquiz2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                btnquiz2.setStyle("-fx-background-color: #D3D3D3; -fx-border-radius: 10; -fx-background-radius: 10");
                lblstatus2.setText("Not Completed");
            }
        });

        btnstatus1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                btnquiz1.setStyle("-fx-background-color:#32CD32; -fx-border-radius: 10; -fx-background-radius: 10");
                lblstatus1.setText("Yet to complete");
            }
        });
        btnstatus2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                btnquiz2.setStyle("-fx-background-color:#32CD32; -fx-border-radius: 10; -fx-background-radius: 10");
                lblstatus2.setText("Yet to complete");
            }
        });

    }


}

