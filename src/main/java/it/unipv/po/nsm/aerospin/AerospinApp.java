
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

/**
 * Classe relativa al main del progetto, utilizzata per il lancio dell'applicativo.
 *
 * @author GruppoNoSuchMethod
 */
public class AerospinApp extends Application {

    /**
     * Metodo che si occupa dell'avvio dell'applicativo.
     *
     * @param stage Finestra grafica che contiene l'applicativo.
     * @throws IOException Segnala che si è verificato un errore durante le operazioni di I/O.
     */
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("view/fxml/Main.fxml")));
        stage.setTitle("Aerospin");
        stage.setScene(new Scene(root));
        stage.getIcons().add(new Image("img/icon.png"));
        stage.setResizable(false);
        stage.show();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Closing");
        alert.setContentText("Grazie e a presto!");
        stage.setOnCloseRequest(we -> System.exit(0));
        stage.setOnCloseRequest(we -> alert.showAndWait());
    }

    public static void main(String[] args) {
        launch(args);
    }
}