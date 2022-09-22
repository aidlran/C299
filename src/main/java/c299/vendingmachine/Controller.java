package c299.vendingmachine;

import c299.io.UserIO;
import c299.vendingmachine.dao.DAO;
import c299.vendingmachine.dao.DAOException;
import c299.vendingmachine.dto.Change;
import c299.vendingmachine.dto.Item;
import c299.vendingmachine.ui.ViewImpl;
import c299.vendingmachine.ui.ViewMenuCoinInsert;
import c299.vendingmachine.ui.ViewMenuMain;
import c299.vendingmachine.ui.ViewMenuProductSelect;

public class Controller {

	private enum Menu {
		MAIN,
		COIN_INSERT,
		PRODUCT_SELECT
	};

	private DAO dao;
	private UserIO userIO;
	private ViewImpl view;
	private Menu currentMenu = Menu.MAIN;
	private int credit = 0;

	public Controller(DAO dao, UserIO userIO, ViewImpl view) {
		this.dao = dao;
		this.userIO = userIO;
		this.view = view;
	}

	public void run() {
		do {
			Item[] allItems = dao.getAllItems();
			view.printItemList(allItems, currentMenu == Menu.PRODUCT_SELECT);
			view.render();
			view.print("");
			view.printCredit(credit);
			try {
				switch (currentMenu) {
					case MAIN:
						runMainMenu();
						break;
					case COIN_INSERT:
						runCoinInsert();
						break;
					case PRODUCT_SELECT:
						runProductSelect(allItems);
						break;
					default:
						return;
				}
			} catch (DAOException e) {
				view.displayErrorMessage(e.getMessage());
			}
		} while (true);
	}

	private void runMainMenu() throws DAOException {
		switch (view.readInt("\nEnter your choice (0-2)", 0, 2)) {
			case 0:
				dao.close();
				view = null;
				return;
			case 1:
				view = new ViewMenuCoinInsert(userIO);
				currentMenu = Menu.COIN_INSERT;
				return;
			case 2:
				if (credit > 0) {
					view = new ViewMenuProductSelect(userIO);
					currentMenu = Menu.PRODUCT_SELECT;
				} else view.displayErrorMessage("No credit.");
				break;
			default:
				view.displayErrorMessage("Unknown command.");
		}
	}

	private void runCoinInsert() {
		int input = view.readInt("\nEnter your choice");
		switch (input) {
			case 0:
				view = new ViewMenuMain(userIO);
				currentMenu = Menu.MAIN;
				return;
			case Change.PENNY:
			case Change.NICKEL:
			case Change.DIME:
			case Change.QUARTER:
				credit += input;
				break;
			default:
				view.displayErrorMessage("Unknown command.");
		}
	}

	private void runProductSelect(Item[] itemList) {
		Item product = itemList[view.readInt("\nEnter your choice", 0, itemList.length) - 1];
		if (product.getQuantity() < 1)
			view.displayErrorMessage("No " + product.getName() + " remaining. Sorry.");
		else if (credit >= product.getPrice()) {
			product.decrementQuantity();
			Change change = new Change(credit -= product.getPrice());
			view.print("Thank you for your purchase of: " + product.getName());
			view.print("Your change:");
			if (change.getQuarters() > 0) view.print("Quarters: " + change.getQuarters());
			if (change.getDimes() > 0) view.print("Dimes: " + change.getDimes());
			if (change.getNickels() > 0) view.print("Nickels: " + change.getNickels());
			if (change.getPennies() > 0) view.print("Pennies: " + change.getPennies());
			credit = 0;
			view = new ViewMenuMain(userIO);
			currentMenu = Menu.MAIN;
		} else view.displayErrorMessage("Insufficient funds.");
	}
}
