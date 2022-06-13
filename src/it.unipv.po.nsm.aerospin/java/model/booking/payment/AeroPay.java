package model.booking.payment;

import model.exception.PaymentException;
import java.util.Random;

/**
 * Classe che si occupa di gestire il pagamento.
 *
 * @author GruppoNoSuchMethod
 */
public class AeroPay implements PaymentStrategy {
    @Override
    public boolean pay() throws PaymentException {
        Random rn = new Random();
        double d = rn.nextDouble();     // random value in range 0.0 - 1.0
        if(d<=0.2){
                throw new PaymentException("The card was declined");
        }else {
                return true;
        }
    }
}
