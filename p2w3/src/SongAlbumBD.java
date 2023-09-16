public class SongAlbumBD extends SongBD implements SongAlbumInterface {

    public SongAlbumBD(String title, String artist, String album) {
        super(title, artist, album);
    }

    @Override
    public int compareTo(SongAlbumInterface o) {
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
