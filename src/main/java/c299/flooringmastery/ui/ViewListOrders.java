package c299.flooringmastery.ui;

import java.util.List;

import c299.flooringmastery.dto.Order;
import c299.io.UserIO;

public class ViewListOrders extends ViewImpl {

    List<Order> orders;

    public ViewListOrders(UserIO io, List<Order> orders) {
        super(io);
        this.orders = orders;
    }

    public String getTitle() {
        return "Listing Orders";
    }

    public void render() {
        for (Order order : orders) {
            new ViewOrder(io, order).displayOrder();
            io.print("");
        }
    }
}
