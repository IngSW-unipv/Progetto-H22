package model.persistence;


import model.flight.aircraft.Manufacturer;
import model.persistence.entity.*;
import model.persistence.service.*;


import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {

//////////////////////////////////////////TEST ROUTE\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
        AirportService airportService = new AirportService();
        RouteService routeService = new RouteService();
        Route route = new Route();
        /*String waypoints = "VAK8Q VAKON T484 LISKO T415 VIE L612 KAPPO L862 LUPAL " +
                            "M872 LATAN UM872 KFN UM601 EVENO M601 " +
                            "BALMA UR655 CAK UN310 LATEB J222 " +
                            "BASEM R785 ZELAF TRF P559 PUSOT M322 DATOB DATO3C";
        Airport departure = airportService.findByIcao("LIMC").get(0);
        Airport arrival = airportService.findByIcao("OMDB").get(0);
        Airport alternate = airportService.findByIata("CAI").get(0);
        route.setDeparture(departure.getIcao());
        route.setArrival(arrival.getIcao());
        route.setWaypoints(waypoints);
        route.setPrice(350.00);
        routeService.persist(route);*/





//////////////////////////////////////////TEST AIRCRAFT\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
        /*AircraftService aircraftService = new AircraftService();
        Aircraft aircraft = new Aircraft();
        aircraft.setTailNumber(5568);
        aircraft.setManufacturer(Manufacturer.AIRBUS.name());
        aircraft.setModel("B777 200-ER");
        aircraft.setMaxRange(4420.0);
        aircraft.setSeats(312);
        aircraft.setAvailability(true);
        aircraftService.persist(aircraft);*/
//////////////////////////////////////////TEST EMPLOYEE\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

        /*EmployeeService employeeService = new EmployeeService();
        Employee employee = new Employee();
        employee.setName("Keith");
        employee.setSurname("Dickens");
        employee.setRole("Captain");
        employee.setHiringDate(new Date(System.currentTimeMillis()));
        employee.setSalary(95000.00);
        employeeService.persist(employee);*/
//////////////////////////////////////////TEST FLIGHT\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

       /* FlightService flightService = new FlightService();
        Flight flight = new Flight();
        flight.setFlightNumber("AES372");
        flight.setAircraft(2);
        flight.setCrew(1);
        flight.setScheduledDate(new Date(System.currentTimeMillis()));
        flight.setScheduledTime(new Time(8,00,00));
        flight.setRouteId(1);
        flightService.persist(flight);*/
//////////////////////////////////////////TEST PASSENGER\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

        /*PassengerService passengerService = new PassengerService();
        Passenger passenger = new Passenger();
        passenger.setName("Kumar");
        passenger.setSurname("Singh");
        passenger.setBirthYear(new Date(1993-05-13)); //occhio le dateeeeee
        passenger.setClassType("Economy");
        passenger.setFlightId(2);
        passenger.setEmail("ilovePunjabiMusic@gmail.com");
        passengerService.persist(passenger);*/
//////////////////////////////////////////TEST USER\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

        System.out.println("ciaooooooooooooooo");
        Thread t = new Thread(()->{
            List<Route> routes = routeService.findByDepName("Lamezia");
            Airport airport = new Airport();
            System.out.println("ciao");
            airport = airportService.findByName("Lamezia").get(0);

            List<String> s = new ArrayList<>();

            for (int i = 0; i <routes.size() ; i++) {
                s.add(routes.get(i).getArrivalName());
            }

            for (String a:s) {
                System.out.println(a);
            }
        });

        t.start();


        //System.exit(0);


    }
}
