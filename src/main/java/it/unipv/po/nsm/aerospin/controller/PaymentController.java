package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Factory;
import model.booking.payment.IPaymentStrategy;
import model.booking.payment.PaymentFactory;
import model.exception.PaymentException;
import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;

/**
 * Classe Controller, relativa al Pattern MVC, che si occupa di gestire la logica dell'applicativo e le richieste del cliente.
 * Classe contenente l'interazione con JavaFX.
 *
 * @author GruppoNoSuchMethod
 */
public class PaymentController implements Initializable {
    private final Session session = Factory.getInstance().getSession();

    @FXML private TextField cardNumber;
    @FXML private TextField cardName;
    @FXML private TextField expiryMonth;
    @FXML private TextField expiryYear;
    @FXML private TextField cvv;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {}

    /**
     * Metodo che si occupa di gestire le operazioni dell'interfaccia grafica di pagamento.
     */
    @FXML
    private void execute() {
        Stage stage = (Stage) Stage.getWindows().get(1);
        try {
            checkCardNumber();
            checkCardName();
            checkExpiryMonth();
            checkExpiryYear();
            checkCvv();

            PaymentFactory paymentFactory = new PaymentFactory();
            IPaymentStrategy payment = paymentFactory.getPaymentStrategy();

            if (payment.pay(info.getPrice())){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Payment");
                    alert.setContentText("Pagamento andato a buon fine!");
                    alert.showAndWait();
                    info.setPaid(true);
                    info.setCardNumber(cardNumber.getText().substring(12,15));
                    stage.close();
            }
        } catch (IllegalArgumentException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Wrong Input");
                alert.setContentText("Controllare i dati inseriti e riprovare");
                alert.showAndWait();
        } catch (PaymentException e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Payment");
                alert.setContentText("Il pagamento non è andato a buon fine...riprovare");
                alert.showAndWait();
        }
    }

    /**
     * Metodo che si occupa di verificare se il numero della carta di pagamento è valido.
     *
     * @throws IllegalArgumentException Segnala un errore nelle informazioni fornite dal cliente.
     */
    private void checkCardNumber() throws IllegalArgumentException {
        if(!cardNumber.getText().matches("^[0-9]{16}$")) {
                throw new IllegalArgumentException();
        }
    }

    /**
     * Metodo che si occupa di verificare se l'intestatario della carta di pagamento è valido.
     *
     * @throws IllegalArgumentException Segnala un errore nelle informazioni fornite dal cliente.
     */
    private void checkCardName() throws IllegalArgumentException {
        if(!cardName.getText().matches("^[a-zA-Z ]+$")) {
                throw new IllegalArgumentException();
        }
    }

    /**
     * Metodo che si occupa di verificare se il mese di scadenza della carta di pagamento è valido.
     *
     * @throws IllegalArgumentException Segnala un errore nelle informazioni fornite dal cliente.
     */
    private void checkExpiryMonth() throws IllegalArgumentException {
        if(!expiryMonth.getText().matches("^[0-9]{1,2}$") || isExpired()) {
                throw new IllegalArgumentException();
        }
    }

    /**
     * Metodo che si occupa di verificare se l'anno di scadenza della carta di pagamento è valido.
     *
     * @throws IllegalArgumentException Segnala un errore nelle informazioni fornite dal cliente.
     */
    private void checkExpiryYear() throws IllegalArgumentException {
        if (!expiryYear.getText().matches("^[0-9]{4}$") || isExpired()) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Metodo che si occupa di verificare se il codice CVV della carta di pagamento è valido.
     *
     * @throws IllegalArgumentException Segnala un errore nelle informazioni fornite dal cliente.
     */
    private void checkCvv() throws IllegalArgumentException {
        if(!cvv.getText().matches("^[0-9]{3}$")) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Metodo che si occupa di verificare se la carta di pagamento è in corso di validità.
     *
     * @return true se la carta di pagamento è in corso di validità, false altrimenti.
     */
    private boolean isExpired() {
        int thisYear = Calendar.getInstance().get(Calendar.YEAR);
        int thisMonth = Calendar.getInstance().get(Calendar.MONTH);
        int yearEx = Integer.parseInt(expiryYear.getText());
        int monthEx = Integer.parseInt(expiryMonth.getText());
        if((yearEx - thisYear) > 0 && (yearEx - thisYear) < 7) {
                return monthEx < 1 || monthEx > 12;
        } else if(yearEx == thisYear){
                return monthEx < 1 || monthEx > 12 || monthEx <= thisMonth + 1;
        } else {
                return true;
        }
    }

    /**
     * Metodo che si occupa della chiusura dell'interfaccia grafica di pagamento.
     */
    @FXML
    private void cancel(){
        Stage stage = (Stage) Stage.getWindows().get(1);
        stage.close();
    }
}
