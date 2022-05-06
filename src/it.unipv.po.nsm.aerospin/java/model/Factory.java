package model;

import model.util.Session;
import view.ScreenContainer;

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

    private static String load = "load";
    private static String screen0File = "fxml/Load.fxml";
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


    public ScreenContainer createContainer() throws IOException {

        ScreenContainer mainContainer = new ScreenContainer();
        mainContainer.loadScreen(load, screen0File);
        mainContainer.loadScreen(home, screen1File);
        mainContainer.loadScreen(search, screen2File);
        mainContainer.loadScreen(result, screen3File);
        mainContainer.loadScreen(login, screen4File);
        mainContainer.loadScreen(account, screen5File);
        mainContainer.setScreen(load);

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

}
