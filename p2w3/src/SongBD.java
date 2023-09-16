public class SongBD implements SongInterface {
    protected String title;
    protected String artist;
    protected String album;
    public SongBD(String title, String artist, String album) {
        this.title = title;
        this.artist = artist;
        this.album = album;
    }
    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getArtist() {
        return artist;
    }

    @Override
    public String getAlbum() {
        return album;
    }
}
