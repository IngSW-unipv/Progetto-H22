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
        if(orders.size() == 0){
            throw new NoMatchException("Not Matched!\n");
        } else {
            return FXCollections.observableArrayList(orders);
        }
    }



//    Il 29/29/2929 (ora?) hai acquistato il volo
//    da Cava Manara Airport a Lamezia Terme Airport del 30/30/3903
//    al prezzo di XXX € in classe STANDARD
//    pagato con carta che termina 7458
//    per il passeggero Davide Morano
    public String detailText(Orders order) {
        return "Il " + order.getOrderDate() + " hai acquistato il volo\n"
                + "da " + order.getFlightByFlightId().getRouteByFlightRouteId().getAirportByDeparture().getAirportName()
                + " a " + order.getFlightByFlightId().getRouteByFlightRouteId().getAirportByArrival().getAirportName()
                + " del " + order.getFlightByFlightId().getScheduledDate() + "\n"
                + "al prezzo di " + order.getPrice() + " € in classe " + order.getFaire() +"\n"
                + "pagato con carta che termina *" + order.getCardDetails() + "\n"
                + "per il passeggero " + order.getPassengerByPassengerId().getSurname()
                + " " + order.getPassengerByPassengerId().getName();
    }

}
