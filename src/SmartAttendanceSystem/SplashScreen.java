package SmartAttendanceSystem;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.Background;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class SplashScreen implements Initializable {

    @FXML
    ProgressBar pgb_status;
    @FXML
    Label lbl_status;
    @FXML
    ProgressIndicator pgi_ind;

    Double progress = 0.0;

    Stage rootStage;

    Double Shift = (double)0.3;

    public void updateProgress(String statusProgress,Double ShiftBy){
        Platform.runLater(() ->lbl_status.setText(statusProgress));
        this.progress+=ShiftBy;
        Platform.runLater(() ->pgb_status.setProgress(this.progress));

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            if(new ModulesDAO().checkModules()) {
                updateProgress("Server Connection Succeeded", Shift);
            }
            else {
                updateProgress("SERVER ERROR!: Connection Failed", 0.0);
                pgb_status.setStyle("-fx-accent: red; -fx-border-color: white;");
                pgi_ind.setStyle("-fx-accent: orange;");
            }

            if(new UserDAO().CheckUsers()) {
                updateProgress("User Connection Succeeded", Shift);
            }
            else {
                updateProgress("SERVER ERROR!: Connection to User Failed", 0.0);
                pgb_status.setStyle("-fx-accent: red; -fx-border-color: white;");
                pgi_ind.setStyle("-fx-accent: orange;");
            }
            if(new UserDAO().checkLoggedUser()) {
                updateProgress("Logged In", 0.4);
            }
            else {
                updateProgress("No User Logged in", 0.0);
                pgb_status.setStyle("-fx-accent: yellow; -fx-border-color: white;");
                pgi_ind.setStyle("-fx-accent: yellow;");
            }

            Timer TestTimer = new Timer();
            TimerTask testTask = new TimerTask() {
                @Override
                public void run() {
                    if(progress >= 1){
                        Platform.runLater(() ->startApplication());
                        TestTimer.cancel();
                    }

                }
            };


            TestTimer.schedule(testTask, 3000);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    
    public void startApplication() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("MainPage.fxml"));
            /*
             * if "fx:controller" is not set in fxml
             * fxmlLoader.setController(NewWindowController);
             */
            Scene scene = new Scene(fxmlLoader.load(), 1100, 600);
            Stage stage = new Stage();
            stage.setTitle("MainPage");
            stage.setScene(scene);
            stage.show();

            Stage thisStage = (Stage) lbl_status.getScene().getWindow();
            // do what you have to do
            thisStage.close();

        } catch (IOException e) {}

    }


}
