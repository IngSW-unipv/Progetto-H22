package model.exception;

/**
 * Classe che si occupa di gestire problemi durante il pagamento
 *
 * @author GruppoNoSuchMethod
 */
public class PaymentException extends Exception {

    public PaymentException(String message){
        super(message);
    }
}
