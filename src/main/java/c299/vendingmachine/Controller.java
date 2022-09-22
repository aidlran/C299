package c299.vendingmachine;

import c299.vendingmachine.dao.DAO;
import c299.vendingmachine.dao.DAOException;
import c299.vendingmachine.ui.ViewImpl;

public class Controller {

	private enum Menu {
		MAIN
	};

	private DAO dao;
	private ViewImpl view;
	private Menu currentMenu = Menu.MAIN;

	public Controller(DAO dao, ViewImpl view) {
		this.dao = dao;
		this.view = view;
	}

	public void run() {
		do {
			switch (currentMenu) {
				case MAIN: runMainMenu();
			}
		} while (true);
	}

	private void runMainMenu() {
		do {
			view.printItemList(dao.getAllItems());
			view.render();
			try {
				switch (view.readInt("\nEnter your choice")) {
					case 0:
						dao.close();
						System.exit(0);
						return;
					case 1:
					case 2:
						view.print("Not implemented");
						break;
					default:
						view.displayErrorMessage("Unknown command.");
				}
			} catch (DAOException e) {
				view.displayErrorMessage(e.getMessage());
			}
		} while (true);
	}
}
