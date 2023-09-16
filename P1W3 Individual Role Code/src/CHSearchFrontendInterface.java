import java.util.List;
//Frontend Developer (FD): Implements user interface for the application.  This involves prompting and reading commands from the user, gracefully handling errors with instructive responses, and displaying the results of the BD code's computations as they are requested by the user.



public interface CHSearchFrontendInterface {
    //public CHSearchFrontendXX(Scanner userInput, CHSearchBackendInterface backend);
    public void runCommandLoop();
    public char mainMenuPrompt();
    public void loadDataCommand();
    public List<String> chooseSearchWordsPrompt();
    public void searchTitleCommand(List<String> words);
    public void searchBodyCommand(List<String> words);
    public void searchPostCommand(List<String> words);
    public void displayStatsCommand();

}