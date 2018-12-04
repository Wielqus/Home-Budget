package HomeBudget.java.controller;

import HomeBudget.java.model.Navigation;
import HomeBudget.java.model.Session;
import HomeBudget.java.model.Users;
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
    }

    @FXML
    private void LoginAction(ActionEvent event) {
        Integer user = Users.Login(LoginText.getText(), PasswordText.getText());
        if(user !=0){
            Session.getCurrentSession().add("user", user.toString());//add user id to session
            Navigation.getNavigation().load(MainController.URL_FXML).Show();//load main view
            return;
        }else{
            InfoLabel.setText("Podane dane są nieprawidłowe");
            return;
        }
        
    }

    @FXML
    private void goToRegisterAction(ActionEvent event) throws IOException {
        Navigation.getNavigation().load(RegisterController.URL_FXML).Show();
    }

}
