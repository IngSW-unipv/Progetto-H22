package controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import view.Factory;
import view.ScreensController;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable, IControlledScreen {

    ScreensController myController;

    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }

    @FXML
    JFXButton jBtn = new JFXButton();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        if (myController.getSession().isLogged())
//            jBtn.setVisible(false);
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
