package StudentApplication;

import Common.ViewItem;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class LeaderBoardMain implements Initializable {

    @FXML
    GridPane ModuleView;
    @FXML
    ImageView testImage;
    @FXML
    Label title_;

    List<ViewItem> LeaderBoardModules;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        LeaderBoardModules = new ArrayList<ViewItem>();
        LeaderBoardModules.add(new ViewItem("SE01", "Bsc in Software Engineering", "19.2 Batch Ply", "Images/modules.png"));
        LeaderBoardModules.add(new ViewItem("CS01", "Bsc in Cyber Security", "19.2 Batch Ply", "Images/modules.png"));
        LeaderBoardModules.add(new ViewItem("CN01", "Bsc in Computer Networks", "19.2 Batch Ply", "Images/modules.png"));
        LeaderBoardModules.add(new ViewItem("NE02","Bsc in Network Engineering", "20.1 Batch Ply", "Images/modules.png"));
        LeaderBoardModules.add(new ViewItem("SE02","Bsc in Software Engineering", "20.1 Batch Ply", "Images/modules.png"));
        LeaderBoardModules.add(new ViewItem("MIS02", "MIS", "20.1 Batch Ply", "Images/modules.png"));


        //Load the modules

        int r=0, c=0;
        for (ViewItem module:LeaderBoardModules) {
            c++;
            Node crModule = module.CreateModuleItem();

            EventHandler<MouseEvent> ItemClickedEvent = new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) { ItemClickedF(module);
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

