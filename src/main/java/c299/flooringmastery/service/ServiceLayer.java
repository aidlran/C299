package c299.flooringmastery.service;

import c299.flooringmastery.dao.DAOException;
import c299.flooringmastery.dto.Order;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface ServiceLayer {

	void loadAllData() throws DAOException;
	void saveAllData() throws DAOException;
	void exportAllData() throws DAOException;

	Order getOrder(int orderNumber);
	List<Order> getAllOrders();
	List<Order> getOrders(String dateString);
	Order addOrder(Order order) throws InvalidOrderException;
	Order removeOrder(int orderNumber);
	Order editOrder(int orderNumber, Order newOrder) throws InvalidOrderException;
	
	Date convertDateStringToDate(String dateString) throws InvalidDateStringException;
	String convertDateToDateString(Date date);

	boolean validateDateString(String dateString);
	boolean validateCustomerName(String customerName);
	boolean validateStateCode(String stateCode);
	boolean validateProduct(String product);
	boolean validateArea(BigDecimal area);

	boolean validateOrder(Order order);

	int getNextOrderNumber();
}
