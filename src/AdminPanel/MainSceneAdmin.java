package AdminPanel;

import Common.UniEvent;
import Common.ViewItemWithBadge;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MainSceneAdmin implements Initializable {
    public GridPane grid_lectureViewAdmin;

    List<UniEvent>
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ViewItemWithBadge item = new ViewItemWithBadge("SOFT255SL", "9 AM - 12 PM", "L102");

        grid_lectureViewAdmin.add(item.getControl(), 0,0);
        grid_lectureViewAdmin.add(item.getControl(), 1,0);
        grid_lectureViewAdmin.add(item.getControl(), 0,1);
        grid_lectureViewAdmin.add(item.getControl(), 1,1);
    }
}
