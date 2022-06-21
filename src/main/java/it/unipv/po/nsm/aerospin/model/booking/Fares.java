package model.booking;

/**
 * Enumeration delle tariffe di volo con relativo moltiplicatore di costo
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
