package HomeBudget.java.controller;

import HomeBudget.java.model.Expenses;
import HomeBudget.java.model.ExpensesCategory;
import HomeBudget.java.model.Navigation;
import HomeBudget.java.model.tableview.Cell;
import HomeBudget.java.model.tableview.ExpensesTableView;
import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
import java.time.Clock;
import java.time.LocalDate;
import java.util.Date;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
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
        ExpensesTableView table = new ExpensesTableView();
        table.load();   
        Grid.add(table.getTable(), 0, 1);
    }

}
