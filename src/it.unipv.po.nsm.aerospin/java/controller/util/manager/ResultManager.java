package controller.util.manager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.util.Callback;
import model.persistence.CachedFlights;
import model.persistence.entity.Flight;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

public class ResultManager {

    CachedFlights cachedFlights = CachedFlights.getInstance();
    List<Flight> results = cachedFlights.findAll();

    public ObservableList<Flight> getFlights(String dep, String ret, Date date) {

        List<Flight> departures;
        departures = results.stream()
                .filter(o -> o.getRouteByFlightRouteId().getAirportByDeparture().equalsString(dep))
                .filter(o -> o.getRouteByFlightRouteId().getAirportByArrival().equalsString(ret))
                .filter(o -> o.getScheduledDate().equals(date))
                .distinct()
                .collect(Collectors.toList());

        return FXCollections.observableArrayList(departures);
    }

    /*  Controllo se l'età selezionata è > 16
     *  e permetto di selezionare solo date passate
     *  come data di nascita
     */
    public boolean isMinor(LocalDate birthDate){
        LocalDate today = LocalDate.now();
        Period age = Period.between(birthDate, today);
        long years = age.getYears();
        return years < 16;
    }
    public Callback<DatePicker, DateCell> ageRange() {
        return new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(final DatePicker param) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate date, boolean empty) {
                        super.updateItem(date, empty);
                        LocalDate today = LocalDate.now().minusDays(1);
                        setDisable(empty || date.isAfter(today));
                    }
                };
            }
        };
    }
}
