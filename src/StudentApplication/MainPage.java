package StudentApplication;

import Common.User;
import StudentApplication.ModulesPage;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MainPage implements Initializable {

    @FXML
    public MenuButton cmb_menu;
    @FXML
    public ImageView btn_bell;
    @FXML
    AnchorPane topBar;
    @FXML
    public AnchorPane base;
    @FXML
    ImageView img_Pic;
    @FXML
    public AnchorPane ap_home;
    @FXML
    public AnchorPane ap_leaderBoard;
    @FXML
    public AnchorPane ap_modules;
    @FXML
    public AnchorPane ap_analytics;
    @FXML
    public ImageView img_home;
    @FXML
    ImageView img_modules;
    @FXML
    ImageView img_analytics;
    @FXML
    javafx.scene.control.Label lbl_home;
    @FXML
    javafx.scene.control.Label lbl_leaderBoard;
    @FXML
    javafx.scene.control.Label lbl_modules;
    @FXML
    javafx.scene.control.Label lbl_analytics;

    String UserName;

    public User user;

    public void setUser(User user){
        this.user = user;
        if(user.getNsbm_id() != null){
            cmb_menu.setText(user.getFirst_name() + " " + user.getLast_name());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // set a clip to apply rounded border to the original image.
        Rectangle clip = new Rectangle(
                img_Pic.getFitWidth(), img_Pic.getFitHeight()
        );

        if(user != null){
            cmb_menu.setText(user.getFirst_name() + user.getLast_name());
        }

        clip.setArcWidth(img_Pic.getFitHeight());
        clip.setArcHeight(img_Pic.getFitHeight());
        img_Pic.setClip(clip);

        // snapshot the rounded image.
        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);
        WritableImage image = img_Pic.snapshot(parameters, null);

        // remove the rounding clip so that our effect can show through.
        img_Pic.setClip(null);

        // apply a shadow effect.
        img_Pic.setEffect(new DropShadow(2, Color.BLACK));

        // store the rounded image in the imageView.
        img_Pic.setImage(image);

        List<MenuItem> options = new ArrayList<>();
        options.add(new MenuItem("Logout"));
        options.add(new MenuItem("Exit"));

        //UniEvent Handler for Menu Item Logout
        EventHandler<ActionEvent> logoutClicked = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    logoutApplication(cmb_menu);
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
        options.get(0).setOnAction(logoutClicked);
        options.get(1).setOnAction(exitClicked);

        // Populating the menu button with the menu items
        cmb_menu.getItems().addAll(options);

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
        primaryStage.setTitle("Student");
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

    @FXML
    public void NavHomeEnter(MouseEvent mouseEvent)
    {
        ap_home.setStyle("-fx-background-color : #6B6B6B; -fx-background-radius: 20;");
    }
    @FXML
    public void NavHomeExit(MouseEvent mouseEvent)
    {
        ap_home.setStyle("-fx-background-color : #636363; ");
    }

    @FXML
    public void NavHomeClick(MouseEvent mouseEvent) throws IOException
    {
        AnchorPane page = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
        base.getChildren().clear();
        base.getChildren().setAll(page);
    }


    public void NavLeaderBoardEnter(MouseEvent mouseEvent)
    {
        ap_leaderBoard.setStyle("-fx-background-color : #6B6B6B; -fx-background-radius: 20;");
    }


    public void NavLeaderBoardExit(MouseEvent mouseEvent) { ap_leaderBoard.setStyle("-fx-background-color : #636363;"); }
    @FXML
    public void NavLeaderBoardClick(MouseEvent mouseEvent) throws IOException{
        AnchorPane page = FXMLLoader.load(getClass().getResource("LeaderBoardMain.fxml"));
        base.getChildren().clear();
        base.getChildren().setAll(page);
    }

    public void NavModulesEnter(MouseEvent mouseEvent)
    {
        ap_modules.setStyle("-fx-background-color : #6B6B6B; -fx-background-radius: 20;");
    }

    public void NavModulesExit(MouseEvent mouseEvent)
    {
        ap_modules.setStyle("-fx-background-color : #636363;");
    }
    @FXML
    public void NavModulesClick(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loder = new FXMLLoader();
        loder.setLocation(getClass().getResource("../StudentApplication/ModulesPage.fxml"));
        AnchorPane page = loder.load();
        base.getChildren().clear();
        base.getChildren().setAll(page);
        ModulesPage Mpage = loder.getController();
        Mpage.setMainPage(this);
    }

    public void NavAnalyticsEnter(MouseEvent mouseEvent)
    {
        ap_analytics.setStyle("-fx-background-color : #6B6B6B; -fx-background-radius: 20;");
    }

    public void NavAnalyticsExit(MouseEvent mouseEvent)
    {
        ap_analytics.setStyle("-fx-background-color : #636363;");
    }

    public void NavAnalyticsClick(MouseEvent mouseEvent)
    {

    }
}
