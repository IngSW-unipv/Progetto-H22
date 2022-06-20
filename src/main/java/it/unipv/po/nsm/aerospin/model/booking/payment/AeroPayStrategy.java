package model.booking.payment;

import model.exception.PaymentException;
import java.util.Random;

/**
 * Classe che si occupa di gestire il pagamento.
 *
 * @author GruppoNoSuchMethod
 */
public class AeroPayStrategy implements IPaymentStrategy {

    /**
     * Metodo che effettua il processo di pagamento.
     *
     * @return true se il pagamento è riuscito, altrimenti propaga una PaymentException Exception
     * @throws PaymentException Segnala che il pagamento non è andato a buon fine
     */
    @Override
    public boolean pay(double price) throws PaymentException {
        Random rn = new Random();
        double d = rn.nextDouble();     // random value in range 0.0 - 1.0
        if(d<=0.2){
                throw new PaymentException("The card was declined");
        }else {
                return true;
        }
    }
}
