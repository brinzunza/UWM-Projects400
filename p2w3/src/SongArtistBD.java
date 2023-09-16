public class SongArtistBD extends SongBD implements SongArtistInterface {
    public SongArtistBD(String title, String artist, String album) {
        super(title, artist, album);
    }

    @Override
    public int compareTo(SongArtistInterface o) {
        if (title.equals(o.getTitle())) {
            if (artist.equals(o.getArtist())) {
                if (album.equals(o.getAlbum())) {
                    return 0;
                }
            }
        }
        return 1;
    }
}
