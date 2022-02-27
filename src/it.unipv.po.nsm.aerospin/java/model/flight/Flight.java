package model.flight;

import model.flight.aircraft.Aircraft;
import model.flight.route.Route;

import java.sql.Time;
import java.time.LocalDateTime;

public class Flight {

    private Route route;
    private Aircraft aircraft;
    private Crew crew;
    private LocalDateTime scheduleDate;
    private Time scheduledTime;




}