package LecturerApplication;

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

public class MainPageLecturer implements Initializable {

    @FXML
    public MenuButton cmb_MenuLecturer;
    @FXML
    public ImageView btn_BellLecturer;
    @FXML
    AnchorPane topBarLecturer;
    @FXML
    public AnchorPane baseLecturer;
    @FXML
    ImageView img_Lecturer;
    @FXML
    public AnchorPane ap_HomeLecturer;
    @FXML
    public AnchorPane ap_LeaderboardLecturer;
    @FXML
    public AnchorPane ap_ModulesLecturer;
    @FXML
    public AnchorPane ap_QuizLecturer;
    @FXML
    public ImageView img_HomeLecturer;
    @FXML
    public ImageView img_LeaderboardLecturer;
    @FXML
    public ImageView img_ModulesLecturer;
    @FXML
    public ImageView img_QuizLecturer;
    @FXML
    javafx.scene.control.Label lbl_HomeLecturer;
    @FXML
    javafx.scene.control.Label lbl_LeaderboardLecturer;
    @FXML
    javafx.scene.control.Label lbl_ModulesLecturer;
    @FXML
    javafx.scene.control.Label lbl_QuizLecturer;

    String UserNameLecturer;

    public User userLecturer;

    //Initializing combobox label name
    public void setUserLecturer(User userLecturer){
        this.userLecturer = userLecturer;
        if(userLecturer.getNsbm_id() != null){
            cmb_MenuLecturer.setText(userLecturer.getPrefix() + " " + userLecturer.getName());
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // set a clip to apply rounded border to the original image.
        Rectangle clipLecturer = new Rectangle(
                img_Lecturer.getFitWidth(), img_Lecturer.getFitHeight()
        );

        if(userLecturer != null){
            cmb_MenuLecturer.setText(userLecturer.getPrefix() + userLecturer.getName());
        }

        clipLecturer.setArcWidth(img_Lecturer.getFitHeight());
        clipLecturer.setArcHeight(img_Lecturer.getFitHeight());
        img_Lecturer.setClip(clipLecturer);

        // snapshot the rounded image.
        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);
        WritableImage image = img_Lecturer.snapshot(parameters, null);

        // remove the rounding clip so that our effect can show through.
        img_Lecturer.setClip(null);

        // apply a shadow effect.
        img_Lecturer.setEffect(new DropShadow(2, Color.BLACK));

        // store the rounded image in the imageView.
        img_Lecturer.setImage(image);


        /** Below code section will add items for the combolist and assign them to Logout and Exit
         */

        List<MenuItem> optionsLecturer = new ArrayList<>();
        optionsLecturer.add(new MenuItem("Logout"));
        optionsLecturer.add(new MenuItem("Exit"));


        //UniEvent Handler for Menu Item Logout
        EventHandler<ActionEvent> logoutClicked = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    logoutApplication(cmb_MenuLecturer);
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
        optionsLecturer.get(0).setOnAction(logoutClicked);
        optionsLecturer.get(1).setOnAction(exitClicked);

        // Populating the menu button with the menu items
        cmb_MenuLecturer.getItems().addAll(optionsLecturer);

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
        primaryStage.setTitle("Lecturer");
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
    //NavHomeLecturer

    @FXML
    public void NavHomeEnterLecturer(MouseEvent mouseEvent)
    {
        ap_HomeLecturer.setStyle("-fx-background-color : #6B6B6B; -fx-background-radius: 20;");
    }
    @FXML
    public void NavHomeExitLecturer(MouseEvent mouseEvent)
    {
        ap_HomeLecturer.setStyle("-fx-background-color : #636363; ");
    }

    @FXML
    public void NavHomeClickLecturer(MouseEvent mouseEvent) throws IOException
    {
        AnchorPane page = FXMLLoader.load(getClass().getResource("MainSceneLecturer.fxml"));
        baseLecturer.getChildren().clear();
        baseLecturer.getChildren().setAll(page);
    }

    //NavLeaderboardLecturer

    @FXML
    public void NavLeaderboadEnterLecturer(MouseEvent mouseEvent)
    {
        ap_LeaderboardLecturer.setStyle("-fx-background-color : #6B6B6B; -fx-background-radius: 20;");
    }
    @FXML
    public void NavLeaderboardExitLecturer(MouseEvent mouseEvent) { ap_LeaderboardLecturer.setStyle("-fx-background-color : #636363;"); }

    @FXML
    public void NavLeaderboardClickLecturer(MouseEvent mouseEvent) throws IOException{
        AnchorPane page = FXMLLoader.load(getClass().getResource("LecturerLeaderBoardPage.fxml"));
        baseLecturer.getChildren().clear();
        baseLecturer.getChildren().setAll(page);
    }

    //NavModulesLecturer

    @FXML
    public void NavModulesEnterLecturer(MouseEvent mouseEvent)
    {
        ap_ModulesLecturer.setStyle("-fx-background-color : #6B6B6B; -fx-background-radius: 20;");
    }
    @FXML
    public void NavModulesExitLecturer(MouseEvent mouseEvent) { ap_ModulesLecturer.setStyle("-fx-background-color : #636363;"); }

    @FXML
    public void NavModulesClickLecturer(MouseEvent mouseEvent) throws IOException{
        AnchorPane page = FXMLLoader.load(getClass().getResource("LecturerModulesPage.fxml"));
        baseLecturer.getChildren().clear();
        baseLecturer.getChildren().setAll(page);
    }

    //NavQuizLecturer

    @FXML
    public void NavQuizEnterLecturer(MouseEvent mouseEvent)
    {
        ap_QuizLecturer.setStyle("-fx-background-color : #6B6B6B; -fx-background-radius: 20;");
    }
    @FXML
    public void NavQuizExitLecturer(MouseEvent mouseEvent) { ap_QuizLecturer.setStyle("-fx-background-color : #636363;"); }

    @FXML
    public void NavQuizClickLecturer(MouseEvent mouseEvent) throws IOException{
        AnchorPane page = FXMLLoader.load(getClass().getResource("LecturerQuizPage.fxml"));
        baseLecturer.getChildren().clear();
        baseLecturer.getChildren().setAll(page);
    }


}
