package model.persistence.entity;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.util.Collection;

@Entity
public class Flight {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "FlightId")
    private int id;
    @Basic
    @Column(name = "flightNumber")
    private String flightNumber;
    @Basic
    @Column(name = "aircraft")
    private int aircraft;
    @Basic
    @Column(name = "crew")
    private Integer crew;
    @Basic
    @Column(name = "scheduledDate")
    private Date scheduledDate;
    @Basic
    @Column(name = "scheduledTime")
    private Time scheduledTime;
    @Basic
    @Column(name = "flightrouteId")
    private Integer routeId;


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

    public int getAircraft() {
        return aircraft;
    }

    public void setAircraft(int aircraft) {
        this.aircraft = aircraft;
    }

    public Integer getCrew() {
        return crew;
    }

    public void setCrew(Integer crew) {
        this.crew = crew;
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

    public Integer getRouteId() {
        return routeId;
    }

    public void setRouteId(Integer routeId) {
        this.routeId = routeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Flight flight = (Flight) o;

        if (id != flight.id) return false;
        if (aircraft != flight.aircraft) return false;
        if (flightNumber != null ? !flightNumber.equals(flight.flightNumber) : flight.flightNumber != null)
            return false;
        if (crew != null ? !crew.equals(flight.crew) : flight.crew != null) return false;
        if (scheduledDate != null ? !scheduledDate.equals(flight.scheduledDate) : flight.scheduledDate != null)
            return false;
        if (scheduledTime != null ? !scheduledTime.equals(flight.scheduledTime) : flight.scheduledTime != null)
            return false;
        if (routeId != null ? !routeId.equals(flight.routeId) : flight.routeId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (flightNumber != null ? flightNumber.hashCode() : 0);
        result = 31 * result + aircraft;
        result = 31 * result + (crew != null ? crew.hashCode() : 0);
        result = 31 * result + (scheduledDate != null ? scheduledDate.hashCode() : 0);
        result = 31 * result + (scheduledTime != null ? scheduledTime.hashCode() : 0);
        result = 31 * result + (routeId != null ? routeId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", flightNumber='" + flightNumber + '\'' +
                ", aircraft=" + aircraft +
                ", crew=" + crew +
                ", scheduledDate=" + scheduledDate +
                ", scheduledTime=" + scheduledTime +
                ", routeId=" + routeId +
                '}';
    }
}
