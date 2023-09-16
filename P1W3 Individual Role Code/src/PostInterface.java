import java.io.FileNotFoundException;
import java.util.List;
//Data Wrangler (DW): Responsible for finding or creating datasets for your application to make use of, code that reads (loads) and writes (saves) data to/from files, and/or code that represents instances of your data within a java program (which may include data validation).
public interface PostInterface {
    // public PostInterface(String title, String url, String body);
    public String getTitle();
    public String getUrl();
    public String getBody();
}

interface PostReaderInterface {
    // public PostReaderInterface();
    public List<PostInterface> readPostsFromFile(String filename) throws FileNotFoundException;
}
