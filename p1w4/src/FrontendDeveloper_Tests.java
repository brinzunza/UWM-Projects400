import java.io.FileNotFoundException;
import java.sql.SQLOutput;
import java.util.List;
import java.util.Scanner;

public class FrontendDeveloper_Tests {



    /*
    Test1 tests the using the title search withouot changing input and inputting words
     */
    public static boolean test1() {
        TextUITester tester = new TextUITester("T\nn\nhello,world,computer\nQ\n");
        Scanner input = new Scanner(System.in);
        CHSearchBackendFD backend = new CHSearchBackendFD();
        CHSearchFrontendFD front = new CHSearchFrontendFD(input, backend);
        front.runCommandLoop();
        String systemOutput = tester.checkOutput();
        //System.out.println(systemOutput);
        if(!systemOutput.contains("Your selection was T. Would you like to change the type of search? [y]es or [n]o")){
            return false;
        }
        if(!systemOutput.contains("Enter search words(separate with commas):")){
            return false;
        }
        if (!systemOutput.contains("[hello, world, computer]")){
            return false;
        }
        return true;
    }
    /*
    test2 tests the load data option
     */
    public static boolean test2() {
        TextUITester tester = new TextUITester("L\ndata/small.txt\nQ");
        Scanner input = new Scanner(System.in);
        CHSearchBackendFD backend = new CHSearchBackendFD();
        CHSearchFrontendFD front = new CHSearchFrontendFD(input, backend);
        front.runCommandLoop();
        String systemOutput = tester.checkOutput();
        //System.out.println(systemOutput);
        if(!systemOutput.contains("Loaded File")){
            return false;
        }
        return true;
    }
    /*
    test3 tests the book search without changing input and entering word choices
     */
    public static boolean test3() {
        TextUITester tester = new TextUITester("B\nn\nbody,test,this\nQ");
        Scanner input = new Scanner(System.in);
        CHSearchBackendFD backend = new CHSearchBackendFD();
        CHSearchFrontendFD front = new CHSearchFrontendFD(input, backend);
        front.runCommandLoop();
        String systemOutput = tester.checkOutput();
        //System.out.println(systemOutput);
        if(!systemOutput.contains("Your selection was B. Would you like to change the type of search? [y]es or [n]o")){
            return false;
        }
        if(!systemOutput.contains("Enter search words(separate with commas):")){
            return false;
        }
        if(!systemOutput.contains("[body, test, this]")){
            return false;
        }
        return true;
    }
    /*
    test4 tests the posts search option without changing input and entering word choices.
     */
    public static boolean test4() {
        TextUITester tester = new TextUITester("P\nn\ntitle,and,body,test,this\nQ");
        Scanner input = new Scanner(System.in);
        CHSearchBackendFD backend = new CHSearchBackendFD();
        CHSearchFrontendFD front = new CHSearchFrontendFD(input, backend);
        front.runCommandLoop();
        String systemOutput = tester.checkOutput();
        //System.out.println(systemOutput);
        if(!systemOutput.contains("Your selection was P. Would you like to change the type of search? [y]es or [n]o")){
            return false;
        }
        if(!systemOutput.contains("Enter search words(separate with commas): ")){
            return false;
        }
        if(!systemOutput.contains("[title, and, body, test, this]")){
            return false;
        }
        return true;
    }
    /*
    test5 tests using the statistics option and the load data option back to back
     */
    public static boolean test5() {
        TextUITester tester = new TextUITester("S\nL\ndata/small.txt\nQ");
        Scanner input = new Scanner(System.in);
        CHSearchBackendFD backend = new CHSearchBackendFD();
        CHSearchFrontendFD front = new CHSearchFrontendFD(input, backend);
        front.runCommandLoop();
        String systemOutput = tester.checkOutput();
        //System.out.println(systemOutput);
        if(!systemOutput.contains("123456: Great")){
            return false;
        }
        if(!systemOutput.contains("Loaded File")) {
            return false;
        }
        return true;
    }

