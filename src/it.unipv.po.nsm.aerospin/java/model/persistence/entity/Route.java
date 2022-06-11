package model.persistence.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

/**
 * Classe generata automaticamente grazie al framework Hibernate con modifiche relative alla struttura del Database.
 *
 * @author GruppoNoSuchMethod
 */
@Entity
public class Route {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "routeId", nullable = false)
    private int routeId;
    @Basic
    @Column(name = "arrival", nullable = false, length = 25, insertable = false, updatable = false)
    private String arrival;
    @Basic
    @Column(name = "departure", nullable = false, length = 25, insertable = false, updatable = false)
    private String departure;
    @OneToMany(mappedBy = "routeByFlightRouteId")
    private Collection<Flight> flightsByRouteId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "arrival", referencedColumnName = "ICAO", nullable = false)
    private Airport airportByArrival;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "departure", referencedColumnName = "ICAO", nullable = false)
    private Airport airportByDeparture;

    @SuppressWarnings("unused")
    public Collection<Flight> getFlightsByRouteId() {
        return flightsByRouteId;
    }

    public Airport getAirportArr() {
        return airportByArrival;
    }

    public Airport getAirportDep() {
        return airportByDeparture;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Route route = (Route) o;
        if (routeId != route.routeId) return false;
        if (!Objects.equals(arrival, route.arrival)) return false;
        return Objects.equals(departure, route.departure);
    }

    @Override
    public int hashCode() {
        int result = routeId;
        result = 31 * result + (arrival != null ? arrival.hashCode() : 0);
        result = 31 * result + (departure != null ? departure.hashCode() : 0);
        return result;
    }
}
