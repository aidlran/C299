// Write a program that prints all prime numbers upto 'N'.

import java.util.Scanner;

public class Problem5PrintPrimes {

	private static final Scanner SCANNER = new Scanner(System.in);

	public static void main(String[] args) {

		int n = getIntegerInput();

		System.out.print("Prime numbers from 1 to " + n + ":");

		// Loop through prime candidates, from 1 to n
		for (int primeCandidate = 1; primeCandidate <= n; primeCandidate++) {

			int productCount = 0;

			// Loop from current prime candidate back to 1
			// Check if evenly divisible into prime candidate using modulus
			// Loop breaks as soon as more than two are found
			for (int divisor = primeCandidate; divisor >= 1; divisor--)
				if (primeCandidate % divisor == 0 && ++productCount > 2)
					break;

			// If there were two, then print the prime candidate
			if (productCount == 2) System.out.print(" " + primeCandidate);
		}

		System.out.println();
	}

	/**
	 * Gets a positive integer from user input.
	 * @return An integer greater than 0.
	 */
	public static int getIntegerInput() {

		while (true) {

			System.out.print("Print prime numbers upto? (n): ");

			String nextLine = SCANNER.nextLine();

			try {
				int n = Integer.parseInt(nextLine);
				if (n > 0) return n;
			} catch (Exception ignored) {}

			System.out.println("Please enter a positive integer.\n");
		}
	}
}
