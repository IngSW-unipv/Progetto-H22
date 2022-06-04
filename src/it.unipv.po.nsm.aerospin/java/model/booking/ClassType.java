package model.booking;

public enum ClassType {
    ECONOMY (1.0),
    BUSINESS( 2.0),
    FIRST(5.0);

    private final double priceMultiplier;

    ClassType(double i) {
        priceMultiplier = i;
    }

    public double getPriceM() {
        return priceMultiplier;
    }
}
