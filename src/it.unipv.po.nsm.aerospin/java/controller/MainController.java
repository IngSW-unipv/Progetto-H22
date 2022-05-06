package controller;

import com.jfoenix.controls.JFXButton;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.SubScene;
import util.Session;
import view.Factory;
import view.ScreensController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    Factory factory = Factory.getInstance();
    ScreensController mainContainer = factory.createContainer();
    Session session = factory.getSession();

    @FXML private SubScene subscene;

    @FXML public JFXButton home;
    @FXML private JFXButton search;
    @FXML private JFXButton login;
    @FXML private JFXButton logout;

    BooleanProperty isLogged = new SimpleBooleanProperty(session.isLogged());

    public MainController() throws IOException {
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Group root = new Group();
        root.getChildren().addAll(mainContainer);
        subscene.setRoot(root);

        //solve
        logout.disableProperty().bind(isLogged);

        //after load
        home.setDisable(false);
        search.setDisable(false);
        login.setDisable(false);
    }

    @FXML
    private void goToHome(ActionEvent event) throws IOException {
        mainContainer.setScreen(Factory.getHome());
    }

    @FXML
    private void goToSearch(ActionEvent event) throws IOException {
        mainContainer.setScreen(Factory.getSearch());
        session.setLogged(true);
    }

    @FXML
    private void goToLogin(ActionEvent event) throws IOException {
        if(session.isLogged()) {
            mainContainer.setScreen(Factory.getAccount());
        } else {
            mainContainer.setScreen(Factory.getLogin());
        }
    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
        //cambia stato come non loggato
        factory.getSession().setLogged(false);
        mainContainer.setScreen(Factory.getHome());

    }
}