    /**
     * test6 tests the use of loading data properly and then searching for posts and bodies with an input of "celery"
     *
     * @return boolean returns true if all functions work and false otherwise
     */
    public static boolean test6() {
        TextUITester tester = new TextUITester("L\nC:\\Users\\Bruno\\IdeaProjects\\p1w4\\src\\data\\small.txt\nP\nn\ncelery\nQ");
        Scanner input = new Scanner(System.in);
        PostReaderDW postReader = new PostReaderDW();
        HashtableWithDuplicateKeysAE hashTable = new HashtableWithDuplicateKeysAE();
        CHSearchBackendBD backend = new CHSearchBackendBD(hashTable, postReader);
        CHSearchFrontendFD front = new CHSearchFrontendFD(input, backend);
        front.runCommandLoop();
        String systemOutput = tester.checkOutput();
        //System.out.println(systemOutput);
        if(!systemOutput.contains("I have so much celery! What do you guys do with it! - https://www.reddit.com/r/EatCheapAndHealthy/comments/zop5zg/i_have_so_much_celery_what_do_you_guys_do_with_it/")){
            return false;
        }
        if(!systemOutput.contains("Loaded File")) {
            return false;
        }
        if(!systemOutput.contains("Your selection was P. Would you like to change the type of search? [y]es or [n]o")){
            return false;
        }
        return true;
    }

    /**
     * test7 tests the use of loading data properly and then getting the statistics of the file loaded
     * @return boolean returns true if all functions work properly and false otherwise.
     */
    public static boolean test7() {
        TextUITester tester = new TextUITester("L\nC:\\Users\\Bruno\\IdeaProjects\\p1w4\\src\\data\\small.txt\nS\nQ");
        Scanner input = new Scanner(System.in);
        PostReaderDW postReader = new PostReaderDW();
        HashtableWithDuplicateKeysAE hashTable = new HashtableWithDuplicateKeysAE();
        CHSearchBackendBD backend = new CHSearchBackendBD(hashTable, postReader);
        CHSearchFrontendFD front = new CHSearchFrontendFD(input, backend);
        front.runCommandLoop();
        String systemOutput = tester.checkOutput();
        //System.out.println(systemOutput);
        if(!systemOutput.contains("12 posts")){
            return false;
        }
        if(!systemOutput.contains("571 unique words")){
            return false;
        }
        if(!systemOutput.contains("2933 total word-post pairs")){
            return false;
        }
        if(!systemOutput.contains("Loaded File")) {
            return false;
        }
        return true;
    }

    /**
     * test8 tests the backend method of load data and the findPostsByTitleOrBodyWords method to get an output
     * @return true if all functions work properly and false otherwise
     */
    public static boolean test8() {
        PostReaderDW postReader = new PostReaderDW();
        HashtableWithDuplicateKeysAE hashTable = new HashtableWithDuplicateKeysAE();
        CHSearchBackendBD backend = new CHSearchBackendBD(hashTable, postReader);
        //System.out.println(systemOutput);
        try {
            backend.loadData("C:\\Users\\Bruno\\IdeaProjects\\p1w4\\src\\data\\small.txt");
        }
        catch(Exception e){
            return false;
        }
        //System.out.println(backend.findPostsByTitleOrBodyWords("celery"));
        if(!backend.findPostsByTitleOrBodyWords("celery").contains("I have so much celery! What do you guys do with it! - https://www.reddit.com/r/EatCheapAndHealthy/comments/zop5zg/i_have_so_much_celery_what_do_you_guys_do_with_it/")){
            return false;
        }
        return true;
    }

    /**
     * test9 tests the use of the backend loadData function and assures that no excpetion is thrown
     * @return true if no exception is thrown and false otherwise
     */
    public static boolean test9() {
        PostReaderDW postReader = new PostReaderDW();
        HashtableWithDuplicateKeysAE hashTable = new HashtableWithDuplicateKeysAE();
        CHSearchBackendBD backend = new CHSearchBackendBD(hashTable, postReader);
        try{
            backend.loadData("C:\\Users\\Bruno\\IdeaProjects\\p1w4\\src\\data\\small.txt");
        }
        catch(Exception e){
            return false;
        }
        return true;
    }


    public static void main(String[] args) {
//        Scanner input = new Scanner(System.in);
//        PostReaderDW postReader = new PostReaderDW();
//        HashtableWithDuplicateKeysAE hashTable = new HashtableWithDuplicateKeysAE();
//        CHSearchBackendBD backend = new CHSearchBackendBD(hashTable, postReader);
//        CHSearchFrontendFD front = new CHSearchFrontendFD(input, backend);
//        front.runCommandLoop();
        System.out.println("Frontend Individual Test 1: " + test1());
        System.out.println("Frontend Individual Test 2: " + test2());
        System.out.println("Frontend Individual Test 3: " + test3());
        System.out.println("Frontend Individual Test 4: " + test4());
        System.out.println("Frontend Individual Test 5: " + test5());
        System.out.println("Frontend Integration Test 1: " + test6());
        System.out.println("Frontend Integration Test 2: " + test7());
        System.out.println("Frontend Partner Backend Test 1: " + test8());
        System.out.println("Frontend Partner Backend Test 2: " + test9());
    }
}
