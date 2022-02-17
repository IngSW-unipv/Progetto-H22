package model.booking.passenger;

public enum ClassType {
    ECONOMY(new Luggage(40,30,8)),
    BUSINESS(new Luggage(15)),
    FIRST(new Luggage(20));

    private final Luggage luggage;

    ClassType(Luggage luggage) {
        this.luggage = luggage;
    }
}
