import java.io.FileNotFoundException;
import java.util.List;

//Backend Developer (BD): Implements the application specific logic and capabilities of your project.  This often brings together the use of the AE's data structure with extended capabilities with the data objects that are loaded by the DW's code.  And this will provide the Frontend Developer with an interface they can call into to track and respond to all user's requests.
public interface CHSearchBackendInterface {
    // public CHSearchBackendInterface(HashtableWithDuplicateKeysInterface<String,PostInterface> hashtable, PostReaderInterface postReader);
    public void loadData(String filename) throws FileNotFoundException, FileNotFoundException;
    public List<String> findPostsByTitleWords(String words);
    public List<String> findPostsByBodyWords(String words);
    public List<String> findPostsByTitleOrBodyWords(String words);
    public String getStatisticsString();
}