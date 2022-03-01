package model.persistence.entity;

import model.persistence.service.AirportService;

import javax.persistence.*;

@Entity
public class Route {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "routeId")
    private int routeId;
    @Basic
    @Column(name = "departure")
    private String departure;
    @Basic
    @Column(name = "arrival")
    private String arrival;
    @Basic
    @Column(name = "waypoints")
    private String waypoints;
    @Basic
    @Column(name = "price")
    private Double price;
    @Basic
    @Column(name = "costIndex")
    private Integer costIndex;





    public String getDepartureName(){
        String arrivalName;
        String departureName;
        AirportService airportService = new AirportService();
        Airport airport = airportService.findByIcao(departure).get(0);
        return airport.getAirportName();
    }


    public String getArrivalName(){
        AirportService airportService = new AirportService();
        Airport airport = airportService.findByIcao(arrival).get(0);
        return airport.getAirportName();
    }






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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getCostIndex() {
        return costIndex;
    }

    public void setCostIndex(Integer costIndex) {
        this.costIndex = costIndex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Route route = (Route) o;

        if (routeId != route.routeId) return false;
        if (departure != null ? !departure.equals(route.departure) : route.departure != null) return false;
        if (arrival != null ? !arrival.equals(route.arrival) : route.arrival != null) return false;
        if (waypoints != null ? !waypoints.equals(route.waypoints) : route.waypoints != null) return false;
        if (price != null ? !price.equals(route.price) : route.price != null) return false;
        if (costIndex != null ? !costIndex.equals(route.costIndex) : route.costIndex != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = routeId;
        result = 31 * result + (departure != null ? departure.hashCode() : 0);
        result = 31 * result + (arrival != null ? arrival.hashCode() : 0);
        result = 31 * result + (waypoints != null ? waypoints.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (costIndex != null ? costIndex.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Route{" +
                "routeId=" + routeId +
                ", departure='" + departure + '\'' +
                ", arrival='" + arrival + '\'' +
                ", waypoints='" + waypoints + '\'' +
                ", price=" + price +
                ", costIndex=" + costIndex +
                '}';
    }
}
