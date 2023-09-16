import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * A collection of songs stored in a Red-Black Tree that is sorted by song name
 */
public class SongCollectionBD implements SongCollectionInterface {
    private RedBlackTreeInterfaceAE<SongTitleInterface> songSortedCollection;
    private RedBlackTreeInterfaceAE<SongArtistInterface> artistSortedCollection;
    private RedBlackTreeInterfaceAE<SongAlbumInterface> albumSortedCollection;
    private SongReaderInterface songReader;
    private String songType;

    public SongCollectionBD(SongReaderInterface songReader, RedBlackTreeInterfaceAE<SongTitleInterface> redBlackTreeTitle, RedBlackTreeInterfaceAE<SongArtistInterface> redBlackTreeArtist, RedBlackTreeInterfaceAE<SongAlbumInterface> redBlackTreeAlbum, String songType) {
        songSortedCollection = redBlackTreeTitle;
        artistSortedCollection = redBlackTreeArtist;
        albumSortedCollection = redBlackTreeAlbum;

        if (!songType.equals("BD") && !songType.equals("DW")) {
            System.out.print(songType);
            throw new IllegalArgumentException();
        }
        this.songType = songType;
        this.songReader = songReader;
    }

    public void loadData(String fileName) throws FileNotFoundException {
        List<SongInterface> list = songReader.readSongsFromFile(fileName);

        SongTitleInterface songTitle;
        SongArtistInterface songArtist;
        SongAlbumInterface songAlbum;

        for (SongInterface s : list) {
            if (songType.equals("BD")) {
                songTitle = new SongTitleBD(s.getTitle(), s.getArtist(), s.getAlbum());
                songArtist = new SongArtistBD(s.getTitle(), s.getArtist(), s.getAlbum());
                songAlbum = new SongAlbumBD(s.getTitle(), s.getArtist(), s.getAlbum());
            }
            else if (songType.equals("DW")) {
                songTitle = new SongTitleDW(s.getTitle(), s.getArtist(), s.getAlbum());
                songArtist = new SongArtistDW(s.getTitle(), s.getArtist(), s.getAlbum());
                songAlbum = new SongAlbumDW(s.getTitle(), s.getArtist(), s.getAlbum());
            }
            else {
                throw new IllegalArgumentException();
            }

            songSortedCollection.insert(songTitle);
            artistSortedCollection.insert(songArtist);
            albumSortedCollection.insert(songAlbum);

//            songSortedCollection.insert((SongTitleInterface) s);
//            artistSortedCollection.insert((SongArtistInterface) s);
//            albumSortedCollection.insert((SongAlbumInterface) s);
        }
    }

    /**
     * Inserts a Song object into the collection
     * @param data the Song to be inserted
     * @return true if the value was inserted, false if not
     * @throws NullPointerException when the provided Song is null
     * @throws IllegalArgumentException when the Song is already contained in the tree
     */
    @Override
    public void insert(SongInterface data) throws NullPointerException, IllegalArgumentException {
        try {

            SongTitleInterface songTitle;
            SongArtistInterface songArtist;
            SongAlbumInterface songAlbum;

            if (songType.equals("BD")) {
                songTitle = new SongTitleBD(data.getTitle(), data.getArtist(), data.getAlbum());
                songArtist = new SongArtistBD(data.getTitle(), data.getArtist(), data.getAlbum());
                songAlbum = new SongAlbumBD(data.getTitle(), data.getArtist(), data.getAlbum());

            }
            else if (songType.equals("DW")) {
                songTitle = new SongTitleDW(data.getTitle(), data.getArtist(), data.getAlbum());
                songArtist = new SongArtistDW(data.getTitle(), data.getArtist(), data.getAlbum());
                songAlbum = new SongAlbumDW(data.getTitle(), data.getArtist(), data.getAlbum());
            }
            else {
                throw new IllegalArgumentException();
            }

            songSortedCollection.insert(songTitle);
            artistSortedCollection.insert(songArtist);
            albumSortedCollection.insert(songAlbum);
//            songSortedCollection.insert((SongTitleInterface) data);
//            artistSortedCollection.insert((SongArtistInterface) data);
//            albumSortedCollection.insert((SongAlbumInterface) data);
        }
        catch (NullPointerException | IllegalArgumentException e) {
            throw e;
        }
    }

    /**
     * Searches for a specific Song given the name, artist, and album
     * @param songName the name of the Song
     * @param artistName the artist of the Song
     * @param albumName the album of the Song
     * @return the Song that contains the matching data
     * @throws NoSuchElementException when there is no Song in the collection with matching data
     */
    public String searchSpecificSong(String songName, String artistName, String albumName) throws NoSuchElementException {
        SongTitleInterface songToSearch;
        if (songType.equals("BD")) {
            songToSearch = new SongTitleBD(songName, artistName, albumName);
        }
        else if (songType.equals("DW")) {
            songToSearch = new SongTitleDW(songName, artistName, albumName);
        }
        else {
            throw new IllegalArgumentException();
        }

        SongInterface songFound = songSortedCollection.searchSpecific(songToSearch);
        if (songFound == null) {
            throw new NoSuchElementException("No such song exists in the collection that matches the provided data name, artist, and album.");
        }
        return songFound.getTitle() + " by " + songFound.getArtist() + " in the album: " + songFound.getAlbum();
    }

