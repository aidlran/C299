import java.util.Random;
import java.util.Scanner;

public class RockPaperScissors {

	enum Choice {
		ROCK,
		PAPER,
		SCISSORS
	}

	private static Choice intToChoice(int integer) {
		return switch (integer) {
			case 1 -> Choice.ROCK;
			case 2 -> Choice.PAPER;
			case 3 -> Choice.SCISSORS;
			default -> throw new IllegalStateException("Unexpected choice value: " + integer);
		};
	}

	private static String choiceToString(Choice choice) {
		return switch (choice) {
			case ROCK -> "rock";
			case PAPER -> "paper";
			case SCISSORS -> "scissors";
		};
	}

	private static final Scanner SCANNER = new Scanner(System.in);
	private static final Random RANDOM = new Random();

	public static void main(String[] args) {

		Choice playerChoice, computerChoice;

		int rounds, ties, playerWins, compWins;

		// Game loop follows
		// Player can terminate at any time by inputting 'exit'
		// Don't declare new variables on the stack during loop

		// noinspection InfiniteLoopStatement
		while (true) {

			// Reset to 0
			ties = playerWins = compWins = 0;

			// Get number of rounds
			rounds = promptForIntegerInput("How many rounds will you play?", 10);

			// Play rounds
			for (int i = 1; i <= rounds; i++) {

				// Get user choice
				playerChoice = intToChoice(promptForIntegerInput("Round " + i + "\nRock (1), paper (2), or scissors (3)?", 3));

				// Get computer choice
				computerChoice = intToChoice(RANDOM.nextInt(3) + 1);
				System.out.println("Computer chose: " + choiceToString(computerChoice));

				// Calculate the winner
				if (playerChoice == computerChoice) {
					System.out.println("It's " + (++ties == 1 ? "a" : "another") + " tie!\n");
				}

				else if (
						(playerChoice == Choice.ROCK && computerChoice == Choice.SCISSORS) ||
						(playerChoice == Choice.PAPER && computerChoice == Choice.ROCK) ||
						(playerChoice == Choice.SCISSORS && computerChoice == Choice.PAPER)
				) {
					System.out.println("Player wins" + (++playerWins > 1 ? " again" : "") + "!\n");
				}

				else {
					System.out.println("Computer wins" + (++compWins > 1 ? " again" : "") + "!\n");
				}
			}

			System.out.println("Game over!\nPlayer wins: " + playerWins + "\nComputer wins: " + compWins + "\nTies: " + ties + "\n");

		}
	}

	/**
	 * Prompt the user to input an integer.
	 * Gets a valid integer from user input between 1 and `maxInt` given inclusively.
	 * Will repeat until a valid integer is given.
	 * User inputting 'exit' will terminate the program.
	 * @param prompt Input prompt message.
	 * @param maxInt Maximum integer allowed.
	 */
	private static int promptForIntegerInput(String prompt, int maxInt) {

		int input;
		boolean valid = false;

		while (true) {

			// Prompt user
			System.out.print("Type 'exit' to terminate the program\n" + prompt + " (1-" + maxInt + "): ");

			// Get input
			// Could use nextInt, but we want to check for exit condition too
			String nextLine = SCANNER.nextLine();

			// Check for exit condition
			if (nextLine.equalsIgnoreCase("exit")) {
				System.out.println("Thanks for playing!");
				System.exit(0);
			}

			// Try parsing, validating, and returning the integer
			try {
				input = Integer.parseInt(nextLine);
				if (input > 0 && input <= maxInt) return input;
			}
			catch (NumberFormatException ignored) {}

			// If a return didn't break the loop, input was not valid
			System.out.println("Unrecognised input.\n");

		}
	}
}
