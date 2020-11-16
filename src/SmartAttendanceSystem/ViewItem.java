package SmartAttendanceSystem;

import javafx.animation.*;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

import javax.swing.text.View;
import java.io.File;

public class ViewItem extends AnchorPane {

    public String ModuleName;
    public String Batch;
    public String ImagePath;
    public String SessionName;
    public String SessionDate;
    public boolean isHovering;
    public boolean isClicked;

    public String getBatch() {
        return Batch;
    }

    public void setBatch(String batch) {
        Batch = batch;
    }

    public String getImagePath() {
        return ImagePath;
    }

    public void setImagePath(String imagePath) {
        ImagePath = imagePath;
    }

    public String getSessionName() {
        return SessionName;
    }

    public void setSessionName(String sessionName) {
        SessionName = sessionName;
    }

    public String getSessionDate() {
        return SessionDate;
    }

    public void setSessionDate(String sessionDate) {
        SessionDate = sessionDate;
    }

    public boolean isHovering() {
        return isHovering;
    }

    public void setHovering(boolean hovering) {
        isHovering = hovering;
    }

    public boolean isClicked() {
        return isClicked;
    }

    public void setClicked(boolean clicked) {
        isClicked = clicked;
    }

    public String getModuleName(){
        return ModuleName;
    }

    public void setModuleName(String ModuleName){
        this.ModuleName = ModuleName;
    }

    @Override
    public String toString() {
        return "ViewItem{" +
                "ModuleName='" + ModuleName + '\'' +
                ", Batch='" + Batch + '\'' +
                ", ImagePath='" + ImagePath + '\'' +
                ", SessionName='" + SessionName + '\'' +
                ", SessionDate='" + SessionDate + '\'' +
                ", isHovering=" + isHovering +
                ", isClicked=" + isClicked +
                '}';
    }

    public ViewItem(String ModuleName, String Batch, String ImagePath) {
        this.ModuleName = ModuleName;
        this.Batch = Batch;
        this.ImagePath = ImagePath;
    }

    public ViewItem(String SessionName, String SessionDate){
        this.SessionName=SessionName;
        this.SessionDate=SessionDate;
    }


    public AnchorPane CreateModuleItem(){

        try {
            AnchorPane ViewItemBack;

            ViewItemBack = new AnchorPane();
            ViewItemBack.setStyle("-fx-background-color:#DEDDDD; -fx-border-radius:20px; -fx-background-radius:20px; -fx-border-color: gray; -fx-border-width: 0px 0px 1px 0px");
            ViewItemBack.setPrefWidth(275);
            ViewItemBack.setPrefHeight(100);

            ImageView ModuleImage = new ImageView();
            File file = new File(ImagePath);
            ModuleImage.setImage(new Image(file.toURI().toString()));
            ModuleImage.setFitWidth(60);
            ModuleImage.setFitHeight(60);
            ModuleImage.setLayoutX(6.0);
            ModuleImage.setLayoutY(4.0);
            ModuleImage.setPickOnBounds(false);
            ViewItemBack.setTopAnchor(ModuleImage, 9.0);
            ViewItemBack.setLeftAnchor(ModuleImage, 12.0);
            ViewItemBack.setBottomAnchor(ModuleImage, 9.0);

            Label ModuleDetails = new Label();
            ModuleDetails.setPrefWidth(180);
            ModuleDetails.setPrefHeight(80);
            ModuleDetails.setFont(new Font("Felix Titling", 11));
            ViewItemBack.setTopAnchor(ModuleDetails, 9.0);
            ViewItemBack.setRightAnchor(ModuleDetails, 10.0);
            ViewItemBack.setBottomAnchor(ModuleDetails, 9.0);

            ModuleDetails.setText(ModuleName + "\n" + Batch);

            ViewItemBack.getChildren().addAll(ModuleImage, ModuleDetails);

            EventHandler<MouseEvent> OnItemHover =new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    ViewItemBack.setStyle("-fx-background-color:#ECECEC; -fx-border-radius:20px; -fx-background-radius:20px; -fx-border-color: gray; -fx-border-width: 0px 0px 0px 0px");

                    DropShadow shadow = new DropShadow();
                    shadow.setColor(Color.web("#BDBAAF"));
                    Timeline timeline = new Timeline();
                    timeline.getKeyFrames().setAll(
                            new KeyFrame(Duration.ZERO,
                                    new KeyValue(shadow.radiusProperty(), shadow.getRadius()),
                                    new KeyValue(ViewItemBack.styleProperty(), "-fx-background-color:#DEDDDD; -fx-border-radius:20px; -fx-background-radius:20px; -fx-border-color: gray; -fx-border-width: 0px 0px 1px 0px")
                            ),
                            new KeyFrame(Duration.millis(100),
                                    new KeyValue(shadow.radiusProperty(), 10),
                                    new KeyValue(ViewItemBack.styleProperty(), "-fx-background-color:#DEDDDD; -fx-border-radius:20px; -fx-background-radius:20px; -fx-border-color: gray; -fx-border-width: 0px 0px 0px 0px")
                            )
                    );
                    timeline.play();


                    ViewItemBack.setEffect(shadow);
                }
            };

