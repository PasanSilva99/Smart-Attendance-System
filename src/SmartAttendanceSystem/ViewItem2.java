package SmartAttendanceSystem;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

import java.io.File;

public class ViewItem2 extends AnchorPane {

    public String ModuleName;
    public String Time;
    public String ImagePath;
    public String id;
    public String icon;
    Label Details;
    AnchorPane item;
    ImageView TopInfoImage;
    AnchorPane ModuleItem;

    public ViewItem2(String ModuleName, String Time, String ImagePath, String id, String icon) {
        this.ModuleName = ModuleName;
        this.Time = Time;
        this.ImagePath = ImagePath;
        this.id = id;
        this.icon = icon;

    }

    public static void addEventFilter() {
    }


    public AnchorPane CreateModuleItem() {
        try {
            ModuleItem = new AnchorPane();

            // BackGround
            ModuleItem.setPrefHeight(50.0);
            ModuleItem.setPrefWidth(230.0);
            ModuleItem.setStyle("-fx-background-color:#DEDDDD; -fx-border-radius:20px; -fx-background-radius:20px; -fx-border-color: gray; -fx-border-width: 0px 0px 1px 0px");
            File file = new File(System.getProperty("user.dir") + "\\src\\SmartAttendanceSystem\\info.png");
            TopInfoImage = new ImageView(new Image(file.toURI().toString()));
            TopInfoImage.setFitHeight(20.0);
            TopInfoImage.setFitWidth(20.0);
            ModuleItem.setTopAnchor(TopInfoImage, -7.0);
            ModuleItem.setRightAnchor(TopInfoImage, 0.0);

            AnchorPane btn;
            btn = new AnchorPane();
            btn.setStyle("-fx-background-color:#DEDDDD; -fx-border-radius:20px; -fx-background-radius:20px; -fx-border-color: gray; -fx-border-width: 0px 0px 1px 0px");
            btn.setPrefWidth(275);
            btn.setPrefHeight(50);

            ImageView ModuleImage = new ImageView();
            File file2 = new File(ImagePath);
            ModuleImage.setImage(new Image(file2.toURI().toString()));
            ModuleImage.setFitWidth(60);
            ModuleImage.setFitHeight(60);
            ModuleImage.setLayoutX(6.0);
            ModuleImage.setLayoutY(4.0);
            ModuleImage.setPickOnBounds(false);
            setTopAnchor(ModuleImage, 9.0);
            btn.setLeftAnchor(ModuleImage, 12.0);
            btn.setBottomAnchor(ModuleImage, 9.0);

            Label ModuleDetails = new Label();
            ModuleDetails.setPrefWidth(180);
            ModuleDetails.setPrefHeight(50);
            ModuleDetails.setFont(new Font("Felix Titling", 12));
            btn.setTopAnchor(ModuleDetails, 9.0);
            btn.setRightAnchor(ModuleDetails, 12.0);
            btn.setBottomAnchor(ModuleDetails, 9.0);

            ModuleDetails.setText(id + "\n" + ModuleName + "\n" + Time);
            btn.getChildren().addAll(ModuleDetails, ModuleImage);

            ModuleItem.getChildren().addAll(btn, TopInfoImage);

            return ModuleItem;
        } catch (Exception e) {
            ModuleItem = new AnchorPane();
            // BackGround
            ModuleItem.setPrefHeight(70.0);
            ModuleItem.setPrefWidth(230.0);
            ModuleItem.setStyle("-fx-background-color: lightgrey;");
            File file = new File("Images/info.png");
            TopInfoImage = new ImageView(new Image(file.toURI().toString()));
            TopInfoImage.setFitHeight(20.0);
            TopInfoImage.setFitWidth(20.0);
            ModuleItem.setTopAnchor(TopInfoImage, 0.0);
            ModuleItem.setRightAnchor(TopInfoImage, 0.0);

            AnchorPane btn;

            btn = new AnchorPane();
            btn.setStyle("-fx-background-color:#DEDDDD; -fx-border-radius:20px; -fx-background-radius:20px; -fx-border-color: gray; -fx-border-width: 0px 0px 1px 0px");
            btn.setPrefWidth(275);
            btn.setPrefHeight(100);

            ImageView ModuleImage = new ImageView();
            File file2 = new File(ImagePath);
            ModuleImage.setImage(new Image(file2.toURI().toString()));
            ModuleImage.setFitWidth(60);
            ModuleImage.setFitHeight(60);
            ModuleImage.setLayoutX(6.0);
            ModuleImage.setLayoutY(4.0);
            ModuleImage.setPickOnBounds(false);
            setTopAnchor(ModuleImage, 9.0);
            btn.setLeftAnchor(ModuleImage, 12.0);
            btn.setBottomAnchor(ModuleImage, 9.0);

            Label ModuleDetails = new Label();
            ModuleDetails.setPrefWidth(200);
            ModuleDetails.setPrefHeight(50);
            ModuleDetails.setFont(new Font("Sans-serif", 12));
            btn.setTopAnchor(ModuleDetails, 9.0);
            btn.setRightAnchor(ModuleDetails, 12.0);
            btn.setBottomAnchor(ModuleDetails, 9.0);

            ModuleDetails.setText(id + "\n" + ModuleName + "\n" + Time);
            ModuleItem.getChildren().addAll(btn, TopInfoImage);
            btn.getChildren().addAll(ModuleDetails, ModuleImage);

            return ModuleItem;

        }
    }

    public String getModuleName() {
        return ModuleName;
    }

    public void setModuleName(String moduleName) {
        ModuleName = moduleName;
    }


}

