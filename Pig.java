/** This project is about gambling a simple dice rolling game of 2-10 players.  */

// Importing necessary packages.

import java.util.Random; // used as a simulator for rolling the dice.
import java.util.Scanner; // simply for taking inputs.

public class Pig {

    public static void main(String[] args) {

        while (true) {

            Scanner sc = new Scanner(System.in);
            String command;

            System.out.print("\nEnter a command (/help, /exit, or number of players form (2-10)): ");
            command = sc.nextLine();

            if (command.equals("/help")) {
                printHelp();
            } else if (command.equals("/exit")) {
                System.out.print("\nThanks for playing the game!\n");
                break;
            } else {
                int playerLen;
                try {
                    playerLen = Integer.parseInt(command); // will give error if the input is character or sting other than /help or /exit.
                    if (playerLen >= 2 && playerLen <= 10) {
                        playGame(playerLen, sc);
                        command = "2";
                    } else {
                        System.out.print("\nNumber of players must be between 2 and 10.\n");
                    }
                } catch (NumberFormatException e) {
                    System.out.print("\nInvalid command. Please enter a number, '/help', or '/exit'.\n");
                }
            }
        }
    }

    // Game function

    public static void playGame(int playerLen, Scanner sc) {

        String[] names = new String[playerLen];

        // Taking the players name and storing it in a 1D array of string.
        for (int i = 0; i < playerLen; i++) {
            System.out.print("\nEnter player " + (i + 1) + "'s name: ");
            names[i] = sc.next();
        }

        int[] store = new int[playerLen]; // to store the final score of the players.

        for (int i = 0; i < playerLen; i++) {
            char ch;
            int score = 0;

            // Simple game logic

            do {
                System.out.println("\n********************************************************************************************");
                System.out.println("\n" + names[i] + "'s turn!");

                System.out.print("Wanna gamble.. so enter 'y' to roll or 'n' to stop: ");
                ch = sc.next().charAt(0);


                if (ch == 'y' || ch == 'Y') {

                    int gamble = roll();

                    // If the gamble is equal to 1 then we display the final score and simply break out of the if-else statement and give chance to the next player.
                    if (gamble == 1) {
                        score = 0;
                        System.out.println("\nYour roll: " + gamble);
                        System.out.println("Your final score: " + score);
                        break;
                    } else {
                        score += gamble;
                    }

                    System.out.print("\nYour roll: " + gamble);
                    System.out.print("\nYour score: " + score+"\n");

                } else if (ch == 'n') {
                    System.out.print(names[i] + ", your final score: " + score);
                    break;
                } else {
                    System.out.print("\nInvalid input. Please enter 'y' or 'n'.");
                }
            } while (ch != 'n' || ch != 'N');
            store[i] = score;
        }

        int winningScore = 0;
        String winner = "";
        for (int i = 0; i < playerLen; i++) {
            if (store[i] > winningScore) {
                winningScore = store[i];
                winner = names[i];
            }
        }

        if (winningScore > 0) {
            System.out.println("\n********************************************************************************************");
            System.out.println("Congratulations! " + winner + " wins with a score of " + winningScore + ".");
            System.out.print("********************************************************************************************\n");
        } else {
            System.out.println("********************************************************************************************");
            System.out.println("It's a tie! No winner this round.");
            System.out.print("********************************************************************************************\n");
        }
    }

    // Game rules and help menu.

    public static void printHelp() {
        System.out.println("\nPig Game Help:");
        System.out.println("  - Enter the number of players (2-4) to start a new game.");
        System.out.println("  - During your turn, enter 'y' to roll the dice and 'n' to stop.");
        System.out.println("  - The first one to get a non-zero score wins! Only if the other players get zero in their turns. Now enjoy!!");
        System.out.println("  - '/help' - Displays this help menu.");
        System.out.println("  - '/exit' - Exits the game at any point.");
    }

    // Getting random values from 1-6.

    public static int roll() {
        Random random = new Random();
        return (random.nextInt(1, 6));
    }
}
