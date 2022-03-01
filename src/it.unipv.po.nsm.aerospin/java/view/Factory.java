package view;

public class Factory {

    public static String home = "home";
    public static String screen1File = "src/it.unipv.po.nsm.aerospin/java/view/fxml/Home.fxml";
    public static String search = "search";
    public static String screen2File = "view/fxml/Search.fxml";
    public static String result = "result";
    public static String screen3File = "view/fxml/Result.fxml";
    public static String login = "login";
    public static String screen4File = "view/fxml/Login.fxml";
    public static String account = "account";
    public static String screen5File = "view/fxml/Account.fxml";
    public static String manage = "manage";
    public static String screen6File = "view/fxml/Manage.fxml";
    public static String employee = "employee";
    public static String screen7File = "view/fxml/Employee.fxml";
    public static String crew = "crew";
    public static String screen8File = "view/fxml/Crew.fxml";
    public static String aircraft = "aircraft";
    public static String screen9File = "view/fxml/Aircraft.fxml";
    public static String route = "route";
    public static String screen10File = "view/fxml/Route.fxml";
    public static String flight = "flight";
    public static String screen11File = "view/fxml/Flight.fxml";

//    private Application mainApplication;

//    public Factory(Application mainApplication) {
//        this.mainApplication = mainApplication;
//
//    };

    public ScreensController getContainer() {

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
