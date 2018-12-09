package HomeBudget.java.controller;

import HomeBudget.java.model.Controller;
import HomeBudget.java.model.Navigation;
import javafx.scene.Node;


public class BaseController implements Controller {

    private Node view;

    @Override
    public Node getView() {
        return view;
    }

    @Override
    public void setView (Node view){
        this.view = view;
    }

    @Override
    public void Show() {
        PreShowing();
        Navigation.getNavigation().Show(this);
        PostShowing();
    }

    public void PreShowing()
    {
       
    }

    public void PostShowing()
    {

    }
}
