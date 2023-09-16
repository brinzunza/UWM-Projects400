import java.io.FileNotFoundException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.ArrayList;


public class SongCollectionFD implements SongCollectionInterface {


   public void insert(SongInterface data) throws NullPointerException, IllegalArgumentException {

	return;

   }



   public void loadData(String fileName) throws FileNotFoundException {

	   if (fileName.equals("song.mpg")) {

	   } else {
		   throw new FileNotFoundException("File not found, sorry!");
	   }
   }


   public String searchSpecificSong(String songName, String artistName, String albumName) throws NoSuchElementException {

	   Song song = new Song("Party Rock Anthem", "LMFAO", "Party Rock");
	   return " ";

   }


   public List<String> searchByTitle(String songName) throws NoSuchElementException {

	   if (songName.equals("Ivy")) {
	
		   ArrayList<String> list = new ArrayList<String>();
		   return list; 

	   } else {
		   throw new NoSuchElementException("No match found for the song title: " + songName);
	   }

   }


   public List<String> searchByArtist(String artistName) throws NoSuchElementException {

	   if (artistName.equals("Drake")) {

		   ArrayList<String> list = new ArrayList<String>();
		   return list;

	   } else {
		   throw new NoSuchElementException("No match found for the artist: " + artistName);
	   }

   }


   public List<String> searchByAlbum(String albumName) throws NoSuchElementException {
	   
	   if (albumName.equals("Her Loss")) {
		   
		   ArrayList<String> list = new ArrayList<String>();
		   return list;
	   } else {
		   throw new NoSuchElementException("No match found for the album: " + albumName);
	   }
   }


   public boolean contains(SongInterface data) {

	   return true;
   }


   public int size() {

	   return 0;

   }


   public boolean isEmpty() {

	   return false;
   }
}

