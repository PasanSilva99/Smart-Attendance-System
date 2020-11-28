package StudentApplication;

import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AfterDashboard implements Initializable {
    public GridPane grid_content;
    public List<AfterDashboardScrollItem>ItemList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ItemList = new ArrayList<>();

        ItemList.add(new AfterDashboardScrollItem("Images/book.png","SOFT 255SL"));
        ItemList.add( new AfterDashboardScrollItem("images/book.png","PUSL 2002"));

        int column=0, row=0;

        for(AfterDashboardScrollItem item:ItemList){
            column++;
            AnchorPane generatedItem = item.getControl();

            EventHandler<MouseEvent> onClick = new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {

                }
            };

            grid_content.add(generatedItem,column,row);
            GridPane.setMargin(generatedItem,new Insets(10, 15, 10,10));

            if(column>1){
                row++;
                column=0;
            }
        }



        }



}
