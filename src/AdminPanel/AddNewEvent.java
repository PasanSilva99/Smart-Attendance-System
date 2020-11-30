package AdminPanel;

import Common.BatchDAO;
import Common.LocationDAO;
import Common.ModulesDAO;
import Common.UniEventDAO;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.util.*;

public class AddNewEvent implements Initializable {
    public TextField tb_id;
    public TextField tb_EventName;
    public ComboBox cmb_eventType;
    public ComboBox cmb_module;
    public ComboBox cmb_batch;
    public ComboBox cmb_startTime;
    public ComboBox cmb_endTime;
    public Button btn_save;
    public TextField tb_lecturer;
    public Button btn_cancel;
    public DatePicker dtp_date;
    public ComboBox cmb_lectureHall;

    public MainSceneAdmin Admin;

    String SelectedModuleCode = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateID();
        dtp_date.setValue(LocalDate.now());
        // Populate combo boxes
        ObservableList<String> EventTypesList = FXCollections.observableArrayList(
                "Lecture",
                "Lab",
                "Other"
        );

        try {
            // Populate Event Type ComboBox
            cmb_eventType.setItems(EventTypesList);
            cmb_eventType.getSelectionModel().selectFirst();
        } catch (Exception e) {
            System.out.println("FIll Event Types error.");
        }

        try {
            // Get ad save all the module codes
            ObservableList<String> ModuleList = ModulesDAO.getModuleCodesOL();
            // Populate cmb_module
            cmb_module.setItems(ModuleList);
            cmb_module.getSelectionModel().selectFirst();
            SelectedModuleCode = cmb_module.getSelectionModel().getSelectedItem().toString();
            cmb_module_SelectionCchanged(SelectedModuleCode);
        } catch (Exception e) {
            System.out.println("Error in fill Modules" + e.getMessage());
        }

        try {
            // Get all Batches
            ObservableList<String> BatchList = FXCollections.observableArrayList(BatchDAO.getBatchList());

            cmb_batch.setItems(BatchList);
            cmb_batch.getSelectionModel().selectFirst();

        } catch (Exception e) {
            System.out.println("Error loading batches" + e.getMessage());
        }
        // Generate Time Selections
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
            cmb_startTime.getSelectionModel().select(18);
            cmb_endTime.setItems(TimeSelections);
            cmb_endTime.getSelectionModel().select(24);

            cmb_module.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
                        System.out.println(newValue);
                        Platform.runLater(() -> cmb_module_SelectionCchanged(newValue.toString()));
                    }
            );
        } catch (Exception e) {
            System.out.println("Error fetching Lecturer");
        }

        // Populate locations
        try{
            ObservableList<String> locations = FXCollections.observableArrayList(new LocationDAO().getLocationCodeList());
            cmb_lectureHall.setItems(locations);
            cmb_lectureHall.getSelectionModel().selectFirst();

        }catch (Exception e){
            System.out.println("Error loading location or No locations registered" + e.getMessage());
        }


    }

    /**
     * This event will handle the Module Selection changed Event
     *
     * @param selectedValue
     */
    public void cmb_module_SelectionCchanged(String selectedValue) {
        // Save the value
        SelectedModuleCode = selectedValue;
        // get lecturer name according to selected module
        String LecturerName = ModulesDAO.getLecturerName(SelectedModuleCode);
        tb_lecturer.setText(LecturerName);
    }

    /**
     * This will get a unique key for the event
     */
    public void updateID() {
        String newID = generateNewID();
        boolean isDuplicate = new UniEventDAO().CheckDuplicateID(newID);
        if (!isDuplicate) {
            tb_id.setText(newID);
        } else {
            updateID();
        }
    }

    /**
     * This returns a Random Alphanumeric String
     *
     * @return Random Alphanumeric String
     */
    public String generateNewID() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        System.out.println(generatedString);
        return generatedString;
    }

    public void setMainPage(MainSceneAdmin admin){
        Admin = admin;
    }

    public void btn_saveClick(ActionEvent actionEvent) {
        String event_id = tb_id.getText();
        String event_name = tb_EventName.getText();
        String module_code = cmb_module.getSelectionModel().getSelectedItem().toString();
        String start_time = dtp_date.getValue() + " " + cmb_startTime.getSelectionModel().getSelectedItem().toString();
        String end_time = dtp_date.getValue() + " " + cmb_endTime.getSelectionModel().getSelectedItem().toString();
        String lecturer = tb_lecturer.getText();
        String batch = cmb_batch.getSelectionModel().getSelectedItem().toString();
        String event_type = cmb_eventType.getSelectionModel().getSelectedItem().toString();
        String location = cmb_lectureHall.getSelectionModel().getSelectedItem().toString();


        new UniEventDAO().AddEvent(event_id, event_name, module_code, start_time, end_time, lecturer, batch, event_type, location);

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setContentText("Event "+event_id+ " Saved Successfully");
        alert.setTitle("✔ Success");
        alert.show();

        Admin.RefreshData();

        Stage stage = (Stage) tb_id.getScene().getWindow();
        stage.close();
    }

    public void btn_cancel_click(ActionEvent actionEvent) {
        Stage stage = (Stage) tb_id.getScene().getWindow();
        stage.close();
    }


    public void dateChanged(ActionEvent actionEvent) {
        System.out.println(dtp_date.getValue());
    }
}
