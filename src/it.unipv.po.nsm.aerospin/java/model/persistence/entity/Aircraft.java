package model.persistence.entity;

import javax.persistence.*;

@Entity
public class Aircraft {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "tailNumber")
    private String tailNumber;
    @Basic
    @Column(name = "manufacturer")
    private String manufacturer;
    @Basic
    @Column(name = "model")
    private String model;
    @Basic
    @Column(name = "seats")
    private Integer seats;
    @Basic
    @Column(name = "availability")
    private Byte availability;
    @Basic
    @Column(name = "maxRange")
    private Double maxRange;

    public String getTailNumber() {
        return tailNumber;
    }

    public void setTailNumber(String tailNumber) {
        this.tailNumber = tailNumber;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }

    public Byte getAvailability() {
        return availability;
    }

    public void setAvailability(Byte availability) {
        this.availability = availability;
    }

    public Double getMaxRange() {
        return maxRange;
    }

    public void setMaxRange(Double maxRange) {
        this.maxRange = maxRange;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Aircraft aircraft = (Aircraft) o;

        if (tailNumber != null ? !tailNumber.equals(aircraft.tailNumber) : aircraft.tailNumber != null) return false;
        if (manufacturer != null ? !manufacturer.equals(aircraft.manufacturer) : aircraft.manufacturer != null)
            return false;
        if (model != null ? !model.equals(aircraft.model) : aircraft.model != null) return false;
        if (seats != null ? !seats.equals(aircraft.seats) : aircraft.seats != null) return false;
        if (availability != null ? !availability.equals(aircraft.availability) : aircraft.availability != null)
            return false;
        if (maxRange != null ? !maxRange.equals(aircraft.maxRange) : aircraft.maxRange != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = tailNumber != null ? tailNumber.hashCode() : 0;
        result = 31 * result + (manufacturer != null ? manufacturer.hashCode() : 0);
        result = 31 * result + (model != null ? model.hashCode() : 0);
        result = 31 * result + (seats != null ? seats.hashCode() : 0);
        result = 31 * result + (availability != null ? availability.hashCode() : 0);
        result = 31 * result + (maxRange != null ? maxRange.hashCode() : 0);
        return result;
    }
}
