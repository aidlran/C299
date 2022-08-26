import java.util.Scanner;

public class HealthyHearts {

	private static final Scanner SCANNER = new Scanner(System.in);

	public static void main(String[] args) {

		int age = getInput();

		System.out.println("Your maximum heart rate should be " + getMaxHeartRate(age) + " beats per minute.");
		System.out.println("Your target heart rate zone is " + getTargetHeartRate(age, 0.5) + " - " + getTargetHeartRate(age, 0.85) + " beats per minute.");
	}

	private static int getInput() {

		while (true) {

			// Prompt user
			System.out.print("Type 'exit' to terminate the program\nWhat is your age? ");

			// Get input
			String nextLine = SCANNER.nextLine();

			// Check for exit condition
			if (nextLine.equalsIgnoreCase("exit"))
				System.exit(0);

			// Try parsing, validating, and returning the integer
			try {
				int input = Integer.parseInt(nextLine);
				if (input > 0 && input <= 150) return input;
				else System.out.println("Please enter a valid age.\n");
			}
			catch (Exception e) {
				System.out.println("Please enter an integer.\n");
			}
		}
	}

	private static int getMaxHeartRate(int age) {
		return 220 - age;
	}

	private static int getTargetHeartRate(int age, double percentage) {
		return (int) (getMaxHeartRate(age) * percentage);
	}
}
