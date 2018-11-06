/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HomeBudget.java.controller;

import HomeBudget.java.model.Navigation;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

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
    private JFXButton homeButton;
    @FXML
    private JFXButton incomeButton;
    @FXML
    private JFXButton expensesButton;
    @FXML
    private JFXButton raportsButton;
    @FXML
    private JFXButton historyButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void goToHome(ActionEvent event) {
        Navigation.load(HomeController.URL_FXML, MainView);
        this.tooggleColor(homeButton);
    }

    @FXML
    private void goToIncome(ActionEvent event) {
        Navigation.load(IncomeController.URL_FXML, MainView);
        this.tooggleColor(incomeButton);
    }

    @FXML
    private void goToExpenses(ActionEvent event) {
        Navigation.load(ExpensesController.URL_FXML, MainView);
        this.tooggleColor(expensesButton);
    }

    @FXML
    private void goToRaports(ActionEvent event) {
        Navigation.load(RaportsController.URL_FXML, MainView);
        this.tooggleColor(raportsButton);
    }

    @FXML
    private void goToHistory(ActionEvent event) {
        Navigation.load(HistoryController.URL_FXML, MainView);
        this.tooggleColor(historyButton);
    }
    
    private void tooggleColor(Button button){
        homeButton.getStyleClass().remove("positive");
        incomeButton.getStyleClass().remove("positive");
        expensesButton.getStyleClass().remove("positive");
        raportsButton.getStyleClass().remove("positive");
        historyButton.getStyleClass().remove("positive");
        
        button.getStyleClass().add("positive");
    }

}
