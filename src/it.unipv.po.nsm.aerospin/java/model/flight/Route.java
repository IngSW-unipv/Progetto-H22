package model.flight;


import model.persistence.entity.Aircraft;
import model.persistence.entity.Airport;

public class Route {

    private String routeId;
    private Airport departure;
    private Airport arrival;
    private String waypoints;
    private double price;
    private double costIndex;
    private double speed;
    private final static int avgSpeed = 10;
    private Aircraft aircraft;



    public Route(Airport departure, Airport arrival,String waypoints, double price, Aircraft aircraft, double costIndex) {
        //this.routeId = routeId;
        this.departure = departure;
        this.arrival = arrival;
        this.waypoints = waypoints;
        this.price = price;
        this.costIndex = costIndex;
        this.aircraft = aircraft;
        speed = costIndex*avgSpeed;
    }


    public double getDistance(){ //IN NAUTICAL MILES!
        double depLat = departure.getLatitude() * (Math.PI/180);
        double depLong = departure.getLongitude() * (Math.PI/180);
        double arrLat = arrival.getLatitude() * (Math.PI/180);
        double arrLong = arrival.getLongitude() * (Math.PI/180);
        double distance = 3963 * Math.acos(Math.sin(depLat)*Math.sin(arrLat) + Math.cos(depLat)*Math.cos(arrLat) * Math.cos(arrLong - depLong));
        return distance;
    }


    public double getFlightTime(){
        double depTimeZone = departure.getTimezone();
        double arrTimeZone = arrival.getTimezone();
        double addedTime = arrTimeZone - depTimeZone;
        speed = avgSpeed*costIndex;
        double flightTime = (getDistance()/speed) + addedTime;
        return Math.round(flightTime * 100.0)  / 100.0;

    }

    public String getRouteId() {
        return routeId;
    }

    public Airport getDeparture() {
        return departure;
    }

    public Airport getArrival() {
        return arrival;
    }

    public String getWaypoints() {
        return waypoints;
    }

    public double getPrice() {
        return price;
    }

    public double getCostIndex() {
        return costIndex;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public void setDeparture(Airport departure) {
        this.departure = departure;
    }

    public void setArrival(Airport arrival) {
        this.arrival = arrival;
    }

    public void setWaypoints(String waypoints) {
        this.waypoints = waypoints;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setCostIndex(double costIndex) {
        this.costIndex = costIndex;
    }

    public double getSpeed() {
        return speed;
    }

    @Override
    public String toString() {
        return "Route{" +
                "RouteId ='" + routeId + '\'' +
                ", Departure =" + departure.getIcao() +
                ", Arrival =" + arrival.getIcao() +
                ", Price =" + price +
                ", Waypoints ='" + waypoints + '\'' +
                ", CostIndex =" + costIndex +
                ", Aircraft = " + aircraft.getModel()+
                ", Speed =" + speed +
                ", Flight Time =" + getFlightTime() +
                '}';
    }
}
