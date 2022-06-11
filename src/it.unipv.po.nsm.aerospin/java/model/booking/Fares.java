package model.booking;

/**
 * Enumeration per la gestione dei moltiplicatori relativi alle differenti tariffe di viaggio.
 *
 * @author GruppoNoSuchMethod
 */
public enum Fares {
    STANDARD(1.0),
    PLUS( 2.0),
    THEAEROSPIN(5.0);

    private final double priceMultiplier;

    Fares(double i) {
        priceMultiplier = i;
    }

    public double getPriceM() {
        return priceMultiplier;
    }
}
