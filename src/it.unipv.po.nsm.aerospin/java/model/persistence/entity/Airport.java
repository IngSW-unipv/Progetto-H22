package model.persistence.entity;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Airport implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "AIRPORT_ID", nullable = false)
    private long airportId;
    @Basic
    @Column(name = "AIRPORT_NAME", nullable = false, length = 100)
    private String airportName;
    @Basic
    @Column(name = "CITY", nullable = true, length = 50)
    private String city;
    @Basic
    @Column(name = "COUNTRY", nullable = true, length = 50)
    private String country;
    @Basic
    @Column(name = "IATA", nullable = true, length = 3)
    private String iata;
    @Basic
    @Column(name = "ICAO", nullable = false, length = 4)
    private String icao;
    @Basic
    @Column(name = "LATITUDE", nullable = true, precision = 0)
    private Double latitude;
    @Basic
    @Column(name = "LONGITUDE", nullable = true, precision = 0)
    private Double longitude;
    @Basic
    @Column(name = "ALTITUDE", nullable = true)
    private Integer altitude;
    @Basic
    @Column(name = "TIMEZONE", nullable = true)
    private Integer timezone;
    @Basic
    @Column(name = "DST", nullable = true, length = 5)
    private String dst;
    @Basic
    @Column(name = "TZ", nullable = true, length = 50)
    private String tz;
    @Basic
    @Column(name = "TYPE_A", nullable = true, length = 15)
    private String typeA;
    @Basic
    @Column(name = "SOURCE_A", nullable = true, length = 20)
    private String sourceA;
    @OneToOne
    @JoinColumn(name = "ICAO", referencedColumnName = "arrival", insertable = false, updatable = false)
    private Route routeByIcao;
    @OneToOne
    @JoinColumn(name = "ICAO", referencedColumnName = "departure", insertable = false, updatable = false)
    private Route routeByIcao_0;

    public long getAirportId() {
        return airportId;
    }

    public void setAirportId(long airportId) {
        this.airportId = airportId;
    }

    public String getAirportName() {
        return airportName;
    }

    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getIata() {
        return iata;
    }

    public void setIata(String iata) {
        this.iata = iata;
    }

    public String getIcao() {
        return icao;
    }

    public void setIcao(String icao) {
        this.icao = icao;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Integer getAltitude() {
        return altitude;
    }

    public void setAltitude(Integer altitude) {
        this.altitude = altitude;
    }

    public Integer getTimezone() {
        return timezone;
    }

    public void setTimezone(Integer timezone) {
        this.timezone = timezone;
    }

    public String getDst() {
        return dst;
    }

    public void setDst(String dst) {
        this.dst = dst;
    }

    public String getTz() {
        return tz;
    }

    public void setTz(String tz) {
        this.tz = tz;
    }

    public String getTypeA() {
        return typeA;
    }

    public void setTypeA(String typeA) {
        this.typeA = typeA;
    }

    public String getSourceA() {
        return sourceA;
    }

    public void setSourceA(String sourceA) {
        this.sourceA = sourceA;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Airport airport = (Airport) o;

        if (airportId != airport.airportId) return false;
        if (airportName != null ? !airportName.equals(airport.airportName) : airport.airportName != null) return false;
        if (city != null ? !city.equals(airport.city) : airport.city != null) return false;
        if (country != null ? !country.equals(airport.country) : airport.country != null) return false;
        if (iata != null ? !iata.equals(airport.iata) : airport.iata != null) return false;
        if (icao != null ? !icao.equals(airport.icao) : airport.icao != null) return false;
        if (latitude != null ? !latitude.equals(airport.latitude) : airport.latitude != null) return false;
        if (longitude != null ? !longitude.equals(airport.longitude) : airport.longitude != null) return false;
        if (altitude != null ? !altitude.equals(airport.altitude) : airport.altitude != null) return false;
        if (timezone != null ? !timezone.equals(airport.timezone) : airport.timezone != null) return false;
        if (dst != null ? !dst.equals(airport.dst) : airport.dst != null) return false;
        if (tz != null ? !tz.equals(airport.tz) : airport.tz != null) return false;
        if (typeA != null ? !typeA.equals(airport.typeA) : airport.typeA != null) return false;
        if (sourceA != null ? !sourceA.equals(airport.sourceA) : airport.sourceA != null) return false;

        return true;
    }



    public boolean equalsString(String airportName) {
        if (this.airportName.equals(airportName)) {
            return true;
        }
        return false;
    }


    @Override
    public int hashCode() {
        int result = (int) (airportId ^ (airportId >>> 32));
        result = 31 * result + (airportName != null ? airportName.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (iata != null ? iata.hashCode() : 0);
        result = 31 * result + (icao != null ? icao.hashCode() : 0);
        result = 31 * result + (latitude != null ? latitude.hashCode() : 0);
        result = 31 * result + (longitude != null ? longitude.hashCode() : 0);
        result = 31 * result + (altitude != null ? altitude.hashCode() : 0);
        result = 31 * result + (timezone != null ? timezone.hashCode() : 0);
        result = 31 * result + (dst != null ? dst.hashCode() : 0);
        result = 31 * result + (tz != null ? tz.hashCode() : 0);
        result = 31 * result + (typeA != null ? typeA.hashCode() : 0);
        result = 31 * result + (sourceA != null ? sourceA.hashCode() : 0);
        return result;
    }

    public Route getRouteByIcao() {
        return routeByIcao;
    }

    public void setRouteByIcao(Route routeByIcao) {
        this.routeByIcao = routeByIcao;
    }

    public Route getRouteByIcao_0() {
        return routeByIcao_0;
    }

    public void setRouteByIcao_0(Route routeByIcao_0) {
        this.routeByIcao_0 = routeByIcao_0;
    }

    @Override
    public String toString() {
        return "Airport{" +
                "airportId=" + airportId +
                ", airportName='" + airportName + '\'' +

                ", iata='" + iata + '\'' +
                ", icao='" + icao + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", altitude=" + altitude +
                ", timezone=" + timezone +
                '}';
    }
}
