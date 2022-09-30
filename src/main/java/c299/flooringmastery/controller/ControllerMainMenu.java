package c299.flooringmastery.controller;

import java.math.BigDecimal;
import java.util.List;

import c299.flooringmastery.dao.DAOException;
import c299.flooringmastery.dto.Order;
import c299.flooringmastery.service.InvalidDateStringException;
import c299.flooringmastery.service.InvalidOrderException;
import c299.flooringmastery.service.ServiceLayer;
import c299.flooringmastery.ui.ViewImpl;
import c299.flooringmastery.ui.ViewListOrders;
import c299.io.UserIO;

public class ControllerMainMenu extends ControllerImpl {

	public ControllerMainMenu(ServiceLayer service, UserIO userIO, ViewImpl view) {
		super(service, userIO, view);
	}

	@Override
	public Controller run() {

		do {

			super.run();

			try {
				switch (userIO.readInt("Enter your choice (0-6)", 0, 6)) {

					case 1:
						searchOrdersByDate();
						break;

					case 2:
						addOrder();
						break;

					case 3:
						editOrder();
						break;

					case 4:
						removeOrder();
						break;

					case 5:
						exportAllData();
						break;

					case 6:
						displayAllOrders();
						break;

					case 0:
						saveAllData();
						return null;
				}
			} catch (InvalidOrderException e) {
				view.displayErrorMessage(e.getMessage());
			}
		} while (true);
	}

	private Integer getOrderNumber() {

		Integer orderNumber = view.readInt("Enter an order number");

		if (service.getOrder(orderNumber) == null) {
			orderNumber = null;
			view.displayErrorMessage("Order not found!");
		}

		return orderNumber;
	}

	private Order getEditedOrder(Order order) {

		String inputString;
		BigDecimal inputDecimal;

		do {
			inputString = view.readString("Enter a customer name (" + order.getCustomerName() + ")");
			if (service.validateCustomerName(inputString)) {
				order.setCustomerName(inputString);
				break;
			} else view.displayErrorMessage("Invalid customer name.");
		} while (true);

		do {
			inputString = view.readString("Enter a two-letter state code (" + order.getState() + ")");
			if (service.validateStateCode(inputString)) {
				order.setState(inputString);
				break;
			} else view.displayErrorMessage("Invalid or unsupported state code.");
		} while (true);

		do {
			inputString = view.readString("Enter a product (" + order.getProductType() + ")");
			if (service.validateProduct(inputString)) {
				order.setProductType(inputString);
				break;
			} else view.displayErrorMessage("Invalid or unsupported product.");
		} while (true);

		do {
			inputString = view.readString("Enter the area in sq. ft. (" + order.getProductType() + ")");
			if (service.validateArea(inputDecimal = new BigDecimal(inputString))) {
				order.setArea(inputDecimal);
				break;
			} else view.displayErrorMessage("Invalid number.");
		} while (true);

		order.setDate(getDateInput());

		return order;
	}

	private void editOrder() throws InvalidOrderException {
		Integer orderNumber = getOrderNumber();
		if (orderNumber != null) {
			service.editOrder(orderNumber, getEditedOrder(service.getOrder(orderNumber)));
		}
	}

	private void removeOrder() {
		Integer orderNumber = getOrderNumber();
		if (orderNumber != null) {
			service.removeOrder(orderNumber);
			view.displaySuccessMessage();
		}
	}

	private void exportAllData() {
		try {
			service.exportAllData();
		} catch (DAOException e) {
			view.displayErrorMessage(e.getMessage());
		}
	}

	private void saveAllData() {
		try {
			service.saveAllData();
		}
		catch (DAOException e) {
			view.displayErrorMessage(e.getMessage());
		}
	}

	private void displayAllOrders() {
		new ViewListOrders(userIO, service.getAllOrders()).render();
	}

	private String getDateInput() {
		do {
			String input = userIO.readString("Enter date (MM-DD-YYYY)");
			try {
				service.convertDateStringToDate(input);
				return input;
			} catch (InvalidDateStringException e) {
				view.displayErrorMessage("Unrecognised date.");
			}
		} while (true);
	}

	private void addOrder() throws InvalidOrderException {
		if (service.addOrder(getEditedOrder(new Order(service.getNextOrderNumber()))) != null)
			view.displaySuccessMessage();
	}

	private void searchOrdersByDate() {

		List<Order> result = service.getOrders(getDateInput());

		if (result.size() > 0) new ViewListOrders(userIO, result).render();
		else view.print("No orders found.");
	}
}
