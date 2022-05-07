package controller;

import com.jfoenix.controls.JFXButton;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.SubScene;
import model.Factory;
import model.util.Session;
import view.ScreenContainer;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    ScreenContainer myContainer = Factory.getInstance().createContainer();
    Session session = Factory.getInstance().getSession();

    @FXML private SubScene subscene;

    @FXML private JFXButton home;
    @FXML private JFXButton search;
    @FXML private JFXButton login;
    @FXML private JFXButton logout;

    public MainController() throws IOException {}

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Group root = new Group();
        root.getChildren().addAll(myContainer);
        subscene.setRoot(root);

        logout.visibleProperty().bind(session.loggedProperty());
        myContainer.getScreen().addListener(listener);
    }

    @FXML
    private void goToHome(ActionEvent event) throws IOException {
        myContainer.setScreen(Factory.getHome());
    }

    @FXML
    private void goToSearch(ActionEvent event) throws IOException {
        myContainer.setScreen(Factory.getSearch());
    }

    @FXML
    private void goToLogin(ActionEvent event) throws IOException {
        if(session.isLogged()) {
            myContainer.setScreen(Factory.getAccount());
        } else {
            myContainer.setScreen(Factory.getLogin());
        }
    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
        //cambia stato come non loggato
        session.setLogged(false);
        myContainer.setScreen(Factory.getHome());
    }

    //commentare
    ChangeListener<String> listener = new ChangeListener<String>() {
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            if (newValue.equals(Factory.getHome())) {
                home.setDisable(false);
                search.setDisable(false);
                login.setDisable(false);
                myContainer.getScreen().removeListener(listener);
            }
        }
    };

}
