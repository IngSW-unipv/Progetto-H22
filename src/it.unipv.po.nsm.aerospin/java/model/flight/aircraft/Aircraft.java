package model.flight.aircraft;

public class Aircraft {

    private String model;
    private String tailNumber;
    private double maxRange;
    private Manufacturer manufacturer;
    private int maxCapacity;
    private int seats;
    private int seatClass[];
    private boolean availability;

    public Aircraft(String model, String tailNumber, double maxRange, Manufacturer manufacturer, int maxCapacity, int seats) {
        this.model = model;
        this.tailNumber = tailNumber;
        this.maxRange = maxRange;
        this.manufacturer = manufacturer;
        this.maxCapacity = maxCapacity;
        this.seats = seats;
        this.availability=true;
    }

    public String getModel() {
        return model;
    }

    public String getTailNumber() {
        return tailNumber;
    }

    public double getMaxRange() {
        return maxRange;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public int getSeats() {
        return seats;
    }

    public int[] getSeatClass() {
        return seatClass;
    }

    public void setAvailable(){
        availability = true;
    }

    public void setInService(){
        availability = false;
    }


    public boolean available(){
        return availability;
    }

    public String aircraftStatus(){
        if(availability){
            return "Aircraft Status: Available...";
        }else
            return  "Aircraft Status: In Service ";
    }
}