            EventHandler<MouseEvent> OnMouseLeave = new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    ViewItemBack.setStyle("-fx-background-color:#DEDDDD; -fx-border-radius:20px; -fx-background-radius:20px; -fx-border-color: gray; -fx-border-width: 0px 0px 1px 0px");
                    ViewItemBack.setEffect(null);
                }
            };

            EventHandler<MouseEvent> OnMousePressed = new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    ViewItemBack.setStyle("-fx-background-color:#8799B1; -fx-border-radius:20px; -fx-background-radius:20px; -fx-border-color: gray; -fx-border-width: 1px 1px 1px 1px");
                    DropShadow ds1 = new DropShadow();
                    ds1.setOffsetY(4.0f);
                    ds1.setColor(Color.GREY);
                    ViewItemBack.setEffect(ds1);
                }
            };

            EventHandler<MouseEvent> OnMouseReleased = new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    ViewItemBack.setStyle("-fx-background-color:#DDDDDD; -fx-border-radius:20px; -fx-background-radius:20px; -fx-border-color: gray; -fx-border-width: 0px 0px 0px 0px");
                    DropShadow ds1 = new DropShadow();
                    ds1.setOffsetY(0.0f);
                    ds1.setColor(Color.GREY);
                    ViewItemBack.setEffect(ds1);
                }
            };

            ViewItemBack.addEventFilter(MouseEvent.MOUSE_ENTERED_TARGET, OnItemHover);
            ViewItemBack.addEventFilter(MouseEvent.MOUSE_EXITED_TARGET, OnMouseLeave);
            ViewItemBack.addEventFilter(MouseEvent.MOUSE_PRESSED, OnMousePressed);
            ViewItemBack.addEventFilter(MouseEvent.MOUSE_RELEASED, OnMouseReleased);


            ModuleDetails.addEventFilter(MouseEvent.MOUSE_ENTERED_TARGET, OnItemHover);
            ModuleDetails.addEventFilter(MouseEvent.MOUSE_EXITED_TARGET, OnMouseLeave);
            ModuleDetails.addEventFilter(MouseEvent.MOUSE_PRESSED, OnMousePressed);
            ModuleDetails.addEventFilter(MouseEvent.MOUSE_RELEASED, OnMouseReleased);

            ModuleImage.addEventFilter(MouseEvent.MOUSE_ENTERED_TARGET, OnItemHover);
            ModuleImage.addEventFilter(MouseEvent.MOUSE_EXITED_TARGET, OnMouseLeave);
            ModuleImage.addEventFilter(MouseEvent.MOUSE_PRESSED, OnMousePressed);
            ModuleImage.addEventFilter(MouseEvent.MOUSE_RELEASED, OnMouseReleased);



            return ViewItemBack;
        }
        catch (Exception e){

            AnchorPane  error = new AnchorPane();
            error.setStyle("-fx-background-color:#ff7979; -fx-border-radius:20px; -fx-background-radius:20px; -fx-border-color: gray; -fx-border-width: 0px 0px 1px 0px");
            error.setPrefWidth(275);
            error.setPrefHeight(100);

            ImageView ModuleImage = new ImageView();
            File file = new File(ImagePath);
            ModuleImage.setImage(new Image(file.toURI().toString()));
            ModuleImage.setFitWidth(60);
            ModuleImage.setFitHeight(60);
            ModuleImage.setLayoutX(6.0);
            ModuleImage.setLayoutY(4.0);
            ModuleImage.setPickOnBounds(false);
            error.setTopAnchor(ModuleImage, 9.0);
            error.setLeftAnchor(ModuleImage, 12.0);
            error.setBottomAnchor(ModuleImage, 9.0);

            Label ModuleDetails = new Label();
            ModuleDetails.setPrefWidth(180);
            ModuleDetails.setPrefHeight(80);
            ModuleDetails.setFont(new Font("Felix Titling", 11));
            error.setTopAnchor(ModuleDetails, 9.0);
            error.setRightAnchor(ModuleDetails, 10.0);
            error.setBottomAnchor(ModuleDetails, 9.0);

            ModuleDetails.setText("Error Setting Module");

            error.getChildren().addAll(ModuleImage, ModuleDetails);


            return error;
        }
    }

    public AnchorPane CreateSessionItem(){

        try {
            AnchorPane ViewItemBack;

            ViewItemBack = new AnchorPane();
            ViewItemBack.setStyle("-fx-background-color:#DEDDDD; -fx-border-radius:20px; -fx-background-radius:20px; -fx-border-color: gray; -fx-border-width: 0px 0px 1px 0px");
            ViewItemBack.setPrefWidth(275);
            ViewItemBack.setPrefHeight(100);

            ImageView ModuleImage = new ImageView();
            File file = new File(ImagePath);
            ModuleImage.setImage(new Image(file.toURI().toString()));
            ModuleImage.setFitWidth(60);
            ModuleImage.setFitHeight(60);
            ModuleImage.setLayoutX(6.0);
            ModuleImage.setLayoutY(4.0);
            ModuleImage.setPickOnBounds(false);
            ViewItemBack.setTopAnchor(ModuleImage, 9.0);
            ViewItemBack.setLeftAnchor(ModuleImage, 12.0);
            ViewItemBack.setBottomAnchor(ModuleImage, 9.0);

            Label ModuleDetails = new Label();
            ModuleDetails.setPrefWidth(180);
            ModuleDetails.setPrefHeight(80);
            ModuleDetails.setFont(new Font("Felix Titling", 11));
            ViewItemBack.setTopAnchor(ModuleDetails, 9.0);
            ViewItemBack.setRightAnchor(ModuleDetails, 10.0);
            ViewItemBack.setBottomAnchor(ModuleDetails, 9.0);

            ModuleDetails.setText(SessionName + "\n" + SessionDate);

            ViewItemBack.getChildren().addAll(ModuleImage, ModuleDetails);

            EventHandler<MouseEvent> OnItemHover =new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    ViewItemBack.setStyle("-fx-background-color:#ECECEC; -fx-border-radius:20px; -fx-background-radius:20px; -fx-border-color: gray; -fx-border-width: 0px 0px 0px 0px");

                    DropShadow shadow = new DropShadow();
                    shadow.setColor(Color.web("#BDBAAF"));
                    Timeline timeline = new Timeline();
                    timeline.getKeyFrames().setAll(
                            new KeyFrame(Duration.ZERO,
                                    new KeyValue(shadow.radiusProperty(), shadow.getRadius()),
                                    new KeyValue(ViewItemBack.styleProperty(), "-fx-background-color:#DEDDDD; -fx-border-radius:20px; -fx-background-radius:20px; -fx-border-color: gray; -fx-border-width: 0px 0px 1px 0px")
                            ),
                            new KeyFrame(Duration.millis(100),
                                    new KeyValue(shadow.radiusProperty(), 10),
                                    new KeyValue(ViewItemBack.styleProperty(), "-fx-background-color:#DEDDDD; -fx-border-radius:20px; -fx-background-radius:20px; -fx-border-color: gray; -fx-border-width: 0px 0px 0px 0px")
                            )
                    );
                    timeline.play();


                    ViewItemBack.setEffect(shadow);
                }
            };

            EventHandler<MouseEvent> OnMouseLeave = new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    ViewItemBack.setStyle("-fx-background-color:#DEDDDD; -fx-border-radius:20px; -fx-background-radius:20px; -fx-border-color: gray; -fx-border-width: 0px 0px 1px 0px");
                    ViewItemBack.setEffect(null);
                }
            };

            EventHandler<MouseEvent> OnMousePressed = new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    ViewItemBack.setStyle("-fx-background-color:#8799B1; -fx-border-radius:20px; -fx-background-radius:20px; -fx-border-color: gray; -fx-border-width: 1px 1px 1px 1px");
                    DropShadow ds1 = new DropShadow();
                    ds1.setOffsetY(4.0f);
                    ds1.setColor(Color.GREY);
                    ViewItemBack.setEffect(ds1);
                }
            };

            EventHandler<MouseEvent> OnMouseReleased = new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    ViewItemBack.setStyle("-fx-background-color:#DDDDDD; -fx-border-radius:20px; -fx-background-radius:20px; -fx-border-color: gray; -fx-border-width: 0px 0px 0px 0px");
                    DropShadow ds1 = new DropShadow();
                    ds1.setOffsetY(0.0f);
                    ds1.setColor(Color.GREY);
                    ViewItemBack.setEffect(ds1);
                }
            };

            ViewItemBack.addEventFilter(MouseEvent.MOUSE_ENTERED_TARGET, OnItemHover);
            ViewItemBack.addEventFilter(MouseEvent.MOUSE_EXITED_TARGET, OnMouseLeave);
            ViewItemBack.addEventFilter(MouseEvent.MOUSE_PRESSED, OnMousePressed);
            ViewItemBack.addEventFilter(MouseEvent.MOUSE_RELEASED, OnMouseReleased);


            ModuleDetails.addEventFilter(MouseEvent.MOUSE_ENTERED_TARGET, OnItemHover);
            ModuleDetails.addEventFilter(MouseEvent.MOUSE_EXITED_TARGET, OnMouseLeave);
            ModuleDetails.addEventFilter(MouseEvent.MOUSE_PRESSED, OnMousePressed);
            ModuleDetails.addEventFilter(MouseEvent.MOUSE_RELEASED, OnMouseReleased);

            ModuleImage.addEventFilter(MouseEvent.MOUSE_ENTERED_TARGET, OnItemHover);
            ModuleImage.addEventFilter(MouseEvent.MOUSE_EXITED_TARGET, OnMouseLeave);
            ModuleImage.addEventFilter(MouseEvent.MOUSE_PRESSED, OnMousePressed);
            ModuleImage.addEventFilter(MouseEvent.MOUSE_RELEASED, OnMouseReleased);



            return ViewItemBack;
        }
        catch (Exception e){

            AnchorPane  error = new AnchorPane();
            error.setStyle("-fx-background-color:#ff7979; -fx-border-radius:20px; -fx-background-radius:20px; -fx-border-color: gray; -fx-border-width: 0px 0px 1px 0px");
            error.setPrefWidth(275);
            error.setPrefHeight(100);

            ImageView ModuleImage = new ImageView();
            File file = new File(ImagePath);
            ModuleImage.setImage(new Image(file.toURI().toString()));
            ModuleImage.setFitWidth(60);
            ModuleImage.setFitHeight(60);
            ModuleImage.setLayoutX(6.0);
            ModuleImage.setLayoutY(4.0);
            ModuleImage.setPickOnBounds(false);
            error.setTopAnchor(ModuleImage, 9.0);
            error.setLeftAnchor(ModuleImage, 12.0);
            error.setBottomAnchor(ModuleImage, 9.0);

            Label ModuleDetails = new Label();
            ModuleDetails.setPrefWidth(180);
            ModuleDetails.setPrefHeight(80);
            ModuleDetails.setFont(new Font("Felix Titling", 11));
            error.setTopAnchor(ModuleDetails, 9.0);
            error.setRightAnchor(ModuleDetails, 10.0);
            error.setBottomAnchor(ModuleDetails, 9.0);

            ModuleDetails.setText("Error Setting Session");

            error.getChildren().addAll(ModuleImage, ModuleDetails);


            return error;
        }
    }



}
