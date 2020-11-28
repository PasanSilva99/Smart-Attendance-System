package Common;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Scanner;
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

    Double Shift = (double) 0.3;
    User user;

    /**
     * Updates the progress bar, progress indicator and sets the text.
     * @param statusProgress The Text to display
     * @param ShiftBy Double value to be increased
     */
    public void updateProgress(String statusProgress, Double ShiftBy) {
        Platform.runLater(() -> lbl_status.setText(statusProgress));
        this.progress += ShiftBy;
        Platform.runLater(() -> pgb_status.setProgress(this.progress));

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        UserDAO userDAO = new UserDAO();
        DeviceDAO deviceDAO = new DeviceDAO();
        try {
            // Check for the modules
            if (new ModulesDAO().checkModules()) {
                updateProgress("Server Connection Succeeded", Shift);
            } else {
                updateProgress("SERVER ERROR!: Connection Failed", 0.0);
                pgb_status.setStyle("-fx-accent: red; -fx-border-color: white;");
                pgi_ind.setStyle("-fx-accent: orange;");
            }

            // Check for available users
            if (userDAO.CheckUsers()) {
                updateProgress("User Connection Succeeded", Shift);
            } else {
                updateProgress("SERVER ERROR!: Connection to User Failed", 0.0);
                pgb_status.setStyle("-fx-accent: red; -fx-border-color: white;");
                pgi_ind.setStyle("-fx-accent: orange;");
            }

            // Check for the user that previously logged in
            if (userDAO.checkLoggedUser()) {

                // Gets User Email from the File If Exists
                String userEmail = null;
                File file = new File("User.bin");
                if (file.exists()) {
                    Scanner scan = new Scanner(file);
                    userEmail = scan.nextLine().replaceAll("\\s", "");
                }

                // Retrieves the User Profile from the Database
                user = userDAO.getUser(userEmail);


                // Checks if the user Valid
                if (user != null) {
                    // User is Valid
                    // Checks if the Device is Valid
                    UserLogin userLogin = new UserLogin();
                    String macAddress = userLogin.getDeviceMacAddress();
                    System.out.println("Auto Login Fetching Mac Address :" + macAddress);
                    // CHeck the mac address has saved with the users email
                    if (deviceDAO.checkOwnership(userEmail, macAddress)) {
                        // Ownership is Valid
                        System.out.println("Auto Login Successfull");
                        updateProgress("Logged in as " + userEmail, 0.4);
                        System.out.println("Progress: " + progress);



                    } else {
                        // Ownership is Invalid
                        // Re login to record
                        updateProgress("Auto Login Failed. Re-Login", 0.0);
                        pgb_status.setStyle("-fx-accent: yellow; -fx-border-color: white;");
                        pgi_ind.setStyle("-fx-accent: yellow;");

                        Timer LoginTimer = new Timer();
                        TimerTask LoginTask = new TimerTask() {
                            @Override
                            public void run() {
                                if (progress <= 1) {
                                    Platform.runLater(() -> startLoginProcess());
                                    Platform.runLater(() -> closeApp(pgb_status));
                                    LoginTimer.cancel();
                                }

                            }
                        };

                        System.out.println("Starting Login Progress");
                        LoginTimer.schedule(LoginTask, 3000);
                    }

                }
                else {
                    // User is Not Valid
                    // Login Again to Record the Device with the User
                    // Re login to record
                    updateProgress("Auto Login Failed. Re-Login", 0.0);
                    pgb_status.setStyle("-fx-accent: yellow; -fx-border-color: white;");
                    pgi_ind.setStyle("-fx-accent: yellow;");

                    Timer LoginTimer = new Timer();
                    TimerTask LoginTask = new TimerTask() {
                        @Override
                        public void run() {
                            if (progress <= 1) {
                                Platform.runLater(() -> startLoginProcess());
                                Platform.runLater(() -> closeApp(pgb_status));
                                LoginTimer.cancel();
                            }

                        }
                    };

                    System.out.println("Starting Login Progress");
                    LoginTimer.schedule(LoginTask, 3000);

                }
            }
            else {
                updateProgress("No User Logged in", 0.0);
                pgb_status.setStyle("-fx-accent: yellow; -fx-border-color: white;");
                pgi_ind.setStyle("-fx-accent: yellow;");

                Timer LoginTimer = new Timer();
                TimerTask LoginTask = new TimerTask() {
                    @Override
                    public void run() {
                        if (progress <= 1) {
                            Platform.runLater(() -> startLoginProcess());
                            Platform.runLater(() -> closeApp(pgb_status));
                            LoginTimer.cancel();
                        }

                    }
                };

                System.out.println("Starting Login Progress");
                LoginTimer.schedule(LoginTask, 3000);
            }


            Timer AppTimer = new Timer();
            TimerTask AppTask = new TimerTask() {
                @Override
                public void run() {
                    if (progress >= 1) {
                        String privilegeLevel = user.getPriviladgeLevel();
                        switch(privilegeLevel){
                            case "admin":
                                Platform.runLater(() ->  startAdminApplication(user));
                                break;
                            case "lecturer" :
                                Platform.runLater(() ->  startLecturerApplication(user));
                                break;
                            case "student":
                                Platform.runLater(() ->  startStudentApplication(user));
                        }
                        AppTimer.cancel();
                    }

                }
            };

            AppTimer.schedule(AppTask, 3000);


        } catch (SQLException | FileNotFoundException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void startAdminApplication(User user) {
        System.out.println("Starting Student Application");
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("../AdminPanel/MainPageAdmin.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1100, 600);
            Stage stage = new Stage();
            MainPage controler = fxmlLoader.getController();
            controler.setUser(user);
            stage.setTitle("MainPage");
            stage.setScene(scene);
            stage.show();

            Stage thisStage = (Stage) lbl_status.getScene().getWindow();
            // do what you have to do
            thisStage.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void startLecturerApplication(User user) {
        System.out.println("Starting Student Application");
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("MainPage.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1100, 600);
            Stage stage = new Stage();
            MainPage controler = fxmlLoader.getController();
            controler.setUser(user);
            stage.setTitle("MainPage");
            stage.setScene(scene);
            stage.show();

            Stage thisStage = (Stage) lbl_status.getScene().getWindow();
            // do what you have to do
            thisStage.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void closeApp(Node node) {
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
    }

    public void startLoginProcess() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("UserLogin.fxml"));
            /*
             * if "fx:controller" is not set in fxml
             * fxmlLoader.setController(NewWindowController);
             */
            Scene scene = new Scene(fxmlLoader.load(), 366, 301);
            Stage stage = new Stage();
            stage.setTitle("Login");
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
            stage.show();

            Stage thisStage = (Stage) lbl_status.getScene().getWindow();
            // do what you have to do
            thisStage.close();

        } catch (IOException e) {
            System.out.println("Starting new Login");
        }
    }

    public void startStudentApplication(User user) {
        System.out.println("Starting Student Application");
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("MainPage.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1100, 600);
            Stage stage = new Stage();
            MainPage controler = fxmlLoader.getController();
            controler.setUser(user);
            stage.setTitle("MainPage");
            stage.setScene(scene);
            stage.show();

            Stage thisStage = (Stage) lbl_status.getScene().getWindow();
            // do what you have to do
            thisStage.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
