package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Factory;
import model.Session;
import model.booking.payment.AeroPay;

import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;

public class PaymentController implements Initializable {

    @FXML private ChoiceBox<String> expiryMonth;
    @FXML private TextField expiryYear;
    @FXML private TextField cardName;
    @FXML private TextField cardNumber;
    @FXML private TextField cvv;

    private String[] months = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};



    Session session = Factory.getInstance().getSession();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (String month : months) {
            expiryMonth.getItems().add(month);
        }

    }

    @FXML
    private void execute(){
        Stage stage = (Stage) Stage.getWindows().get(1);


        if (!checkCardNumber() || !checkExpiryYear() || !checkExpiryMonth() || !checkCvv() || !checkCardName()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errore");
            alert.setContentText("Controllare i dati inseriti e riprovare");
            alert.showAndWait();
        }else{
            AeroPay paymentMethod = new AeroPay(cardName.getText(), cardNumber.getText(), expiryMonth.getValue(), expiryYear.getText(), cvv.getText());
            paymentMethod.pay();
            if (paymentMethod.isPayed()){
                session.setPaid(true);
                stage.close();
            }

        }


    }

    private boolean checkCardNumber(){
        return cardNumber.getText().matches("^[0-9]{16}$");
    }

    private boolean checkExpiryYear(){
        return expiryYear.getText().matches("^[0-9]{4}$") && Integer.parseInt(expiryYear.getText()) >= Calendar.getInstance().get(Calendar.YEAR);
    }

    private boolean checkExpiryMonth(){
        return expiryMonth.getValue().matches("^[0-9]{2}$") && Integer.parseInt(expiryMonth.getValue()) >= Calendar.getInstance().get(Calendar.MONTH) + 1;
    }

    private boolean checkCvv(){
        return cvv.getText().matches("^[0-9]{3}$");
    }

    private boolean checkCardName(){
        return cardName.getText().matches("^[a-zA-Z ]+$");
    }








    @FXML
    private void cancel(){
        Stage stage = (Stage) Stage.getWindows().get(1);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Cancel Order");
        alert.setContentText("Potrà tornare alla Home oppure concludere l'ordine");
        alert.showAndWait();
        stage.close();
    }
}
