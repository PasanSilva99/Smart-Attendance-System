package Common;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import java.io.File;

public class ViewItemWithBadge extends AnchorPane {
    private String ImagePath = "Images/openBook.png";
    private String Heading = "Heading";
    private String Subheading1 = "Subheading";
    private String Subheading2 = "Subheading";
    private String Batch = "";
    private String Other = "Other";
    private boolean isNew = true;

    AnchorPane control;

    public ViewItemWithBadge() {

    }

    public String getBatch() {
        return Batch;
    }

    public void setBatch(String batch) {
        this.Batch = batch;
    }

    public String getImagePath() {
        return ImagePath;
    }

    public void setImagePath(String imagePath) {
        ImagePath = imagePath;
    }

    public String getHeading() {
        return Heading;
    }

    public void setHeading(String heading) {
        Heading = heading;
    }

    public String getSubheading1() {
        return Subheading1;
    }

    public void setSubheading1(String subheading1) {
        Subheading1 = subheading1;
    }

    public String getSubheading2() {
        return Subheading2;
    }

    public void setSubheading2(String subheading2) {
        Subheading2 = subheading2;
    }

    public String getOther() {
        return Other;
    }

    public void setOther(String other) {
        Other = other;
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
        if(isNew){

        }
    }

    public void setControlStyle(String color){

    }

    public ViewItemWithBadge(String heading, String subheading1, String subheading2, String batch, String other) {
        Heading = heading;
        Subheading1 = subheading1;
        Subheading2 = subheading2;
        Batch = batch;
        Other = other;
    }

    public ViewItemWithBadge(String heading, String subheading1, String subheading2, String other, String batch,boolean isNew) {
        Heading = heading;
        Subheading1 = subheading1;
        Subheading2 = subheading2;
        Batch = batch;
        Other = other;
        this.isNew = isNew;
    }

    public ViewItemWithBadge(String imagePath, String heading, String subheading1, String subheading2, String other, String batch) {
        ImagePath = imagePath;
        Heading = heading;
        Subheading1 = subheading1;
        Subheading2 = subheading2;
        Batch = batch;
        Other = other;
    }

    public ViewItemWithBadge(String imagePath, String heading, String subheading1, String subheading2, String other, String batch, boolean isNew) {
        ImagePath = imagePath;
        Heading = heading;
        Subheading1 = subheading1;
        Subheading2 = subheading2;
        Batch = batch;
        Other = other;
        this.isNew = isNew;
    }

    private AnchorPane generateControl(){

        // This will be the background pane
        control = new AnchorPane();
        control.setStyle("-fx-background-color: #C7C6CC; \n" +
                "               -fx-background-radius: 10; \n" +
                "               -fx-border-radius: 10; \n" +
                "               -fx-border-width: 0px 0px 2px 0px; \n" +
                "               -fx-border-color: #545454;");

        control.setPrefSize(350,100);

        // This will be the heading Label
        Label lbl_heading = new Label(Heading);
        lbl_heading.setAlignment(Pos.CENTER);
        lbl_heading.setFont(new Font("Felix Titling", 16.0));
        lbl_heading.setTextAlignment(TextAlignment.CENTER);
        AnchorPane.setTopAnchor(lbl_heading, 5.0);
        AnchorPane.setLeftAnchor(lbl_heading, 60.0);
        AnchorPane.setRightAnchor(lbl_heading, 5.0);

        // This will be the sub heading Label
        Label lbl_subHeading = new Label(Batch+"\n"+Subheading1+"\n"+Subheading2);
        lbl_subHeading.setAlignment(Pos.CENTER);
        lbl_subHeading.setTextAlignment(TextAlignment.CENTER);
        lbl_subHeading.setFont(new Font("Century", 12.0));

        AnchorPane.setTopAnchor(lbl_subHeading, 28.0);
        AnchorPane.setLeftAnchor(lbl_subHeading, 60.0);
        AnchorPane.setRightAnchor(lbl_subHeading, 5.0);

        // This will be the others Label
        Label lbl_other = new Label(Other);
        lbl_other.setAlignment(Pos.CENTER);
        lbl_other.setFont(new Font("Felix Titling", 14.0));

        AnchorPane.setLeftAnchor(lbl_other, 60.0);
        AnchorPane.setRightAnchor(lbl_other, 5.0);
        AnchorPane.setBottomAnchor(lbl_other, 2.0);

        // This will be the Item Image
        File file = new File(ImagePath);
        ImageView img_item = new ImageView(new Image(file.toURI().toString()));
        img_item.setPickOnBounds(true);
        img_item.setPreserveRatio(true);
        AnchorPane.setBottomAnchor(img_item,3.0);
        AnchorPane.setLeftAnchor(img_item, 10.0);
        AnchorPane.setTopAnchor(img_item, 12.0);
        img_item.setFitHeight(70);
        img_item.setFitWidth(img_item.getFitHeight());

        // This will be the Info Image
        File file2 = new File("Images/info.png");
        ImageView img_info = new ImageView(new Image(file2.toURI().toString()));
        img_info.setFitHeight(20.0);
        img_info.setFitWidth(20.0);
        img_info.setPickOnBounds(true);
        img_info.setPreserveRatio(true);
        if(isNew) img_info.setVisible(true);
        else img_info.setVisible(false);
        AnchorPane.setRightAnchor(img_info, -5.0);
        AnchorPane.setTopAnchor(img_info, -5.0);

        control.getChildren().addAll(img_info, img_item, lbl_heading, lbl_subHeading,lbl_other);

        return control;
    }

    public AnchorPane getControl(){
        AnchorPane pane = generateControl();

        EventHandler<MouseEvent> OnMouseEnter = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                pane.setStyle("" +
                        "-fx-background-color: #C7C6CC; \n" +
                        "-fx-background-radius: 10; \n" +
                        "-fx-border-radius: 10; \n" +
                        "-fx-border-width: 0px 0px 0px 0px; \n" +
                        "-fx-border-color: #545454;");

                DropShadow shadow = new DropShadow();
                shadow.setColor(Color.LIGHTGRAY);

                pane.setEffect(shadow);
            }
        };

        EventHandler<MouseEvent> OnMouseExit = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                pane.setStyle("" +
                        "-fx-background-color: #C7C6CC; \n" +
                        "-fx-background-radius: 10; \n" +
                        "-fx-border-radius: 10; \n" +
                        "-fx-border-width: 0px 0px 2px 0px; \n" +
                        "-fx-border-color: #141414;");

                pane.setEffect(null);
            }
        };

        pane.addEventFilter(MouseEvent.MOUSE_ENTERED, OnMouseEnter);
        pane.addEventFilter(MouseEvent.MOUSE_EXITED, OnMouseExit);

        return pane;
    }

}
