package HomeBudget.java.controller;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Wielq
 */
public class HomeController extends BaseController implements Initializable {
    
    public static final String URL_FXML = "/HomeBudget/resources/view/home.fxml";
    @FXML
    private FontAwesomeIconView homeIcon;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
