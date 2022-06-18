package model.booking.payment;

import model.exception.PaymentException;
import java.util.Random;

/**
 * Classe che si occupa di gestire il pagamento.
 *
 * @author GruppoNoSuchMethod
 */
public class AeroPay implements PaymentStrategy {

    /**
     * Metodo che effettua il processo di pagamento.
     *
     * @return true se il pagamento è riuscito, altrimenti genera un messaggio di errore tramite l'eccezione PaymentException
     * @throws PaymentException Segnala che il pagamento non è andato a buon fine.
     */
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
