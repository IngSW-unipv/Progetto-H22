package view;

import java.util.HashMap;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import controller.IControlledScreen;

public class ScreensController  extends StackPane {

    //Contiene gli screen da mostrare
    private HashMap<String, Node> screens = new HashMap<>();

    public ScreensController() {
        super();
    }

    //Aggiunge lo screen allo Stack
    public void addScreen(String name, Node screen) {
        screens.put(name, screen);
    }

    //Ritorna il Node con il determinato nome
    public Node getScreen(String name) {
        return screens.get(name);
    }

    //Carica il file fxml, aggiunge lo screen alla collection e
    //infine recupero il controller.
    public boolean loadScreen(String name, String resource) {
        try {
            FXMLLoader myLoader = new FXMLLoader(getClass().getResource(resource));
            Parent loadScreen = (Parent) myLoader.load();
            IControlledScreen myScreenController = ((IControlledScreen) myLoader.getController());
            myScreenController.setScreenParent(this);
            addScreen(name, loadScreen);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    //Questo metodo mostra lo screen con un determinato nome.
    //Prima controllo se ho uno screen caricato.
    //In caso positivo rimuovo lo screen attuale e carico il nuovo;
    //altrimenti carico direttamente lo screen.
    public boolean setScreen(final String name) {
        if (screens.get(name) != null) {   //screen caricato
            final DoubleProperty opacity = opacityProperty();

            if (!getChildren().isEmpty()) {    //se lo screen ha un "parent"
                Timeline fade = new Timeline(
                        new KeyFrame(Duration.ZERO, new KeyValue(opacity, 1.0)),
                        new KeyFrame(new Duration(100), new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent t) {
                                getChildren().remove(0);                    //rimuovo screen attuale
                                getChildren().add(0, screens.get(name));     //aggiungo nuovo screen
                                Timeline fadeIn = new Timeline(
                                        new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)),
                                        new KeyFrame(new Duration(200), new KeyValue(opacity, 1.0)));
                                fadeIn.play();
                            }
                        }, new KeyValue(opacity, 0.0)));
                fade.play();
            } else {
                setOpacity(0.0);
                getChildren().add(screens.get(name));       //primo screen, semplicemente carica
                Timeline fadeIn = new Timeline(
                        new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)),
                        new KeyFrame(new Duration(1000), new KeyValue(opacity, 1.0)));
                fadeIn.play();
            }
            return true;
        } else {
            System.out.println("Screen non caricato!!\n");
            return false;
        }
    }

    //Rimuovo lo screen con un determinato nome dallo Stack
    public boolean unloadScreen(String name) {
        if (screens.remove(name) == null) {
            System.out.println("Screen non esiste!!");
            return false;
        } else {
            return true;
        }
    }
}
