package model.booking.payment;

import java.util.HashMap;
import java.util.Random;

/**
 * Interfaccia per la Strategy della richiesta di effettuare un pagamento.
 *
 * @author GruppoNoSuchMethod
 */
public interface PaymentStrategy {
    public void pay();
}
