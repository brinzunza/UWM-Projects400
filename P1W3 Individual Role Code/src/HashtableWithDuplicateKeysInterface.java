import java.util.List;
//Algorithm Engineer (AE): Extends the central data structure (and algorithms) that your project is designed around to provide extra capabilities.  These extra capabilities should be generally useful (like part of a library) to many different applications, and should not dependent on any code that is specific to your project.
public interface HashtableWithDuplicateKeysInterface<KeyType, ValueType> extends MapADT<KeyType,List<ValueType>> {
    //public HashtableWithDuplicateKeysInterface(int capacity);
    //public HashtableWithDuplicateKeysInterface();
    public void putOne(KeyType key, ValueType value);
    public void removeOne(KeyType key, ValueType value);
    public int getNumberOfValues();
}

