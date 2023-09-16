public class FrontendDeveloperTests {
    /*
    Test1 tests the using the title search withouot changing input and inputting words
     */
    public static boolean test1() {
        TextUITester tester = new TextUITester("T\nn\nhello,world,computer\nQ\n");
        CHSearchFrontend front = new CHSearchFrontend();
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
    test2 tests the load data option while entering an invalid input
     */
    public static boolean test2() {
        TextUITester tester = new TextUITester("L\nn\nQ");
        CHSearchFrontend front = new CHSearchFrontend();
        front.runCommandLoop();
        String systemOutput = tester.checkOutput();
        //System.out.println(systemOutput);
        if(!systemOutput.contains("Loaded File")){
            return false;
        }
        if (!systemOutput.contains("Please enter a valid input")){
            return false;
        }
        return true;
    }
    /*
    test3 tests the book search without changing input and entering word choices
     */
    public static boolean test3() {
        TextUITester tester = new TextUITester("B\nn\nbody,test,this\nQ");
        CHSearchFrontend front = new CHSearchFrontend();
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
        CHSearchFrontend front = new CHSearchFrontend();
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
        TextUITester tester = new TextUITester("S\nL\nQ");
        CHSearchFrontend front = new CHSearchFrontend();
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
     * test6
     * @return
     */
//    public static boolean test6() {
//        TextUITester tester = new TextUITester("S\nL\nQ");
//        CHSearchFrontend front = new CHSearchFrontend();
//        front.runCommandLoop();
//        String systemOutput = tester.checkOutput();
//        System.out.println(systemOutput);
//        if(!systemOutput.contains("123456: Great")){
//            return false;
//        }
//        if(!systemOutput.contains("Loaded File")) {
//            return false;
//        }
//        return true;
//    }

    /**
     * test7
     * @return
     */
    public static boolean test7() {
        TextUITester tester = new TextUITester("S\nL\nQ");
        CHSearchFrontend front = new CHSearchFrontend();
        front.runCommandLoop();
        String systemOutput = tester.checkOutput();
        System.out.println(systemOutput);
        if(!systemOutput.contains("")){
            return false;
        }
        if(!systemOutput.contains("Loaded File")) {
            return false;
        }
        return true;
    }


    public static void main(String[] args) {
       // CHSearchFrontend test = new CHSearchFrontend();
        //test.runCommandLoop();
        System.out.println("Test1: " + test1());
        System.out.println("Test2: " + test2());
        System.out.println("Test3: " + test3());
        System.out.println("Test4: " + test4());
        System.out.println("Test5: " + test5());
        //System.out.println("Test6: " + test6());
        System.out.println("Test7: " + test7());
    }
}
