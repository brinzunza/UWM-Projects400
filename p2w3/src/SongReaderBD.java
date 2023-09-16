import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class SongReaderBD implements SongReaderInterface {
    @Override
    public List<SongInterface> readSongsFromFile(String fileName) throws FileNotFoundException {
        if (fileName.equals("invalidFile")) {
            throw new FileNotFoundException();
        }
        List<SongInterface> toReturn = new ArrayList<SongInterface>();

        SongInterface song1 = new SongBD("Breakeven", "The Script", "Album 1");
        SongInterface song2 = new SongBD("All of Me", "John Legend", "Album 2");
        SongInterface song3 = new SongBD("Paradise", "Coldplay", "Album 3");
        SongInterface song4 = new SongBD("Things Fall Apart", "Alan Walker", "Album 4");
        SongInterface song5 = new SongBD("Team", "Lorde", "Album 5");
        SongInterface song6 = new SongBD("Bad Habit", "Steve Lacy", "Album 6");
        SongInterface song7 = new SongBD("Superhero", "Metro Boomin", "Album 7");

        toReturn.add(song1);
        toReturn.add(song2);
        toReturn.add(song3);
        toReturn.add(song4);
        toReturn.add(song5);
        toReturn.add(song6);
        toReturn.add(song7);


        return toReturn;
    }
}
