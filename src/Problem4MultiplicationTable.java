// Write a program that prints a multiplication table for numbers up to 12.

public class Problem4MultiplicationTable {

	/**
	 * The size of the multiplication table to print.
	 */
	private static final int MAX = 12;

	/**
	 * Number of integers in `MAX`.
	 */
	private static final int MAX_DIGIT_COUNT = getIntegerDigitCount(MAX);

	/**
	 * Number of integers in the greatest product that will be printed.
	 */
	private static final int MAX_PRODUCT_DIGIT_COUNT = getIntegerDigitCount(MAX * MAX);

	public static void main(String[] args) {

		printLeftIndent(); // prints a gap on gap rows of the left axis labels

		// Top axis labels
		for (int i = 1; i <= MAX; i++) {
			System.out.print(" ");
			printCell(i);
		}

		System.out.println();

		// First +---+---+ border
		printHorizontalBorder();

		// Print each multiplication table row
		for (int x = 1; x <= MAX; x++) {

			printLeftIndent(x); // Print the left axis label

			// Print each cell of the multiplication row
			for (int y = 1; y <= MAX; y++) {

				int product = x * y;

				System.out.print("|");

				printCell(product);
			}

			// Print the right border for the row
			System.out.println("|");

			// Print a +---+---+ border
			printHorizontalBorder();
		}

		// Done
	}

	/**
	 * Counts the number of digits in the given integer.
	 * @param n An integer.
	 */
	private static int getIntegerDigitCount(int n) {
		return (int) (Math.log10(n) + 1);
	}

	/**
	 * Prints an ASCII style table border. e.g. `+---+---+`
	 */
	private static void printHorizontalBorder() {

		printLeftIndent();

		for (int i = 0; i < MAX; i++) {

			System.out.print("+");

			for (int space = -2; space < MAX_PRODUCT_DIGIT_COUNT; space++)
				System.out.print("-");
		}

		System.out.println("+");
	}

	/**
	 * Prints the given digit in a table cell, accounting for whitespace.
	 * @param n An integer.
	 */
	private static void printCell(int n) {

		System.out.print(" " + n);

		for (int space = -1; space < MAX_PRODUCT_DIGIT_COUNT - getIntegerDigitCount(n); space++)
			System.out.print(" ");
	}

	/**
	 * Prints a gap for the left axis to keep the row aligned.
	 */
	private static void printLeftIndent() {
		for (int i = -1; i < MAX_DIGIT_COUNT; i++)
			System.out.print(" ");
	}

	/**
	 * Prints the given digit on the left axis, accounting for whitespace.
	 * @param n An integer
	 */
	private static void printLeftIndent(int n) {

		System.out.print(n);

		for (int space = -1; space < MAX_DIGIT_COUNT - getIntegerDigitCount(n); space++)
			System.out.print(" ");
	}
}
