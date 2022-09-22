package c299.vendingmachine.dao;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import c299.vendingmachine.dto.Item;
import c299.io.DataStoreException;
import c299.io.DataStoreFile;

public class DAOImplFile implements DAO {

	private static final String DELIMITER = "::";

	private final List<Item> ITEMS = new ArrayList<>();

	private DataStoreFile persist;

	public DAOImplFile(DataStoreFile dataStore) throws DAOException {
		this.persist = dataStore;
		init();
	}

	private void read() throws DataStoreException, DAOException {

		Scanner in = persist.getReader();

		ITEMS.clear();

		while (in.hasNextLine())
			ITEMS.add(unmarshall(in.nextLine()));

		in.close();
	}

	private void write() throws DataStoreException {

		PrintWriter out = persist.getWriter();

		for (Item object : ITEMS)
			out.println(marshall(object));

		out.flush();
		out.close();
	}

	private Item unmarshall(String marshalledObject) throws DAOException {

		String[] tokens = marshalledObject.split(DELIMITER);

		Item object = new Item();
		object.setName(tokens[0]);

		try {
			object.setPrice(Integer.parseInt(tokens[1]));
			object.setQuantity(Integer.parseInt(tokens[2]));
		} catch (NumberFormatException e) {
			throw new DAOException("Failed to parse integer for: " + object.getName(), e);
		}

		return object;
	}

	private String marshall(Item object) {
		return object.getName()
			+ DELIMITER + object.getPrice()
			+ DELIMITER + object.getQuantity();
	}

	@Override
	public void init() throws DAOException {
		try {
			read();
		} catch (DataStoreException e) {
			throw new DAOException(e.getMessage(), e);
		}
	}

	@Override
	public void close() throws DAOException {
		try {
			write();
		} catch (DataStoreException e) {
			throw new DAOException(e.getMessage(), e);
		}
	}

	@Override
	public Item[] getAllItems() {

		int count = ITEMS.size();
		Item[] all = new Item[count];

		for (int i = 0; i < count; i++)
			all[i] = ITEMS.get(i);

		return all;
	}
}
