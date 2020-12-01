package Common;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.File;

public class Module {
    public String ModuleCode;
    public String ModuleName;
    public String LecturerName;
    public String DegreeProgram;

    public String getModuleCode() {
        return ModuleCode;
    }

    public void setModuleCode(String moduleCode) {
        ModuleCode = moduleCode;
    }

    public String getModuleName() {
        return ModuleName;
    }

    public void setModuleName(String moduleName) {
        ModuleName = moduleName;
    }

    public String getLecturerName() {
        return LecturerName;
    }

    public void setLecturerName(String lecturerName) {
        LecturerName = lecturerName;
    }

    public String getDegreeProgram() {
        return DegreeProgram;
    }

    public void setDegreeProgram(String degreeProgram) {
        DegreeProgram = degreeProgram;
    }

    public Module(String moduleCode, String moduleName, String lecturerName, String degreeProgram) {
        ModuleCode = moduleCode;
        ModuleName = moduleName;
        LecturerName = lecturerName;
        DegreeProgram = degreeProgram;
    }

    public Module() {
    }

    //To generate module view

    public AnchorPane generateModuleView(){
        AnchorPane base = new AnchorPane();
        base.setPrefSize(665, 60);
        base.setStyle("" +
                "-fx-background-color: #e3e3e3; " +
                "-fx-border-color: #a4a4a4; " +
                "-fx-border-radius: 5; " +
                "-fx-background-radius: 5");

        File file = new File("Images/modulePic.png");
        Image img = new Image(file.toURI().toString());
        ImageView img_student = new ImageView(img);
        img_student.setFitHeight(40);
        img_student.setFitWidth(40);
        AnchorPane.setLeftAnchor(img_student, 7.0);
        AnchorPane.setTopAnchor(img_student, 10.0);

        Label lbl_details = new Label( getModuleCode()+ "\t" + getModuleName());
        lbl_details.setFont(new Font("Century", 14.0));
        AnchorPane.setLeftAnchor(lbl_details,65.0);
        AnchorPane.setTopAnchor(lbl_details, 23.0);

        base.getChildren().addAll(img_student, lbl_details);

        return base;
    }


    public AnchorPane getModuleView() {
        AnchorPane view = generateModuleView();

        EventHandler<MouseEvent> onMouseEnter = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                view.setStyle("" +
                        "-fx-background-color: #fefefe; " +
                        "-fx-border-color: #f4f4f4; " +
                        "-fx-border-radius: 5; " +
                        "-fx-background-radius: 5");

                DropShadow shadow = new DropShadow();
                shadow.setColor(Color.LIGHTGRAY);

                view.setEffect(shadow);
            }
        };
        EventHandler<MouseEvent> onMouseExit = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                view.setStyle("" +
                        "-fx-background-color: #e3e3e3; " +
                        "-fx-border-color: #a4a4a4; " +
                        "-fx-border-radius: 5; " +
                        "-fx-background-radius: 5");

                view.setEffect(null);
            }
        };

        view.addEventFilter(MouseEvent.MOUSE_ENTERED, onMouseEnter);
        view.addEventFilter(MouseEvent.MOUSE_EXITED, onMouseExit);

        return view;
    }





}
