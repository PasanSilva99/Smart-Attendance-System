package StudentApplication;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class info implements Initializable {
    @FXML
    Label lblcourse;
    @FXML
    Label lblmodule;
    @FXML
    Label lblbatch;
    @FXML
    Label lbllocation;
    @FXML
    Label lbldatetime;


    String batch;
    String course;
    String location;
    String dateTime;
    String module;

  public void getValue1(String course, String module, String batch, String location, String dateTime){
      lblcourse.setText(course);
      lblmodule.setText(module);
      lblbatch.setText(batch);
      lbllocation.setText(location);
      lbldatetime.setText(location);
  }
    public void getValue2(String course, String module, String batch, String location, String dateTime){
        lblcourse.setText(course);
        lblmodule.setText(module);
        lblbatch.setText(batch);
        lbllocation.setText(location);
        lbldatetime.setText(location);
    }
    public void getValue3(String course, String module, String batch, String location, String dateTime){
        lblcourse.setText(course);
        lblmodule.setText(module);
        lblbatch.setText(batch);
        lbllocation.setText(location);
        lbldatetime.setText(location);
    }
    public void getValue4(String course, String module, String batch, String location, String dateTime){
        lblcourse.setText(course);
        lblmodule.setText(module);
        lblbatch.setText(batch);
        lbllocation.setText(location);
        lbldatetime.setText(location);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }
}
