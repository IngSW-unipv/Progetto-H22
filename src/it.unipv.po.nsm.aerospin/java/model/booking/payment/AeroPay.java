package model.booking.payment;

import javafx.scene.control.Alert;

import java.util.Random;

public class AeroPay implements PaymentStrategy{


    @Override
    public void pay() {

        Random rn = new Random();
        double d = rn.nextDouble();     // random value in range 0.0 - 1.0
        if(d<=0.3){
            //System.out.println("Payment Failed..........\n");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Pagamento");
            alert.setHeaderText(null);
            alert.setContentText("Il pagamento non è andato a buon fine....riprovare");
            alert.showAndWait();
        }else {
            //System.out.println("Payment Succeded!\n");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Pagamento");
            alert.setHeaderText(null);
            alert.setContentText("Pagamento andato a buon fine!");
            alert.showAndWait();
        }

    }

}
