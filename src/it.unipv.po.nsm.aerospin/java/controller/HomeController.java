package controller;

import controller.util.IControlledScreen;
import javafx.fxml.Initializable;
import view.ScreenContainer;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable, IControlledScreen {

    ScreenContainer myContainer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @Override
    public void setScreenParent(ScreenContainer screenParent) {
        myContainer = screenParent;
    }
}
