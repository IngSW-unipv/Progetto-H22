package model.exception;

/**
 * Classe che si occupa di gestire il non rispetto dei formati di input richiesti
 *
 * @author GruppoNoSuchMethod
 */
public class NoMatchException extends Exception {

    public NoMatchException(String message){
        super(message);
    }
}
