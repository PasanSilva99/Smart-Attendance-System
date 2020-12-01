package Common;

import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

import java.io.File;
import java.util.List;

public class UniEvent {
    private String EventID;
    private String EventName;
    private String ModuleO;
    private String StartTime;
    private String EndTime;
    private String Lecturer;
    private String Batch;
    private String EventType;
    private String Location;
    private List<Quiz> ConnectedQuizzes;

    public UniEvent(String event_id, String event_name, String module_code, String start_time, String end_time, String lecturer, String batch, String event_type, String location) {
        EventID = event_id;
        EventName =event_name;
        ModuleO = module_code;
        StartTime = start_time;
        EndTime = end_time;
        Lecturer = lecturer;
        Batch = batch;
        EventType = event_type;
        Location = location;
    }

    public String getModuleO() {
        return ModuleO;
    }

    public void setModuleO(String moduleO) {
        ModuleO = moduleO;
    }

    public String getStartTime() {
        return StartTime;
    }

    public void setStartTime(String startTime) {
        StartTime = startTime;
    }

    public String getEndTime() {
        return EndTime;
    }

    public void setEndTime(String endTime) {
        EndTime = endTime;
    }

    public String getLecturer() {
        return Lecturer;
    }

    public void setLecturer(String lecturer) {
        Lecturer = lecturer;
    }

    public String getBatch() {
        return Batch;
    }

    public void setBatch(String batch) {
        this.Batch = batch;
    }

    public String getEventID() {
        return EventID;
    }

    public void setEventID(String eventID) {
        EventID = eventID;
    }

    public String getEventName() {
        return EventName;
    }

    public void setEventName(String eventName) {
        EventName = eventName;
    }

    public String getEventType() {
        return EventType;
    }

    public void setEventType(String eventType) {
        EventType = eventType;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public void setConnectedQuizzes(List<Quiz> connectedQuizzes) {
        ConnectedQuizzes = connectedQuizzes;
    }

    public List<Quiz> getConnectedQuizzes() {
        return ConnectedQuizzes;
    }

    public void AddConnectedQuiz(Quiz quiz) {
        ConnectedQuizzes.add(quiz);
    }

    public void removeConnectedQuiz(Quiz quiz) {
        ConnectedQuizzes.add(quiz);
    }

    public AnchorPane generateEventView(){
        AnchorPane base = new AnchorPane();
        base.setPrefSize(665, 60);
        base.setStyle("" +
                "-fx-background-color: #e3e3e3; " +
                "-fx-border-color: #a4a4a4; " +
                "-fx-border-radius: 5; " +
                "-fx-background-radius: 5");

        File file = new File("Images/event.png");
        Image img = new Image(file.toURI().toString());
        ImageView img_student = new ImageView(img);
        img_student.setFitHeight(40);
        img_student.setFitWidth(40);
        AnchorPane.setLeftAnchor(img_student, 7.0);
        AnchorPane.setTopAnchor(img_student, 10.0);

        Label lbl_details = new Label( EventID+ ": " + EventName + "\n" +ModuleO+" " + Batch+"\n"+Location);
        lbl_details.setFont(new Font("Century", 14.0));
        AnchorPane.setLeftAnchor(lbl_details,65.0);
        AnchorPane.setTopAnchor(lbl_details, 10.0);

        base.getChildren().addAll(img_student, lbl_details);

        return base;
    }

    public AnchorPane getEventView(){
        AnchorPane view = generateEventView();

        return view;
    }

}
