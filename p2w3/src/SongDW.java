// --== CS400 Project Two File Header ==--
// Name: Krish Isserdasani
// CSL Username: isserdasani
// Email: isserdasani@wisc.edu
// Lecture #: 004 
// Notes to Grader: <any optional extra notes to your grader>

/**
 * Defines SongDW and the emthods used by it and its children
 */
public class SongDW implements SongInterface {

private String title;
private String artist;
private String album;

/**
 * Constructing a Song requires the following data
 *   
 * @param title of the song
 * @param artist who made the song
 * @param album of the song 
 */
public SongDW (String title, String artist, String  album){
    this.title = title;
    this.artist = artist;
    this.album = album;
}

@Override
public String getTitle() {
    return this.title;
}
@Override
public String getArtist() {
    return this.artist;
}
@Override
public String getAlbum() {
    return this.album;
}

}
