import java.util.List;
import java.io.FileNotFoundException;
// Your placeholder classes should implement their corresponding interfaces, and will need method stubs: definitions that do nothing and return null or a zero-ish value as needed to compile.

public class CHSearchBackendFD implements CHSearchBackendInterface{
    @Override
    public void loadData(String filename) throws FileNotFoundException {

    }

    @Override
    public List<String> findPostsByTitleWords(String words) {
        return null;
    }

    @Override
    public List<String> findPostsByBodyWords(String words) {
        return null;
    }

    @Override
    public List<String> findPostsByTitleOrBodyWords(String words) {
        return null;
    }

    @Override
    public String getStatisticsString() {
        return null;
    }
}
