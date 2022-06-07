package model.persistence.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Route {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "routeId", nullable = false)
    private int routeId;
    @Basic
    @Column(name = "arrival", nullable = false, length = 25, insertable = false, updatable = false)
    private String arrival;
    @Basic
    @Column(name = "departure", nullable = false, length = 25, insertable = false, updatable = false)
    private String departure;
    @Basic
    @Column(name = "waypoints", nullable = true, length = 300)
    private String waypoints;
    @OneToMany(mappedBy = "routeByFlightRouteId")
    private Collection<Flight> flightsByRouteId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "arrival", referencedColumnName = "ICAO", nullable = false)
    private Airport airportByArrival;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "departure", referencedColumnName = "ICAO", nullable = false)
    private Airport airportByDeparture;

    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
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

        if (routeId != route.routeId) return false;
        if (arrival != null ? !arrival.equals(route.arrival) : route.arrival != null) return false;
        if (departure != null ? !departure.equals(route.departure) : route.departure != null) return false;
        if (waypoints != null ? !waypoints.equals(route.waypoints) : route.waypoints != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = routeId;
        result = 31 * result + (arrival != null ? arrival.hashCode() : 0);
        result = 31 * result + (departure != null ? departure.hashCode() : 0);
        result = 31 * result + (waypoints != null ? waypoints.hashCode() : 0);
        return result;
    }

    public Collection<Flight> getFlightsByRouteId() {
        return flightsByRouteId;
    }

    public void setFlightsByRouteId(Collection<Flight> flightsByRouteId) {
        this.flightsByRouteId = flightsByRouteId;
    }

    public Airport getAirportByArrival() {
        return airportByArrival;
    }

    public void setAirportByArrival(Airport airportByArrival) {
        this.airportByArrival = airportByArrival;
    }

    public Airport getAirportByDeparture() {
        return airportByDeparture;
    }

    public void setAirportByDeparture(Airport airportByDeparture) {
        this.airportByDeparture = airportByDeparture;
    }
}
