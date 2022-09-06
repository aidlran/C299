// Write a program that asks the user for a number n
// and gives them the possibility to choose between
// computing the sum and computing the product of 1,…,n.

import java.util.Scanner;

public class Problem3SumOrProduct {

	private static final Scanner SCANNER = new Scanner(System.in);

	public static void main(String[] args) {

        int n = getInputInteger();

        System.out.println("\nThe range (1 through n) will be either summed or the product found.");

        System.out.println(getInputSumOrProduct()
            ? "The sum of the range is " + calculateSum(n)
            : "The product of the range is " + calculateProduct(n));
	}

	private static int getInputInteger() {

		while (true) {

			System.out.print("Choose a number (n): ");

			String nextLine = SCANNER.nextLine();

			try {
				return Integer.parseInt(nextLine);
			}
			catch (Exception ignored) {
				System.out.println("Invalid input. Please enter an integer.\n");
			}
		}
	}

    /**
     * @return TRUE = sum; FALSE = product.
     */
    private static boolean getInputSumOrProduct() {

        while (true) {

			System.out.print("Choose sum/product: ");

            switch (SCANNER.nextLine()) {
                case "sum":
                case "s":
                    return true;
                case "product":
                case "p":
                    return false;
                default:
                    System.out.println("Unrecognised input.");
            }
        }
    }

	private static int calculateSum(int n) {

		int total = 0;

		for (int i = 1; i <= n; i++)
            total += i;

		return total;
	}

    private static int calculateProduct(int n) {

        int total = 1;

        for (int i = 2; i <= n; i++)
            total *= i;

        return total;
    }
}
