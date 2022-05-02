package model.persistence.entity;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

import org.hibernate.annotations.Cache;
@Entity


public class Route implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "routeId", nullable = false, length = 20)
    private String routeId;
    @Basic
    @Column(name = "arrival", nullable = false, length = 25)
    private String arrival_ICAO;
    @Basic
    @Column(name = "departure", nullable = false, length = 25)
    private String departure_ICAO;
    @Basic
    @Column(name = "waypoints", nullable = false, length = 300)
    private String waypoints;
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "routeByFlightRouteId",cascade = CascadeType.ALL)
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private List<Flight> flightsByRouteId;
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "routeByIcao",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Airport> arrival;
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "routeByIcao_0",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Airport> departure;

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public String getArrival_ICAO() {
        return arrival_ICAO;
    }

    public void setArrival_ICAO(String arrival_ICAO) {
        this.arrival_ICAO = arrival_ICAO;
    }

    public String getDeparture_ICAO() {
        return departure_ICAO;
    }

    public void setDeparture_ICAO(String departure_ICAO) {
        this.departure_ICAO = departure_ICAO;
    }

    public String getWaypoints() {
        return waypoints;
    }

    public void setWaypoints(String waypoints) {
        this.waypoints = waypoints;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Route route = (Route) o;

        if (routeId != null ? !routeId.equals(route.routeId) : route.routeId != null) return false;
        if (arrival_ICAO != null ? !arrival_ICAO.equals(route.arrival_ICAO) : route.arrival_ICAO != null) return false;
        if (departure_ICAO != null ? !departure_ICAO.equals(route.departure_ICAO) : route.departure_ICAO != null)
            return false;
        if (waypoints != null ? !waypoints.equals(route.waypoints) : route.waypoints != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = routeId != null ? routeId.hashCode() : 0;
        result = 31 * result + (arrival_ICAO != null ? arrival_ICAO.hashCode() : 0);
        result = 31 * result + (departure_ICAO != null ? departure_ICAO.hashCode() : 0);
        result = 31 * result + (waypoints != null ? waypoints.hashCode() : 0);
        return result;
    }

    public List<Flight> getFlightsByRouteId() {
        return flightsByRouteId;
    }

    public void setFlightsByRouteId(List<Flight> flightsByRouteId) {
        this.flightsByRouteId = flightsByRouteId;
    }

    public List<Airport> getArrival() {
        return arrival;
    }

    public void setArrival(List<Airport> arrival) {
        this.arrival = arrival;
    }

    public List<Airport> getDeparture() {
        return departure;
    }

    public void setDeparture(List<Airport> departure) {
        this.departure = departure;
    }

    @Override
    public String toString() {
        return "Route{" +
                "routeId='" + routeId + '\'' +
                ", arrival_ICAO='" + arrival_ICAO + '\'' +
                ", departure_ICAO='" + departure_ICAO + '\'' +
                ", waypoints='" + waypoints + '\'' +
                ", arrival=" + arrival +
                ", departure=" + departure +
                '}';
    }
}
