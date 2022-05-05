package model.persistence.entity;

import javax.persistence.*;

@Entity
public class Crew {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "crewId", nullable = false)
    private int crewId;
    @Basic
    @Column(name = "employeeId", nullable = true, insertable = false, updatable = false)
    private Integer employeeId;
    @Basic
    @Column(name = "role", nullable = true, length = 25)
    private String role;
    @OneToOne
    @JoinColumn(name = "crewId", referencedColumnName = "employeeId", nullable = false)
    private Employee employeeByCrewId;
    @ManyToOne
    @JoinColumn(name = "employeeId", referencedColumnName = "employeeId")
    private Employee employeeByEmployeeId;

    public int getCrewId() {
        return crewId;
    }

    public void setCrewId(int crewId) {
        this.crewId = crewId;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Crew crew = (Crew) o;

        if (crewId != crew.crewId) return false;
        if (employeeId != null ? !employeeId.equals(crew.employeeId) : crew.employeeId != null) return false;
        if (role != null ? !role.equals(crew.role) : crew.role != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = crewId;
        result = 31 * result + (employeeId != null ? employeeId.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }

    public Employee getEmployeeByCrewId() {
        return employeeByCrewId;
    }

    public void setEmployeeByCrewId(Employee employeeByCrewId) {
        this.employeeByCrewId = employeeByCrewId;
    }

    public Employee getEmployeeByEmployeeId() {
        return employeeByEmployeeId;
    }

    public void setEmployeeByEmployeeId(Employee employeeByEmployeeId) {
        this.employeeByEmployeeId = employeeByEmployeeId;
    }
}
