package HomeBudget.java.model.tableview;

import com.jfoenix.controls.JFXButton;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.LabelBuilder;
import javafx.scene.layout.HBox;

/**
 * This class describe one row in table
 * @author Wielq
 */
public class Cell {
    
    HBox box = new HBox();
    
    public Cell(){
       this.box.getStyleClass().add("table-cell");        
    }

    /**
     * Add style class to cell
     * @return Cell
     */
    public Cell addClass(String className){
        this.box.getStyleClass().add(className);   
        return this;
    }

    /**
     * Add label to cell
     * @return
     */
    public Cell SimpleLabelCell(String name){      
        box.getChildren().add(LabelBuilder.create().text(name).wrapText(true).build());  
        return this;
    }


    public void addRowButton(JFXButton button){
        button.setCursor(Cursor.HAND);
        box.getChildren().add(button);
    }


    public void addRowButton(Button button){
        button.setCursor(Cursor.HAND);
        box.getChildren().add(button);
    }


    public HBox getCell(){
        return this.box;       
    }
    
}
