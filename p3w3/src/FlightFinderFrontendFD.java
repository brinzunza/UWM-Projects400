// --== CS400 Project Two File Header ==--
// Name: Krish Isserdasani
// CSL Username: isserdasani
// Email: isserdasani@wisc.edu
// Lecture #: 004 
// Notes to Grader: <any optional extra notes to your grader>
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * FlightFinderFrontendFD implements the frontend for a Flight Finder application.
 * It allows users to interact with the application to load data, search for the cheapest flights, and more.
 */
public class FlightFinderFrontendFD implements FlightFinderFrontendInterface {
	private Scanner userInput;
	private FlightFinderInterface backend;

/**
* Constructor for the FlightFinderFrontendFD class.
* @param input Scanner for user input
* @param backend Interface for backend operations
*/
public FlightFinderFrontendFD (Scanner input, FlightFinderInterface backend){
	this.backend = backend;
	this.userInput = input;
}

    /**
     * Processes user commands and calls the corresponding methods.
     * Continues until the user chooses to quit.
     */
    @Override
	public void runCommandLoop() {
		System.out.println("Welcome to FlightFinder!");
		List<String> words;
		char command = '\0';
		while (command != 'Q') { // main loop continues until user chooses to quit
			command = this.mainMenuPrompt();
			switch (command) {
				case 'L': //  [L]oad data from file
					loadDataCommand();
					break;
				case 'C': // Search the [C]heapest route from origin to destination
						words = chooseAirportPrompt();
					searchCheapestPath(words.get(0), words.get(1), Integer.parseInt(words.get(2)));
					searchCheapestPathCost(words.get(0), words.get(1), Integer.parseInt(words.get(2)));
					break;
				case 'Q': // System.out.println(" [Q]uit");
					// do nothing, containing loop condition will fail
					break;
				default:
					System.out.println(
							"Didn't recognize that command.  Please type one of the letters presented within []s to identify the command you would like to choose.");
					break;
			}
		}

		System.out.println("Thank you for using FlightFinder!");

	}

    /**
     * Prompts the user to choose a command from the main menu.
     * @return Character representing the chosen command
     */
    @Override
    public char mainMenuPrompt() {

		System.out.println("Choose a command from the list below:");
		System.out.println("[L]oad data from file");
		System.out.println("Search the [C]heapest route from origin to destination");
		System.out.println("[Q]uit");
		
		String input = userInput.nextLine().trim();
		if (input.length() == 0) {
			return '\0';
		}
		return Character.toUpperCase(input.charAt(0));

	}

    /**
     * Executes the Load Data command, prompting the user for a filename and loading data.
     */
    @Override
    public void loadDataCommand() {
        System.out.println("Enter the name of the file to load!");
		String filename = userInput.nextLine().trim();
		try {
			backend.loadData(filename);
			System.out.println("Data loaded!");
		} catch (FileNotFoundException e) {
			System.out.println("I'm sorry, this file was not found!");
		}
    }

    /**
     * Prompts the user to enter origin and destination airports and the maximum number of layovers.
     * @return List of strings containing the origin, destination, and max layovers as strings
     */
	@Override
	public List<String> chooseAirportPrompt() {
		List<String> airports = new ArrayList<>();
		String origin, destination;
		int maxLayovers;
	
		while (true) {
			System.out.print("Origin airport: ");
			origin = userInput.nextLine().trim();
			System.out.print(origin);
			if (isValidAirportName(origin)) {
				airports.add(origin);
				break;
			} else {
				System.out.println("Enter a valid origin airport!");
			}
		}
	
		while (true) {
			System.out.print("Destination airport: ");
			destination = userInput.nextLine().trim();
			System.out.print(destination);
			if (isValidAirportName(destination)) {
				airports.add(destination);
				break;
			} else {
				System.out.println("Enter a valid destination airport!");
			}
		}
	
		while (true) {
			System.out.print("Maximum number of layovers (-1 for no limit): ");
			try {
				maxLayovers = Integer.parseInt(userInput.nextLine().trim());
				System.out.print(maxLayovers);
				if (maxLayovers >= -1) {
					break;
				} else {
					System.out.println("Please enter a number greater than or equal to -1.");
				}
			} catch (NumberFormatException e) {
				System.out.println("Please enter a valid integer!");
			}
		}
	
		airports.add(String.valueOf(maxLayovers));
		return airports;
	}
	

	/**
     * Checks if the provided airport name is valid.
     * @param airport The airport name to validate
     * @return true if valid, false otherwise
     */
	private boolean isValidAirportName(String airport) {
        return !airport.isEmpty() && !airport.trim().isEmpty() && airport.matches("^[a-zA-Z0-9\\s]+$");
    }

	/**
     * Searches for the cheapest path between the origin and destination airports.
     * @param origin The origin airport
     * @param destination The destination airport
     * @param layovers The maximum number of layovers allowed
     * @return List of AirportInterface objects representing the cheapest path, or null if not found
     */
    @Override
    public List<AirportInterface> searchCheapestPath(String origin, String destination, int layovers) {
        try {
			List<AirportInterface> path = backend.searchByCost(origin, destination, layovers);
                System.out.println("Result found!");
				for (int i = 0; i < path.size(); i++) {
				System.out.println(path.get(i).getAirportName() + ": " + path.get(i).getCountry());
				}
		} catch (NoSuchElementException e) {
			System.out.println("Result not found!");
		}
		return null;
    }

	/**
     * Searches for the cost of the cheapest path between the origin and destination airports.
     * @param origin The origin airport
     * @param destination The destination airport
     * @param numLayover The maximum number of layovers allowed
     * @return Double representing the cost of the cheapest path, or null if not found
     */
	@Override
	public Double searchCheapestPathCost(String origin, String destination, int numLayover) {
		try {
			Double pathCost = backend.searchByCostDouble(origin, destination, numLayover);
				System.out.println();
                System.out.println("Total cost: $" + pathCost);
		} catch (NoSuchElementException e) {
			System.out.println("Result not found!");
		}
		return null;
	}
    
}
