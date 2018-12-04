package HomeBudget.java.model.tableview;

import HomeBudget.java.model.*;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.LabelBuilder;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * This class describe one row in table
 * @author Wielq
 */
public class Cell {
    
    HBox box = new HBox();
    
    public Cell(){
       this.box.getStyleClass().add("table-cell");        
    }
    
    public void SimpleLabelCell(String name){      
        box.getChildren().add(LabelBuilder.create().text(name).wrapText(true).build());  
    }
    public HBox getCell(){
        return this.box;       
    }
    
}
