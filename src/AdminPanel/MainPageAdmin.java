package AdminPanel;

import Common.User;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.FileWriter;
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
            cmb_MenuAdmin.setText(userAdmin.getPrefix() + " " + userAdmin.getName());
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // set a clip to apply rounded border to the original image.
        Rectangle clipAdmin = new Rectangle(
                img_Admin.getFitWidth(), img_Admin.getFitHeight()
        );

        if(userAdmin != null){
            cmb_MenuAdmin.setText(userAdmin.getPrefix() + userAdmin.getName());
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

        /** Below code section will add items for the combolist and assign them to Logout and Exit
          */

        List<MenuItem> optionsAdmin = new ArrayList<>();
        optionsAdmin.add(new MenuItem("Logout"));
        optionsAdmin.add(new MenuItem("Exit"));


        //UniEvent Handler for Menu Item Logout
        EventHandler<ActionEvent> logoutClicked = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    logoutApplication(cmb_MenuAdmin);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

        // UniEvent Handler for Menu Item Exit
        EventHandler<ActionEvent> exitClicked = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                exitApplication();
            }
        };

        // Assigning the event handlers to the menu items
        optionsAdmin.get(0).setOnAction(logoutClicked);
        optionsAdmin.get(1).setOnAction(exitClicked);

        // Populating the menu button with the menu items
        cmb_MenuAdmin.getItems().addAll(optionsAdmin);

    }

    /**
     * This will remove the login details from the file and restart the app
     * @param node Any Control In the Stage
     * @throws IOException
     */
    public void logoutApplication(Node node) throws IOException {
        try {
            File file = new File("User.bin");
            FileWriter writer = new FileWriter(file);
            writer.write("");
            writer.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        // ReStart
        FXMLLoader loder = new FXMLLoader();
        loder.setLocation(getClass().getResource("../Common/SplashScreen.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("../Common/SplashScreen.fxml"));
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Admin");
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(new Scene(root, 600, 300));
        primaryStage.show();

        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setX((primScreenBounds.getWidth() - primaryStage.getWidth()) / 2);
        primaryStage.setY((primScreenBounds.getHeight() - primaryStage.getHeight()) / 2);

        // Close this Window
        Stage thisStage = (Stage) node.getScene().getWindow();
        thisStage.close();
    }

    /**
     * Closes and Exists the Application.
     */
    public void exitApplication(){
        Platform.exit();
        System.exit(0);
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
        AnchorPane page = FXMLLoader.load(getClass().getResource("AdminStudentPage.fxml"));
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
        AnchorPane page = FXMLLoader.load(getClass().getResource("AdminLecturerPage.fxml"));
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
        AnchorPane page = FXMLLoader.load(getClass().getResource("../AdminPanel/AdminDegreePage.fxml"));
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
        AnchorPane page = FXMLLoader.load(getClass().getResource("AdminModulesPage.fxml"));
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
        AnchorPane page = FXMLLoader.load(getClass().getResource("AdminEventsPage.fxml"));
        baseAdmin.getChildren().clear();
        baseAdmin.getChildren().setAll(page);
    }

}
