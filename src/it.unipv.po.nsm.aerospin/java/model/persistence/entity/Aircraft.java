package model.persistence.entity;

import javax.persistence.*;

@Entity
public class Aircraft {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "tailNumber")
    private int tailNumber;
    @Basic
    @Column(name = "manufacturer")
    private String manufacturer;
    @Basic
    @Column(name = "maxRange")
    private Double maxRange;
    @Basic
    @Column(name = "maxCapacity")
    private Integer maxCapacity;
    @Basic
    @Column(name = "seats")
    private Integer seats;
    @Basic
    @Column(name = "availability")
    private Boolean availability;
    @Basic
    @Column(name = "model")
    private String model;

    public int getTailNumber() {
        return tailNumber;
    }

    public void setTailNumber(int tailNumber) {
        this.tailNumber = tailNumber;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Double getMaxRange() {
        return maxRange;
    }

    public void setMaxRange(Double maxRange) {
        this.maxRange = maxRange;
    }

    public Integer getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(Integer maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }

    public Boolean getAvailability() {
        return availability;
    }

    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Aircraft aircraft = (Aircraft) o;

        if (tailNumber != aircraft.tailNumber) return false;
        if (manufacturer != null ? !manufacturer.equals(aircraft.manufacturer) : aircraft.manufacturer != null)
            return false;
        if (maxRange != null ? !maxRange.equals(aircraft.maxRange) : aircraft.maxRange != null) return false;
        if (maxCapacity != null ? !maxCapacity.equals(aircraft.maxCapacity) : aircraft.maxCapacity != null)
            return false;
        if (seats != null ? !seats.equals(aircraft.seats) : aircraft.seats != null) return false;
        if (availability != null ? !availability.equals(aircraft.availability) : aircraft.availability != null)
            return false;
        if (model != null ? !model.equals(aircraft.model) : aircraft.model != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = tailNumber;
        result = 31 * result + (manufacturer != null ? manufacturer.hashCode() : 0);
        result = 31 * result + (maxRange != null ? maxRange.hashCode() : 0);
        result = 31 * result + (maxCapacity != null ? maxCapacity.hashCode() : 0);
        result = 31 * result + (seats != null ? seats.hashCode() : 0);
        result = 31 * result + (availability != null ? availability.hashCode() : 0);
        result = 31 * result + (model != null ? model.hashCode() : 0);
        return result;
    }
}
