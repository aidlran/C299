// Write a program that asks the user for a number n and prints the sum of the numbers 1 to n
// Modify the program such that only multiples of three or five are considered in the sum, e.g. 3, 5, 6, 9, 10, 12, 15 for n=17

import java.util.Scanner;

public class Problem2SumSeries {

	private static final Scanner SCANNER = new Scanner(System.in);

	public static void main(String[] args) {
		System.out.println("Your sum is: " + sum(getInput()));
	}

	private static int getInput() {

		while (true) {

			System.out.print("Choose a number: ");

			String nextLine = SCANNER.nextLine();

			try {
				return Integer.parseInt(nextLine);
			}
			catch (Exception ignored) {
				System.out.println("Invalid input. Please enter an integer.\n");
			}
		}
	}

	private static int sum(int n) {

		int total = 0;

		for (int i = 1; i < n; i++)
			total += i;

		return total;
	}
}
