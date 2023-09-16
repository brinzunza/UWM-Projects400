import java.sql.SQLOutput;
import java.util.NoSuchElementException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SongPlayerFrontendFD implements SongPlayerFrontendInterface {
	
	private Scanner input; 
	private SongCollectionInterface backend;

	public SongPlayerFrontendFD(Scanner input, SongCollectionInterface backend) {

		this.input = input;
		this.backend = backend;

	}


/*
 * Process the command from the user and call the corresponding methods for their commands
 */
	public void runCommandLoop() {

		System.out.println("Welcome to Song Player!");
		char command = '\0';
		List<String> userPrompt;
		while (command != 'Q') {
			command = this.mainMenuPrompt();
			if (command=='L') {
				loadDataCommand();
				//sbreak;
			}
			else if (command=='T') {
				userPrompt = chooseSearchWordsPrompt();
				searchSongCommand(userPrompt);
				//break;
			}
			else if (command=='A') {
				userPrompt = chooseSearchWordsPrompt();
				searchArtistCommand(userPrompt);	
				//break;
			}
			else if (command=='B') {
				userPrompt = chooseSearchWordsPrompt();
				searchAlbumCommand(userPrompt);	
				//break;
			}
			else if (command=='Q') {
				//break;
			}
			else {
			       	System.out.println("Error: this command was not recognized. Try again!");
				//break;
			}
		}
		
		System.out.println("Thank you for using Song Player! See you again soon!");		

	}

/*
 *Display the main menu for the user so they know their command options
*/
       	public char mainMenuPrompt() {

		System.out.println("Choose a command from the list below:");
		System.out.println("[L]oad data from file");
		System.out.println("Search Song [T]itles");
		System.out.println("Search Song [A]rtist");
		System.out.println("Search Song Al[B]um");
		System.out.println("[Q]uit");
		
		String userInput = input.nextLine().trim();
		if (userInput.length() == 0) {
			return '\0';
		}
		return Character.toUpperCase(userInput.charAt(0));

	}


	/*
	 * load a file that is inputted by the user, throw a FileNotFoundException if none found
	 */

	public void loadDataCommand() {

		System.out.println("Enter the name of the file to load!");
		String filename = input.nextLine().trim();
		try {
			backend.loadData(filename);
			System.out.println("Data loaded!");
		} catch (FileNotFoundException e) {
			System.out.println("I'm sorry, this file was not found!");
		}
	}


	/*
	 * prompt the user for the song/artist/album they would like to search for and allow them to add or delete words from their prompt
	 */

	public List<String> chooseSearchWordsPrompt() {

		List<String> words = new ArrayList<>();
		int forever = 0;
		while (forever==0) {
			System.out.println("List of words to search for: " + oneString(words));
			System.out.println("Words to add or remove from input, or press enter to search! ");
			String theInput = input.nextLine();
			theInput = theInput.replaceAll(",","").trim();
			if (theInput.length()<=0)
				return words;
			else {
				String[] inputString = theInput.split(" ");
				for (int i = 0; i<inputString.length; i++){ 
					if (words.contains(inputString[i]))
						words.remove(inputString[i]);
					else 
						words.add(inputString[i]);
				}
			}
		}
		return words;
	}


	/*
	 * search for a song that the user chose
	 */

	public void searchSongCommand(List<String> songs) {

		try {
			List<String> songsFound = backend.searchByTitle(oneString(songs));
                        System.out.println("Result found!");
			for (int i = 0; i<songsFound.size(); i++) {
				System.out.println(songsFound.get(i));
			}
		} catch (NoSuchElementException e) {

			System.out.println("Result not found!");

		}
	}

	/*
	 * search for an artist that the user chose
	 */

	public void searchArtistCommand(List<String> artists) {


		try {
			List<String> artistsFound = backend.searchByArtist(oneString(artists));
                        System.out.println("Result found!");
			for (int i = 0; i<artistsFound.size(); i++) {
				System.out.println(artistsFound.get(i));
			}
			if(artistsFound.isEmpty()){
				System.out.println("Result not found!");

			}
		} catch (NoSuchElementException e) {

			System.out.println("Result not found!");

		}		

	}

	/* 
	 * search for an album that the user chose
	 */
	public void searchAlbumCommand(List<String> albums) {


		try {
			List<String> albumsFound = backend.searchByAlbum(oneString(albums));
                        System.out.println("Result found!");
			for (int i = 0; i<albumsFound.size(); i++) {
				System.out.println(albumsFound.get(i));
			}
		} catch (NoSuchElementException e) {

			System.out.println("Result not found!");

		}	

	}
	
	/* 
	 * turn the list of strings recieved from the user and make them into one string
	 * (in case it is a song, artist, or album that is more than one word)
	 */
	private String oneString(List<String> list) {
		String s = "";
		for (int i = 0; i<list.size(); i++) {
			s += list.get(i) + " ";
		}
		s = s.trim();
		return s;
	}

//	public static void main(String[] args){
//		SongCollectionFD bd = new SongCollectionFD();
//		Scanner the = new Scanner(System.in);
//		SongPlayerFrontendFD fd = new SongPlayerFrontendFD(the, bd);
//		fd.runCommandLoop();
//	}


}
