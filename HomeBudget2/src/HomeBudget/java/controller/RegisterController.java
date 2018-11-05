/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HomeBudget.java.controller;

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
 *
 * @author Wielq
 */
public class RegisterController extends BaseController implements Initializable {
    
    public static final String URL_FXML = "/HomeBudget/resources/view/register.fxml";
    
    private Label label;
    @FXML
    private TextField LoginText;
    @FXML
    private TextField PasswordText;
    @FXML
    private PasswordField RPasswordText;
    @FXML
    private Label InfoLabel;
    @FXML
    private Button RegisterButton;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
    }    

    @FXML
    private void RegisterAction(ActionEvent event) throws IOException {
        String Login = LoginText.getText();
        String Password = PasswordText.getText();
        String RPassword = RPasswordText.getText();
        
        if(Login.length()<4){
            InfoLabel.setText("Login musi mieć minimum 4 znaki");
            return;
        }
        if(Password.length()<6){
            InfoLabel.setText("Hasło musi mieć minimum 6 znaków");
            return;
        }
        if(!Password.equals(RPassword)){
            InfoLabel.setText("Podane hasła rożnią się");
            return;
        }

        
        
        InfoLabel.setText("Wszystko ok");
    }
    
}
