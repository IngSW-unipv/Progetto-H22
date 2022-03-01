package controller;

import com.jfoenix.controls.JFXButton;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import util.Session;
import view.Factory;
import view.ScreensController;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable, IControlledScreen {

    private ScreensController myController;
    private Session session;
    private Factory factory;

    @FXML
    JFXButton jBtn = new JFXButton();

    @Override
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }



    @Override
    public void initialize(URL url, ResourceBundle rb) {
       // System.out.println(factory.session.isLogged());
        //session = factory.getSession();
        jBtn.disableProperty().bind(new SimpleBooleanProperty(true));
       //System.out.println(session.isLogged());
//        myController.getSession().isLogged();
//        jBtn.disableProperty().bind(booleanBind);
    }


    @FXML
    private void goToSearch(ActionEvent event){
        myController.setScreen(Factory.search);
    }

    @FXML
    private void goToLogin(ActionEvent event){
        myController.setScreen(Factory.login);
    }

    @FXML
    private void logout(ActionEvent event){
        //cambia stato come non loggato
//        session.setLogged(false);
        myController.setScreen(Factory.home);
    }

}
