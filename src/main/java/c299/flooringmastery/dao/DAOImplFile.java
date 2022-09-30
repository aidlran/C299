package c299.flooringmastery.dao;

import c299.flooringmastery.dto.Order;
import c299.flooringmastery.dto.Product;
import c299.flooringmastery.dto.StateTax;
import c299.io.DataStoreException;
import c299.io.DataStoreFile;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class DAOImplFile implements DAO{

	private static final String
		BASE_DIR = "data/",
		DATA_DIR = BASE_DIR + "Data/",
		ORDER_DIR = BASE_DIR + "Orders/",
		BACKUP_DIR = BASE_DIR + "Backup/",
		DELIMITER = "::";

	private static final String[] ORDER_HEADER_LABELS = {
		"OrderNumber",
		"CustomerName",
		"State",
		"TaxRate",
		"Product",
		"Area",
		"CostPerSquareFoot",
		"LaborCostPerSquareFoot",
		"MaterialCost",
		"LaborCost",
		"Tax",
		"Total",
		"OrderDate"
	};

	private final DataStoreFile
		FILE_STATE_TAXES = new DataStoreFile(DATA_DIR + "StateTaxes.txt"),
		FILE_PRODUCTS = new DataStoreFile(DATA_DIR + "Products.txt"),
		FILE_EXPORT = new DataStoreFile(BACKUP_DIR + "DataExport.txt");

	private final Map<Integer, Order> MAP_ORDERS = new HashMap<>();
	private final Map<String, StateTax> MAP_STATE_TAXES = new HashMap<>();
	private final Map<String, Product> MAP_PRODUCTS = new HashMap<>();


	@Override
	public void loadAllData() throws DAOException {
		try {
			loadOrders();
			loadStateTaxes();
			loadProducts();
		} catch (DataStoreException e) {
			throw new DAOException(e.getMessage(), e);
		}
	}


	/* TODO
	 * The DAO uses HashMaps, so each type (products, states, orders) 
	 * could use the same CRUD DAO class generic which implements all
	 * of the CRUD functionality for a HashMap reducing code repetition.
	 */


	// Orders

	@Override
	public boolean hasOrder(int orderNumber) {
		return MAP_ORDERS.containsKey(orderNumber);
	}

	@Override
	public Order getOrder(int orderId) {
		return MAP_ORDERS.get(orderId);
	}

	@Override
	public List<Order> getAllOrders() {
		return new ArrayList<Order>(MAP_ORDERS.values());
	}

	@Override
	public Order addOrder(Order order) {
		return hasOrder(order.getOrderNumber()) ? MAP_ORDERS.put(order.getOrderNumber(), order) : null;
	}

	@Override
	public Order editOrder(int orderNumber, Order newOrder) {
		return hasOrder(orderNumber) ? MAP_ORDERS.put(orderNumber, newOrder) : null;
	}

	@Override
	public Order removeOrder(int orderNumber) {
		return MAP_ORDERS.remove(orderNumber);
	}

	private List<File> getOrderFiles() {
		File[] fileList = new File(ORDER_DIR).listFiles();
		return fileList == null ? null : Arrays.stream(fileList)
		.filter(file -> file.getName().startsWith("Orders_"))
		.collect(Collectors.toList());
	}

	private void loadOrders() throws DataStoreException {

		List<File> fileList = getOrderFiles();
		if (fileList == null) return;

		for (String fileName : fileList.stream()
			.map(file -> file.getName())
			.collect(Collectors.toList()))
		{
			Scanner scanner = new DataStoreFile(ORDER_DIR + fileName).getReader();

			// Skip header
			scanner.nextLine();

			Order current;
	
			while (scanner.hasNextLine()) {
				current = unmarshallOrder(scanner.nextLine());
				MAP_ORDERS.put(current.getOrderNumber(), current);
			}
	
			scanner.close();
		}
	}

	/**
	 * Write all of the orders into multiple files grouped by order date.
	 */
	@Override
	public void saveOrders() throws DAOException{

		List<File> fileList = getOrderFiles();

		for (File file : fileList) if (!file.isDirectory()) file.delete();

		PrintWriter out;

		// Group orders by the order date.
		Map<String, List<Order>> fileMap = getAllOrders().stream()
			.collect(Collectors.groupingBy(order -> order.getDate()));

		// Iterate on the groupings
		for (String date : fileMap.keySet()) {

			// Create and open file for the date group
			try {
				out = new DataStoreFile(ORDER_DIR + "Orders_" + date + ".txt").getWriter();
			} catch (DataStoreException e) {
				throw new DAOException(e.getMessage(), e);
			}

			// Write the file header labels
			for (int i = 0; i < ORDER_HEADER_LABELS.length;) {
				out.print(ORDER_HEADER_LABELS[i]);
				if (++i != ORDER_HEADER_LABELS.length) out.print("::");
			}

			// Write orders
			for (Order currentOrder : fileMap.get(date))
				out.println(marshallOrder(currentOrder));

			out.flush();
			out.close();
		}
	}

	/**
	 * Write all of the order files into a single file.
	 */
	@Override
	public void exportOrders() throws DAOException {

		PrintWriter out;

		// Open file.
		try {
			out = FILE_EXPORT.getWriter();
		}
		catch (DataStoreException e) {
			throw new DAOException(e.getMessage(), e);
		}

		// Write the file header labels
		for (int i = 0; i < ORDER_HEADER_LABELS.length;) {
			out.print(ORDER_HEADER_LABELS[i]);
			if (++i != ORDER_HEADER_LABELS.length) out.print("::");
		}

		// Write orders
		for (Order order : getAllOrders())
			out.println(marshallOrder(order));

		out.flush();
		out.close();
	}

	private String marshallOrder(Order order) {
		return order.getOrderNumber() + DELIMITER
			+ order.getCustomerName() + DELIMITER
			+ order.getState() + DELIMITER
			+ order.getTaxRate() + DELIMITER
			+ order.getProductType() + DELIMITER
			+ order.getArea() + DELIMITER
			+ order.getCostPerSquareFoot() + DELIMITER
			+ order.getLaborCostPerSquareFoot() + DELIMITER
			+ order.getMaterialCost() + DELIMITER
			+ order.getLaborCost() + DELIMITER
			+ order.getTax() + DELIMITER
			+ order.getTotal() + DELIMITER
			+ order.getDate();
	}
	
	private Order unmarshallOrder(String orderEntryText) {

		String[] orderTokens = orderEntryText.split(DELIMITER);
		int i = 0;

		Order orderFromFile = new Order(Integer.parseInt(orderTokens[i++]));

		orderFromFile.setCustomerName(orderTokens[i++]);
		orderFromFile.setState(orderTokens[i++]);
		orderFromFile.setTaxRate(new BigDecimal(orderTokens[i++]));
		orderFromFile.setProductType(orderTokens[i++]);
		orderFromFile.setArea(new BigDecimal(orderTokens[i++]));
		orderFromFile.setCostPerSquareFoot(new BigDecimal(orderTokens[i++]));
		orderFromFile.setLaborCostPerSquareFoot(new BigDecimal(orderTokens[i++]));
		orderFromFile.setMaterialCost(new BigDecimal(orderTokens[i++]));
		orderFromFile.setLaborCost(new BigDecimal(orderTokens[i++]));
		orderFromFile.setTax(new BigDecimal(orderTokens[i++]));
		orderFromFile.setTotal(new BigDecimal(orderTokens[i++]));
		orderFromFile.setDate(orderTokens[i]);

		// Don't mind the calculated fields here.
		// Calculation will be done with a separate method in service layer.
		return orderFromFile;
	}


	// States & Tax Rates

	@Override
	public StateTax getStateTax(String stateCode) {
		return MAP_STATE_TAXES.get(stateCode);
	}

	private StateTax unmarshallTax(String taxEntryText) {
		String[] taxTokens = taxEntryText.split(DELIMITER);
		return new StateTax(taxTokens[0], taxTokens[1], new BigDecimal(taxTokens[2]));
	}

	private void loadStateTaxes() throws DataStoreException{

		Scanner scanner = FILE_STATE_TAXES.getReader();

		// Skip header
		scanner.nextLine();

		StateTax current;

		while (scanner.hasNextLine()) {
			current = unmarshallTax(scanner.nextLine());
			MAP_STATE_TAXES.put(current.getStateCode(), current);
		}

		scanner.close();
	}


	// Products

	@Override
	public Product getProduct(String productType) {
		return MAP_PRODUCTS.get(productType);
	}


	private Product unmarshallProduct(String productEntryText) {
		String[] productTokens = productEntryText.split(DELIMITER);
		return new Product(productTokens[0],  new BigDecimal(productTokens[1]), new BigDecimal(productTokens[2]));
	}

	private void loadProducts() throws DataStoreException {

		Scanner scanner = FILE_PRODUCTS.getReader();

		// Skip header
		scanner.nextLine();

		Product current;

		while (scanner.hasNextLine()) {
			current = unmarshallProduct(scanner.nextLine());
			MAP_PRODUCTS.put(current.getProductType(), current);
		}

		scanner.close();
	}

	// Order file
}
