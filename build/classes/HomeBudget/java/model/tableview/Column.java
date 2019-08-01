package HomeBudget.java.model.tableview;

import HomeBudget.java.model.ChoiceBoxItem;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.LabelBuilder;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;


/**
 * This class describe one column in table
 * @author Wielq
 */
public class Column {

    public enum filterFieldType {textField,NumericField,DateField};
    
    ArrayList<Filter> filters = new ArrayList();
    public String columnName;
    public String caption;

    VBox column = new VBox();

    public Column(String name) {
        column.getStyleClass().add("table-header");
        this.columnName=name;
    }
    
    public Column setCaption(String caption){
        this.caption=caption;
        column.getChildren().add(LabelBuilder.create().text(caption).wrapText(true).build());
        return this;
    }
    /**
     * Add filter 
     * @param placeholder in query
     * @param table 
     */
    public Column addInputFilter(Table table,String placeholder) {
        
        Filter filter = new Filter(this.columnName,Filter.filterType.textFilter);     
        TextField filterField = new TextField();
        filterField.setPromptText(placeholder == null ? "" : placeholder);          
        filterField.setOnKeyReleased(e -> {
            filter.value="";
            if (filterField.getText().length() != 0) {
                filter.value = filterField.getText();                        
            }
            table.load(); 
        });
        this.filters.add(filter);
        this.column.getChildren().add(filterField);
        return this;
    }

    /**
     * Add filter to table.
     * @param table
     * @param placeholder
     * @return
     */
    public Column addEqualFilter(Table table,String placeholder){   
        Filter filter = new Filter(this.columnName,Filter.filterType.doubleFilter);   
        TextField filterField = new TextField();
        filterField.setPromptText(placeholder == null ? "" : placeholder);          
        filterField.setOnKeyReleased(e -> {
            filter.value="";
            if (filterField.getText().length() != 0) {
                filter.value = filterField.getText();                        
            }
            table.load(); 
        });
        this.filters.add(filter);
        this.column.getChildren().add(filterField);
        return this;
    }

    /**
     * Add date filter to table
     * @param table
     * @param placeholder
     * @return
     */
    public Column addLessDateFilter(Table table,String placeholder){           
        Filter filter = new Filter(this.columnName,Filter.filterType.dateLessFilter);   
        DatePicker filterField = new DatePicker();
        filterField.setPromptText(placeholder == null ? "" : placeholder);          
        filterField.setOnAction(e -> {
            filter.value=null;
            if (filterField.getValue()!=null) {
                filter.value = String.valueOf(filterField.getValue());
            }
            table.load(); 
        });
        filterField.setOnKeyReleased(e -> {
            filter.value=null;
            if (filterField.getValue()!=null) {
                filter.value = String.valueOf(filterField.getValue());
            }
            table.load(); 
        });
        this.filters.add(filter);
        this.column.getChildren().add(filterField);
        return this;
    }

    /**
     * Add date filter to table
     * @param table
     * @param placeholder
     * @return
     */
    public Column addMoreDateFilter(Table table,String placeholder){           
        Filter filter = new Filter(this.columnName,Filter.filterType.dateMoreFilter);   
        DatePicker filterField = new DatePicker();
        filterField.setPromptText(placeholder == null ? "" : placeholder);          
        filterField.setOnAction(e -> {
            filter.value=null;
            if (filterField.getValue()!=null) {
                filter.value = String.valueOf(filterField.getValue());
            }
            table.load(); 
        });
        filterField.setOnKeyReleased(e -> {
            filter.value=null;
            if (filterField.getValue()!=null) {
                filter.value = String.valueOf(filterField.getValue());
            }
            table.load(); 
        });
        this.filters.add(filter);
        this.column.getChildren().add(filterField);
        return this;
    }

    /**
     * Add double filter to table
     * @param table
     * @param placeholder
     * @return
     */
    public Column addLessDoubleFilter(Table table,String placeholder){           
        Filter filter = new Filter(this.columnName,Filter.filterType.doubleLessFilter);   
        TextField filterField = new TextField();
        filterField.setPromptText(placeholder == null ? "" : placeholder);          
        filterField.setOnKeyReleased(e -> {
            filter.value="";
            if (filterField.getText().length() != 0) {
                filter.value = filterField.getText();                        
            }
            table.load(); 
        });
        this.filters.add(filter);
        this.column.getChildren().add(filterField);
        return this;
    }

    /**
     * Add double filter to table
     * @param table
     * @param placeholder
     * @return
     */
    public Column addMoreDoubleFilter(Table table,String placeholder){           
        Filter filter = new Filter(this.columnName,Filter.filterType.doubleMoreFilter);   
        TextField filterField = new TextField();
        filterField.setPromptText(placeholder == null ? "" : placeholder);          
        filterField.setOnKeyReleased(e -> {
            filter.value="";
            if (filterField.getText().length() != 0) {
                filter.value = filterField.getText();                        
            }
            table.load(); 
        });
        this.filters.add(filter);
        this.column.getChildren().add(filterField);
        return this;
    }

    /**
     * Add select filter to table
     * @param table
     * @param placeholder
     * @param elements
     * @return
     */
    public Column addSelectFilter(Table table,String placeholder,ArrayList<ChoiceBoxItem> elements){
        Filter filter = new Filter(this.columnName,Filter.filterType.listFilter); 
        ChoiceBox<ChoiceBoxItem> filterField = new ChoiceBox();
        filterField.setMaxWidth(Double.MAX_VALUE);   
        ChoiceBoxItem defaultText = new ChoiceBoxItem(0, placeholder);
        filterField.getItems().add(defaultText);
        filterField.getSelectionModel().select(0);
        elements.forEach(element->{
            filterField.getItems().add(element);
        });
        filterField.setOnAction(e->{
            filter.value="0";                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 
            if(filterField.getSelectionModel().getSelectedItem().value!=0){
                filter.value = String.valueOf(filterField.getSelectionModel().getSelectedItem().value);                                   
            }
            table.load();
        });
        this.filters.add(filter);
        this.column.getChildren().add(filterField);
        return this;
    }

    public List<Filter> getFilter() {
        return this.filters;
    }

    public VBox getColumn() {
        return column;
    }

}
