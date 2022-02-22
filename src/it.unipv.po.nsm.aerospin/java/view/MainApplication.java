package view;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.image.Image;

public class MainApplication extends Application {

    public static String screen1ID = "main";
    public static String screen1File = "fxml/SHome.fxml";
    public static String screen2ID = "screen2";
    public static String screen2File = "fxml/SSearch.fxml";
    public static String screen3ID = "screen3";
    public static String screen3File = "fxml/SLogin.fxml";


    @Override
    public void start(Stage stage) {

        ScreensController mainContainer = new ScreensController();
        mainContainer.loadScreen(MainApplication.screen1ID, MainApplication.screen1File);
        mainContainer.loadScreen(MainApplication.screen2ID, MainApplication.screen2File);
        mainContainer.loadScreen(MainApplication.screen3ID, MainApplication.screen3File);

        mainContainer.setScreen(MainApplication.screen1ID);

        Group root = new Group();
        root.getChildren().addAll(mainContainer);
        Scene scene = new Scene(root);
        stage.setTitle("Aerospin");
        stage.setScene(scene);
        stage.getIcons().add(new Image("file:src/it.unipv.po.nsm.aerospin/resources/img/icon.png"));
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}