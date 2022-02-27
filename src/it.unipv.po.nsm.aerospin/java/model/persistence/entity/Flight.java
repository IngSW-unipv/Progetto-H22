package model.persistence.entity;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Entity
public class Flight {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;//aggiungi flightnumber
    @Basic
    @Column(name = "routeId")
    private Integer routeId;
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
    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "tailNumber", nullable = false)
    private Aircraft aircraftById;
    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "id", nullable = false)
    private Crew crewById;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getRouteId() {
        return routeId;
    }

    public void setRouteId(Integer routeId) {
        this.routeId = routeId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Flight flight = (Flight) o;

        if (id != flight.id) return false;
        if (aircraft != flight.aircraft) return false;
        if (routeId != null ? !routeId.equals(flight.routeId) : flight.routeId != null) return false;
        if (crew != null ? !crew.equals(flight.crew) : flight.crew != null) return false;
        if (scheduledDate != null ? !scheduledDate.equals(flight.scheduledDate) : flight.scheduledDate != null)
            return false;
        if (scheduledTime != null ? !scheduledTime.equals(flight.scheduledTime) : flight.scheduledTime != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (routeId != null ? routeId.hashCode() : 0);
        result = 31 * result + aircraft;
        result = 31 * result + (crew != null ? crew.hashCode() : 0);
        result = 31 * result + (scheduledDate != null ? scheduledDate.hashCode() : 0);
        result = 31 * result + (scheduledTime != null ? scheduledTime.hashCode() : 0);
        return result;
    }

    public Aircraft getAircraftById() {
        return aircraftById;
    }

    public void setAircraftById(Aircraft aircraftById) {
        this.aircraftById = aircraftById;
    }

    public Crew getCrewById() {
        return crewById;
    }

    public void setCrewById(Crew crewById) {
        this.crewById = crewById;
    }
}
