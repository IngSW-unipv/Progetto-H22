package model.persistence.entity;

import javax.persistence.*;

@Entity
public class Crew {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "captain")
    private String captain;
    @Basic
    @Column(name = "firstOfficer")
    private String firstOfficer;
    @Basic
    @Column(name = "secondOfficer")
    private String secondOfficer;
    @Basic
    @Column(name = "cabinCrew")
    private Integer cabinCrew;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCaptain() {
        return captain;
    }

    public void setCaptain(String captain) {
        this.captain = captain;
    }

    public String getFirstOfficer() {
        return firstOfficer;
    }

    public void setFirstOfficer(String firstOfficer) {
        this.firstOfficer = firstOfficer;
    }

    public String getSecondOfficer() {
        return secondOfficer;
    }

    public void setSecondOfficer(String secondOfficer) {
        this.secondOfficer = secondOfficer;
    }

    public Integer getCabinCrew() {
        return cabinCrew;
    }

    public void setCabinCrew(Integer cabinCrew) {
        this.cabinCrew = cabinCrew;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Crew crew = (Crew) o;

        if (id != crew.id) return false;
        if (captain != null ? !captain.equals(crew.captain) : crew.captain != null) return false;
        if (firstOfficer != null ? !firstOfficer.equals(crew.firstOfficer) : crew.firstOfficer != null) return false;
        if (secondOfficer != null ? !secondOfficer.equals(crew.secondOfficer) : crew.secondOfficer != null)
            return false;
        if (cabinCrew != null ? !cabinCrew.equals(crew.cabinCrew) : crew.cabinCrew != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (captain != null ? captain.hashCode() : 0);
        result = 31 * result + (firstOfficer != null ? firstOfficer.hashCode() : 0);
        result = 31 * result + (secondOfficer != null ? secondOfficer.hashCode() : 0);
        result = 31 * result + (cabinCrew != null ? cabinCrew.hashCode() : 0);
        return result;
    }
}
