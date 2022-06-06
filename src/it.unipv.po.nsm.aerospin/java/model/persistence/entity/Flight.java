package model.persistence.entity;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.util.Collection;

@Entity
public class Flight {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "flightNumber", nullable = true, length = 45)
    private String flightNumber;

    @Basic
    @Column(name = "flightRouteId", nullable = true, insertable = false, updatable = false)
    private Integer flightRouteId;
    @Basic
    @Column(name = "scheduledDate", nullable = true)
    private Date scheduledDate;
    @Basic
    @Column(name = "scheduledTime", nullable = true)
    private Time scheduledTime;
    @Basic
    @Column(name = "arrivalTime", nullable = true)
    private Time arrivalTime;
    @Basic
    @Column(name = "price", nullable = true, precision = 0)
    private double price;
    @ManyToOne
    @JoinColumn(name = "flightRouteId", referencedColumnName = "routeId")
    private Route routeByFlightRouteId;
    @Basic
    @Column(name = "seats", nullable = true)
    private int seats;
    @OneToMany(mappedBy = "flightByFlightId")
    private Collection<Orders> ordersById;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public Integer getFlightRouteId() {
        return flightRouteId;
    }
    
    public void setFlightRouteId(Integer flightRouteId) {
        this.flightRouteId = flightRouteId;
    }

    public Date getScheduledDate() {
        return scheduledDate;
    }

    public void setScheduledDate(Date scheduledDate) {
        this.scheduledDate = scheduledDate;
    }

    public Time getScheduledTime() {
        return scheduledTime;
    }

    public void setScheduledTime(Time scheduledTime) {
        this.scheduledTime = scheduledTime;
    }

    public Time getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Time arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Flight flight = (Flight) o;

        if (id != flight.id) return false;
        if (Double.compare(flight.price, price) != 0) return false;
        if (flightNumber != null ? !flightNumber.equals(flight.flightNumber) : flight.flightNumber != null)
            return false;
        if (flightRouteId != null ? !flightRouteId.equals(flight.flightRouteId) : flight.flightRouteId != null)
            return false;
        if (scheduledDate != null ? !scheduledDate.equals(flight.scheduledDate) : flight.scheduledDate != null)
            return false;
        if (scheduledTime != null ? !scheduledTime.equals(flight.scheduledTime) : flight.scheduledTime != null)
            return false;
        if (arrivalTime != null ? !arrivalTime.equals(flight.arrivalTime) : flight.arrivalTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (flightNumber != null ? flightNumber.hashCode() : 0);
        result = 31 * result + (flightRouteId != null ? flightRouteId.hashCode() : 0);
        result = 31 * result + (scheduledDate != null ? scheduledDate.hashCode() : 0);
        result = 31 * result + (scheduledTime != null ? scheduledTime.hashCode() : 0);
        result = 31 * result + (arrivalTime != null ? arrivalTime.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    public Route getRouteByFlightRouteId() {
        return routeByFlightRouteId;
    }

    public void setRouteByFlightRouteId(Route routeByFlightRouteId) {
        this.routeByFlightRouteId = routeByFlightRouteId;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }

    public void setSeats(short seats) {
        this.seats = seats;
    }

    public Collection<Orders> getOrdersById() {
        return ordersById;
    }

    public void setOrdersById(Collection<Orders> ordersById) {
        this.ordersById = ordersById;
    }
}
