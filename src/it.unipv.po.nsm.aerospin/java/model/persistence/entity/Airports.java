package model.persistence.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Airports {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "AIRPORTD_ID")
    private long airportdId;
    @Basic
    @Column(name = "AIRPORT_NAME")
    private String airportName;
    @Basic
    @Column(name = "CITY")
    private String city;
    @Basic
    @Column(name = "COUNTRY")
    private String country;
    @Basic
    @Column(name = "IATA")
    private String iata;
    @Basic
    @Column(name = "ICAO")
    private String icao;
    @Basic
    @Column(name = "LATITUDE")
    private Double latitude;
    @Basic
    @Column(name = "LONGITUDE")
    private Double longitude;
    @Basic
    @Column(name = "ALTITUDE")
    private Integer altitude;
    @Basic
    @Column(name = "TIMEZONE")
    private Integer timezone;
    @Basic
    @Column(name = "DST")
    private String dst;
    @Basic
    @Column(name = "TZ")
    private String tz;
    @Basic
    @Column(name = "TYPE_A")
    private String typeA;
    @Basic
    @Column(name = "SOURCE_A")
    private String sourceA;

    public long getAirportdId() {
        return airportdId;
    }

    public void setAirportdId(long airportdId) {
        this.airportdId = airportdId;
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
        Airports airports = (Airports) o;
        return airportdId == airports.airportdId && Objects.equals(airportName, airports.airportName) && Objects.equals(city, airports.city) && Objects.equals(country, airports.country) && Objects.equals(iata, airports.iata) && Objects.equals(icao, airports.icao) && Objects.equals(latitude, airports.latitude) && Objects.equals(longitude, airports.longitude) && Objects.equals(altitude, airports.altitude) && Objects.equals(timezone, airports.timezone) && Objects.equals(dst, airports.dst) && Objects.equals(tz, airports.tz) && Objects.equals(typeA, airports.typeA) && Objects.equals(sourceA, airports.sourceA);
    }

    @Override
    public int hashCode() {
        return Objects.hash(airportdId, airportName, city, country, iata, icao, latitude, longitude, altitude, timezone, dst, tz, typeA, sourceA);
    }
}
