package model.persistence.entity;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.util.Collection;
import java.util.Objects;

/**
 * Classe generata automaticamente grazie al framework Hibernate con modifiche relative alla struttura del Database.
 *
 * @author GruppoNoSuchMethod
 */
@Entity
public class Flight {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "flightNumber", length = 45)
    private String flightNumber;
    @Basic
    @Column(name = "flightRouteId", insertable = false, updatable = false)
    private Integer flightRouteId;
    @Basic
    @Column(name = "scheduledDate")
    private Date scheduledDate;
    @Basic
    @Column(name = "scheduledTime")
    private Time scheduledTime;
    @Basic
    @Column(name = "arrivalTime")
    private Time arrivalTime;
    @Basic
    @Column(name = "price")
    private double price;
    @Basic
    @Column(name = "seats")
    private int seats;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "flightRouteId", referencedColumnName = "routeId")
    private Route routeByFlightRouteId;
    @OneToMany(mappedBy = "flightByFlightId")
    private Collection<Booking> bookingById;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public Date getScheduledDate() {
        return scheduledDate;
    }

    public Time getScheduledTime() {
        return scheduledTime;
    }

    public Time getArrivalTime() {
        return arrivalTime;
    }

    public double getPrice() {
        return price;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public Route getRouteById() {
        return routeByFlightRouteId;
    }

    @SuppressWarnings("unused")
    public Collection<Booking> getOrdersById() {
        return bookingById;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Flight flight = (Flight) o;
        if (id != flight.id) return false;
        if (Double.compare(flight.price, price) != 0) return false;
        if (!Objects.equals(flightNumber, flight.flightNumber)) return false;
        if (!Objects.equals(flightRouteId, flight.flightRouteId)) return false;
        if (!Objects.equals(scheduledDate, flight.scheduledDate)) return false;
        if (!Objects.equals(scheduledTime, flight.scheduledTime)) return false;
        return Objects.equals(arrivalTime, flight.arrivalTime);
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
}
