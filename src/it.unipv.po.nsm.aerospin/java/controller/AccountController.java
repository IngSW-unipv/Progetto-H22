package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import util.Session;
import view.Factory;
import view.ScreensController;

import java.net.URL;
import java.util.ResourceBundle;

public class AccountController implements Initializable, IControlledScreen {

    @FXML
    private ListView lvMain;


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
    private void goToSearch(ActionEvent event){
        myController.setScreen(Factory.search);
    }

/*    @FXML
    private void goToAccount(ActionEvent event){
        myController.setScreen(MainApplication.account);
    }*/
}