package StudentApplication;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import java.io.File;

public class AfterDashboardScrollItem {

    String ImagePath;
    String Text;

    public String getImagePath() {
        return ImagePath;
    }

    public void setImagePath(String imagePath) {
        ImagePath = imagePath;
    }

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }

    public AfterDashboardScrollItem() {
    }

    public AfterDashboardScrollItem(String imagePath, String text) {
        ImagePath = imagePath;
        Text = text;
    }

    private AnchorPane generateControl() {
        AnchorPane myControl = new AnchorPane();
        myControl.setPrefHeight(100.0);
        myControl.setPrefWidth(200.0);
        myControl.setStyle("-fx-background-color: lightgray");


        Label lbl_label = new Label("image Name");
        lbl_label.setAlignment(Pos.CENTER_RIGHT);
        lbl_label.setLayoutX(88.0);
        lbl_label.setLayoutY(17.0);
        lbl_label.setPrefHeight(64.0);
        lbl_label.setPrefWidth(100.0);

        AnchorPane.setLeftAnchor(lbl_label, 88.0);
        AnchorPane.setRightAnchor(lbl_label, 12.0);

        File file = new File(ImagePath);
        ImageView image_img = new ImageView(new Image(file.toURI().toString()));
        image_img.setFitWidth(74.0);
        image_img.setFitHeight(59.0);
        image_img.setLayoutX(6.0);
        image_img.setLayoutY(24.0);
        image_img.setPickOnBounds(true);
        image_img.setPreserveRatio(true);

        AnchorPane.setBottomAnchor(image_img, 17.0);
        AnchorPane.setTopAnchor(image_img, 24.0);
        AnchorPane.setLeftAnchor(image_img, (image_img.getParent().getScene().getWidth() - image_img.getFitWidth()) / 2);
        AnchorPane.setRightAnchor(image_img, (image_img.getParent().getScene().getWidth() - image_img.getFitWidth()) / 2);

        myControl.getChildren().addAll(lbl_label, image_img);

        return myControl;
    }


       public AnchorPane getControl(){
           AnchorPane myControl = generateControl();
        myControl.setStyle("-fx-border-color: GRAY; -fx-border-width: 1;-fx-border-radius: 10;-fx-background-color: Lightgray");


           javafx.event.EventHandler<MouseEvent> onMouseEnter = new javafx.event.EventHandler<MouseEvent>() {
               @Override
               public void handle(MouseEvent mouseEvent) {
                   myControl.setStyle("-fx-border-width: 0");
                   DropShadow shadow = new DropShadow();
                   shadow.setColor(Color.GRAY);

                   myControl.setEffect(shadow);

               }
           };

           EventHandler<MouseEvent> onMouseLeave = new EventHandler<MouseEvent>() {
               @Override
               public void handle(MouseEvent mouseEvent) {
                   myControl.setStyle("-fx-border-color: grey; -fx-border-width: 1;-fx-border-radius: 10");
                   myControl.setEffect(null);

               }
           };



           myControl.addEventFilter(MouseEvent.MOUSE_ENTERED, onMouseEnter);
           myControl.addEventFilter(MouseEvent.MOUSE_EXITED, onMouseLeave);
           return myControl;


       }

}

