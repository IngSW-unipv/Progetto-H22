package model.persistence.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "email")
    private String email;
    @Basic
    @Column(name = "pwd")
    private String pwd;
    @Basic
    @Column(name = "admin")
    private Integer admin;
    @OneToMany(mappedBy = "userByEmail")
    private Collection<Passenger> passengersByEmail;
    @ManyToOne
    @JoinColumn(name = "admin", referencedColumnName = "id")
    private Employee employeeByAdmin;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Integer getAdmin() {
        return admin;
    }

    public void setAdmin(Integer admin) {
        this.admin = admin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (pwd != null ? !pwd.equals(user.pwd) : user.pwd != null) return false;
        if (admin != null ? !admin.equals(user.admin) : user.admin != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = email != null ? email.hashCode() : 0;
        result = 31 * result + (pwd != null ? pwd.hashCode() : 0);
        result = 31 * result + (admin != null ? admin.hashCode() : 0);
        return result;
    }

    public Collection<Passenger> getPassengersByEmail() {
        return passengersByEmail;
    }

    public void setPassengersByEmail(Collection<Passenger> passengersByEmail) {
        this.passengersByEmail = passengersByEmail;
    }

    public Employee getEmployeeByAdmin() {
        return employeeByAdmin;
    }

    public void setEmployeeByAdmin(Employee employeeByAdmin) {
        this.employeeByAdmin = employeeByAdmin;
    }
}
