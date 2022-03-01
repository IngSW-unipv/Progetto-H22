package view;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.image.Image;
import util.Session;

public class MainApplication extends Application {

    public static String home = "home";
    public static String screen1File = "fxml/Home.fxml";
    public static String search = "search";
    public static String screen2File = "fxml/Search.fxml";
    public static String result = "result";
    public static String screen3File = "fxml/Result.fxml";
    public static String login = "login";
    public static String screen4File = "fxml/Login.fxml";
    public static String account = "account";
    public static String screen5File = "fxml/Account.fxml";
    public static String manage = "manage";
    public static String screen6File = "fxml/Manage.fxml";
    public static String employee = "employee";
    public static String screen7File = "fxml/Employee.fxml";
    public static String crew = "crew";
    public static String screen8File = "fxml/Crew.fxml";
    public static String aircraft = "aircraft";
    public static String screen9File = "fxml/Aircraft.fxml";
    public static String route = "route";
    public static String screen10File = "fxml/Route.fxml";
    public static String flight = "flight";
    public static String screen11File = "fxml/Flight.fxml";

    @Override
    public void start(Stage stage) {

        ScreensController mainContainer = new ScreensController();

        mainContainer.loadScreen(MainApplication.home, MainApplication.screen1File);
        mainContainer.loadScreen(MainApplication.search, MainApplication.screen2File);
        mainContainer.loadScreen(MainApplication.result, MainApplication.screen3File);
        mainContainer.loadScreen(MainApplication.login, MainApplication.screen4File);
       // mainContainer.loadScreen(MainApplication.account, MainApplication.screen5File);
        mainContainer.loadScreen(MainApplication.manage, MainApplication.screen6File);
        mainContainer.loadScreen(MainApplication.employee, MainApplication.screen7File);
        mainContainer.loadScreen(MainApplication.crew, MainApplication.screen8File);
        mainContainer.loadScreen(MainApplication.aircraft, MainApplication.screen9File);
        mainContainer.loadScreen(MainApplication.route, MainApplication.screen10File);
        mainContainer.loadScreen(MainApplication.flight, MainApplication.screen11File);

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