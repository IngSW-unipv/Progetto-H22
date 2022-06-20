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

/**
 * Classe Controller, relativa al Pattern MVC, che si occupa di gestire la logica dell'applicativo e le richieste del cliente.
 * Classe contenente l'interazione con JavaFX.
 *
 * @author GruppoNoSuchMethod
 */
public class MainController implements Initializable {
    private final ScreenContainer myContainer = Factory.getInstance().createContainer();
    private final Session session = Factory.getInstance().getSession();

    @FXML private SubScene subscene;
    @FXML private JFXButton home;
    @FXML private JFXButton search;
    @FXML private JFXButton login;
    @FXML private JFXButton logout;

    /**
     * Metodo che si occupa di gestire le sessioni dell'interfaccia grafica.
     *
     * @param url URL della risorsa.
     * @param rb Oggetto locale.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Group root = new Group();
        root.getChildren().addAll(myContainer);
        subscene.setRoot(root);

        login.textProperty().bind(session.getLoggedButton());
        logout.visibleProperty().bind(session.loggedProperty());
        myContainer.getScreen().addListener(loadListener);
    }

    /**
     * Metodo che si occupa del ritorno alla schermata home dell'interfaccia grafica.
     *
     * @throws IOException Segnala che si è verificato un errore durante le operazioni di I/O.
     */
    @FXML
    private void goToHome() throws IOException {
        myContainer.setScreen(Factory.getHome());
    }

    /**
     * Metodo che si occupa del ritorno alla schermata di ricerca dell'interfaccia grafica.
     *
     * @throws IOException Segnala che si è verificato un errore durante le operazioni di I/O.
     */
    @FXML
    private void goToSearch() throws IOException {
        myContainer.setScreen(Factory.getSearch());
    }

    /**
     * Metodo che si occupa del ritorno alla schermata di login dell'interfaccia grafica.
     *
     * @throws IOException Segnala che si è verificato un errore durante le operazioni di I/O.
     */
    @FXML
    private void goToLogin() throws IOException {
        if(session.isLogged()) {
            myContainer.setScreen(Factory.getAccount());
        } else {
            myContainer.setScreen(Factory.getLogin());
        }
    }

    /**
     * Metodo che si occupa del logout utente nell'interfaccia grafica.
     *
     * @throws IOException Segnala che si è verificato un errore durante le operazioni di I/O.
     */
    @FXML
    private void logout() throws IOException {
        //cambia stato come non loggato
        session.setLogged(false);
        myContainer.setScreen(Factory.getHome());
    }

    //commentare
    /**
     * Costruttore dell'oggetto Listener, con relativo aggiornamento al successivo oggetto Observable.
     */
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
