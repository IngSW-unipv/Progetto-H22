package model.persistence.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Passenger {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "userId", nullable = true)
    private Integer userId;
    @Basic
    @Column(name = "name", length = 25, nullable = true)
    private String name;
    @Basic
    @Column(name = "surname",length = 25, nullable = true)
    private String surname;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId", referencedColumnName = "id", insertable = false, updatable = false)
    private User userByUserId;
    @OneToMany(mappedBy = "passengerByPassengerId", fetch = FetchType.EAGER)
    private Collection<Orders> ordersById;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Passenger passenger = (Passenger) o;

        if (id != passenger.id) return false;
        if (!Objects.equals(userId, passenger.userId)) return false;
        if (!Objects.equals(name, passenger.name)) return false;
        return Objects.equals(surname, passenger.surname);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        return result;
    }

    public User getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(User userByUserId) {
        this.userByUserId = userByUserId;
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "id=" + id +
                ", userId=" + userId +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", userByUserId=" + userByUserId +
                '}';
    }

    public Collection<Orders> getOrdersById() {
        return ordersById;
    }

    public void setOrdersById(Collection<Orders> ordersById) {
        this.ordersById = ordersById;
    }
}
