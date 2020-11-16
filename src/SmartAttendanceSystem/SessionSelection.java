package SmartAttendanceSystem;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SessionSelection implements Initializable {

    private List<ViewItem> SessionList = new ArrayList<ViewItem>();

    ViewItem ClickedItem;

    MainPage base;

    @FXML
    Label lbl_selectedItem;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void BackRequested() throws IOException {
        FXMLLoader loder = new FXMLLoader();
        loder.setLocation(getClass().getResource("Modules.fxml"));
        AnchorPane page = loder.load();
        base.base.getChildren().clear();
        base.base.getChildren().setAll(page);
        Modules Mpage = loder.getController();
        Mpage.setMainPage(base);
    }

    public void setModule(ViewItem ClickedItem){
        this.ClickedItem=ClickedItem;
        lbl_selectedItem.setText(ClickedItem.ModuleName);
    }

    public void getBase(MainPage base){
        this.base = base;
    }
}//

