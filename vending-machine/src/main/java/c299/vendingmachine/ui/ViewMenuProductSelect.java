package c299.vendingmachine.ui;

import c299.io.UserIO;

public class ViewMenuProductSelect extends ViewImpl {

	public ViewMenuProductSelect(UserIO io) {
		super(io);
	}

	@Override
	public String getTitle() {
		return "Product select";
	}
}
