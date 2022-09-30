package c299.flooringmastery.service;

import c299.flooringmastery.dao.DAO;
import c299.flooringmastery.dao.DAOException;
import c299.flooringmastery.dto.Order;
import c299.flooringmastery.dto.Product;
import c299.flooringmastery.dto.StateTax;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ServiceLayerImpl implements ServiceLayer {

	private static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("mm-dd-yyyy");
	private DAO dao;
	private int greatestOrderNumber;

	public ServiceLayerImpl(DAO dao) {
		this.dao = dao;
	}

	@Override
	public void loadAllData() throws DAOException {
		dao.loadAllData();
	}

	@Override
	public void saveAllData() throws DAOException {
		dao.saveOrders();
	}

	@Override
	public void exportAllData() throws DAOException {
		dao.exportOrders();
	}

	@Override
	public Order getOrder(int orderNumber) {
		return dao.getOrder(orderNumber);
	}

	@Override
	public List<Order> getAllOrders() {
		return dao.getAllOrders();
	}

	@Override
	public List<Order> getOrders(String dateString) {
		return getAllOrders().stream()
			.filter(order -> order.getDate().equals(dateString))
			.collect(Collectors.toList());
	}

	@Override
	public Order addOrder(Order order) throws InvalidOrderException {
		if (validateOrder(order)) return dao.addOrder(recalculateOrder(order));
		else throw new InvalidOrderException("Cannot add an order with invalid field(s).");
	}

	@Override
	public Order removeOrder(int orderNumber) {
		return dao.removeOrder(orderNumber);
	}

	@Override
	public Order editOrder(int orderNumber, Order newOrder) throws InvalidOrderException {
		if (validateOrder(newOrder)) return dao.editOrder(orderNumber, recalculateOrder(newOrder));
		else throw new InvalidOrderException("Order has invalid field(s).");
	}

	private Order recalculateOrder(Order order) {

		StateTax state = dao.getStateTax(order.getState());
		Product product = dao.getProduct(order.getProductType());

		// Get values from State/Product
		order.setTaxRate(state.getTaxRate());
		order.setCostPerSquareFoot(product.getCostPerSquareFoot());
		order.setLaborCostPerSquareFoot(product.getLaborCostPerSquareFoot());

		// Calculate other values
		order.setMaterialCost(order.getArea()
			.multiply(order.getCostPerSquareFoot())
			.setScale(2, RoundingMode.HALF_UP));

		order.setLaborCost(order.getArea()
			.multiply(order.getLaborCostPerSquareFoot())
			.setScale(2, RoundingMode.HALF_UP));

		order.setTax(order.getMaterialCost()
			.add(order.getLaborCost())
			.multiply(order.getTaxRate())
			.divide(new BigDecimal("100"))
			.setScale(2, RoundingMode.HALF_UP));

		order.setTotal(order.getMaterialCost()
			.add(order.getLaborCost())
			.add(order.getTax()));

		return order;
	}

	@Override
	public Date convertDateStringToDate(String dateString) throws InvalidDateStringException {
		try {
			return DATE_FORMATTER.parse(dateString);
		}
		catch (ParseException e) {
			throw new InvalidDateStringException(
				"Unrecognised date format. Please use 'MM-dd-yyyy'.", e);
		}
	}

	@Override
	public String convertDateToDateString(Date date) {
		return DATE_FORMATTER.format(date);
	}

	@Override
	public boolean validateDateString(String dateString) {
		try {
			return convertDateStringToDate(dateString)
				.after(new Date(System.currentTimeMillis()));
		} catch (InvalidDateStringException e) {
			return false;
		}
	}

	@Override
	public boolean validateCustomerName(String customerName) {
		return customerName != null && customerName.trim().length() > 0;
	}

	@Override
	public boolean validateStateCode(String stateCode) {
		return dao.getStateTax(stateCode) != null;
	}

	@Override
	public boolean validateProduct(String product) {
		return dao.getProduct(product) != null;
	}

	@Override
	public boolean validateArea(BigDecimal area) {
		return area.compareTo(new BigDecimal("0")) != 0;
	}

	@Override
	public boolean validateOrder(Order order) {
		return validateCustomerName(order.getCustomerName())
			&& order.getState() != null
			&& validateProduct(order.getProductType())
			&& validateArea(order.getArea());
	}

	@Override
	public int getNextOrderNumber() {

		if (greatestOrderNumber < 1) {

			int currOrderNumber;

			for (Order order : dao.getAllOrders()) {
				currOrderNumber = order.getOrderNumber();
				if (currOrderNumber > greatestOrderNumber) {
					greatestOrderNumber = currOrderNumber;
				}
			}
		}

		return greatestOrderNumber + 1; 
	}
}
