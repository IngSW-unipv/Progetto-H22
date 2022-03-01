package model.booking.payment;

public interface IPayment {
    boolean checkCardNumber();
    boolean checkCardExp();
    void makePayment();
}
