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
    private static int input;

    public static void main(String[] args) {

        Choice playerChoice, computerChoice;

        int rounds, ties, playerWins, compWins;

        // Player can terminate at anytime by inputting 'exit'
        // Don't declare new variables on the stack during loop
        // noinspection InfiniteLoopStatement
        do {
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

                // Get winner
                if (playerChoice == computerChoice)
                    System.out.println("It's " + (++ties == 1 ? "a" : "another") + " tie!");
                else if (
                        (playerChoice == Choice.ROCK && computerChoice == Choice.SCISSORS)
                    ||  (playerChoice == Choice.PAPER && computerChoice == Choice.ROCK)
                    ||  (playerChoice == Choice.SCISSORS && computerChoice == Choice.PAPER)
                )
                    System.out.println("Player wins" + (++playerWins > 1 ? " again" : "") + "!");
                else
                    System.out.println("Computer wins" + (++compWins > 1 ? " again" : "") + "!");

                System.out.println();
            }

            System.out.println("Game over!\nPlayer wins: " + playerWins + "\nComputer wins: " + compWins + "\nTies: " + ties + "\n");

        } while (true);
    }

    /**
     * Repeatedly prints a message, gets input, and validates it until a valid integer between (1 & MAX) is input.
     * @param prompt Input prompt message.
     * @param maxInt Maximum integer allowed.
     */
    private static int promptForIntegerInput(String prompt, int maxInt) {
        do System.out.print("Type 'exit' to terminate the program\n" + prompt + " (1-" + maxInt + "): ");
        while (!isValidIntegerInput(maxInt));
        return input;
    }

    /**
     * Gets input and validates it as being between 1 and the provided max inclusive.
     * @param max Maximum integer allowed.
     */
    private static boolean isValidIntegerInput(int max) {
        String nextLine = SCANNER.nextLine();
        if (nextLine.equalsIgnoreCase("exit")) {
            System.out.println("Thanks for playing!");
            System.exit(0);
        }
        try {
            input = Integer.parseInt(nextLine);
            if (input > 0 && input <= max) return true;
        }
        catch (NumberFormatException ignored) {}
        System.out.println("Unrecognised input.\n");
        return false;
    }
}
