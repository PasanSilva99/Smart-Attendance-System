package AdminPanel;

import Common.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class MainPageAdmin implements Initializable {

    @FXML
    public MenuButton cmb_MenuAdmin;
    @FXML
    public ImageView btn_BellAdmin;
    @FXML
    AnchorPane topBarAdmin;
    @FXML
    public AnchorPane baseAdmin;
    @FXML
    ImageView img_Admin;
    @FXML
    public AnchorPane ap_HomeAdmin;
    @FXML
    public AnchorPane ap_StudentAdmin;
    @FXML
    public AnchorPane ap_LecturerAdmin;
    @FXML
    public AnchorPane ap_DegreeAdmin;
    @FXML
    public AnchorPane ap_ModulesAdmin;
    @FXML
    public AnchorPane ap_EventsAdmin;
    @FXML
    public ImageView img_HomeAdmin;
    @FXML
    public ImageView img_StudentAdmin;
    @FXML
    public ImageView img_LecturerAdmin;
    @FXML
    public ImageView img_DegreeAdmin;
    @FXML
    public ImageView img_ModulesAdmin;
    @FXML
    public ImageView img_EventsAdmin;
    @FXML
    javafx.scene.control.Label lbl_HomeAdmin;
    @FXML
    javafx.scene.control.Label lbl_StudentAdmin;
    @FXML
    javafx.scene.control.Label lbl_LecturerAdmin;
    @FXML
    javafx.scene.control.Label lbl_DegreeAdmin;
    @FXML
    javafx.scene.control.Label lbl_ModulesAdmin;
    @FXML
    javafx.scene.control.Label lbl_EventsAdmin;

    String UserNameAdmin;

    public User userAdmin;

    //Initializing combobox label name
    public void setUserAdmin(User userAdmin){
        this.userAdmin = userAdmin;
        if(userAdmin.getNsbm_id() != null){
            cmb_MenuAdmin.setText(userAdmin.getFirst_name() + " " + userAdmin.getLast_name());
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // set a clip to apply rounded border to the original image.
        Rectangle clipAdmin = new Rectangle(
                img_Admin.getFitWidth(), img_Admin.getFitHeight()
        );

        if(userAdmin != null){
            cmb_MenuAdmin.setText(userAdmin.getFirst_name() + userAdmin.getLast_name());
        }

        clipAdmin.setArcWidth(img_Admin.getFitHeight());
        clipAdmin.setArcHeight(img_Admin.getFitHeight());
        img_Admin.setClip(clipAdmin);

        // snapshot the rounded image.
        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);
        WritableImage image = img_Admin.snapshot(parameters, null);

        // remove the rounding clip so that our effect can show through.
        img_Admin.setClip(null);

        // apply a shadow effect.
        img_Admin.setEffect(new DropShadow(2, Color.BLACK));

        // store the rounded image in the imageView.
        img_Admin.setImage(image);

        List<MenuItem> optionsAdmin = new ArrayList<>();
        optionsAdmin.add(new MenuItem("Device Settings"));
        optionsAdmin.add(new MenuItem("Logout"));
        optionsAdmin.add(new MenuItem("Exit"));

        cmb_MenuAdmin.getItems().addAll(optionsAdmin);

    }

    //Following events will added xml loaders and animations to the navigation panel
    //NavHomeAdmin

    @FXML
    public void NavHomeEnterAdmin(MouseEvent mouseEvent)
    {
        ap_HomeAdmin.setStyle("-fx-background-color : #6B6B6B; -fx-background-radius: 20;");
    }
    @FXML
    public void NavHomeExitAdmin(MouseEvent mouseEvent)
    {
        ap_HomeAdmin.setStyle("-fx-background-color : #636363; ");
    }

    @FXML
    public void NavHomeClickAdmin(MouseEvent mouseEvent) throws IOException
    {
        AnchorPane page = FXMLLoader.load(getClass().getResource("MainSceneAdmin.fxml"));
        baseAdmin.getChildren().clear();
        baseAdmin.getChildren().setAll(page);
    }

    //NavStudentAdmin

    @FXML
    public void NavStudentEnterAdmin(MouseEvent mouseEvent)
    {
        ap_StudentAdmin.setStyle("-fx-background-color : #6B6B6B; -fx-background-radius: 20;");
    }
    @FXML
    public void NavStudentExitAdmin(MouseEvent mouseEvent) { ap_StudentAdmin.setStyle("-fx-background-color : #636363;"); }

    @FXML
    public void NavStudentClickAdmin(MouseEvent mouseEvent) throws IOException{
        AnchorPane page = FXMLLoader.load(getClass().getResource("StudentAdmin.fxml"));
        baseAdmin.getChildren().clear();
        baseAdmin.getChildren().setAll(page);
    }

    //NavLecturerAdmin

    @FXML
    public void NavLecturerEnterAdmin(MouseEvent mouseEvent)
    {
        ap_LecturerAdmin.setStyle("-fx-background-color : #6B6B6B; -fx-background-radius: 20;");
    }
    @FXML
    public void NavLecturerExitAdmin(MouseEvent mouseEvent) { ap_LecturerAdmin.setStyle("-fx-background-color : #636363;"); }

    @FXML
    public void NavLecturerClickAdmin(MouseEvent mouseEvent) throws IOException{
        AnchorPane page = FXMLLoader.load(getClass().getResource("LecturerAdmin.fxml"));
        baseAdmin.getChildren().clear();
        baseAdmin.getChildren().setAll(page);
    }

    //NavDegreeAdmin

    @FXML
    public void NavDegreeEnterAdmin(MouseEvent mouseEvent)
    {
        ap_DegreeAdmin.setStyle("-fx-background-color : #6B6B6B; -fx-background-radius: 20;");
    }
    @FXML
    public void NavDegreeExitAdmin(MouseEvent mouseEvent) { ap_DegreeAdmin.setStyle("-fx-background-color : #636363;"); }

    @FXML
    public void NavDegreeClickAdmin(MouseEvent mouseEvent) throws IOException{
        AnchorPane page = FXMLLoader.load(getClass().getResource("DegreeAdmin.fxml"));
        baseAdmin.getChildren().clear();
        baseAdmin.getChildren().setAll(page);
    }

    //NavModulesAdmin

    @FXML
    public void NavModulesEnterAdmin(MouseEvent mouseEvent)
    {
        ap_ModulesAdmin.setStyle("-fx-background-color : #6B6B6B; -fx-background-radius: 20;");
    }
    @FXML
    public void NavModulesExitAdmin(MouseEvent mouseEvent) { ap_ModulesAdmin.setStyle("-fx-background-color : #636363;"); }

    @FXML
    public void NavModulesClickAdmin(MouseEvent mouseEvent) throws IOException{
        AnchorPane page = FXMLLoader.load(getClass().getResource("ModulesAdmin.fxml"));
        baseAdmin.getChildren().clear();
        baseAdmin.getChildren().setAll(page);
    }

    //NavEventsAdmin

    @FXML
    public void NavEventsEnterAdmin(MouseEvent mouseEvent)
    {
        ap_EventsAdmin.setStyle("-fx-background-color : #6B6B6B; -fx-background-radius: 20;");
    }
    @FXML
    public void NavEventsExitAdmin(MouseEvent mouseEvent) { ap_EventsAdmin.setStyle("-fx-background-color : #636363;"); }

    @FXML
    public void NavEventsClickAdmin(MouseEvent mouseEvent) throws IOException{
        AnchorPane page = FXMLLoader.load(getClass().getResource("EventsAdmin.fxml"));
        baseAdmin.getChildren().clear();
        baseAdmin.getChildren().setAll(page);
    }

}