    /**
     * Searches for Songs by name and returns a list of Songs with the provided name
     * @param songName the song name to search
     * @return a list of Songs with the given name
     * @throws NoSuchElementException
     */
    @Override
    public List<String> searchByTitle(String songName) throws NoSuchElementException {
        List<SongInterface> songs = new ArrayList<SongInterface>();
        List<String> toReturn = new ArrayList<String>();

        SongInterface next;

        if(!songSortedCollection.isEmpty()) {
            if(songSortedCollection.getRoot() != null) {
                SongInterface root = songSortedCollection.getRoot();
                if(root.getTitle().equals(songName)) {
                    songs.add(root);
                    toReturn.add(root.getTitle() + " by " + root.getArtist() + " in the album: " + root.getAlbum());
                }
                if(songSortedCollection.next((SongTitleInterface) root) != null ) {
                    next = songSortedCollection.next((SongTitleInterface) root);
                    if(next.getTitle().equals(songName)) {
                        songs.add(next);
                        toReturn.add(next.getTitle() + " by " + next.getArtist() + " in the album: " + next.getAlbum());
                    }
                    while(songSortedCollection.next((SongTitleInterface) next) != null) {
                        next = songSortedCollection.next((SongTitleInterface) next);
                        if(next.getTitle().equals(songName)) {
                            songs.add(next);
                            toReturn.add(next.getTitle() + " by " + next.getArtist() + " in the album: " + next.getAlbum());
                        }
                    }
                }
            }
        }
//        return songs;
        return toReturn;
//        List<SongInterface> songs = new ArrayList<SongInterface>();
//        List<String> toReturn = new ArrayList<String>();
//
//        SongInterface next;
//
//        if (!songSortedCollection.isEmpty()) {
//            if (songSortedCollection.getRoot() != null) {
//                next = songSortedCollection.getRoot();
//                while (songSortedCollection.hasNext((SongTitleInterface) next)) {
//                    if (next.getTitle().equals(songName)) {
//                        toReturn.add(next.getTitle() + " by " + next.getArtist() + " in the album: " + next.getAlbum());
//                    }
//                    next = songSortedCollection.next((SongTitleInterface) next);
//                }
//            }
//        }
//
//
//        if (!songSortedCollection.isEmpty()) {
//            if (songSortedCollection.getRoot() != null) {
//                SongInterface root = songSortedCollection.getRoot();
//                if (root.getTitle().equals(songName)) {
//                    songs.add(root);
//                    toReturn.add(root.getTitle() + " by " + root.getArtist() + " in the album: " + root.getAlbum());
//                }
//                if (songSortedCollection.next((SongTitleInterface) root) != null) {
//                    next = songSortedCollection.next((SongTitleInterface) root);
//                    if (next.getTitle().equals(songName)) {
//                        songs.add(next);
//                        toReturn.add(next.getTitle() + " by " + next.getArtist() + " in the album: " + next.getAlbum());
//                    }
//                    while(songSortedCollection.next((SongTitleInterface) root) != null) {
//                        next = songSortedCollection.next((SongTitleInterface) next);
//                        if (next == null) {
//                            if (songs.size() == 0) {
//                                throw new NoSuchElementException();
//                            }
////                            return songs;
//                            return toReturn;
//                        }
//                        if (next.getTitle().equals(songName)) {
//                            songs.add(next);
//                            toReturn.add(next.getTitle() + " by " + next.getArtist() + " in the album: " + next.getAlbum());
//                        }
//                    }
//                }
//            }
//        }
////        return songs;
//        return toReturn;

    }

