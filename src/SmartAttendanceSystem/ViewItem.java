package SmartAttendanceSystem;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.text.Font;

import java.io.File;

public class ViewItem extends AnchorPane {

    private String ModuleName;
    private String Batch;
    private String ImagePath;
    private String SessionName;
    private String SessionDate;

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
            AnchorPane btn;

            btn = new AnchorPane();
            btn.setStyle("-fx-background-color:#DEDDDD; -fx-border-radius:20px; -fx-background-radius:20px; -fx-border-color: gray; -fx-border-width: 0px 0px 1px 0px");
            btn.setPrefWidth(275);
            btn.setPrefHeight(100);

            ImageView ModuleImage = new ImageView();
            File file = new File(ImagePath);
            ModuleImage.setImage(new Image(file.toURI().toString()));
            ModuleImage.setFitWidth(60);
            ModuleImage.setFitHeight(60);
            ModuleImage.setLayoutX(6.0);
            ModuleImage.setLayoutY(4.0);
            ModuleImage.setPickOnBounds(false);
            btn.setTopAnchor(ModuleImage, 9.0);
            btn.setLeftAnchor(ModuleImage, 12.0);
            btn.setBottomAnchor(ModuleImage, 9.0);

            Label ModuleDetails = new Label();
            ModuleDetails.setPrefWidth(180);
            ModuleDetails.setPrefHeight(80);
            ModuleDetails.setFont(new Font("Felix Titling", 11));
            btn.setTopAnchor(ModuleDetails, 9.0);
            btn.setRightAnchor(ModuleDetails, 10.0);
            btn.setBottomAnchor(ModuleDetails, 9.0);

            ModuleDetails.setText(ModuleName + "\n" + Batch);

            btn.getChildren().addAll(ModuleImage, ModuleDetails);

            return btn;
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
            AnchorPane btn;

            btn = new AnchorPane();
            btn.setStyle("-fx-background-color:#DEDDDD; -fx-border-radius:20px; -fx-background-radius:20px; -fx-border-color: gray; -fx-border-width: 0px 0px 1px 0px");
            btn.setPrefWidth(275);
            btn.setPrefHeight(100);

            ImageView ModuleImage = new ImageView();
            File file = new File(ImagePath);
            ModuleImage.setImage(new Image(file.toURI().toString()));
            ModuleImage.setFitWidth(60);
            ModuleImage.setFitHeight(60);
            ModuleImage.setLayoutX(6.0);
            ModuleImage.setLayoutY(4.0);
            ModuleImage.setPickOnBounds(false);
            btn.setTopAnchor(ModuleImage, 9.0);
            btn.setLeftAnchor(ModuleImage, 12.0);
            btn.setBottomAnchor(ModuleImage, 9.0);

            Label ModuleDetails = new Label();
            ModuleDetails.setPrefWidth(180);
            ModuleDetails.setPrefHeight(80);
            ModuleDetails.setFont(new Font("Felix Titling", 11));
            btn.setTopAnchor(ModuleDetails, 9.0);
            btn.setRightAnchor(ModuleDetails, 10.0);
            btn.setBottomAnchor(ModuleDetails, 9.0);

            ModuleDetails.setText(ModuleName + "\n" + Batch);

            btn.getChildren().addAll(ModuleImage, ModuleDetails);

            return btn;
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
