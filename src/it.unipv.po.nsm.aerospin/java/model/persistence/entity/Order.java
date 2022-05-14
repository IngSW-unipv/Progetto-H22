package model.persistence.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
public class Order {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "PassengerId", nullable = false)
    private int passengerId;
    @Basic
    @Column(name = "flightIdA", nullable = false)
    private int flightIdA;
    @Basic
    @Column(name = "flightIdR", nullable = false)
    private int flightIdR;
    @Basic
    @Column(name = "flightClass", nullable = false, length = 25)
    private String flightClass;
    @Basic
    @Column(name = "cardDetails", nullable = false)
    private int cardDetails;
    @Basic
    @Column(name = "orderDate", nullable = false)
    private Date orderDate;
    @Basic
    @Column(name = "price", nullable = false)
    private double price;
    @ManyToOne
    @JoinColumn(name = "PassengerId", referencedColumnName = "id", nullable = false)
    private Passenger passengerByPassengerId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(int passengerId) {
        this.passengerId = passengerId;
    }

    public int getFlightIdA() {
        return flightIdA;
    }

    public void setFlightIdA(int flightIdA) {
        this.flightIdA = flightIdA;
    }

    public int getFlightIdR() {
        return flightIdR;
    }

    public void setFlightIdR(int flightIdR) {
        this.flightIdR = flightIdR;
    }

    public String getFlightClass() {
        return flightClass;
    }

    public void setFlightClass(String flightClass) {
        this.flightClass = flightClass;
    }

    public int getCardDetails() {
        return cardDetails;
    }

    public void setCardDetails(int cardDetails) {
        this.cardDetails = cardDetails;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (id != order.id) return false;
        if (passengerId != order.passengerId) return false;
        if (flightIdA != order.flightIdA) return false;
        if (flightIdR != order.flightIdR) return false;
        if (cardDetails != order.cardDetails) return false;
        if (Double.compare(order.price, price) != 0) return false;
        if (!Objects.equals(flightClass, order.flightClass)) return false;
        return Objects.equals(orderDate, order.orderDate);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + passengerId;
        result = 31 * result + flightIdA;
        result = 31 * result + flightIdR;
        result = 31 * result + (flightClass != null ? flightClass.hashCode() : 0);
        result = 31 * result + cardDetails;
        result = 31 * result + (orderDate != null ? orderDate.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    public Passenger getPassengerByPassengerId() {
        return passengerByPassengerId;
    }

    public void setPassengerByPassengerId(Passenger passengerByPassengerId) {
        this.passengerByPassengerId = passengerByPassengerId;
    }
}
