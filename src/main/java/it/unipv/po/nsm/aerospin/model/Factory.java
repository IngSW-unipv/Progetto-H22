package model;

import view.ScreenContainer;
import java.io.IOException;

/**
 * Classe Singleton che si occupa di istanziare la sessione e lo ScreenContainer
 *
 * @author GruppoNoSuchMethod
 */
public class Factory {
    private static Factory instance = null;
    private static final String load = "load";
    private static final String screen0File = "fxml/Load.fxml";
    private static final String home = "home";
    private static final String screen1File = "fxml/Home.fxml";
    private static final String search = "search";
    private static final String screen2File = "fxml/Search.fxml";
    private static final String result = "result";
    private static final String screen3File = "fxml/Result.fxml";
    private static final String login = "login";
    private static final String screen4File = "fxml/Login.fxml";
    private static final String account = "account";
    private static final String screen5File = "fxml/Account.fxml";

    private Factory() {
        Session.getInstance();
    }

    public static Factory getInstance() {
        if(instance == null) {
            instance = new Factory();
        }
        return instance;
    }

    /**
     * Metodo che istanzia lo ScreenContainer contenente le schermate dell'applicazione
     *
     * @return ScreenContainer
     */
    public ScreenContainer createContainer() {
        ScreenContainer mainContainer = new ScreenContainer();
        mainContainer.loadScreen(load, screen0File);
        mainContainer.loadScreen(home, screen1File);
        mainContainer.loadScreen(search, screen2File);
        mainContainer.loadScreen(result, screen3File);
        mainContainer.loadScreen(login, screen4File);
        mainContainer.loadScreen(account, screen5File);
        try {
            mainContainer.setScreen(load);
            return mainContainer;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getLoad() {
        return load;
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
