package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import view.ScreensController;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable, IControlledScreen {

    ScreensController myController;

    @FXML
    private ImageView img;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @Override
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }
}
