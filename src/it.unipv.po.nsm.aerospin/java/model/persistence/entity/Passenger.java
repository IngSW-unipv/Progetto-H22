package model.persistence.entity;

import javax.persistence.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity

public class Passenger implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "passengerId", nullable = false)
    private int passengerId;
    @Basic
    @Column(name = "name", nullable = false, length = 100)
    private String name;
    @Basic
    @Column(name = "surname", nullable = false, length = 100)
    private String surname;
    @Basic
    @Column(name = "classType", nullable = false, length = 10)
    private String classType;
    @Basic
    @Column(name = "flightId", nullable = false, length = 10)
    private String flightId;
    @Basic
    @Column(name = "passengerEmail", nullable = false, length = 100)
    private String passengerEmail;

    public int getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(int passengerId) {
        this.passengerId = passengerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public String getFlightId() {
        return flightId;
    }

    public void setFlightId(String flightId) {
        this.flightId = flightId;
    }

    public String getPassengerEmail() {
        return passengerEmail;
    }

    public void setPassengerEmail(String passengerEmail) {
        this.passengerEmail = passengerEmail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Passenger passenger = (Passenger) o;

        if (passengerId != passenger.passengerId) return false;
        if (name != null ? !name.equals(passenger.name) : passenger.name != null) return false;
        if (surname != null ? !surname.equals(passenger.surname) : passenger.surname != null) return false;
        if (classType != null ? !classType.equals(passenger.classType) : passenger.classType != null) return false;
        if (flightId != null ? !flightId.equals(passenger.flightId) : passenger.flightId != null) return false;
        if (passengerEmail != null ? !passengerEmail.equals(passenger.passengerEmail) : passenger.passengerEmail != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = passengerId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (classType != null ? classType.hashCode() : 0);
        result = 31 * result + (flightId != null ? flightId.hashCode() : 0);
        result = 31 * result + (passengerEmail != null ? passengerEmail.hashCode() : 0);
        return result;
    }
}
