// Write a program that asks the user for their name and greets them with their name.
// Modify the program such that only the users Biraj and DAS are greeted with their names.

import java.util.Scanner;

public class Problem1HelloModified {

	private static Scanner SCANNER = new Scanner(System.in);

	public static void main(String[] args) {
		System.out.print("What is your name? ");
		final String NAME = SCANNER.nextLine();
		System.out.println(NAME.equalsIgnoreCase("biraj") || NAME.equalsIgnoreCase("das") ? "Hello, " + NAME + "." : "I don't know you.");
	}
}
