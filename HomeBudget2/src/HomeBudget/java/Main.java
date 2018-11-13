package HomeBudget.java;

import HomeBudget.java.controller.LoginController;
import HomeBudget.java.controller.MainController;
import HomeBudget.java.hibernate.util.HibernateUtil;
import HomeBudget.java.model.Navigation;
import HomeBudget.java.model.Users;
import com.mchange.v2.c3p0.impl.C3P0Defaults;
import java.*;
import javafx.application.Application;
import javafx.stage.Stage;
import jdk.nashorn.internal.runtime.regexp.JoniRegExp;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.sql.ordering.antlr.Factory;

public class Main extends Application {

    private static Navigation navigation;

    public static Navigation getNavigation() {
        return navigation;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        
        navigation = new Navigation(primaryStage);

        primaryStage.setTitle("VA navigation");
        primaryStage.show();

        //navigate to first view
        Main.getNavigation().load(LoginController.URL_FXML).Show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
