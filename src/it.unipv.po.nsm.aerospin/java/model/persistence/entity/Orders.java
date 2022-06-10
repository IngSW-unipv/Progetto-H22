package model.persistence.entity;

import model.booking.Fares;
import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
public class Orders {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false, length = 25)
    private String id;
    @Basic
    @Column(name = "PassengerId", nullable = false)
    private int passengerId;
    @Basic
    @Column(name = "flightId", nullable = false)
    private int flightId;
    @Basic
    @Column(name = "fare", nullable = false, length = 25)
    private String fare;
    @Basic
    @Column(name = "cardDetails", nullable = false)
    private int cardDetails;
    @Basic
    @Column(name = "orderDate", nullable = false)
    private Date orderDate;
    @Basic
    @Column(name = "price", nullable = false)
    private double price;
    @ManyToOne()
    @JoinColumn(name = "PassengerId", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private Passenger passengerByPassengerId;
    @ManyToOne()
    @JoinColumn(name = "flightId", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private Flight flightByFlightId;

    //TODO VELOCIZZA ORDER QUERY

    public String getId() {
        return id;
    }

    public void setPassengerId(int passengerId) {
        this.passengerId = passengerId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    public String getFare() {
        return fare;
    }

    public void setFare(Fares fare) {
        this.fare = fare.toString();
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

    public Passenger getPassengerById() {
        return passengerByPassengerId;
    }

    public Flight getFlightById() {
        return flightByFlightId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Orders orders = (Orders) o;
        if (!Objects.equals(id, orders.id)) return false;
        if (passengerId != orders.passengerId) return false;
        if (flightId != orders.flightId) return false;
        if (cardDetails != orders.cardDetails) return false;
        if (Double.compare(orders.price, price) != 0) return false;
        return Objects.equals(orderDate, orders.orderDate);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id != null ? id.hashCode() : 0;
        result = 31 * result + passengerId;
        result = 31 * result + flightId;
        result = 31 * result + cardDetails;
        result = 31 * result + (orderDate != null ? orderDate.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
