package controller;

import com.jfoenix.controls.JFXButton;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.SubScene;
import model.persistence.CachedFlights;
import view.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    Factory factory = Factory.getInstance();
    ScreensController mainContainer = factory.createContainer();
    CachedFlights searchResult = CachedFlights.getInstance();

    @FXML private SubScene subscene;

    @FXML private JFXButton home;
    @FXML private JFXButton search;
    @FXML private JFXButton login;
    @FXML private JFXButton account;
    @FXML private JFXButton manage;
    @FXML private JFXButton logout;

    public MainController() throws IOException {
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Group root = new Group();
        root.getChildren().addAll(mainContainer);
        subscene.setRoot(root);

        Thread t1 = new Thread(()->{
            try {
                searchResult.findAll();
                mainContainer.setScreen(Factory.getHome());
                home.setDisable(false);
                search.setDisable(false);
                login.setDisable(false);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        t1.start();
    }

    @FXML
    private void goToHome(ActionEvent event) throws IOException {
        mainContainer.setScreen(Factory.getHome());
    }

    @FXML
    private void goToSearch(ActionEvent event) throws IOException {
        mainContainer.setScreen(Factory.getSearch());
    }

    @FXML
    private void goToLogin(ActionEvent event) throws IOException {
        mainContainer.setScreen(Factory.getLogin());
    }

    @FXML
    private void goToAccount(ActionEvent event) throws IOException {
        mainContainer.setScreen(Factory.getAccount());
    }

    @FXML
    private void goToManage(ActionEvent event) throws IOException {
        mainContainer.setScreen(Factory.getManage());
    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
        //cambia stato come non loggato
        factory.getSession().setLogged(false);
        mainContainer.setScreen(Factory.getHome());
        logout.visibleProperty().bind(new SimpleBooleanProperty(factory.getSession().isLogged()));
    }
}
