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
    private Factory factory = Factory.getInstance();

    @FXML
    JFXButton jBtn = new JFXButton();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        jBtn.visibleProperty().bind(new SimpleBooleanProperty(factory.getSession().isLogged()));

    }

    @Override
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }

    @FXML
    private void goToSearch(ActionEvent event){
        factory.getSession().setLogged(true);
        myController.setScreen(factory.getSearch());
    }

    @FXML
    private void goToLogin(ActionEvent event){
        myController.setScreen(factory.getLogin());
    }

    @FXML
    private void logout(ActionEvent event){
        //cambia stato come non loggato
        factory.getSession().setLogged(false);
        myController.setScreen(factory.getHome());
    }

}
