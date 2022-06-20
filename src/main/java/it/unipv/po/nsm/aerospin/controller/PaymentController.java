package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Factory;
import model.Session;
import model.booking.payment.AeroPay;
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

    @FXML
    private void execute() {
        Stage stage = (Stage) Stage.getWindows().get(1);
        try {
            checkCardNumber();
            checkCardName();
            checkExpiryMonth();
            checkExpiryYear();
            checkCvv();
            AeroPay paymentMethod = new AeroPay();
            if (paymentMethod.pay()){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Payment");
                    alert.setContentText("Pagamento andato a buon fine!");
                    alert.showAndWait();
                    session.getInfo().setPaid(true);
                    session.getInfo().setCardNumber(cardNumber.getText().substring(12,15));
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

    private void checkCardNumber() throws IllegalArgumentException {
        if(!cardNumber.getText().matches("^[0-9]{16}$")) {
                throw new IllegalArgumentException();
        }
    }

    private void checkCardName() throws IllegalArgumentException {
        if(!cardName.getText().matches("^[a-zA-Z ]+$")) {
                throw new IllegalArgumentException();
        }
    }

    private void checkExpiryMonth() throws IllegalArgumentException {
        if(!expiryMonth.getText().matches("^[0-9]{1,2}$") || isExpired()) {
                throw new IllegalArgumentException();
        }
    }

    private void checkExpiryYear() throws IllegalArgumentException {
        if (!expiryYear.getText().matches("^[0-9]{4}$") || isExpired()) {
            throw new IllegalArgumentException();
        }
    }

    private void checkCvv() throws IllegalArgumentException {
        if(!cvv.getText().matches("^[0-9]{3}$")) {
            throw new IllegalArgumentException();
        }
    }

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

    @FXML
    private void cancel(){
        Stage stage = (Stage) Stage.getWindows().get(1);
        stage.close();
    }
}
