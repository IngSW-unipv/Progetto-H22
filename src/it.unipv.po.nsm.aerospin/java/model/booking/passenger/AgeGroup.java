package model.booking.passenger;

public enum AgeGroup {
    INFANTS ((short) 2),
    CHILDREN ((short) 15),
    ADULTS((short) 1000);

    private final short maxAge;

    AgeGroup(short i) {
        maxAge = i;
    }

    public short getMaxAge() {
        return maxAge;
    }
}
