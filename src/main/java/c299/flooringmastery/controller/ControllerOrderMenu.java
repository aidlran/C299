package c299.flooringmastery.controller;

import c299.flooringmastery.dto.Order;
import c299.flooringmastery.service.ServiceLayer;
import c299.flooringmastery.ui.ViewImpl;
import c299.io.UserIO;

/**
 * Unused.
 * Was going to be a menu where individual order fields can be edited one at a time,
 * but spec wanted to ask for all fields at once so I did that.
 * This method would likely have led to better UX.
 */
public class ControllerOrderMenu extends ControllerImpl {

    Order order;

    public ControllerOrderMenu(ServiceLayer service, UserIO userIO, ViewImpl view, Order order) {
        super(service, userIO, view);
        this.order = order;
    }

    public Controller run() {

        super.run();

        do {
            switch (view.readInt("Enter your choice", 0, 0)) {
                case 0:
                    return new ControllerMainMenu(service, userIO, view);
            }
        } while (true);
    }
}
