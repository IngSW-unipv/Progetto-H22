package view;

import controller.util.IControlledScreen;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import java.io.IOException;
import java.util.HashMap;

/**
 * Classe container che raccoglie i vari Screen in uno stack
 *
 * @author GruppoNoSuchMethod
 */
public class ScreenContainer extends StackPane {
    //Contiene gli screen da mostrare
    /**
     * Costruttore della struttura dati che contiene le schermate dell'applicativo.
     */
    private final HashMap<String, String> screens = new HashMap<>();
    private final SimpleStringProperty actualScreen = new SimpleStringProperty();


    //Ritorna il nome dello Screen attuale
    /**
     * Metodo che permette di ottenere lo Screen attuale
     *
     * @return Screen attuale
     */
    public StringProperty getScreen() {
        return actualScreen;
    }

    //Carica il file fxml, aggiunge lo screen alla collection e
    //infine recupero il controller.
    /**
     * Metodo che carica nella HashMap il nome dello Screen e il suo path
     *
     * @param name Nome schermata.
     * @param resource Indirizzo di memoria.
     */
    public void loadScreen(String name, String path) {
        try {
            screens.put(name, path);
        } catch (Exception e) {
                e.printStackTrace();
        }
    }

    /**
     * Metodo per mostrare lo Screen richiamato.
     * Se è già presente uno Screen, viene sostituito
     * Altrimenti viene caricato direttamente
     *
     * @param name Screen name
     * @throws IOException Segnala che si è verificato un errore nel caricamento dello Screen
     */
    public void setScreen(final String name) throws IOException {
        if (screens.get(name) != null) {   //screen caricato
            actualScreen.set(name);
            FXMLLoader myLoader = new FXMLLoader(getClass().getResource(screens.get(name)));
            Parent loadScreen = myLoader.load();
            IControlledScreen myScreenController = myLoader.getController();
            myScreenController.setScreenParent(this);
            final DoubleProperty opacity = opacityProperty();
            if (!getChildren().isEmpty()) {    //se lo screen ha un "parent"
                Timeline fade = new Timeline(
                        new KeyFrame(Duration.ZERO, new KeyValue(opacity, 1.0)),
                        new KeyFrame(new Duration(100), t -> {
                            getChildren().remove(0);                    //rimuovo screen attuale
                            getChildren().add(0, loadScreen);     //aggiungo nuovo screen
                            Timeline fadeIn = new Timeline(
                                    new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)),
                                    new KeyFrame(new Duration(200), new KeyValue(opacity, 1.0)));
                            fadeIn.play();
                        }, new KeyValue(opacity, 0.0)));
                fade.play();
            } else {
                setOpacity(0.0);
                getChildren().add(loadScreen);       //primo screen, semplicemente carica
                Timeline fadeIn = new Timeline(
                        new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)),
                        new KeyFrame(new Duration(1000), new KeyValue(opacity, 1.0)));
                fadeIn.play();
            }
        } else {
            System.out.println("Screen non caricato!!\n");
        }
    }
}
