import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class RedBlackTreeAEBD<T extends Comparable<T>> extends RedBlackTree<T> implements RedBlackTreeInterfaceAE<T> {

    public RedBlackTreeAEBD() {
        super();
    }

    @Override
    public boolean insert(T data) throws NullPointerException, IllegalArgumentException {
        if (data == null) {
            throw new NullPointerException("This RedBlackTree cannot store null references.");
        }

        Node<T> newNode = new Node<T>(data);
        if (this.root == null) {
            // add first node to an empty tree
            root = newNode; size++; //this.enforceRBTreePropertiesAfterInsert(newNode);
            return true;
        }
        else {
            Node next = this.root;
            while (next.context[2] != null) {
                next = next.context[2];
            }
            next.context[2] = newNode;
            size++;
            return true;
        }
    }
    
    @Override
    public T getRoot() {
        return root.data;
    }

    @Override
    public T searchSpecific(T data) {
        if (findNodeWithData(data) == null) {
            return null;
        }
        return findNodeWithData(data).data;
    }

    public List<T> iterator() {
	return null;
    }

    @Override
    public T next(T data) {
        Node<T> newNode = findNodeWithData(data);
        if(newNode.context[2] != null) {
            return newNode.context[2].data;
        }
        return null;
    }

    @Override
    public boolean hasNext(T data) {
        Node newNode = findNodeWithData(data);
        if (newNode != null && newNode.context[2] != null) {
            return true;
        }
        return false;
    }
}

