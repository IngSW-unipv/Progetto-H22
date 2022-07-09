package controller;

import java.net.URL;
import java.util.ResourceBundle;

import controller.util.IControlledScreen;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import model.Session;
import view.ScreenContainer;

public class AdminController implements Initializable, IControlledScreen {

    private ScreenContainer myContainer;
    private final Session session = Session.getInstance();
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField usernameTextBox;

    @FXML
    void initialize() {
    }

    @Override
    public void setScreenParent(ScreenContainer screenParent) {
        myContainer = screenParent;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
