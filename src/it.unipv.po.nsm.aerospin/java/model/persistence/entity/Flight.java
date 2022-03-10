package model.persistence.entity;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.util.Collection;

@Entity
public class Flight {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "flightNumber")
    private String flightNumber;
    @Basic
    @Column(name = "aircraft")
    private String aircraft;
    @Basic
    @Column(name = "scheduledDate")
    private Date scheduledDate;
    @Basic
    @Column(name = "scheduledTime")
    private Time scheduledTime;
    @Basic
    @Column(name = "flightrouteId")
    private String routeId;
    @Basic
    @Column(name = "arrivalTime")
    private Time arrivalTime;
    @Basic
    @Column(name = "price")
    private Double price;





    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAircraft() {
        return aircraft;
    }

    public void setAircraft(String aircraft) {
        this.aircraft = aircraft;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Time getArrivalTime() {
        return arrivalTime;
    }


    public void setArrivalTime(Time arrivalTime) {
        this.arrivalTime = arrivalTime;
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

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Flight flight = (Flight) o;

        if (aircraft != flight.aircraft) return false;
        if (flightNumber != null ? !flightNumber.equals(flight.flightNumber) : flight.flightNumber != null)
            return false;
        if (scheduledTime != null ? !scheduledTime.equals(flight.scheduledTime) : flight.scheduledTime != null)
            return false;
        if (routeId != null ? !routeId.equals(flight.routeId) : flight.routeId != null) return false;

        return true;
    }


    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", flightNumber='" + flightNumber + '\'' +
                ", aircraft='" + aircraft + '\'' +
                ", scheduledDate=" + scheduledDate +
                ", scheduledTime=" + scheduledTime +
                ", routeId='" + routeId + '\'' +
                ", arrivalTime=" + arrivalTime +
                ", price=" + price +
                '}';
    }
}
