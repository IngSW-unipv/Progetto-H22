package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import view.*;


public class SHomeController implements Initializable, IControlledScreen {

    ScreensController myController;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }

    @FXML
    private void goToScreen2(ActionEvent event){
        myController.setScreen(MainApplication.screen2ID);
    }

    @FXML
    private void goToScreen3(ActionEvent event){
        myController.setScreen(MainApplication.screen3ID);
    }
}