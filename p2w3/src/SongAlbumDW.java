// --== CS400 Project Two File Header ==--
// Name: Krish Isserdasani
// CSL Username: isserdasani
// Email: isserdasani@wisc.edu
// Lecture #: 004 
// Notes to Grader: <any optional extra notes to your grader>

/**
 * Class that defines SongAlbumDW and methods used by it
 * Will extend SongDW and implemets the SongTitle Interface
 */
public class SongAlbumDW extends SongDW implements SongAlbumInterface {
    /**
     * Constructing a Song requires the following data
     *   
     * @param title of the song
     * @param artist who made the song
     * @param album of the song 
     */
    public SongAlbumDW (String title, String artist, String album) {
        super(title, artist, album);
     }
     
     /**
      * uses method from super to get title of the song
      */
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
     * Overriding compareTo to look for album being different, else looking at artist, then title
     */
    @Override
    public int compareTo(SongAlbumInterface o) {
        if(this.getAlbum().compareTo(o.getAlbum()) > 0){
            return 1;
        }
        else if(this.getAlbum().compareTo(o.getAlbum()) < 0){
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
                if(this.getTitle().compareTo(o.getTitle()) > 0){
                    return 1;
                }
                else if(this.getTitle().compareTo(o.getTitle()) < 0){
                    return -1;
                }
                else{
                    return 0;
                    }
                }
            }
    }
    }
        
    
