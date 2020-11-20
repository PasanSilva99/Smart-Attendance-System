package SmartAttendanceSystem;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import javax.accessibility.AccessibleValue;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SessionSelection implements Initializable {

    private List<ViewItem> SessionList = new ArrayList<ViewItem>();

    ViewItem ClickedItem;

    MainPage base;

    @FXML
    Label lbl_selectedItem;
    @FXML
    GridPane scrl_sessionItem;

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

        List<Session> AvailableSessions;

        AvailableSessions = new SessionLoder().LoadSessions(ClickedItem.ModuleCode, LocalDate.now().format(DateTimeFormatter.ofPattern("dd:MM:yyyy")));

        int c=0,r=0;
        for (Session _session:AvailableSessions){
            c++;
            ViewItem item = new ViewItem(_session.SessionName, _session.getDate() + " "+_session.getStartTime() + " - " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd:MM:yyyy")));
            Node sessionItem = item.CreateSessionItem();
            scrl_sessionItem.add(sessionItem,c,r);
            scrl_sessionItem.setMargin(sessionItem, new Insets(0,40,20,0));
            if(c>1) {
                r++;
                c=0;
            }
        }

    }

    public void getBase(MainPage base){
        this.base = base;
    }
}

