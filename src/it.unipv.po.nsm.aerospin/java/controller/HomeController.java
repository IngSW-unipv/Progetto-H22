package controller;

import javafx.fxml.Initializable;
import view.ScreensController;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable, IControlledScreen {

    ScreensController myController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @Override
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }
}
