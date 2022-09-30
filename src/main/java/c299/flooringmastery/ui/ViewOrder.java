package c299.flooringmastery.ui;

import c299.flooringmastery.dto.Order;
import c299.io.UserIO;

public class ViewOrder extends ViewImpl {

    Order order;

    public ViewOrder(UserIO io, Order order) {
        super(io);
        this.order = order;
    }

    public String getTitle() {
        return "Order " + order.getOrderNumber();
    }

    public void render() {
        super.render();
        displayOrder();
    }

    public void displayOrder() {
		io.print("Order number: " + order.getOrderNumber());
		io.print("Customer Name: " + order.getCustomerName());
		io.print("State: " + order.getState());
		io.print("Tax Rate: " + order.getTaxRate());
		io.print("Product Type: " + order.getProductType());
		io.print("Area: " + order.getArea());
		io.print("Cost per Square Foot: " + order.getCostPerSquareFoot());
		io.print("Labor cost per Square Foot: " + order.getLaborCostPerSquareFoot());
		io.print("Material cost: " + order.getMaterialCost());
		io.print("Labor cost: " + order.getLaborCost());
		io.print("Tax: " + order.getTax());
		io.print("Total: " + order.getTotal());
		io.print("Order Date: " + order.getDate().substring(0, 2) + "/" + order.getDate().substring(2, 4) + "/" + order.getDate().substring(4));
    }
}
