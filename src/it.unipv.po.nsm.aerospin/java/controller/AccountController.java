package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import view.Factory;
import view.ScreensController;

import java.net.URL;
import java.util.ResourceBundle;

public class AccountController implements Initializable, IControlledScreen {


    private Factory factory = Factory.getInstance();

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

/*    @FXML
    private void goToAccount(ActionEvent event){
        myController.setScreen(MainApplication.account);
    }*/
}