package c299.vendingmachine.ui;

import c299.io.UserIO;
import c299.vendingmachine.dto.Item;

public abstract class ViewImpl implements View, UserIO {

	protected UserIO io;

	public ViewImpl(UserIO io) {
		this.io = io;
	}

	public void print(String text) {
		io.print(text);
	}

	public void displaySuccessMessage() {
		io.displaySuccessMessage();
	}

	public void displayErrorMessage(String error) {
		io.displayErrorMessage(error);
	}

	public String readString(String prompt) {
		return io.readString(prompt);
	}

	public int readInt(String prompt) {
		return io.readInt(prompt);
	}

	public int readInt(String prompt, int min, int max) {
		return io.readInt(prompt, min, max);
	}

	public long readLong(String prompt) {
		return io.readLong(prompt);
	}

	public long readLong(String prompt, long min, long max) {
		return io.readLong(prompt, min, max);
	}

	public double readDouble(String prompt) {
		return io.readDouble(prompt);
	}

	public double readDouble(String prompt, double min, double max) {
		return io.readDouble(prompt, min, max);
	}

	public void render() {

		String underline = "";

		for (int i = 0; i < getTitle().length(); i++)
			underline += '=';

		io.print("\n" + underline);
		io.print(getTitle());
		io.print(underline);
	}

	private String getCreditString(int credit) {
		int dollars = credit / 100;
		int pennies = credit % 100;
		return dollars > 0

			// Printed if >= $1
			? "$" + dollars + "." + (pennies < 10 ? pennies + "0" : pennies)

			// Printed if < $1
			: pennies + "c";

	}

	public void printCredit(int credit) {
		print("Credit: " + getCreditString(credit));
	}

	public void printItemList(Item[] itemList) {
		print("Products");
		print("--------\n");
        for (Item item : itemList)
			print(item.getName() + " - " + getCreditString(item.getPrice()) + " - " + item.getQuantity() + " remaining.");
	}
}
