/**
 * Author: Jacob Joseph
 * Date: Jan 7, 2018
 *
 * This is a simple program that plays the water pouring logic
 * problem (wikipedia link below) with the user as a command line game.
 *
 * https://en.wikipedia.org/wiki/Water_pouring_puzzle
 * 
 * This is free, unencumbered software released into the public domain.
 */

import java.util.Scanner;

public class PouringProblem {
	/**
	 * Main entry point of the program.
	 */
	public static void main(String[] args) {
		Game game = new Game();
		Scanner input = new Scanner(System.in);

		printGreeting(game);

		do {
			playGame(game, input);
		} while (playAgain(input));
	}

	/**
	 * Prints a greeting (with instructions) to the user.
	 */
	private static void printGreeting(Game game) {
		final String WELCOME = "\n" +
			"welcome! suppose you have 3 buckets of water with capacities\n" +
			"of 8, 5, and 3. can you figure out how to end up with\n" +
			"volumes of 4, 4, and 0 (respectively) by pouring 8 gallons\n" +
			"of water between them? to begin, all 8 gallons are in the\n" +
			"first bucket. to pour water from bucket to bucket, type\n" +
			"the number of the bucket you'd like to pour from and the\n" +
			"number of the bucket you'd like to fill up, separated by a\n" +
			"space. for example, to pour 1 into 2, type '1 2'. " +
			"good luck!\n\n(ctrl + c to quit)\n\n";

		System.out.println(WELCOME);
	}

	/**
	 * Runs the game in a loop until the user has solved the puzzle.
	 *
	 * @param game  - the game object
	 * @param input - scanner object for user kb input
	 */
	private static void playGame(Game game, Scanner input) {
		int pour; 	// the bucket index to pour from
		int fill;	// the bucket index to fill up

		while (!game.isOver()) {
			// initializes pour and fill so that isValid() may be called
			pour = -1;
			fill = -1;

			// prints current volumes
			System.out.println("current volumes: ");

			for (int i = 0; i < game.getSize(); i++) {
				System.out.println(
					"\tbucket " + (i + 1) + 
					" (capacity: " + game.getCapacity(i) + "): " +
					game.getCurrentVolume(i));
			}

			// prompts user for input and validates it
			System.out.print("pour > ");
			
			if (input.hasNextInt()) {
				pour = input.nextInt();
			} else {
				System.out.println("invalid input.");
				input.nextLine();
				continue;
			}

			if (input.hasNextInt()) {
				fill = input.nextInt();
				input.nextLine();
			} else {
				System.out.println("invalid input.");
				input.nextLine();
				continue;
			}

			// checks if input corresponds to bucket indexes
			if (isValid(pour, game) && isValid(fill, game)) {
				game.pour(pour - 1, fill - 1);
			} else {
				System.out.println("input out of bounds.");
			}
		}

		System.out.println("you win!");
		game.reset();
		
	}

	/**
     * Checks to see if the number passed in will correspond to
     * a bucket's index in the Game class. Returns true if so, false
     * if not.
     * 
	 * @param index	- the bucket index
	 * @param game  - the game object
	 * @return true if index is valid
	 */
	private static boolean isValid(int index, Game game) {
		int size = game.getSize();

		if (index > size || index < 1) {
			return false;
		}

		return true;
	}

	/**
     * The playAgain() method asks the users if they want to play 
     * again, restricting their input to y or n, case insensitive. While 
     * this method returns true, the program runs again.
	 * @param input	- the Scanner from main
	 * @return true if the user wants to play again
	 */
	private static boolean playAgain(Scanner input) {
    	char response;				//user's response to playing again
    	boolean playAgain = false;	//if user chooses to play again or not
    	
    	//gets user input, restricts first character to 
    	//'y', 'n', 'Y', or 'N' and reads that as their response
    	do {
    		System.out.print("play again? y/n: ");
	    	response = input.next().charAt(0);
	    	response = Character.toLowerCase(response);
	    	input.nextLine();
    	} while (response != 'y' && response != 'n');
    	
    	//a 'y'/ 'Y' response returns true
    	if (response == 'y') {
    		playAgain = true;
    		return playAgain;
    	} else
    		return playAgain;
    }
}