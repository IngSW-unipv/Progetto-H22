package model.exception;

/**
 * Classe che si occupa di gestire l'eccezione PaymentException.
 *
 * @author GruppoNoSuchMethod
 */
public class PaymentException extends Exception {

    public PaymentException(String message){
        super(message);
    }
}