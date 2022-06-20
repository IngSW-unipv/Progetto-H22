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
 * Classe Controller, relativa al Pattern MVC, che si occupa di gestire la logica dell'applicativo e le richieste del cliente.
 * Classe contenente l'interazione con JavaFX.
 *
 * @author GruppoNoSuchMethod
 */
public class LoginController implements Initializable, IControlledScreen {
    private ScreenContainer myContainer;
    private final Session session = Factory.getInstance().getSession();
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
     * Metodo che verifica il login di un account.
     *
     * @throws IOException Segnala che si è verificato un errore durante le operazioni di I/O.
     */
    @FXML
    private void login() throws IOException {
        if(isRegistered()) {
            myContainer.setScreen(Factory.getAccount());
        } else {
            pwd.clear();
        }
    }

    /**
     * Metodo che si occupa della registrazione di un utente, verificando se è gia registrato.
     */
    @FXML
    private void register() {
        errLabel.setText("");
        try {
                checkMail();
                checkPwd();
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
     * Metodo che si occupa di gestire le operazioni dell'interfaccia grafica di supporto.
     *
     * @throws IOException Segnala che si è verificato un errore durante le operazioni di I/O.
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

    //CONTROLLO SE UTENTE REGISTRATO E PWD CORRETTA
    /**
     * Metodo che si occupa di verificare se l'utente è registrato e la password corrispondente è corretta.
     *
     * @return true se accesso eseguito correttamente, false altrimenti.
     */
    private boolean isRegistered() {
        errLabel.setText("");
        try{
                checkMail();
                try {
                        User logged = service.findByEmail(email.getText());
                        String decrypted = encryption.decrypt(logged.getPwd());
                        if (decrypted.equals(pwd.getText())) {
                                session.setUser(logged);
                                return true;
                        } else {
                                throw new NoMatchException("Not Matched!\n");
                        }
                } catch (NoMatchException e) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Wrong Login");
                        alert.setHeaderText("Controllare accessi o Registrarsi");
                        alert.showAndWait();
                        return false;
                }
        } catch (NoMatchException e){
                pwd.clear();
                return false;
        }
    }

    //CONTROLLO FORMATO EMAIL
    /**
     * Metodo che verifica il formato dell'e-mail inserita.
     *
     * @throws NoMatchException Segnala se il confronto non è andato a buon fine.
     */
    private void checkMail() throws NoMatchException {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email.getText());
        if(!matcher.find()){
                errLabel.setText("Formato email non valido!");
                throw new NoMatchException("Not Matched!\n");
        }
    }

    //CONTROLLO FORMATO PWD
    /**
     * Metodo che verifica il formato della password inserita.
     *
     * @throws NoMatchException Segnala se il confronto non è andato a buon fine.
     */
    private void checkPwd() throws NoMatchException {
        Matcher matcher = VALID_PWD_REGEX.matcher(pwd.getText());
        if(!matcher.find()){
            errLabel.setText("Inserire Password da 8-20 caratteri");
            throw new NoMatchException("Not Matched!\n");
        }
    }
}
