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
public class Airport {
    @Basic
    @Column(name = "AIRPORT_ID", nullable = false)
    private long airportId;
    @Basic
    @Column(name = "AIRPORT_NAME", nullable = false, length = 100)
    private String airportName;
    @Basic
    @Column(name = "CITY", length = 50)
    private String city;
    @Basic
    @Column(name = "COUNTRY", length = 50)
    private String country;
    @Basic
    @Column(name = "IATA", length = 3)
    private String iata;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ICAO", nullable = false, length = 25)
    private String icao;
    @OneToMany(mappedBy = "airportByArrival", fetch = FetchType.EAGER)
    private Collection<Route> routesByIcao;
    @OneToMany(mappedBy = "airportByDeparture", fetch = FetchType.EAGER)
    private Collection<Route> routesByIcao_0;

    public String getAirportName() {
        return airportName;
    }

    public String getCity() {
        return city;
    }

    public String getIata() {
        return iata;
    }

    @SuppressWarnings("unused")
    public Collection<Route> getRoutesByIcao() {
        return routesByIcao;
    }

    @SuppressWarnings("unused")
    public Collection<Route> getRoutesByIcao_0() {
        return routesByIcao_0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Airport airport = (Airport) o;
        if (airportId != airport.airportId) return false;
        if (!Objects.equals(airportName, airport.airportName)) return false;
        if (!Objects.equals(city, airport.city)) return false;
        if (!Objects.equals(country, airport.country)) return false;
        if (!Objects.equals(iata, airport.iata)) return false;
        return Objects.equals(icao, airport.icao);
    }

    public boolean equalsString(String airportName) {
        return this.airportName.equals(airportName);
    }

    @Override
    public int hashCode() {
        int result = (int) (airportId ^ (airportId >>> 32));
        result = 31 * result + (airportName != null ? airportName.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (iata != null ? iata.hashCode() : 0);
        result = 31 * result + (icao != null ? icao.hashCode() : 0);
        return result;
    }
}
