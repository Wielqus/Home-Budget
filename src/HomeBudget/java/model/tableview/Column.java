package HomeBudget.java.model.tableview;

import javafx.scene.control.LabelBuilder;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;


/**
 * This class describe one column in table
 * @author Wielq
 */
public class Column {

    String filters = "";

    VBox column = new VBox();

    public Column(String name) {
        column.getStyleClass().add("table-header");
        column.getChildren().add(LabelBuilder.create().text(name).wrapText(true).build());
    }
    /**
     * Add filter 
     * @param column in query
     * @param table 
     */
    public void addInputFilter(String column, Table table) {
        TextField filter = new TextField();
        filter.setOnKeyReleased(e -> {
            this.filters = "";
            if (filter.getText().length() != 0) {
                this.filters += " AND " + column + " LIKE '%" + filter.getText() + "%'";                         
            }
            table.load(); 
        });
        this.column.getChildren().add(filter);
    }

    public String getFilter() {
        return this.filters;
    }

    public VBox getColumn() {
        return column;
    }

}
