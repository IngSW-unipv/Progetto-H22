package view;

import util.Session;

public class Factory {

    public Session session;

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

    public ScreensController createContainer() {

        ScreensController mainContainer = new ScreensController();
        mainContainer.loadScreen(home, screen1File);
        mainContainer.loadScreen(search, screen2File);
        mainContainer.loadScreen(result, screen3File);
        mainContainer.loadScreen(login, screen4File);
        // mainContainer.loadScreen(account, screen5File);
        mainContainer.loadScreen(manage, screen6File);
        mainContainer.loadScreen(employee, screen7File);
        mainContainer.loadScreen(crew, screen8File);
        mainContainer.loadScreen(aircraft, screen9File);
        mainContainer.loadScreen(route, screen10File);
        mainContainer.loadScreen(flight, screen11File);
        mainContainer.setScreen(home);

        return mainContainer;
    }
}
