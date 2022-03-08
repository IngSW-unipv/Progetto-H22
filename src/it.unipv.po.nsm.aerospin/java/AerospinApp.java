import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.util.Objects;

public class AerospinApp extends Application {

    @Override
    public void start(Stage stage) throws IOException {

//        ScreensController mainContainer = factory.createContainer();
//        Group root = new Group();
//        root.getChildren().addAll(mainContainer);
//        Scene scene = new Scene(root);
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("view/fxml/Main.fxml")));
        stage.setTitle("Aerospin");
        stage.setScene(new Scene(root));
        stage.getIcons().add(new Image("file:src/it.unipv.po.nsm.aerospin/resources/img/icon.png"));
        stage.setResizable(false);
        stage.show();
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}

probva