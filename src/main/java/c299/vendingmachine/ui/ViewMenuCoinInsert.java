package c299.vendingmachine.ui;

import c299.io.UserIO;
import c299.vendingmachine.dto.Change;

public class ViewMenuCoinInsert extends ViewImpl {

	public ViewMenuCoinInsert(UserIO io) {
		super(io);
	}

	@Override
	public String getTitle() {
		return "Insert Coins";
	}

	@Override
	public void render() {

		super.render();

		io.print("");
		io.print(Change.PENNY + ": Insert penny");
		io.print(Change.NICKEL + ": Insert nickel");
		io.print(Change.DIME + ": Insert dime");
		io.print(Change.QUARTER + ": Insert quarter");
		io.print("");
		io.print("0: Back");
	}
}
