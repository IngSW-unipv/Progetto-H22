package model.persistence.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class Orders {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "PassengerId", nullable = false)
    private int passengerId;
    @Basic
    @Column(name = "flightId", nullable = false)
    private int flightId;
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
    @Column(name = "price", nullable = false, precision = 0)
    private double price;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PassengerId", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private Passenger passengerByPassengerId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "flightId", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private Flight flightByFlightId;

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

    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
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

        Orders orders = (Orders) o;

        if (id != orders.id) return false;
        if (passengerId != orders.passengerId) return false;
        if (flightId != orders.flightId) return false;
        if (cardDetails != orders.cardDetails) return false;
        if (Double.compare(orders.price, price) != 0) return false;
        if (flightClass != null ? !flightClass.equals(orders.flightClass) : orders.flightClass != null) return false;
        if (orderDate != null ? !orderDate.equals(orders.orderDate) : orders.orderDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + passengerId;
        result = 31 * result + flightId;
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

    public Flight getFlightByFlightId() {
        return flightByFlightId;
    }

    public void setFlightByFlightId(Flight flightByFlightId) {
        this.flightByFlightId = flightByFlightId;
    }


    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", passengerId=" + passengerId +
                ", flightId=" + flightId +
                ", flightClass='" + flightClass + '\'' +
                ", cardDetails=" + cardDetails +
                ", orderDate=" + orderDate +
                ", price=" + price +
                ", passengerByPassengerId=" + passengerByPassengerId +
                '}';
    }
}
