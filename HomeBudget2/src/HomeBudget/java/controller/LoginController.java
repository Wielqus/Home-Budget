/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HomeBudget.java.controller;

import HomeBudget.java.Main;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


/**
 * FXML Controller class
 *
 * @author Wielq
 */
public class LoginController extends BaseController implements Initializable {

    public static final String URL_FXML = "/HomeBudget/resources/view/login.fxml";
    
    @FXML
    private TextField LoginText;
    @FXML
    private PasswordField PasswordText;
    @FXML
    private Label InfoLabel;
    @FXML
    private Button RegisterButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void LoginAction(ActionEvent event) {
        Main.getNavigation().load(MainController.URL_FXML).Show();
    }

    @FXML
    private void goToRegisterAction(ActionEvent event) throws IOException {
        Main.getNavigation().load(RegisterController.URL_FXML).Show();
    }

}
