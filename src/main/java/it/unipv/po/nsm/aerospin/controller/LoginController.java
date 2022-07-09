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

    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,6}$");
    private static final Pattern VALID_PWD_REGEX =
            Pattern.compile("^[0-9a-zA-Z!@#&–:;',?/*~$^+=<>]{8,20}$");

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
        String[] screens = { Factory.getAccount(), Factory.getAdmin() };

        if(user != null) {
            String screen = null;
            Integer userType = user.getUserType();
            if (userType > screens.length - 1) screen = Factory.getAccount(); // TODO: Inve farà un eccezione qui
            else screen = screens[userType];

            myContainer.setScreen(screen);
        } else {
            pwd.clear();
        }
    }

    /**
     * Metodo che la registrazione di un nuovo ytente, dopo averne verificato le credenziali
     */
    @FXML
    private void register() {
        errLabel.setText("");
        try {
                checkMail(email.getText());
                checkPwd(pwd.getText());
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
                        service.persist(newUser);
                        session.setUser(newUser);
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Register Confirmation");
                        alert.setHeaderText("Abbiamo completato la sua registrazione!");
                        alert.setContentText("Potrà ora usare la sua area Account");
                        alert.showAndWait();
                        myContainer.setScreen(Factory.getAccount());
                }
        } catch (NoMatchException | IOException e) {
            pwd.clear();
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
                checkMail(email.getText());
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
                return null;
        }
    }

    /**
     * Metodo che verifica il formato dell'e-mail inserita
     *
     * @throws NoMatchException Segnala se il confronto non è andato a buon fine
     */
    private void checkMail(String text) throws NoMatchException {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(text);
        if(!matcher.find()){
                errLabel.setText("Formato email non valido!");
                throw new NoMatchException("Not Matched!\n");
        }
    }

    /**
     * Metodo che verifica il formato della password inserita
     *
     * @throws NoMatchException Segnala se il confronto non è andato a buon fine
     */
    private void checkPwd(String text) throws NoMatchException {
        Matcher matcher = VALID_PWD_REGEX.matcher(text);
        if(!matcher.find()){
            errLabel.setText("Inserire Password da 8-20 caratteri");
            throw new NoMatchException("Not Matched!\n");
        }
    }
}
