package AdminPanel;

import StudentApplication.BatchDAO;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddNewBatch implements Initializable {
    public Button btn_save;
    public Button btn_cancel;
    public TextField tb_batchNumber;
    public TextField tb_uni;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void btn_save_Click() throws SQLException {
        String newBatch = tb_batchNumber.getText() + " " + tb_uni;
        new BatchDAO().AddNewBatch(newBatch);
    }

    public void btn_cancel_Click(){
       Stage window = (Stage) btn_cancel.getScene().getWindow();
       window.close();
    }
}
