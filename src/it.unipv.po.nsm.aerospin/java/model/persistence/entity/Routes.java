package model.persistence.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Routes {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ROUTE_ID")
    private int routeId;
    @Basic
    @Column(name = "DEPARTURE")
    private String departure;
    @Basic
    @Column(name = "ARRIVAL")
    private String arrival;
    @Basic
    @Column(name = "WAYPOINTS")
    private String waypoints;
    @Basic
    @Column(name = "PRICE")
    private String price;
    @Basic
    @Column(name = "AIRCRAFT")
    private String aircraft;
    @Basic
    @Column(name = "COSTINDEX")
    private String costindex;

    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public String getWaypoints() {
        return waypoints;
    }

    public void setWaypoints(String waypoints) {
        this.waypoints = waypoints;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAircraft() {
        return aircraft;
    }

    public void setAircraft(String aircraft) {
        this.aircraft = aircraft;
    }

    public String getCostindex() {
        return costindex;
    }

    public void setCostindex(String costindex) {
        this.costindex = costindex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Routes routes = (Routes) o;
        return routeId == routes.routeId && Objects.equals(departure, routes.departure) && Objects.equals(arrival, routes.arrival) && Objects.equals(waypoints, routes.waypoints) && Objects.equals(price, routes.price) && Objects.equals(aircraft, routes.aircraft) && Objects.equals(costindex, routes.costindex);
    }

    @Override
    public int hashCode() {
        return Objects.hash(routeId, departure, arrival, waypoints, price, aircraft, costindex);
    }
}
