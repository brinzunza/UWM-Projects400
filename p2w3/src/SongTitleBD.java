public class SongTitleBD extends SongBD implements SongTitleInterface {
    public SongTitleBD(String title, String artist, String album) {
        super(title, artist, album);
    }

    @Override
    public int compareTo(SongTitleInterface o) {
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
