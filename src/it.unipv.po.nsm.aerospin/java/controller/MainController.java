package controller;

import com.jfoenix.controls.JFXButton;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.SubScene;
import view.Factory;
import view.ScreensController;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    Factory factory = Factory.getInstance();
    ScreensController mainContainer = factory.createContainer();

    @FXML
    private SubScene subscene;

    @FXML
    JFXButton login;

    @FXML
    JFXButton logout;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        logout.setVisible(false);
        Group root = new Group();
        root.getChildren().addAll(mainContainer);
        subscene.setRoot(root);
    }

    @FXML
    private void goToHome(ActionEvent event){
        mainContainer.setScreen(factory.getHome());
    }

    @FXML
    private void goToSearch(ActionEvent event){
        mainContainer.setScreen(factory.getSearch());
    }

    @FXML
    private void goToLogin(ActionEvent event){
        mainContainer.setScreen(factory.getLogin());
    }

    @FXML
    private void logout(ActionEvent event){
        //cambia stato come non loggato
        factory.getSession().setLogged(false);
        mainContainer.setScreen(factory.getHome());
        logout.visibleProperty().bind(new SimpleBooleanProperty(factory.getSession().isLogged()));
    }
}
