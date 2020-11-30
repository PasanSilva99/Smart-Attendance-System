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
import java.lang.annotation.Retention;
import java.util.List;

public class User {
    private String nsbm_id;
    private String first_name;
    private String last_name;
    private String nsbm_email;
    private String password_hash;
    private String degree_program;
    private String batch;
    private String priviladgeLevel;
    public List<Module> moduleList;

    public User(String nsbm_id, String first_name, String last_name,
                String nsbm_email, String password_hash, String degree_program,
                String batch, String priviladgeLevel) {
        this.nsbm_id = nsbm_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.nsbm_email = nsbm_email;
        this.password_hash = password_hash;

        this.degree_program = degree_program;
        this.batch = batch;
        this.priviladgeLevel = priviladgeLevel;

    }

    public String getNsbm_id() {
        return nsbm_id;
    }

    public void setNsbm_id(String nsbm_id) {
        this.nsbm_id = nsbm_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getNsbm_email() {
        return nsbm_email;
    }

    public void setNsbm_email(String nsbm_email) {
        this.nsbm_email = nsbm_email;
    }

    public String getPassword_hash() {
        return password_hash;
    }

    public void setPassword_hash(String password_hash) {
        this.password_hash = password_hash;
    }

    public String getDegree_program() {
        return degree_program;
    }

    public void setDegree_program(String degree_program) {
        this.degree_program = degree_program;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getPriviladgeLevel() {
        return priviladgeLevel;
    }

    public void setPriviladgeLevel(String priviladgeLevel) {
        this.priviladgeLevel = priviladgeLevel;
    }

    public List<Module> getModuleList() {
        return moduleList;
    }

    public void setModuleList(List<Module> moduleList) {
        this.moduleList = moduleList;
    }

    public AnchorPane generateStudentView(){
        AnchorPane base = new AnchorPane();
        base.setPrefSize(665, 60);
        base.setStyle("" +
                "-fx-background-color: #e3e3e3; " +
                "-fx-border-color: #a4a4a4; " +
                "-fx-border-radius: 5; " +
                "-fx-background-radius: 5");

        File file = new File("Images/student.png");
        Image img = new Image(file.toURI().toString());
        ImageView img_student = new ImageView(img);
        img_student.setFitHeight(40);
        img_student.setFitWidth(40);
        AnchorPane.setLeftAnchor(img_student, 7.0);
        AnchorPane.setTopAnchor(img_student, 10.0);

        Label lbl_details = new Label( nsbm_id+ "\t" +first_name+" "+last_name + "\t"+nsbm_email);
        lbl_details.setFont(new Font("Century", 14.0));
        AnchorPane.setLeftAnchor(lbl_details,65.0);
        AnchorPane.setTopAnchor(lbl_details, 23.0);

        base.getChildren().addAll(img_student, lbl_details);

        return base;
    }

    public AnchorPane getUserView(){
        AnchorPane view = generateStudentView();

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
