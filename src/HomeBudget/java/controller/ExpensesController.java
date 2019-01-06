package HomeBudget.java.controller;

import HomeBudget.java.model.Navigation;
import HomeBudget.java.model.tableview.ExpensesTableView;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
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
    @FXML
    private JFXButton addExpensesButton;
    
    private ExpensesTableView table = new ExpensesTableView();
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /**
         * Create and show ExpensesTableView
         */
        table.load();   
        Grid.add(table.getTable(), 0, 1);
    }

    @FXML
    private void addExpenses(ActionEvent event) {
        Navigation.getNavigation().openModal(AddexpensesController.URL_FXML,table);
    }

}
