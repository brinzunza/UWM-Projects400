import java.io.FileNotFoundException;
import java.util.List;
import java.util.NoSuchElementException;
// NEW BACKEND INTERFACE
public interface SongCollectionInterface {
    // public SongCollectionBD(SongReaderInterface songReader, RedBlackTreeInterfaceAE<SongTitleInterface> redBlackTreeTitle, RedBlackTreeInterfaceAE<SongArtistInterface> redBlackTreeArtist, RedBlackTreeInterfaceAE<SongAlbumInterface> redBlackTreeAlbum, String songType);
    // String songType is "BD" or "DW" which corresponds to SongBD or SongDW
    public void insert(SongInterface data) throws NullPointerException, IllegalArgumentException;
    public void loadData(String fileName) throws FileNotFoundException;
    public String searchSpecificSong(String songName, String artistName, String albumName) throws NoSuchElementException;
    public List<String> searchByTitle(String songName) throws NoSuchElementException;
    public List<String> searchByArtist(String artistName) throws NoSuchElementException;
    public List<String> searchByAlbum(String albumName) throws NoSuchElementException;
    public boolean contains(SongInterface data);
    public int size();
    public boolean isEmpty();
}
