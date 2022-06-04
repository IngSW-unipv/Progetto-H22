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

}
