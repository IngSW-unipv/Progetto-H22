package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.persistence.Encryption;
import controller.util.IControlledScreen;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Factory;
import model.Session;
import model.exception.NoMatchException;
import model.persistence.entity.User;
import model.persistence.service.UserService;
import view.ScreenContainer;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import controller.util.manager.UserManager;

/**
 * Controller dello screen Login
 *
 * @author GruppoNoSuchMethod
 */
public class LoginController implements Initializable, IControlledScreen {
    private ScreenContainer myContainer;
    private final Session session = Session.getInstance();
    private final UserService service = new UserService();
    private final Encryption encryption = new Encryption();

    @FXML private TextField email;
    @FXML private TextField pwd;
    @FXML private Label errLabel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {}

    public void setScreenParent(ScreenContainer screenParent){
        myContainer = screenParent;
    }

    /**
     * Metodo che effettua il login dell'utente
     *
     * @throws IOException Segnala che si è verificato un errore durante le operazioni di I/O
     */
    @FXML
    private void login() throws IOException {
        User user = getRegisteredUser();
        String[] screens = { Factory.getAccount(), Factory.getAdmin(), Factory.getFlightMan()};

        if(user != null) {
            String screen = null;
            Integer userType = user.getUserType();
            if (userType > screens.length - 1) screen = Factory.getAccount();
            else screen = screens[userType];

            myContainer.setScreen(screen);
        } else {
            pwd.clear();
        }
    }

    /**
     * Metodo che la registrazione di un nuovo utente, dopo averne verificato le credenziali
     */
    @FXML
    private void register() {
        errLabel.setText("");
        try {
            UserManager.checkMail(email.getText());
                try {
                    UserManager.checkPwd(pwd.getText());
                    try {
                        service.findByEmail(email.getText());
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Invalid Input");
                        alert.setHeaderText("Utente già esistente");
                        alert.showAndWait();
                    } catch (NoMatchException e) {
                        User newUser = new User();
                        newUser.setEmail(email.getText());
                        newUser.setPwd(encryption.encrypt(pwd.getText()));
                        newUser.setUserType(0);
                        service.persist(newUser);
                        session.setUser(newUser);
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Register Confirmation");
                        alert.setHeaderText("Abbiamo completato la sua registrazione!");
                        alert.setContentText("Potrà ora usare la sua area Account");
                        alert.showAndWait();
                        myContainer.setScreen(Factory.getAccount());
                    }
                } catch (NoMatchException e){
                    pwd.clear();
                    errLabel.setText("Inserire Password da 8-20 caratteri");
                }
        } catch (NoMatchException | IOException e) {
            pwd.clear();
            errLabel.setText("Formato email non valido!");
        }
    }

    /**
     * Metodo che si occupa di caricare la schermata Support
     *
     * @throws IOException Segnala che si è verificato un errore durante le operazioni di I/O
     */
    @FXML
    private void support() throws IOException {
        Parent root1 = FXMLLoader.load(
                Objects.requireNonNull(getClass().getResource("util/subscreen/Support.fxml")));
        Stage childStage = new Stage();
        childStage.initModality(Modality.APPLICATION_MODAL);
        childStage.initStyle(StageStyle.TRANSPARENT);
        childStage.setScene(new Scene(root1));
        childStage.showAndWait();
    }

    /**
     * Metodo che si occupa di verificare se l'utente è registrato e la password inserita è corretta
     *
     * @return true se accesso eseguito correttamente, false altrimenti
     */
    private User getRegisteredUser() {
        errLabel.setText("");
        try{
                UserManager.checkMail(email.getText());
                try {
                        User logged = service.findByEmail(email.getText());
                        String decrypted = encryption.decrypt(logged.getPwd());
                        if (decrypted.equals(pwd.getText())) {
                                session.setUser(logged);
                                return logged;
                        } else {
                                throw new NoMatchException("Not Matched!\n");
                        }
                } catch (NoMatchException e) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Wrong Login");
                        alert.setHeaderText("Controllare accessi o Registrarsi");
                        alert.showAndWait();
                        return null;
                }
        } catch (NoMatchException e){
                pwd.clear();
                errLabel.setText("Formato email non valido!");
                return null;
        }
    }


}
