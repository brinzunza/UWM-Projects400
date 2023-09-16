import java.net.SocketTimeoutException;
import java.sql.SQLOutput;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

    // Your placeholder classes should implement their corresponding interfaces, and will need method stubs: definitions that do nothing and return null or a zero-ish value as needed to compile.

public class CHSearchFrontend implements CHSearchFrontendInterface{
        Scanner input = new Scanner(System.in);
        char choice;//global user input variable

    /**
     * mainMenuPrompt asks the user for an input after printing a multitude of selections. Then sends the data to other methods.
     * @return returns the character that the user confirms to choose
     */
        @Override
        public char mainMenuPrompt(){
            System.out.println("Select an option: \n[L]oad data from file\nSearch Post [T]itles\nSearch Post [B]odies\nSearch [P]ost titles and bodies\nDisplay [S]tatistics for dataset\n[Q]uit");
            this.choice = input.nextLine().charAt(0);
            if(choice == 'L'){
                loadDataCommand();
                return 'L';
            }
            if(choice == 'T'){
                searchTitleCommand(chooseSearchWordsPrompt());
                return ('T');
            }
            if(choice == 'B'){
                searchBodyCommand(chooseSearchWordsPrompt());
                return ('B');
            }
            if(choice == 'P'){
                searchPostCommand(chooseSearchWordsPrompt());
                return ('P');
            }
            if(choice == 'S'){
                displayStatsCommand();
                return('S');
            }
            if(choice == 'Q'){
                return ('Q');
            }
            else {
                System.out.println("Please enter a valid input");
                runCommandLoop();
                return '\0';
            }
        }

    /**
     * loadDataCommand method loads the file that needs to be loaded and catches exceptions if not functional
     */
    @Override
        public void loadDataCommand() {
            CHSearchBackend temp = new CHSearchBackend();
            try {
                temp.loadData("text.txt");
                System.out.println("Loaded File");
            }
            catch(Exception e){
            }
        }

    /**
     * runCommandLoop continuously runs the mainMenuPrompt method until the user chooses to quit by inputting 'q'
     */
    @Override
        public void runCommandLoop(){
            while(choice != 'Q'){
                mainMenuPrompt();
            }

        }

    /**
     * chooseSearchWordsPrompt ends the program if user chooses to quit and also allows to give the user a confirm option. Then asks user for following input options for searchs
     * @return the words that the user wants to input for the search
     */
    @Override
        public List<String> chooseSearchWordsPrompt(){
            if(choice == 'q'){
                return null;
            }
            System.out.println("Your selection was " + this.choice + ". Would you like to change the type of search? [y]es or [n]o");
            char choiceTwo = input.nextLine().charAt(0);
            while(choiceTwo != 'Q') {
                if (choiceTwo == 'n' || choiceTwo == 'N') {
                    System.out.println("Enter search words(separate with commas): ");
                    String words = input.nextLine();
                    if (words == null) {
                        System.out.println("Please enter words again");
                        chooseSearchWordsPrompt();
                    }
                    String[] wordArray = words.split(",");
                    ArrayList<String> list = new ArrayList<>(Arrays.asList(wordArray));
                    return list;
                }
                if (choiceTwo == 'y' || choiceTwo == 'Y') {
                    runCommandLoop();
                }
                else {
                    System.out.println("Please enter y or n");
                    chooseSearchWordsPrompt();
                }
                choiceTwo = input.nextLine().charAt(0);
            }
            return null;
        }

    /**
     * searchTitleCommand calls on the backend function to search the title based on user inputs
     * @param words user inputted words for search
     */
    @Override
        public void searchTitleCommand(List<String> words) {
            CHSearchBackend temp = new CHSearchBackend();
            String result = null;
            for(String word : words){
                result += word;
            }
            System.out.println(temp.findPostsByTitleWords(result));
        }

    /**
     * searchBodyCommand calls on the backend function to search the body command based on user inputs
     * @param words user inputted words for search
     */
    @Override
        public void searchBodyCommand(List<String> words) {
            CHSearchBackend temp = new CHSearchBackend();
            String result = null;
            for(String word : words){
                result += word;
            }
            System.out.println(temp.findPostsByBodyWords(result));
        }

    /**
     * searchPostCommand calls on the backend functicon to search the post command based on user inputs
     * @param words user inputted words for search
     */
    @Override
        public void searchPostCommand(List<String> words) {
            CHSearchBackend temp = new CHSearchBackend();
            String result = "";
            for(String word : words){
                result += word;
            }
            System.out.println(temp.findPostsByTitleOrBodyWords(result));
        }

    /**
     * displayStatsCommand calls on the backend function to search the stats command based on user inputs
     */
    @Override
        public void displayStatsCommand() {
            CHSearchBackend temp = new CHSearchBackend();
            System.out.println(temp.getStatisticsString());
        }


    }


