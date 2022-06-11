package model.exception;

/**
 * Classe che si occupa di gestire l'eccezione NoMatchException.
 *
 * @author GruppoNoSuchMethod
 */
public class NoMatchException extends Exception {

    public NoMatchException(String message){
        super(message);
    }

}