import java.util.Iterator;
import java.util.List;

public interface RedBlackTreeInterfaceAE<T extends Comparable<T>> extends SortedCollectionInterface<T> {
    public boolean insert(T Data) throws NullPointerException, IllegalArgumentException;

    public int size();

    public boolean isEmpty();

    public boolean contains(T data);

    public T searchSpecific(T data);

   public List<T> iterator();

   public T next(T data);

   public boolean hasNext(T data);

   public T getRoot();

}