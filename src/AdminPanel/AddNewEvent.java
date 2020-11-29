package AdminPanel;

import Common.BatchDAO;
import Common.ModulesDAO;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

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

    String SelectedModuleCode = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        // Populate combo boxes
        ObservableList<String> EventTypesList= FXCollections.observableArrayList(
                "Lecture",
                "Lab",
                "Other"
        );

        // Populate Event Type ComboBox
        cmb_eventType.setItems(EventTypesList);
        cmb_eventType.getSelectionModel().selectFirst();

        // Get ad save all the module codes
        ObservableList<String> ModuleList = ModulesDAO.getModuleCodesOL();
        // Populate cmb_module
        cmb_module.setItems(ModuleList);
        cmb_module.getSelectionModel().selectFirst();
        SelectedModuleCode = cmb_module.getSelectionModel().getSelectedItem().toString();
        cmb_module_SelectionCchanged(SelectedModuleCode);
        // Get all Batches
        ObservableList<String> BatchList = FXCollections.observableArrayList(BatchDAO.getBatchList());

        cmb_batch.setItems(BatchList);
        cmb_batch.getSelectionModel().selectFirst();

        // Generate Time Selections
        List<String>TimeSelectionsList = new ArrayList<>();
        for(int h=0; h<24; h++){
            for(int m=0; m<60; m+=30){
                String Hours = Integer.toString(h),
                        Minutes = Integer.toString(m);
                if(h<10) Hours = "0"+Hours;
                if(m<10) Minutes = "0"+Minutes;

                TimeSelectionsList.add(Hours+":"+Minutes);

            }
        }

        // List Fir the Time Selections for ComboBox
        ObservableList<String> TimeSelections = FXCollections.observableArrayList(TimeSelectionsList);
        // Populate Start Time Combo Box
        cmb_startTime.setItems(TimeSelections);
        cmb_startTime.getSelectionModel().select(18);
        cmb_endTime.setItems(TimeSelections);
        cmb_endTime.getSelectionModel().select(24);

        cmb_module.getSelectionModel().selectedItemProperty().addListener( (options, oldValue, newValue) -> {
                    System.out.println(newValue);
            Platform.runLater(()-> cmb_module_SelectionCchanged(newValue.toString()));
                }
        );




    }

    public void cmb_module_SelectionCchanged(String selectedValue){
        SelectedModuleCode = selectedValue;
        // get lecturer name according to selected module
        String LecturerName = ModulesDAO.getLecturerName(SelectedModuleCode);
        tb_lecturer.setText(LecturerName);
    }


    public void btn_saveClick(ActionEvent actionEvent) {
    }

    public void btn_cancel_click(ActionEvent actionEvent) {
    }


}
