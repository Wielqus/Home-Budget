package HomeBudget.java.controller;

import HomeBudget.java.model.Navigation;
import HomeBudget.java.model.tableview.ExpensesTableView;
import HomeBudget.java.model.tableview.IncomesTableView;
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
public class IncomeController extends BaseController implements Initializable {
    
    public static final String URL_FXML = "/HomeBudget/resources/view/income.fxml";
    
     @FXML
    private GridPane Grid;
    
    private IncomesTableView table = new IncomesTableView();
    @FXML
    private JFXButton addIncomeButton;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        table.load();   
        Grid.add(table.getTable(), 0, 2);
        
    }    

    @FXML
    private void addIncome(ActionEvent event) {
        Navigation.getNavigation().openModal(AddincomesController.URL_FXML,table);
    }
    
}
