package c299.vendingmachine;

import c299.io.UserIO;
import c299.vendingmachine.dao.DAO;
import c299.vendingmachine.dao.DAOException;
import c299.vendingmachine.dto.Change;
import c299.vendingmachine.ui.ViewImpl;
import c299.vendingmachine.ui.ViewMenuCoinInsert;
import c299.vendingmachine.ui.ViewMenuMain;

public class Controller {

	private enum Menu {
		MAIN,
		COIN_INSERT
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
			view.printItemList(dao.getAllItems());
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
				view.print("Not implemented");
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
}
