package SmartAttendanceSystem;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import javax.swing.text.View;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Modules implements Initializable {

    @FXML
    GridPane ModuleView;
    @FXML
    ImageView testImage;
    @FXML
    Label title_;
    
    List<ViewItem> Modules;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Modules = new ArrayList<ViewItem>();
        Modules.add(new ViewItem("Bsc in Software Engineering", "19.2 Batch Ply", "Images/modules.png"));
        Modules.add(new ViewItem("Bsc in Cyber Security", "19.2 Batch Ply", "Images/modules.png"));
        Modules.add(new ViewItem("Bsc in Computer Networks", "19.2 Batch Ply", "Images/modules.png"));
        Modules.add(new ViewItem("Bsc in Network Engineering", "20.1 Batch Ply", "Images/modules.png"));
        Modules.add(new ViewItem("Bsc in Software Engineering", "20.1 Batch Ply", "Images/modules.png"));
        Modules.add(new ViewItem("MIS", "20.1 Batch Ply", "Images/modules.png"));


        //Load the modules

        int r=0, c=0;
        for (ViewItem module:Modules) {
            c++;
            Node crModule = module.CreateModuleItem();

            EventHandler<MouseEvent> ItemClickedEvent = new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    ItemClickedF(module);
                }

            };

            crModule.addEventFilter(MouseEvent.MOUSE_CLICKED, ItemClickedEvent);



            ModuleView.add(crModule, c, r);
            ModuleView.setMargin(crModule, new Insets(0,40,20,0));
            if(c>1) {
                r++;
                c=0;
            }

        }

    }

    public void ItemClickedF(ViewItem ClickedItem){
        title_.setText("Clicked On " + ClickedItem.getModuleName());
    }
}
