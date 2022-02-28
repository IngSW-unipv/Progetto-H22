package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import view.MainApplication;
import view.ScreensController;

import java.net.URL;
import java.util.ResourceBundle;

public class ManageController implements Initializable, IControlledScreen {

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
        myController.setScreen(MainApplication.home);
    }

    @FXML
    private void goToEmployee(ActionEvent event){
        myController.setScreen(MainApplication.employee);
    }

    @FXML
    private void goToCrew(ActionEvent event){
        myController.setScreen(MainApplication.crew);
    }

    @FXML
    private void goToAircraft(ActionEvent event){
        myController.setScreen(MainApplication.aircraft);
    }

    @FXML
    private void goToRoute(ActionEvent event){
        myController.setScreen(MainApplication.route);
    }

    @FXML
    private void goToFlight(ActionEvent event){
        myController.setScreen(MainApplication.flight);
    }

    @FXML
    private void logout(ActionEvent event){
        //cambia stato come non loggato
        myController.setScreen(MainApplication.home);
    }

}
