import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class CHSearchBackendFD implements CHSearchBackendInterface {
  private List<String> wordList = new ArrayList<>();

  @Override
  public void loadData(String filename) throws FileNotFoundException {
    System.out.println("File Loaded");
    
  }

  @Override
  public List<String> findPostsByTitleWords(String words) {
    wordList.clear();
    if(words == null) {
      return null;
    }
    wordList.add("hello");
    wordList.add("world");
    wordList.add("computer");
    return wordList;
  }

  @Override
  public List<String> findPostsByBodyWords(String words) {
    wordList.clear();
    if(words == null) {
      return null;
    }
    wordList.add("body");
    wordList.add("test");
    wordList.add("this");
    return wordList;
  }

  @Override
  public List<String> findPostsByTitleOrBodyWords(String words) {
    wordList.clear();
    if(words == null) {
      return null;
    }
    wordList.add("title");
    wordList.add("and");
    wordList.add("body");
    wordList.add("test");
    wordList.add("this");
    return wordList;
  }

  @Override
  public String getStatisticsString() {
    return "123456: Great";
  }

}
