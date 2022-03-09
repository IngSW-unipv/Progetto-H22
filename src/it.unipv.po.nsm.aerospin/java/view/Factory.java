package view;

import util.Session;

import java.io.IOException;

//Singleton
public class Factory {

    private static Factory instance = null;
    private final Session session;

    // Hide the contructor
    private Factory() {
        session = new Session();
    }

    // Allow construction only once
    public static Factory getInstance() {
        if(instance == null) {
            instance = new Factory();
        }
        return instance;
    }

    public Session getSession() {
        return session;
    }

    private static String home = "home";
    private static String screen1File = "fxml/Home.fxml";
    private static String search = "search";
    private static String screen2File = "fxml/Search.fxml";
    private static String result = "result";
    private static String screen3File = "fxml/Result.fxml";
    private static String login = "login";
    private static String screen4File = "fxml/Login.fxml";
    private static String account = "account";
    private static String screen5File = "fxml/Account.fxml";
    private static String manage = "manage";
    private static String screen6File = "fxml/Manage.fxml";
    private static String employee = "employee";
    private static String screen7File = "fxml/Employee.fxml";
    private static String aircraft = "aircraft";
    private static String screen9File = "fxml/Aircraft.fxml";
    private static String route = "route";
    private static String screen10File = "fxml/Route.fxml";
    private static String flight = "flight";
    private static String screen11File = "fxml/Flight.fxml";

    public ScreensController createContainer() throws IOException {

        ScreensController mainContainer = new ScreensController();
        mainContainer.loadScreen(home, screen1File);
        mainContainer.loadScreen(search, screen2File);
        mainContainer.loadScreen(result, screen3File);
        mainContainer.loadScreen(login, screen4File);
        // mainContainer.loadScreen(account, screen5File);
        mainContainer.loadScreen(manage, screen6File);
        mainContainer.loadScreen(employee, screen7File);
        mainContainer.loadScreen(aircraft, screen9File);
        mainContainer.loadScreen(route, screen10File);
        mainContainer.loadScreen(flight, screen11File);
        mainContainer.setScreen(home);

        return mainContainer;
    }

    public static String getHome() {
        return home;
    }

    public static String getSearch() {
        return search;
    }

    public static String getResult() {
        return result;
    }

    public static String getLogin() {
        return login;
    }

    public static String getAccount() {
        return account;
    }

    public static String getManage() {
        return manage;
    }

    public static String getEmployee() {
        return employee;
    }

    public static String getAircraft() {
        return aircraft;
    }

    public static String getRoute() {
        return route;
    }

    public static String getFlight() {
        return flight;
    }

}
