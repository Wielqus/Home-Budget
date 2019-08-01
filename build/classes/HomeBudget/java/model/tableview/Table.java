/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HomeBudget.java.model.tableview;

import java.util.ArrayList;
import javafx.geometry.HPos;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;

/**
 * Table is a class to support presenting data in table
 * with automatically pagination and filters.
 * @author Wielq
 */
public class Table {

    protected ArrayList<Column> columns = new ArrayList();//list of columns
    protected ArrayList<RowConstraints> rows = new ArrayList();//list of rows
    protected final GridPane table;
    protected final VBox container;
    protected Pagination pagination;

    protected int limit = 10;//default limit
    protected ArrayList filters=new ArrayList();//default filters
    /**
     * Create Table,initialize pagination
     */
    protected Table() {
        container = new VBox();
        container.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        table = new GridPane();
        table.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        this.pagination = new Pagination();
    }
    /**
     * Add columns to table
     * @param columns 
     */
    protected void setColumns(Column... columns) {
        RowConstraints Row = new RowConstraints(50, 120, Double.MAX_VALUE);
        Row.setVgrow(Priority.ALWAYS);
        Row.setFillHeight(true);
        table.getRowConstraints().add(rows.size(), Row);
        for (Column column : columns) {
            ColumnConstraints columnC = new ColumnConstraints(50, 120, Double.MAX_VALUE);
            columnC.setHalignment(HPos.CENTER);
            columnC.setPercentWidth(100.0 / columns.length);
            table.getColumnConstraints().add(this.columns.size(), columnC);
            table.add(column.getColumn(), this.columns.size(), rows.size());
            this.columns.add(column);
        }
        this.rows.add(Row);
    }
    /**
     * Add one row to table
     * @param cell 
     */
    protected void addRow(Cell... cell) {
        RowConstraints Row = new RowConstraints(30, 60, Double.MAX_VALUE);
        Row.setVgrow(Priority.ALWAYS);
        Row.setFillHeight(true);
        table.getRowConstraints().add(rows.size(), Row);
        try {
            if (cell.length != columns.size()) {
                throw new Exception("Numbers of cell not equal number of columns");
            }
            int i = 0;
            for (Cell element : cell) {
                table.add(element.getCell(), i, rows.size());
                i++;
            }
        } catch (Exception e) {
            System.err.print("Error:" + e.toString());
        }
        rows.add(Row);

    }
    /**
     * It cleans table,clean filters,and removes data rows
     */
    protected void clean() {
        this.filters.clear();
        while (table.getRowConstraints().size() > 1) {
            table.getRowConstraints().remove(rows.size() - 1);
            rows.remove(rows.size() - 1);
        }
        while (table.getChildren().size() > this.columns.size()) {
            table.getChildren().remove(table.getChildren().size() - 1);
        }
        for (Column column : columns) {
            filters.add(column.getFilter());
        }
    }

    public void load() {
        
    }

    private void generateContainer() {     
        container.getChildren().add(table);   
        container.getChildren().add(pagination.getPagination(this));
    }

    public VBox getTable() {
        this.generateContainer();
        return this.container;
    }
}