    /**
     * Searches for Songs by artist and returns a list of Songs with the provided artist
     * @param artistName the artist name to search
     * @return a list of Songs by the given artist
     * @throws NoSuchElementException if no Songs exist in the given album
     */
    @Override
    public List<String> searchByArtist(String artistName) throws NoSuchElementException {
        List<SongInterface> songs = new ArrayList<SongInterface>();
        List<String> toReturn = new ArrayList<String>();

        SongInterface next;

        if(!artistSortedCollection.isEmpty()) {
            if(artistSortedCollection.getRoot() != null) {
                SongInterface root = artistSortedCollection.getRoot();
                if(root.getArtist().equals(artistName)) {
                    songs.add(root);
                    toReturn.add(root.getTitle() + " by " + root.getArtist() + " in the album: " + root.getAlbum());
                }
                if(artistSortedCollection.next((SongArtistInterface) root) != null ) {
                    next = artistSortedCollection.next((SongArtistInterface) root);
                    if(next.getArtist().equals(artistName)) {
                        songs.add(next);
                        toReturn.add(next.getTitle() + " by " + next.getArtist() + " in the album: " + next.getAlbum());
                    }
                    while(artistSortedCollection.next((SongArtistInterface) next) != null) {
                        next = artistSortedCollection.next((SongArtistInterface) next);
                        if(next.getArtist().equals(artistName)) {
                            songs.add(next);
                            toReturn.add(next.getTitle() + " by " + next.getArtist() + " in the album: " + next.getAlbum());
                        }
                        else {
                            if (songs.size() > 0) {
                                return toReturn;
                            }
                        }
                    }
                }
            }
        }
//        return songs;
        return toReturn;

//        if (!artistSortedCollection.isEmpty()) {
//            if (artistSortedCollection.getRoot() != null) {
//                SongInterface root = artistSortedCollection.getRoot();
//                if (root.getArtist().equals(artistName)) {
//                    songs.add(root);
//                    toReturn.add(root.getTitle() + " by " + root.getArtist() + " in the album: " + root.getAlbum());
//                }
//                if (artistSortedCollection.hasNext((SongArtistInterface) root)) {
//                    next = artistSortedCollection.next((SongArtistInterface) root);
//                    if (next.getArtist().equals(artistName)) {
//                        songs.add(next);
//                        toReturn.add(next.getTitle() + " by " + next.getArtist() + " in the album: " + next.getAlbum());
//                    }
//                    while(artistSortedCollection.hasNext((SongArtistInterface) root)) {
//                        next = artistSortedCollection.next((SongArtistInterface) next);
//                        if (next == null) {
//                            if (songs.size() == 0) {
//                                throw new NoSuchElementException();
//                            }
////                            return songs;
//                            return toReturn;
//                        }
//                        if (next.getArtist().equals(artistName)) {
//                            songs.add(next);
//                            toReturn.add(next.getTitle() + " by " + next.getArtist() + " in the album: " + next.getAlbum());
//                        }
//                    }
//                }
//            }
//        }
////        return songs;
//        return toReturn;
    }

    /**
     * Returns a list with all Song objects in the provided album
     * @param albumName the name of the album to search
     * @return a List of Songs in the album
     * @throws NoSuchElementException if no Songs exist in the given album
     */
    @Override
    public List<String> searchByAlbum(String albumName) throws NoSuchElementException {
        List<SongInterface> songs = new ArrayList<SongInterface>();
        List<String> toReturn = new ArrayList<String>();

        SongInterface next;

        if (!albumSortedCollection.isEmpty()) {
            if (albumSortedCollection.getRoot() != null) {
                SongInterface root = albumSortedCollection.getRoot();
                if (root.getAlbum().equals(albumName)) {
                    songs.add(root);
                    toReturn.add(root.getTitle() + " by " + root.getArtist() + " in the album: " + root.getAlbum());
                }
                if (albumSortedCollection.hasNext((SongAlbumInterface) root)) {
                    next = albumSortedCollection.next((SongAlbumInterface) root);
                    if (next.getAlbum().equals(albumName)) {
                        songs.add(next);
                        toReturn.add(next.getTitle() + " by " + next.getArtist() + " in the album: " + next.getAlbum());
                    }
                    while(albumSortedCollection.hasNext((SongAlbumInterface) root)) {
                        next = albumSortedCollection.next((SongAlbumInterface) next);
                        if (next == null) {
                            if (songs.size() == 0) {
                                throw new NoSuchElementException();
                            }
//                            return songs;
                            return toReturn;
                        }
                        if (next.getAlbum().equals(albumName)) {
                            songs.add(next);
                            toReturn.add(next.getTitle() + " by " + next.getArtist() + " in the album: " + next.getAlbum());
                        }
                    }
                }
            }
        }
//        return songs;
        return toReturn;
    }

    /**
     * Checks whether the collection contains the provided Song
     * @param data the Song to test for
     * @return true if the provided Song is in the tree, false if it is not in the tree
     */
    @Override
    public boolean contains(SongInterface data) {
        SongTitleInterface songContains;
        if (songType.equals("BD")) {
            songContains = new SongTitleBD(data.getTitle(), data.getArtist(), data.getAlbum());
        }
        else if (songType.equals("DW")) {
            songContains = new SongTitleDW(data.getTitle(), data.getArtist(), data.getAlbum());
        }
        else {
            throw new IllegalArgumentException();
        }

        return songSortedCollection.contains(songContains);
    }

    /**
     * Gets the number of Songs in the collection
     * @return the number of Songs in the collection
     */
    @Override
    public int size() {
        return songSortedCollection.size();
    }

    /**
     * Checks if the collection is empty (does not contain any Songs)
     * @return true if the collection is empty, false if not
     */
    @Override
    public boolean isEmpty() {
        return songSortedCollection.isEmpty();
    }
}
