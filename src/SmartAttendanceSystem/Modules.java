package SmartAttendanceSystem;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Modules implements Initializable {

    @FXML
    GridPane ModuleView;
    @FXML
    ImageView testImage;



    List<ModuleViewItem> Modules;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Modules = new ArrayList<ModuleViewItem>();
        Modules.add(new ModuleViewItem("Bsc in Software Engineering", "19.2 Batch Ply", "Images/modules.png"));
        Modules.add(new ModuleViewItem("Bsc in Cyber Security", "19.2 Batch Ply", "Images/modules.png"));
        Modules.add(new ModuleViewItem("Bsc in Computer Networks", "19.2 Batch Ply", "Images/modules.png"));
        Modules.add(new ModuleViewItem("Bsc in Network Engineering", "20.1 Batch Ply", "Images/modules.png"));
        Modules.add(new ModuleViewItem("Bsc in Software Engineering", "20.1 Batch Ply", "Images/modules.png"));


        //Load the modules

        int r=0, c=0;
        for (ModuleViewItem module:Modules) {
            c++;
            Node crModule = module.Create();
            ModuleView.add(crModule, c, r);
            ModuleView.setMargin(crModule, new Insets(0,40,20,0));
            if(c>1) {
                r++;
                c=0;
            }

        }

    }
}
