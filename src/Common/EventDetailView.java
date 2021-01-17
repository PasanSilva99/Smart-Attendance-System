package Common;

import AdminPanel.MainSceneAdmin;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.UncheckedIOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class EventDetailView implements Initializable {
    public Label lbl_eventName;
    public Button btn_delete;
    public Button btn_delete1;
    public ComboBox cmb_module;
    public ComboBox cmb_batch;
    public TextField tb_eventName;
    public TextField tb_eventCode;
    public DatePicker dtp_date;
    public ComboBox cmb_startTime;
    public ComboBox cmb_endTime;
    public TextField tb_lecturer;
    public ComboBox cmb_eventType;
    public ComboBox cmb_lectureHall;
    public Label lbl_eventName1;
    public VBox vbox_quizView;
    public Button btn_save;

    public UniEvent uniEvent;
    public Button btn_goBack;

    public AnchorPane baseAdmin;

    /**
     * Save the Event
     * @param actionEvent
     */
    public void btn_save_Click(ActionEvent actionEvent) {
        String event_id = tb_eventCode.getText();
        String event_name = tb_eventName.getText();
        String module_code = cmb_module.getSelectionModel().getSelectedItem().toString();
        String start_time = dtp_date.getValue() + " " + cmb_startTime.getSelectionModel().getSelectedItem().toString();
        String end_time = dtp_date.getValue() + " " + cmb_endTime.getSelectionModel().getSelectedItem().toString();
        String lecturer = tb_lecturer.getText();
        String batch = cmb_batch.getSelectionModel().getSelectedItem().toString();
        String event_type = cmb_eventType.getSelectionModel().getSelectedItem().toString();
        String location = cmb_lectureHall.getSelectionModel().getSelectedItem().toString();

        new UniEventDAO().updateEvent(event_id, event_name, module_code, start_time, end_time, lecturer, batch, event_type, location);
        uniEvent = new UniEvent(event_id, event_name, module_code, start_time, end_time, lecturer, batch, event_type, location);

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Event "+event_id+ " Saved Successfully");
        alert.setTitle("✔ Success");
        alert.show();
      
        // Lock Controls
        lbl_eventName.setText(event_name);
        btn_save.setVisible(false);
        tb_eventName.setEditable(false);
        cmb_module.getItems().clear();
        cmb_module.getItems().add(uniEvent.getModuleO());
    }

    public void btn_edit_Click(ActionEvent actionEvent) {
        // First need to enable editing in all text boxes exept the Event Code

        //Enable Event Name
        tb_eventName.setEditable(true);

        // Enable Lecturer
        tb_lecturer.setEditable(true);

        // Enable Date
        dtp_date.setEditable(true);

        // Load the Modules to combo box
        try {
            // Get ad save all the module codes
            ObservableList<String> ModuleList = ModulesDAO.getModuleCodesOL();
            // Populate cmb_module
            cmb_module.setItems(ModuleList);
            cmb_module.getSelectionModel().select(uniEvent.getModuleO());
        } catch (Exception e) {
            System.out.println("Error in fill Modules" + e.getMessage());
        }

        // Load the Batches to combo box
        try {
            // Get all Batches
            ObservableList<String> BatchList = FXCollections.observableArrayList(BatchDAO.getBatchList());

            cmb_batch.setItems(BatchList);
            cmb_batch.getSelectionModel().select(uniEvent.getBatch());

        } catch (Exception e) {
            System.out.println("Error loading batches" + e.getMessage());
        }

        // Load And Generate TimeSeries
        List<String> TimeSelectionsList = new ArrayList<>();
        for (int h = 0; h < 24; h++) {
            for (int m = 0; m < 60; m += 30) {
                String Hours = Integer.toString(h),
                        Minutes = Integer.toString(m);
                if (h < 10) Hours = "0" + Hours;
                if (m < 10) Minutes = "0" + Minutes;

                TimeSelectionsList.add(Hours + ":" + Minutes);

            }
        }

        try {
            // List Fir the Time Selections for ComboBox
            ObservableList<String> TimeSelections = FXCollections.observableArrayList(TimeSelectionsList);
            // Populate Start Time Combo Box
            cmb_startTime.setItems(TimeSelections);
            cmb_startTime.getSelectionModel().select(uniEvent.getStartTime().substring(11, 16));
            cmb_endTime.setItems(TimeSelections);
            cmb_endTime.getSelectionModel().select(uniEvent.getStartTime().substring(11, 16));

        } catch (Exception e) {
            System.out.println("Error fetching TimeSeries");
        }

        // Populate combo boxes
        ObservableList<String> EventTypesList = FXCollections.observableArrayList(
                "Lecture",
                "Lab",
                "Other"
        );

        try {
            // Populate Event Type ComboBox
            cmb_eventType.setItems(EventTypesList);
            cmb_eventType.getSelectionModel().select(uniEvent.getEventType());
        } catch (Exception e) {
            System.out.println("FIll Event Types error.");
        }

        // Populate locations
        try{
            ObservableList<String> locations = FXCollections.observableArrayList(new LocationDAO().getLocationCodeList());
            cmb_lectureHall.setItems(locations);
            cmb_lectureHall.getSelectionModel().select(uniEvent.getLocation());

        }catch (Exception e){
            System.out.println("Error loading location or No locations registered" + e.getMessage());
        }

        // Show the save button
        btn_save.setVisible(true);

    }

    public void btn_delete_Click(ActionEvent actionEvent) {
        if(new UniEventDAO().removeEvent(tb_eventCode.getText()))
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Successfully Deleted Event");
            alert.setTitle("Success");
            alert.setHeaderText("Delete Success");
            alert.show();

            btn_goBack_Click();
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Error Deleting Event");
            alert.setTitle("Error");
            alert.setHeaderText("Delete Failed");
            alert.show();
        }
    }

    public void setEvent(UniEvent event){
        uniEvent = event;

        try {
            cmb_module.setValue(event.getModuleO());
        } catch (Exception e) {
            System.out.println("Error in fill Modules" + e.getMessage());
        }

        try {
            cmb_batch.setValue(event.getBatch());
        } catch (Exception e) {
            System.out.println("Error in fill Batch" + e.getMessage());
        }

        try {
            cmb_module.setValue(event.getModuleO());
        } catch (Exception e) {
            System.out.println("Error in fill Modules" + e.getMessage());
        }

        try {
            cmb_startTime.setValue(event.getStartTime().substring(11, 16));
        } catch (Exception e) {
            System.out.println("Error in fill Start Time" + e.getMessage());
        }

        try {
            cmb_endTime.setValue(event.getEndTime().substring(11, 16));
        } catch (Exception e) {
            System.out.println("Error in fill End Time" + e.getMessage());
        }

        try {
            dtp_date.setValue(LOCAL_DATE(event.getStartTime().substring(0, 10)));
        } catch (Exception e) {
            System.out.println("Error in fill Date" + e.getMessage());
        }

        try {
            tb_lecturer.setText(event.getLecturer());
        } catch (Exception e) {
            System.out.println("Error in fill Lecturer" + e.getMessage());
        }

        try {
            cmb_eventType.setValue(event.getEventType());
        } catch (Exception e) {
            System.out.println("Error in fill Type" + e.getMessage());
        }

        try {
            cmb_lectureHall.setValue(event.getLocation());
        } catch (Exception e) {
            System.out.println("Error in fill Lecture Hall" + e.getMessage());
        }

        lbl_eventName.setText(event.getEventName());
        tb_eventName.setText(event.getEventName());
        tb_eventCode.setText(event.getEventID());
        cmb_module.getSelectionModel().select(event.getModuleO());

        // Get the quizzes from the database
        System.out.println("Initializing quizzes");
        List<Quiz> AvailableQuizzes = new QuizDAO().getQuizListForModule(event.getEventID());

        for (Quiz quiz: AvailableQuizzes) {
            AnchorPane q = quiz.getQuizView();
            System.out.println("Loading Quiz: " + q.getId());
            vbox_quizView.getChildren().add(q);
        }

        btn_save.setVisible(false);

    }

    public void setBaseAdmin(AnchorPane baseAdmin){
        this.baseAdmin = baseAdmin;
    }

    public static final LocalDate LOCAL_DATE (String dateString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        return localDate;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void btn_goBack_Click() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../AdminPanel/MainSceneAdmin.fxml"));
            AnchorPane page = loader.load();
            MainSceneAdmin controller = loader.getController();
            controller.setMainPage(baseAdmin);
            baseAdmin.getChildren().clear();
            baseAdmin.getChildren().setAll(page);

        }catch (Exception e){
            System.out.println("Couldn't Load the Page");
        }
    }
}
