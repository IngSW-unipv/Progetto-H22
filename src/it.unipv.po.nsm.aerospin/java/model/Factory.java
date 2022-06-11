package model;

import view.ScreenContainer;

import java.io.IOException;

//Singleton
/**
 * Classe Singleton che si occupa della grafica tramite il pattern Factory.
 *
 * @author GruppoNoSuchMethod
 */
public class Factory {

    private static Factory instance = null;
    private final Session session;

    // Hide the contructor
    private Factory() {
        session = new Session();
    }

    // Allow construction only once
    /**
     * Metodo che verifica la presenza di un solo costruttore, creandone uno se non ancora presente o restituendolo se già presente.
     *
     * @return instance
     */
    public static Factory getInstance() {
        if(instance == null) {
            instance = new Factory();
        }
        return instance;
    }

    public Session getSession() {
        return session;
    }

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
