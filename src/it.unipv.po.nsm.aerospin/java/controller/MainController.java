package controller;

import com.jfoenix.controls.JFXButton;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.SubScene;
import model.*;
import view.ScreenContainer;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    private final ScreenContainer myContainer = Factory.getInstance().createContainer();
    private final Session session = Factory.getInstance().getSession();

    @FXML private SubScene subscene;
    @FXML private JFXButton home;
    @FXML private JFXButton search;
    @FXML private JFXButton login;
    @FXML private JFXButton logout;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Group root = new Group();
        root.getChildren().addAll(myContainer);
        subscene.setRoot(root);

        login.textProperty().bind(session.getLoggedButton());
        logout.visibleProperty().bind(session.loggedProperty());
        myContainer.getScreen().addListener(loadListener);
    }

    @FXML
    private void goToHome() throws IOException {
        myContainer.setScreen(Factory.getHome());
    }

    @FXML
    private void goToSearch() throws IOException {
        myContainer.setScreen(Factory.getSearch());
    }

    @FXML
    private void goToLogin() throws IOException {
        if(session.isLogged()) {
            myContainer.setScreen(Factory.getAccount());
        } else {
            myContainer.setScreen(Factory.getLogin());
        }
    }

    @FXML
    private void logout() throws IOException {
        //cambia stato come non loggato
        session.setLogged(false);
        myContainer.setScreen(Factory.getHome());
    }

    //commentare
    private final ChangeListener<String> loadListener = new ChangeListener<>() {
        @Override
        public void changed(ObservableValue<? extends String> o, String s1, String s2) {
            if (s2.equals(Factory.getLoad())) {
                    home.setDisable(true);
                    search.setDisable(true);
                    login.setDisable(true);
            } else {
                    home.setDisable(false);
                    search.setDisable(false);
                    login.setDisable(false);
            }
        }
    };
}
