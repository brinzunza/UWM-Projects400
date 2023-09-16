// --== CS400 Project One File Header ==--
// Name: Bruno Inzunza
// CSL Username: bruno
// Email: binzunza@wisc.edu
// Lecture #: LEC4 3:30-4:20
// Notes to Grader: <any optional extra notes to your grader>
public class HashtableMapTests {
    /**
     * test1 tests the creation of a Hashtable and the put method with multiple things. Also tests if entering a null key correctly creates an exception
     * @return true if no errors exist and false otherwise
     */
    public static boolean test1() {
        HashtableMap test1 = new HashtableMap(8);
        test1.put(1, "theo");
        test1.put(2, "brandon");
        test1.put(3, "Rishab");
        test1.put(4, "Shri");

        try{
            test1.put(null, "theo");
            return false;
        }
        catch(Exception e){
            return true;
        }
    }

    /**
     * test2 tests the get method with a true and exception values
     * @return true if no errors and false otherwise
     */
    public static boolean test2() {
        HashtableMap test1 = new HashtableMap(8);
        test1.put(1, "theo");
        test1.put(2, "brandon");
        test1.put(3, "Rishab");
        test1.put(4, "Shri");

        if(test1.get(1) != "theo"){
            return false;
        }
        try{
            test1.get(0);
        }
        catch(Exception e){
            return true;
        }
        return true;
    }

    /**
     * test3 tests the containskey method with a false and true value as well as the remove method
     * @return true if no errors are found and false otherwise
     */
    public static boolean test3() {
        HashtableMap test1 = new HashtableMap(8);
        test1.put(1, "theo");
        test1.put(2, "brandon");
        test1.put(3, "Rishab");
        test1.put(4, "Shri");

        test1.remove(1);

        if(test1.containsKey(1) != false){
            return false;
        }
        if(test1.containsKey(2) != true){
            return false;
        }
        return true;
    }

    /**
     * test4 tests the getCapacity method and the grow method. Then checks the clear method.
     * @return true if no errors are found and false otherwise
     */
    public static boolean test4() {
        HashtableMap test1 = new HashtableMap(8);
        test1.put(1, "theo");
        test1.put(2, "brandon");
        test1.put(3, "Rishab");
        test1.put(4, "Shri");

        if(test1.getCapacity() != 8){
            return false;
        }

        test1.put(5, "bruno");

        test1.put(6, "ronaldo");

        if(test1.getCapacity() != 16){
            System.out.println(test1.getCapacity());
            return false;
        }


        test1.clear();

        if(test1.getSize() != 0){
            return false;
        }
        return true;

    }

    /**
     * tests the remove and put methods as well as checks if you can properly add keys after previously removing them
     * @return true if no errors are found and false otherwise
     */
    public static boolean test5() {
        HashtableMap test1 = new HashtableMap(8);
        test1.put(1, "theo");
        test1.put(2, "brandon");
        test1.put(3, "Rishab");
        test1.put(4, "Shri");

        test1.remove(1);
        test1.remove(2);
        test1.remove(3);
        test1.put(1, "theo");
        test1.put(2, "brandon");
        test1.put(3, "Rishab");

        if(test1.containsKey(1) != true){
            return false;
        }
        if(test1.containsKey(2) != true){
            return false;
        }
        if(test1.containsKey(3) != true){
            return false;
        }
        try{
            test1.put(1, "theo");
        }
        catch(Exception e){
            return true;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println("Test1: " + test1());
        System.out.println("Test2: " + test2());
        System.out.println("Test3: " + test3());
        System.out.println("Test4: " + test4());
        System.out.println("Test5: " + test5());
    }
}
