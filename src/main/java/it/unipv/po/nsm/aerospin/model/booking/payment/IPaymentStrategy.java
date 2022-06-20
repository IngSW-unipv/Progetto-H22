package model.booking.payment;

import model.exception.PaymentException;

/**
 * Interfaccia per la gestione delle diverse strategie di pagamento
 *
 * @author GruppoNoSuchMethod
 */
public interface IPaymentStrategy {
    boolean pay(double price) throws PaymentException;
}
