package controller;

import model.persistence.Encryption;
import controller.util.IControlledScreen;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.*;
import model.exception.NoMatchException;
import model.persistence.entity.User;
import model.persistence.service.UserService;
import view.ScreenContainer;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginController implements Initializable, IControlledScreen {
    private ScreenContainer myContainer;
    private final Session session = Factory.getInstance().getSession();
    private final UserService userService = new UserService();
    private final Encryption encryption = new Encryption();

    @FXML private TextField email;
    @FXML private TextField pwd;
    @FXML private Label errLabel;

    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,6}$");

    @Override
    public void initialize(URL url, ResourceBundle rb) {}

    public void setScreenParent(ScreenContainer screenParent){
        myContainer = screenParent;
    }

    @FXML
    private void login() throws IOException {
        if(isRegistered()) {
            myContainer.setScreen(Factory.getAccount());
        } else {
            pwd.clear();
        }


//        //CONTROLLO FORMATO EMAIL
//        if(checkMail()) {
//            errLabel.setText("");
//            //CONTROLLO SE UTENTE REGISTRATO E PWD CORRETTA
//            if (isRegistered()) {
//                myContainer.setScreen(Factory.getAccount());
//            }
//        }
//        email.clear();
//        pwd.clear();
    }

    @FXML
    private void register() {
        try {
            checkMail();
        } catch (NoMatchException e) {

        }



//        if(checkMail()) {
//            errLabel.setText("");
//            int i = pwd.getText().length();
//            if(i > 3 && i < 13) {
//                if (userService.findByEmail(email.getText()) == null) {
//                    User newUser = new User();
//                    newUser.setEmail(email.getText());
//                    //NO VINCOLI SU PASSWORD
//
//                    String encrypted = encrypt(pwd.getText());
//                    newUser.setPwd(encrypted);
//                    userService.persist(newUser);
//
//                    session.setUser(newUser);
//                    myContainer.setScreen(Factory.getAccount());
//                } else {
//                    Alert alert = new Alert(Alert.AlertType.ERROR);
//                    alert.setTitle("Invalid Input");
//                    alert.setHeaderText(null);
//                    alert.setContentText("Utente già esistente!");
//                    alert.showAndWait();
//                }
//            } else {
//                errLabel.setText("Inserire Password da 4-12 Caratteri");
//            }
//        }
//        email.clear();
//        pwd.clear();

    }

    public boolean isRegistered(){
        errLabel.setText("");
        try{
            checkMail();
            User logged = userService.findByEmail(email.getText());
            String decrypted = encryption.decrypt(logged.getPwd());
            if(decrypted.equals(pwd.getText()))  {
                session.setUser(logged);
                return true;
            } else {
                throw new NoMatchException("Not Matched!\n");
            }
        }catch (NoMatchException e){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Validate Fields");
                alert.setHeaderText(null);
                alert.setContentText("Login Errato!\nControllare accessi o Registrarsi");
                alert.showAndWait();
            return false;
        }
    }

    public boolean checkMail() throws NoMatchException {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email.getText());
        if(matcher.find()){
            return true;
        } else {
            errLabel.setText("Formato email non valido!");
            throw new NoMatchException("Not Matched!\n");
        }
    }
}
