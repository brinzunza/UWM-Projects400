/**
 * Class that defines SongTitleDW and methods used by it
 */
public class SongTitleDW extends SongDW implements SongTitleInterface{
/**
 * Constructing a Song requires the following data
 *   
 * @param title of the song
 * @param artist who made the song
 * @param album of the song 
 */
public SongTitleDW (String title, String artist, String album) {
   super(title, artist, album);
}

// Using super's methods here
public String getTitle() {
   return super.getTitle();
}
public String getArtist() {
   return super.getArtist();
}
public String getAlbum() {
   return super.getAlbum();
}

/**
 * Overriding compareTo to look for Title being different, else looking t artist, else album
 */
@Override
public int compareTo(SongTitleInterface o) {
    if(this.getTitle().compareTo(o.getTitle()) > 0){
        return 1;
    }
    else if(this.getTitle().compareTo(o.getTitle()) < 0){
        return -1;
    }
    else{
        if(this.getArtist().compareTo(o.getArtist()) > 0){
            return 1;
        }
        else if(this.getArtist().compareTo(o.getArtist()) < 0){
            return -1;
        }
        else{
                if(this.getAlbum().compareTo(o.getAlbum()) > 0){
                    return 1;
                }
                else if(this.getAlbum().compareTo(o.getAlbum()) < 0){
                    return -1;
                }
                else{
                    return 0;
                }
            }
        }
    }
}
    

