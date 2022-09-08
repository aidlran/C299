// Write a program that prints the next 20 leap years.

import java.time.Year;

public class Problem8LeapYears {

	private static final int CURRENT_YEAR = Year.now().getValue();

	public static void main(String[] args) {

		int nextLeapYear = CURRENT_YEAR + (4 - CURRENT_YEAR % 4);

		System.out.print("The next 10 leap years are: " + nextLeapYear);

		for (int i = 0; i < 19; i++)
			System.out.print(" " + (nextLeapYear += 4));
	}
}
