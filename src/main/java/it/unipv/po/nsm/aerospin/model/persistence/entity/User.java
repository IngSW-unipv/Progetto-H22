package model.persistence.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

/**
 * Classe che definisce l'entità User tramite ORM
 *
 * @author GruppoNoSuchMethod
 */
@Entity
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "email", nullable = false, length = 45)
    private String email;
    @Basic
    @Column(name = "pwd", nullable = false, length = 45)
    private String pwd;
    @OneToMany(mappedBy = "userByUserId")
    private Collection<Passenger> passengersById;

    @Column(name = "userType", nullable = false)
    private Integer userType;

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public Collection<Passenger> getPassengersById() {
        return passengersById;
    }

    public void setPassengersById(Collection<Passenger> passengersById) {
        this.passengersById = passengersById;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;
        if (id != user.id) return false;
        if (!Objects.equals(email, user.email)) return false;
        return Objects.equals(pwd, user.pwd);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (pwd != null ? pwd.hashCode() : 0);
        return result;
    }
}
