package model.booking.payment;

import model.exception.PaymentException;

/**
 * Interfaccia per la Strategy della richiesta di effettuare un pagamento.
 *
 * @author GruppoNoSuchMethod
 */
public interface PaymentStrategy {
    boolean pay() throws PaymentException;
}
