package AdminPanel;

import AdminPanel.AdminLecturerPage;
import Common.DegreeDAO;
import Common.User;
import Common.UserDAO;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.SQLException;

public class RemoveDegree {
        public TextField tb_degreeCode;
        public Button btn_confirm;
        public Button btn_cancel;
        public Button btn_fetch;
        public Text lbl_degreeCode;
        public Text lbl_degreeName;

        public AdminDegreePage mainPage;

        public void btn_confirm_Click(ActionEvent actionEvent) throws SQLException {
            new DegreeDAO().RemoveDegree(tb_degreeCode.getText());
            tb_degreeCode.setText(null);
            lbl_degreeName.setText("Type a Degree Code to Show");
            System.out.println("Degree Removed");
            mainPage.RefreshDegreeList();

        }

        public void btn_cancel_Click(ActionEvent actionEvent) {
            mainPage.RefreshDegreeList();
            Stage stage = (Stage) btn_cancel.getScene().getWindow();
            stage.close();
        }

        //Cannot get degree name to the label
        public void btn_fetch_Click(ActionEvent actionEvent) throws SQLException {
            String degreeName = new DegreeDAO().getDegreeNameByID(tb_degreeCode.getText());

            if(degreeName == null){
                lbl_degreeName.setText("Degree cannot be found");

            }
            else {
                lbl_degreeName.setText(degreeName);
            }
    }

    public void setMainPage(AdminDegreePage main){
        mainPage = main;
    }
}
