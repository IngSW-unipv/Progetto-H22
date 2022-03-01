package model.booking.payment;

import model.booking.Ticket;
import java.util.Date;

public class Payment implements IPayment{
    private Ticket ticket;
    private String cardNumber;
    private Date cardExp;

    public Payment(Ticket ticket, String cardNumber, Date cardExp) {
        this.ticket = ticket;
        this.cardNumber = cardNumber;
        this.cardExp = cardExp;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Date getCardExp() {
        return cardExp;
    }

    public void setCardExp(Date cardExp) {
        this.cardExp = cardExp;
    }

    @Override
    public void makePayment() {

    }
}
