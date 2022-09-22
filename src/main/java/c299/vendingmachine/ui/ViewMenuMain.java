package c299.vendingmachine.ui;

import c299.io.UserIO;

public class ViewMenuMain extends ViewImpl {

	public ViewMenuMain(UserIO io) {
		super(io);
	}

	@Override
	public String getTitle() {
		return "Main Menu";
	}

	@Override
	public void render() {

		super.render();

		io.print("");
		io.print("1: Insert coins");
		io.print("2: Choose a product");
		io.print("");
		io.print("0: Exit");
	}
}
