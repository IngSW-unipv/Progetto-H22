package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import util.Session;
import view.Factory;
import view.ScreensController;

import java.net.URL;
import java.util.ResourceBundle;

public class RouteController implements Initializable, IControlledScreen {

    ScreensController myController;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }

    @FXML
    private void goToHome(ActionEvent event){
        myController.setScreen(Factory.home);
    }

    @FXML
    private void goToManage(ActionEvent event){
        myController.setScreen(Factory.manage);
    }

    @FXML
    private void logout(ActionEvent event){
        //cambia stato come non loggato
        myController.setScreen(Factory.home);
    }

}
