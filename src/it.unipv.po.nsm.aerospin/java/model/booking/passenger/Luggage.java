package model.booking.passenger;

public class Luggage {
    private int width, length;
    private double weight;

    public Luggage(int a,int b,int c) {
        width=a;
        length=b;
        weight=c;
    }

    public Luggage(int a) {
        weight=a;
    }

    public double getWeight() {
        return weight;
    }
}
