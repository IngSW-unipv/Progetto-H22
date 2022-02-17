package model.flight.route.airport;

public class Airport {
    private String airportName;
    private String city;
    private String country;
    private String iata;
    private String icao;
    private float latitude;
    private float longitude;
    private float timeZone;

    public Airport(String airportName, String city, String country, String iata, String icao, float latitude, float longitude,float timeZone) {
        this.airportName = airportName;
        this.city = city;
        this.country = country;
        this.iata = iata;
        this.icao = icao;
        this.latitude = latitude;
        this.longitude = longitude;
        this.timeZone = timeZone;
    }

    public String getAirportName() {
        return airportName;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getIata() {
        return iata;
    }

    public String getIcao() {
        return icao;
    }

    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public float getTimeZone() {
        return timeZone;
    }

    @Override
    public String toString() {
        return
                "Airport Name: " + airportName +
                        "| City: " + city  +
                        "| Country: " + country +
                        "| IATA: " + iata +
                        "| ICAO: " + icao +
                        "| TIMEZONE: "+ timeZone;
    }


}
