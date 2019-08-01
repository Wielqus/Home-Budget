package HomeBudget.java.model.tableview;

import com.jfoenix.controls.JFXButton;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

/**
 * This class support automatically pagination in table
 *
 * @author Wielq
 */
public class Pagination {

    private final HBox pagination;
    private Integer page = 1;
    private Integer pagesMax = 1;
    private Integer itemsMax;
    private Integer itemsPerPage = 10;

    public Pagination() {
        pagination = new HBox();
        pagination.setMaxWidth(Double.MAX_VALUE);
        pagination.getStyleClass().add("pagination");
    }

    /**
     * Set current page
     *
     * @param page
     */
    public void setPage(int page) {
        this.page = page;
    }

    public void setItemsMax(int count) {
        this.itemsMax = count;
        if (count != 0) {
            this.pagesMax = (int) Math.ceil((double) itemsMax / itemsPerPage);
        }
    }

    public void setItemsPerPage(int count) {
        this.itemsPerPage = count;
    }

    public int getPage() {
        return this.page;
    }

    private void generatePaginationItem(String label, int page, Table table) {
        JFXButton item = new JFXButton();
        item.setText(label);
        item.getStyleClass().add("positive");
        item.getStyleClass().add("pagination-item");
        item.setCursor(Cursor.HAND);
        if (this.page == page || page < 1 || page > this.pagesMax) {
            item.setDisable(true);
        }
        item.setOnAction(e -> {
            this.page = page;
            table.load();
        });
        pagination.getChildren().add(item);
    }

    public void generatePagination(Table table) {
        Region items = new Region();
        pagination.setHgrow(items, Priority.ALWAYS);
        pagination.getChildren().add(items);

        pagination.getChildren().clear();
        this.generatePaginationItem("Poprzedni", page - 1, table);
        for (Integer i = 1; i <= this.pagesMax; i++) {
            this.generatePaginationItem(i.toString(), i, table);
        }
        this.generatePaginationItem("Następny", page + 1, table);

        Region infoRegion = new Region();
        pagination.setHgrow(infoRegion, Priority.ALWAYS);
        pagination.getChildren().add(items);

        Label info = new Label();
        info.setText("Ilość:" + itemsMax.toString() + " | " + page.toString() + "/" + pagesMax.toString());
        info.getStyleClass().add("positive");
        info.getStyleClass().add("pagination-info");
        info.setAlignment(Pos.BASELINE_RIGHT);
        pagination.getChildren().add(info);

    }

    public HBox getPagination(Table table) {
        this.generatePagination(table);
        return this.pagination;
    }
}
