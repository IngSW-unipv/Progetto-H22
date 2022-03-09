package model.persistence.entity;

import model.persistence.service.AirportService;

import javax.persistence.*;

@Entity
public class Route {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "routeId")
    private String routeId;
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


    @Transient
    private Airport dep;
    @Transient
    private Airport arr;
    @Transient
    private AirportService airportService;





    public Airport getDeparture(){
        airportService = new AirportService();
        dep= airportService.findByIcao(departure).get(0);
        return dep;
    }


    public Airport getArrival(){
        airportService = new AirportService();
        arr = airportService.findByIcao(arrival).get(0);
        return arr;
    }


    public double getDistance(){
        double depLat = getDeparture().getLatitude() * (Math.PI/180);
        double depLong = getDeparture().getLongitude() * (Math.PI/180);
        double arrLat = getArrival().getLatitude() * (Math.PI/180);
        double arrLong = getArrival().getLongitude() * (Math.PI/180);

        double distance = 3963 * Math.acos(Math.sin(depLat)*Math.sin(arrLat) + Math.cos(depLat)*Math.cos(arrLat) * Math.cos(arrLong - depLong));
        return distance;
    }


    public double getFlightTime(){
        double depTimeZone = getDeparture().getTimezone();
        double arrTimeZone = getArrival().getTimezone();
        double addedTime = arrTimeZone - depTimeZone;
        double speed = 10 * costIndex; //avg speed coefficient = 10;
        double flightTime = (getDistance()/speed) + addedTime;
        return Math.round(flightTime * 100.0)  / 100.0;
    }





    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }


    public void setDeparture(String departure) {
        this.departure = departure;
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
