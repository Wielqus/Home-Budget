package HomeBudget.java.model;

import HomeBudget.java.controller.BaseController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.layout.BorderPane;
/**
 * Navigation is a class, who support navigation between fxml views
 * @author Wielq
 */
public class Navigation {

    private final Stage stage;
    private final Scene scene;

    public static Navigation navigation;

    public static Navigation getNavigation() {
        return navigation;
    }

    private List<Controller> controllers = new ArrayList<>();

    public Navigation(Stage stage) {
        this.stage = stage;
        scene = new Scene(new Pane());
        stage.setScene(scene);
    }
    /**
     * Load fxml to primaryStage
     * @param sUrl url to fxml view
     * @return 
     */
    public Controller load(String sUrl) {
        try {

            //loads the fxml file
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(sUrl));
            Node root = fxmlLoader.load();

            Controller controller = fxmlLoader.getController();
            controller.setView(root);

            return controller;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * It Loads fxml view dynamically to BorderPane item
     * @param sUrl url to fxml view
     * @param item 
     */
    public static void load(String sUrl, BorderPane item) {
        Parent root;
        try {
            root = FXMLLoader.load(BaseController.class.getResource(sUrl));
            item.setCenter(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Show(Controller controller) {
        try {
            scene.setRoot((Parent) controller.getView());
            controllers.add(controller);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
