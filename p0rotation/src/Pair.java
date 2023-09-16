public class Pair<ValueType, KeyType> {
    private ValueType value;
    private KeyType key;
    public Pair(KeyType key, ValueType value){
        this.key = key;
        this.value = value;
    }

    public KeyType getKey(){
        return this.key;
    }

    public ValueType getValue(){
        return this.value;
    }

}
