package StudentApplication;

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
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import javafx.scene.input.MouseEvent;
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
            cmb_menu.setText(user.getPrefix() + " " + user.getName());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        if(user != null){
            cmb_menu.setText(user.getPrefix() + user.getName());
        }

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


        Platform.runLater(()-> {
            try {
                NavHomeClick();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

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
    public void NavHomeClick() throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("MainScene.fxml"));
        AnchorPane page = loader.load();
        MainScene controler = loader.getController();
        controler.setUser(user);
        base.getChildren().clear();
        base.getChildren().setAll(page);
    }


}
