package view;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.image.Image;

public class MainApplication extends Application {

    public static String home = "home";
    public static String screen1File = "fxml/Home.fxml";
    public static String search = "search";
    public static String screen2File = "fxml/Search.fxml";
    public static String login = "login";
    public static String screen3File = "fxml/Login.fxml";
    public static String account = "accountC";
    public static String screen4File = "fxml/Account.fxml";
    public static String manage = "management";
    public static String screen5File = "fxml/Manage.fxml";

    @Override
    public void start(Stage stage) {

        ScreensController mainContainer = new ScreensController();
        mainContainer.loadScreen(MainApplication.home, MainApplication.screen1File);
        mainContainer.loadScreen(MainApplication.search, MainApplication.screen2File);
        mainContainer.loadScreen(MainApplication.login, MainApplication.screen3File);
        mainContainer.loadScreen(MainApplication.account, MainApplication.screen4File);
        mainContainer.loadScreen(MainApplication.manage, MainApplication.screen5File);


        mainContainer.setScreen(MainApplication.home);

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