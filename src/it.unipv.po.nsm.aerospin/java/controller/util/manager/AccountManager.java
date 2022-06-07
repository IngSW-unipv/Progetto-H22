package controller.util.manager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Factory;
import model.exception.NoMatchException;
import model.persistence.entity.Orders;
import model.persistence.service.OrdersService;

import java.util.List;

public class AccountManager {

    OrdersService ordersService = new OrdersService();

    public ObservableList<Orders> getOrders() throws NoMatchException {

        List<Orders> orders = ordersService.findByEmail(Factory.getInstance().getSession().getUser().getEmail());
        orders.sort((o1, o2) -> {
            if(o1.getOrderDate().before(o2.getOrderDate())) {
                return -1;
            } else {
                return 0;
            }
        });
        if(orders.size() == 0){
            throw new NoMatchException("Not Matched!\n");
        } else {
            return FXCollections.observableArrayList(orders);
        }
    }

    public String detailText(Orders order) {
        return "Il " + order.getOrderDate() + " hai acquistato il volo\n"
                + "da "
                + order.getFlightByFlightId().getRouteByFlightRouteId().getAirportByDeparture().getAirportName()
                + "\na "
                + order.getFlightByFlightId().getRouteByFlightRouteId().getAirportByArrival().getAirportName()
                + "\ndel " + order.getFlightByFlightId().getScheduledDate() + "\n"
                + "al prezzo di " + order.getPrice()
                + " €\ncon la tariffa " + order.getFare() + "\n"
                + "pagato con carta che termina *" + order.getCardDetails() + "\n"
                + "per il passeggero " + order.getPassengerByPassengerId().getSurname()
                + " " + order.getPassengerByPassengerId().getName();
    }

}
