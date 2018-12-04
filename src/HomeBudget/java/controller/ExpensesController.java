package HomeBudget.java.controller;

import HomeBudget.java.model.tableview.ExpensesTableView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author Wielq
 */
public class ExpensesController extends BaseController implements Initializable {

    public static final String URL_FXML = "/HomeBudget/resources/view/expenses.fxml";
    @FXML
    private GridPane Grid;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /**
         * Create and show ExpensesTableView
         */
        ExpensesTableView table = new ExpensesTableView();
        table.load();   
        Grid.add(table.getTable(), 0, 1);
    }

}
