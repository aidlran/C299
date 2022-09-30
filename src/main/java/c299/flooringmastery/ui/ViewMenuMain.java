package c299.flooringmastery.ui;

import c299.io.UserIO;

public class ViewMenuMain extends ViewImpl {

	public ViewMenuMain(UserIO io) {
		super(io);
	}

	public String getTitle() {
		return "Main Menu";
	}

	@Override
	public void render() {
		super.render();
		displayMenu();
	}

	private void displayMenu() {
		io.print("  * * * * * * * * * * * * * * * * * * * * *");
		io.print("  *  TSG Corp. Flooring Program");
		io.print("  *");
		io.print("  *  1. Display orders for a date");
		io.print("  *  2. Add a new order");
		io.print("  *  3. Edit an order");
		io.print("  *  4. Remove an order");
		io.print("  *  5. Export all data");
		io.print("  *  6. Display all orders");
		io.print("  *");
		io.print("  *  0. Quit");
		io.print("  *");
		io.print("  * * * * * * * * * * * * * * * * * * * * *");
	}
}
