// --== CS400 Project Two File Header ==--
// Name: Krish Isserdasani
// CSL Username: isserdasani
// Email: isserdasani@wisc.edu
// Lecture #: 004 
// Notes to Grader: <any optional extra notes to your grader>

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Defines SongReaderDW and its methods that will be used to load contents from the csv file
 */
public class SongReaderDW implements SongReaderInterface {

    /**
     * Method that will define how the CSV file is read through
     * @param String filename of the CSV file to be looked through
     */
    public List<SongInterface> readSongsFromFile(String filename) throws FileNotFoundException{
    // initializing a list of songs
    List<SongInterface> songs = new ArrayList<SongInterface>();
    File file = new File(filename);
        
    try {
    Scanner scanner = new Scanner(file);
    scanner.nextLine(); // skip header row
    // for each line of the file
    while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        List<String> data = new ArrayList<>();
        // Keeping track of whether something is in quotes
        boolean inQuotes = false;
        int quoteCount = 0;
        String currentParam = "";
        // Looking at each char in the line to find data we need
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            // when quoe found:
            if (c == '\"') {
                quoteCount++;
                inQuotes = !inQuotes;
            } else if (c == ',' && quoteCount % 2 == 0) {
                data.add(currentParam.toString());
                currentParam = "";
            } else {
                currentParam += c;
            }
        }
        
        // Add the last parameter
        data.add(currentParam);
        
        // If not all 3 parts of data are found, throw an error
        if (data.size() != 3) {
           throw new IllegalArgumentException("Invalid number of parameters in line: " + line);
        }
        
        // Use all of the data to make a new SongDW object to add into the list
        String title = data.get(0);
        String artist = data.get(1);
        String album = data.get(2);
        
        SongDW song = new SongDW(title, artist, album);
        // Adding song to the list
        songs.add(song);
        }
        scanner.close();
    } catch (FileNotFoundException e) {
    // throw exception is file is not found
    throw new FileNotFoundException("Requested file not found!");
    } 
        return songs;
    }
}