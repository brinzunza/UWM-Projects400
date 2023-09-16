import java.io.FileNotFoundException;
import java.util.List;

public interface SongReaderInterface {
    // public SongReaderInterface();
    public List<SongInterface> readSongsFromFile(String filename) throws FileNotFoundException;
 }
 