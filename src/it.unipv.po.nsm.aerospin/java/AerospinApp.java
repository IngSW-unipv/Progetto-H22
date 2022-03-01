import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import view.*;

public class AerospinApp extends Application {

    Factory factory = new Factory();




    @Override
    public void start(Stage stage) {

        ScreensController mainContainer = factory.createContainer();
        Group root = new Group();
        root.getChildren().addAll(mainContainer);
        Scene scene = new Scene(root);
        stage.setTitle("Aerospin");
        stage.setScene(scene);
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