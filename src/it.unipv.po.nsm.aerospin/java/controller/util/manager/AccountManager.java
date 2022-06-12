package controller.util.manager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Factory;
import model.exception.NoMatchException;
import model.persistence.entity.Orders;
import model.persistence.service.OrdersService;
import java.util.List;

public class AccountManager {
    private final OrdersService service = new OrdersService();

    public ObservableList<Orders> getOrders() throws NoMatchException {
        List<Orders> orders = service.findByUser(Factory.getInstance().getSession().getUser());
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

    public String getDetailText(Orders order) {
        return "Il " + order.getOrderDate()
                + " hai acquistato il volo " + order.getFlightById().getFlightNumber()
                + "\nda " + order.getFlightById().getRouteById().getAirportDep().getAirportName()
                + "\na " + order.getFlightById().getRouteById().getAirportArr().getAirportName()
                + "\ndel " + order.getFlightById().getScheduledDate()
                + "\nal prezzo di " + order.getPrice()
                + " €\ncon la tariffa " + order.getFare()
                + "\npagato con carta che termina *" + order.getCardDetails()
                + "\nper il passeggero " + order.getPassengerById().getSurname()
                + " " + order.getPassengerById().getName();
    }
}
