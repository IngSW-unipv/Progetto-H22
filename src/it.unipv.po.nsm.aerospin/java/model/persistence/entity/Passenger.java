package model.persistence.entity;

import javax.persistence.*;

@Entity
public class Passenger {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")//tnumber
    private int id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "surname")
    private String surname;
    @Basic
    @Column(name = "age")
    private short age;//datadinascitaaa
    @Basic
    @Column(name = "classType")
    private String classType;
    @Basic
    @Column(name = "flightNumber")//cambia in flightID
    private String flightNumber;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public short getAge() {
        return age;
    }

    public void setAge(short age) {
        this.age = age;
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Passenger passenger = (Passenger) o;

        if (id != passenger.id) return false;
        if (age != passenger.age) return false;
        if (name != null ? !name.equals(passenger.name) : passenger.name != null) return false;
        if (surname != null ? !surname.equals(passenger.surname) : passenger.surname != null) return false;
        if (classType != null ? !classType.equals(passenger.classType) : passenger.classType != null) return false;
        if (flightNumber != null ? !flightNumber.equals(passenger.flightNumber) : passenger.flightNumber != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (int) age;
        result = 31 * result + (classType != null ? classType.hashCode() : 0);
        result = 31 * result + (flightNumber != null ? flightNumber.hashCode() : 0);
        return result;
    }
}
