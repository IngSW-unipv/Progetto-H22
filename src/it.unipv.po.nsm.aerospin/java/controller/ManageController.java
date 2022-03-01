package controller;

import com.sun.scenario.effect.EffectHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import util.Session;
import view.Factory;
import view.ScreensController;

import java.net.URL;
import java.util.ResourceBundle;

public class ManageController implements Initializable, IControlledScreen {

    ScreensController myController;
    private Factory factory = Factory.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }

    @FXML
    private void goToHome(ActionEvent event){
        myController.setScreen(factory.getHome());
    }

    @FXML
    private void goToEmployee(ActionEvent event){
        myController.setScreen(factory.getEmployee());
    }

    @FXML
    private void goToCrew(ActionEvent event){
        myController.setScreen(factory.getCrew());
    }

    @FXML
    private void goToAircraft(ActionEvent event){
        myController.setScreen(factory.getAircraft());
    }

    @FXML
    private void goToRoute(ActionEvent event){
        myController.setScreen(factory.getRoute());
    }

    @FXML
    private void goToFlight(ActionEvent event){
        myController.setScreen(factory.getFlight());
    }

    @FXML
    private void logout(ActionEvent event){
        //cambia stato come non loggato
        myController.setScreen(factory.getHome());
    }

}
