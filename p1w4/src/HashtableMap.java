// --== CS400 Project One File Header ==--
// Name: Bruno Inzunza
// CSL Username: bruno
// Email: binzunza@wisc.edu
// Lecture #: LEC4 3:30-4:20
// Notes to Grader: <any optional extra notes to your grader>
import java.util.NoSuchElementException;
import java.lang.IllegalArgumentException;


public class HashtableMap<KeyType, ValueType> implements MapADT<KeyType, ValueType> {
    protected Pair<KeyType, ValueType>[] entries;
    protected int size;

    public HashtableMap(int capacity) {

        entries = (Pair<KeyType, ValueType>[]) new Pair[capacity];
    }

    public HashtableMap() {
        this(8);
    }

    /**
     * puts a key and value pair into the hashtable.
     * @param key the key in which the index will be found from
     * @param value the value attached to the key which will go into the hashtable
     * @throws IllegalArgumentException thrown if key is null or if the hashtable already contains that key
     */
    @Override
    public void put(KeyType key, ValueType value) throws IllegalArgumentException {
        if (key == null || containsKey(key)) {
            throw new IllegalArgumentException("input key is not a valid input to put");
        }
        int index = Math.abs(key.hashCode() % entries.length);
        while (entries[index] != null) {
            index = (index + 1) % entries.length;
        }
        entries[index] = new Pair<>(key, value);
        size++;
        if ((double) size / entries.length >= 0.7) {
            grow();
        }
    }

    /**
     * method uses a key to get the value that is attached to it inside of the hashtable.
     * @param key the key which will find the value in the hashtable
     * @return returns the value that is attached with the key
     * @throws NoSuchElementException thrown if the hashtable does not contain the key
     */
    @Override
    public ValueType get(KeyType key) throws NoSuchElementException {
        int index = Math.abs(key.hashCode() % entries.length);
        if(!containsKey(key)){
            throw new NoSuchElementException();
        }
        while (entries[index] != null) {
            if (entries[index].key.equals(key)) {
                return entries[index].value;
            }
            index = (index + 1) % entries.length;
        }
        return null;
    }

    /**
     * looks up the key in the hashtable and checks whether it is included or not
     * @param key the key that will be looked up in the hashtable
     * @return true if key is found and false otherwise
     */
    @Override
    public boolean containsKey(KeyType key) {
        int index = Math.abs(key.hashCode() % entries.length);
        for(int i = 0; i < entries.length; i++){
            if(entries[i] != null && entries[i].key.equals(key)){
                return true;
            }
        }
        return false;
    }

    /**
     * removes the key and value pair in the hashtable and replaces it with a sentinel object
     * @param key the key that will be removed from the hashtable
     * @return the value from the key that was removed from the hashtable
     * @throws NoSuchElementException thrown if the key does not exist in the hashtable
     */
    @Override
    public ValueType remove(KeyType key) throws NoSuchElementException{
        Pair<KeyType, ValueType> SENTINEL = new Pair((KeyType)"o",(ValueType)"p");
        int index = Math.abs(key.hashCode() % entries.length);
        while (entries[index] != null) {
            if (entries[index].key.equals(key)) {
                ValueType value = entries[index].value;
                entries[index] = SENTINEL;
                size--;
                return value;
            }
            index = (index + 1) % entries.length;
        }
        throw new NoSuchElementException("Cannot remove as there is no value using this key");
    }

    /**
     * method clears the contents of the hashtable
     */
    @Override
    public void clear() {
        for(int i = 0; i < entries.length; i++){
            entries[i] = null;
        }
        size = 0;
    }

    /**
     * method gets the size of the hashtable
     * @return int the size of the hashtable
     */
    @Override
    public int getSize(){
        return size;
    }

    /**
     * method gets the total capacity of the hashtable
     * @return int the capacity of the hashtable
     */
    @Override
    public int getCapacity(){
        return entries.length;
    }

    /**
     * when hashtable is filled at 70% or greater, it will double it's capacity
     */
    private void grow() {
        Pair<KeyType, ValueType>[] oldEntries = entries;
        entries = (Pair<KeyType, ValueType>[]) new Pair[entries.length * 2];
        size = 0;
        for (Pair<KeyType, ValueType> entry : oldEntries) {
            if (entry != null) {
                put(entry.key, entry.value);
            }
        }
    }

    /**
     * creates a key value pair
     * @param <KeyType> the key
     * @param <ValueType> the value
     */
    private static class Pair<KeyType, ValueType> {
        KeyType key;
        ValueType value;

        Pair(KeyType key, ValueType value) {
            this.key = key;
            this.value = value;
        }
    }
}
