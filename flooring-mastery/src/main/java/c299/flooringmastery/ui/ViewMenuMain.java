package c299.flooringmastery.ui;

import c299.io.UserIO;

public class ViewMenuMain extends ViewImpl {

	UserIO io;

	public ViewMenuMain(UserIO io) {
		super(io);
	}

	public String getTitle() {
		return "Main Menu";
	}

	@Override
	public void render() {

		super.render();

		io.print("1: Manage an order by order number");
		io.print("2: Display all orders");
		io.print("3: Search orders by date");
		io.print("4: Add a new order");
		io.print("5: Delete an order");
		io.print("6: Export all data");
		io.print("7: Save all data");
		io.print("\n0: Quit");
	}
}
