package HomeBudget.java;

import HomeBudget.java.controller.LoginController;
import HomeBudget.java.hibernate.util.HibernateUtil;
import HomeBudget.java.model.Navigation;
import HomeBudget.java.model.Session;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        /**
         * 
         * Initialize Session and navigation.Load basic view.
         * 
         */
        
        HibernateUtil.getSessionFactory().openSession();
        
        Session.CurrentSession = new Session();

        Navigation.navigation = new Navigation(primaryStage);

        primaryStage.setTitle("Home Budget");
        primaryStage.show();

        Navigation.getNavigation().load(LoginController.URL_FXML).Show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
