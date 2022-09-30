package c299.flooringmastery.dao;

import c299.flooringmastery.dto.Order;
import c299.flooringmastery.dto.Product;
import c299.flooringmastery.dto.StateTax;

import java.util.List;

public interface DAO {

	boolean hasOrder(int orderNumber);
	Order getOrder(int orderNumber);
	List<Order> getAllOrders();
	Order addOrder(Order order);
	Order editOrder(int orderNumber, Order newOrder);
	Order removeOrder(int orderNumber);

	void loadAllData() throws DAOException;
	void saveOrders() throws DAOException;
	void exportOrders() throws DAOException;

	StateTax getStateTax(String state);
	Product getProduct(String productType);
}
