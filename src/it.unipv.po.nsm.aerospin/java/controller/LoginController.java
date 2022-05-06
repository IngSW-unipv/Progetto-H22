package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.persistence.CachedFlights;
import model.persistence.entity.User;
import model.persistence.service.UserService;
import model.util.ControllerMethods;
import model.util.exception.NoMatchException;
import model.util.Session;
import model.Factory;
import view.ScreenContainer;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginController implements Initializable, IControlledScreen {

    Factory factory = Factory.getInstance();
    ScreenContainer myContainer;
    Session session = factory.getSession();
    CachedFlights searchResult = CachedFlights.getInstance();
    ControllerMethods methods = new ControllerMethods();
    UserService userService = new UserService();

    @FXML private TextField email;
    @FXML private TextField pwd;

    @FXML private Label errLabel;

    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //CONTROLLARE CHE SIA CACHED!!
        //POCO SICURO, CONTROLLO INPUT EMAIL
//        Thread t1 = new Thread(()->{
//            users = userService.findAll();
//        });
//        t1.start();

    }

    public void setScreenParent(ScreenContainer screenParent){
        myContainer = screenParent;
    }

    @FXML
    private void login(ActionEvent event) throws IOException {
        //AGGIUNGERE THREAD, CONTROLLARE
        //disabilitare campi e button

        //CONTROLLO FORMATO EMAIL
        if(checkMail()) {
            errLabel.setText("");
            //CONTROLLO SE UTENTE REGISTRATO E PWD CORRETTA
            if (isRegistered()) {
                //CONTROLLO SE ADMIN O USER
                if(session.getUser().isAdmin()){
//                    myContainer.setScreen(Factory.getManage());
                } else {
                    myContainer.setScreen(Factory.getAccount());
                }
            }
        }
        email.clear();
        pwd.clear();
    }

    @FXML
    private void register(ActionEvent event) throws IOException {

        if(checkMail()) {
            errLabel.setText("");
            if(userService.findByEmail(email.getText()) == null) {
                User newUser = new User();
                newUser.setEmail(email.getText());
                //NO VINCOLI SU PASSWORD
                newUser.setPwd(pwd.getText());

                userService.persist(newUser);

                session.setUser(newUser);
                myContainer.setScreen(Factory.getAccount());
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid Input");
                alert.setHeaderText(null);
                alert.setContentText("Utente già esistente!");
                alert.showAndWait();
            }
        }

    }

    public boolean checkMail(){
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email.getText());
        if(matcher.find()){
            return true;
        } else {
            errLabel.setText("Formato email non valido!");
            return false;
        }
    }

    public boolean isRegistered(){
        User logged;

        //CONTROLLARE CHE SIA NOT CACHED
        try{
            logged = userService.findByEmail(email.getText());
            if(logged.getPwd().equals(pwd.getText()))  {
                session.setUser(logged);
                return true;
            } else {
                throw new NoMatchException("Not Matched!\n");
            }
        }catch (IndexOutOfBoundsException | NoMatchException e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate Fields");
            alert.setHeaderText(null);
            alert.setContentText("Login Errato!\nControllare accessi o Registrarsi");
            alert.showAndWait();
            return false;
        }
    }

}


