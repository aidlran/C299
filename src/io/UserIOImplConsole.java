package io;

import java.util.Scanner;

public class UserIOImplConsole implements UserIO {

	private static final Scanner SCANNER = new Scanner(System.in);

	private void printInputTypeErrorMessage(String desiredInputType) {
		print("Invalid input. Please enter a " + desiredInputType + ".");
	}

	private void printInputRangeErrorMessage(String desiredInputType, String min, String max) {
		print("Invalid input. Please enter a " + desiredInputType + " between " + min + " - " + max + " inclusive.");
	}

	private void prompt(String prompt) {
		System.out.print(prompt + ": ");
	}

	@Override
	public void print(String text) {
		System.out.println(text);
	}

	@Override
	public void displaySuccessMessage() {
		print("Operation successful.");
	}

	@Override
	public void displayErrorMessage(String error) {
		print("Error: " + error);
	}

	@Override
	public String readString(String prompt) {
		this.prompt(prompt);
		return SCANNER.nextLine();
	}

	@Override
	public int readInt(String prompt) {
		do {
			try {
				return Integer.parseInt(readString(prompt));
			} catch (NumberFormatException e) {
				printInputTypeErrorMessage("integer");
			}
		} while (true);
	}

	@Override
	public int readInt(String prompt, int min, int max) {

		int input;

		do {
			if ((input = readInt(prompt)) < min || input > max)
				printInputRangeErrorMessage("integer", Integer.toString(min), Integer.toString(max));
			else return input;
		} while (true);
	}

	@Override
	public long readLong(String prompt) {
		do {
			try {
				return Long.parseLong(readString(prompt));
			} catch (NumberFormatException e) {
				printInputTypeErrorMessage("integer");
			}
		} while (true);
	}

	@Override
	public long readLong(String prompt, long min, long max) {

		long input;

		do {
			if ((input = readLong(prompt)) < min || input > max)
				printInputRangeErrorMessage("integer", Long.toString(min), Long.toString(max));
			else return input;
		} while (true);
	}

	@Override
	public double readDouble(String prompt) {
		do {
			try {
				return Double.parseDouble(readString(prompt));
			} catch (NumberFormatException e) {
				printInputTypeErrorMessage("number");
			}
		} while (true);
	}

	@Override
	public double readDouble(String prompt, double min, double max) {

		double input;

		do {
			if ((input = readDouble(prompt)) < min || input > max)
				printInputRangeErrorMessage("number", Double.toString(min), Double.toString(max));
			else return input;
		} while (true);
	}
}
