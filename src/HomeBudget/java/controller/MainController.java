package HomeBudget.java.controller;

import HomeBudget.java.model.Navigation;
import HomeBudget.java.model.Session;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author Wielq
 */


public class MainController extends BaseController implements Initializable {

    public static final String URL_FXML = "/HomeBudget/resources/view/main.fxml";
    @FXML
    private BorderPane MainView;
    @FXML
    private HBox homeButton;
    @FXML
    private HBox incomeButton;
    @FXML
    private HBox expensesButton;
    @FXML
    private HBox raportsButton;
    @FXML
    private HBox historyButton;
    @FXML
    private Label userLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Navigation.load(HomeController.URL_FXML, MainView);
    }

    /**
     * remove class positive from all buttons,
     * and add this class to button passed in function argument
     * @param button 
     */
    private void tooggleColor(HBox button) {
        homeButton.getStyleClass().remove("positive");
        incomeButton.getStyleClass().remove("positive");
        expensesButton.getStyleClass().remove("positive");
        raportsButton.getStyleClass().remove("positive");
        historyButton.getStyleClass().remove("positive");

        button.getStyleClass().add("positive");
    }

    @FXML
    private void goToHome(MouseEvent event) {
         Navigation.load(HomeController.URL_FXML, MainView);
        this.tooggleColor(homeButton);
    }

    @FXML
    private void goToIncome(MouseEvent event) {
         Navigation.load(IncomeController.URL_FXML, MainView);
        this.tooggleColor(incomeButton);
    }

    @FXML
    private void goToExpenses(MouseEvent event) {
        Navigation.load(ExpensesController.URL_FXML, MainView);
        this.tooggleColor(expensesButton);
    }

    @FXML
    private void goToHistory(MouseEvent event) {
         Navigation.load(HistoryController.URL_FXML, MainView);
        this.tooggleColor(historyButton);
    }

    @FXML
    private void goToRaports(MouseEvent event) {
        Navigation.load(RaportsController.URL_FXML, MainView);
        this.tooggleColor(raportsButton);
    }

}
