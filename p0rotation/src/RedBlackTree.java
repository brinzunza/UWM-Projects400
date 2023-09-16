import java.util.LinkedList;
import java.util.Stack;

/**
 * Red-Black Tree implementation with a Node inner class for representing
 * the nodes of the tree. Currently, this implements a Binary Search Tree that
 * we will turn into a red black tree by modifying the insert functionality.
 * In this activity, we will start with implementing rotations for the binary
 * search tree insert algorithm.
 */
public class RedBlackTree<T extends Comparable<T>> implements SortedCollectionInterface<T> {

    /**
     * This class represents a node holding a single value within a binary tree.
     */
    protected static class Node<T> {
        public T data;
        // The context array stores the context of the node in the tree:
        // - context[0] is the parent reference of the node,
        // - context[1] is the left child reference of the node,
        // - context[2] is the right child reference of the node.
        // The @SupressWarning("unchecked") annotation is used to supress an unchecked
        // cast warning. Java only allows us to instantiate arrays without generic
        // type parameters, so we use this cast here to avoid future casts of the
        // node type's data field.
        @SuppressWarnings("unchecked")
        public Node<T>[] context = (Node<T>[])new Node[3];
        public Node(T data) { this.data = data; }

        /**
         * @return true when this node has a parent and is the right child of
         * that parent, otherwise return false
         */
        public boolean isRightChild() {
            return context[0] != null && context[0].context[2] == this;
        }

    }

    protected Node<T> root; // reference to root node of tree, null when empty
    protected int size = 0; // the number of values in the tree

    /**
     * Performs a naive insertion into a binary search tree: adding the input
     * data value to a new node in a leaf position within the tree. After
     * this insertion, no attempt is made to restructure or balance the tree.
     * This tree will not hold null references, nor duplicate data values.
     * @param data to be added into this binary search tree
     * @return true if the value was inserted, false if not
     * @throws NullPointerException when the provided data argument is null
     * @throws IllegalArgumentException when data is already contained in the tree
     */
    public boolean insert(T data) throws NullPointerException, IllegalArgumentException {
        // null references cannot be stored within this tree
        if(data == null) throw new NullPointerException(
                "This RedBlackTree cannot store null references.");

        Node<T> newNode = new Node<>(data);
        if (this.root == null) {
            // add first node to an empty tree
            root = newNode; size++; return true;
        } else {
            // insert into subtree
            Node<T> current = this.root;
            while (true) {
                int compare = newNode.data.compareTo(current.data);
                if (compare == 0) {
                    throw new IllegalArgumentException("This RedBlackTree already contains value " + data.toString());
                } else if (compare < 0) {
                    // insert in left subtree
                    if (current.context[1] == null) {
                        // empty space to insert into
                        current.context[1] = newNode;
                        newNode.context[0] = current;
                        this.size++;
                        return true;
                    } else {
                        // no empty space, keep moving down the tree
                        current = current.context[1];
                    }
                } else {
                    // insert in right subtree
                    if (current.context[2] == null) {
                        // empty space to insert into
                        current.context[2] = newNode;
                        newNode.context[0] = current;
                        this.size++;
                        return true;
                    } else {
                        // no empty space, keep moving down the tree
                        current = current.context[2];
                    }
                }
            }
        }
    }

    /**
     * Performs the rotation operation on the provided nodes within this tree.
     * When the provided child is a left child of the provided parent, this
     * method will perform a right rotation. When the provided child is a
     * right child of the provided parent, this method will perform a left rotation.
     * When the provided nodes are not related in one of these ways, this method
     * will throw an IllegalArgumentException.
     * @param child is the node being rotated from child to parent position
     *      (between these two node arguments)
     * @param parent is the node being rotated from parent to child position
     *      (between these two node arguments)
     * @throws IllegalArgumentException when the provided child and parent
     *      node references are not initially (pre-rotation) related that way
     */
    private void rotate(Node<T> child, Node<T> parent) throws IllegalArgumentException {
        //if child node and parent node are not connected
        if (!child.context[0].equals(parent)) {
            throw new IllegalArgumentException("Not a valid parent and child input");
        }
        if (!child.isRightChild()) {
            if (parent.context[0] == null) {
                this.root = child;
            }
            //right rotation
            if (parent.context[0] != null) {
                if (parent.isRightChild()) {
                    child.context[0] = parent.context[1];
                    parent.context[0].context[2] = child;
                }
                else {
                    child.context[0] = parent.context[0];
                    parent.context[0].context[1] = child;
                }
            }
            parent.context[1] = child.context[2];
            child.context[2] = parent;
            parent.context[0] = child;
        }
        //left rotation
        else {
            if (parent.context[0] == null) {
                this.root = child;
            }

            if (parent.context[0] != null) {
                if (parent.isRightChild()) {
                    child.context[0] = parent.context[0];
                    parent.context[0].context[2] = child;
                } else {
                    child.context[0] = parent.context[0];
                    parent.context[0].context[1] = child;
                }
            }
            parent.context[2] = child.context[1];
            child.context[1] = parent;
        }
    }

    /**
     * Get the size of the tree (its number of nodes).
     * @return the number of nodes in the tree
     */
    public int size() {
        return size;
    }

    /**
     * Method to check if the tree is empty (does not contain any node).
     * @return true of this.size() return 0, false if this.size() > 0
     */
    public boolean isEmpty() {
        return this.size() == 0;
    }

    /**
     * Removes the value data from the tree if the tree contains the value.
     * This method will not attempt to rebalance the tree after the removal and
     * should be updated once the tree uses Red-Black Tree insertion.
     * @return true if the value was remove, false if it didn't exist
     * @throws NullPointerException when the provided data argument is null
     * @throws IllegalArgumentException when data is not stored in the tree
     */
    public boolean remove(T data) throws NullPointerException, IllegalArgumentException {
        // null references will not be stored within this tree
        if (data == null) {
            throw new NullPointerException("This RedBlackTree cannot store null references.");
        } else {
            Node<T> nodeWithData = this.findNodeWithData(data);
            // throw exception if node with data does not exist
            if (nodeWithData == null) {
                throw new IllegalArgumentException("The following value is not in the tree and cannot be deleted: " + data.toString());
            }
            boolean hasRightChild = (nodeWithData.context[2] != null);
            boolean hasLeftChild = (nodeWithData.context[1] != null);
            if (hasRightChild && hasLeftChild) {
                // has 2 children
                Node<T> successorNode = this.findMinOfRightSubtree(nodeWithData);
                // replace value of node with value of successor node
                nodeWithData.data = successorNode.data;
                // remove successor node
                if (successorNode.context[2] == null) {
                    // successor has no children, replace with null
                    this.replaceNode(successorNode, null);
                } else {
                    // successor has a right child, replace successor with its child
                    this.replaceNode(successorNode, successorNode.context[2]);
                }
            } else if (hasRightChild) {
                // only right child, replace with right child
                this.replaceNode(nodeWithData, nodeWithData.context[2]);
            } else if (hasLeftChild) {
                // only left child, replace with left child
                this.replaceNode(nodeWithData, nodeWithData.context[1]);
            } else {
                // no children, replace node with a null node
                this.replaceNode(nodeWithData, null);
            }
            this.size--;
            return true;
        }
    }

    /**
     * Checks whether the tree contains the value *data*.
     * @param data the data value to test for
     * @return true if *data* is in the tree, false if it is not in the tree
     */
    public boolean contains(T data) {
        // null references will not be stored within this tree
        if (data == null) {
            throw new NullPointerException("This RedBlackTree cannot store null references.");
        } else {
            Node<T> nodeWithData = this.findNodeWithData(data);
            // return false if the node is null, true otherwise
            return (nodeWithData != null);
        }
    }

    /**
     * Helper method that will replace a node with a replacement node. The replacement
     * node may be null to remove the node from the tree.
     * @param nodeToReplace the node to replace
     * @param replacementNode the replacement for the node (may be null)
     */
    protected void replaceNode(Node<T> nodeToReplace, Node<T> replacementNode) {
        if (nodeToReplace == null) {
            throw new NullPointerException("Cannot replace null node.");
        }
        if (nodeToReplace.context[0] == null) {
            // we are replacing the root
            if (replacementNode != null)
                replacementNode.context[0] = null;
            this.root = replacementNode;
        } else {
            // set the parent of the replacement node
            if (replacementNode != null)
                replacementNode.context[0] = nodeToReplace.context[0];
            // do we have to attach a new left or right child to our parent?
            if (nodeToReplace.isRightChild()) {
                nodeToReplace.context[0].context[2] = replacementNode;
            } else {
                nodeToReplace.context[0].context[1] = replacementNode;
            }
        }
    }

    /**
     * Helper method that will return the inorder successor of a node with two children.
     * @param node the node to find the successor for
     * @return the node that is the inorder successor of node
     */
    protected Node<T> findMinOfRightSubtree(Node<T> node) {
        if (node.context[1] == null && node.context[2] == null) {
            throw new IllegalArgumentException("Node must have two children");
        }
        // take a steop to the right
        Node<T> current = node.context[2];
        while (true) {
            // then go left as often as possible to find the successor
            if (current.context[1] == null) {
                // we found the successor
                return current;
            } else {
                current = current.context[1];
            }
        }
    }

    /**
     * Helper method that will return the node in the tree that contains a specific
     * value. Returns null if there is no node that contains the value.
     * @return the node that contains the data, or null of no such node exists
     */
    protected Node<T> findNodeWithData(T data) {
        Node<T> current = this.root;
        while (current != null) {
            int compare = data.compareTo(current.data);
            if (compare == 0) {
                // we found our value
                return current;
            } else if (compare < 0) {
                // keep looking in the left subtree
                current = current.context[1];
            } else {
                // keep looking in the right subtree
                current = current.context[2];
            }
        }
        // we're at a null node and did not find data, so it's not in the tree
        return null;
    }

    /**
     * This method performs an inorder traversal of the tree. The string
     * representations of each data value within this tree are assembled into a
     * comma separated string within brackets (similar to many implementations
     * of java.util.Collection, like java.util.ArrayList, LinkedList, etc).
     * @return string containing the ordered values of this tree (in-order traversal)
     */
    public String toInOrderString() {
        // generate a string of all values of the tree in (ordered) in-order
        // traversal sequence
        StringBuffer sb = new StringBuffer();
        sb.append("[ ");
        if (this.root != null) {
            Stack<Node<T>> nodeStack = new Stack<>();
            Node<T> current = this.root;
            while (!nodeStack.isEmpty() || current != null) {
                if (current == null) {
                    Node<T> popped = nodeStack.pop();
                    sb.append(popped.data.toString());
                    if(!nodeStack.isEmpty() || popped.context[2] != null) sb.append(", ");
                    current = popped.context[2];
                } else {
                    nodeStack.add(current);
                    current = current.context[1];
                }
            }
        }
        sb.append(" ]");
        return sb.toString();
    }

    /**
     * This method performs a level order traversal of the tree. The string
     * representations of each data value
     * within this tree are assembled into a comma separated string within
     * brackets (similar to many implementations of java.util.Collection).
     * This method will be helpful as a helper for the debugging and testing
     * of your rotation implementation.
     * @return string containing the values of this tree in level order
     */
    public String toLevelOrderString() {
        StringBuffer sb = new StringBuffer();
        sb.append("[ ");
        if (this.root != null) {
            LinkedList<Node<T>> q = new LinkedList<>();
            q.add(this.root);
            while(!q.isEmpty()) {
                Node<T> next = q.removeFirst();
                if(next.context[1] != null) q.add(next.context[1]);
                if(next.context[2] != null) q.add(next.context[2]);
                sb.append(next.data.toString());
                if(!q.isEmpty()) sb.append(", ");
            }
        }
        sb.append(" ]");
        return sb.toString();
    }

    public String toString() {
        return "level order: " + this.toLevelOrderString() +
                "\nin order: " + this.toInOrderString();
    }


    // Implement at least 3 boolean test methods by using the method signatures below,
    // removing the comments around them and adding your testing code to them. You can
    // use your notes from lecture for ideas on concrete examples of rotation to test for.
    // Make sure to include rotations within and at the root of a tree in your test cases.
    // Give each of the methods a meaningful header comment that describes what is being
    // tested and make sure your test hafe inline comments to help developers read through them.
    // If you are adding additional tests, then name the method similar to the ones given below.
    // Eg: public static boolean test4() {}
    // Do not change the method name or return type of the existing tests.
    // You can run your tests by commenting in the calls to the test methods

    /**
     * Tester checks right rotation of a 3 level tree with parent and child are the top two
     * @return returns false if rotation failed and true otherwise
     */
    public static boolean test1() {
        RedBlackTree<Integer> test1 = new RedBlackTree<Integer>();
        test1.insert(4);
        test1.insert(2);
        test1.insert(1);
        System.out.println(test1);
        test1.rotate(test1.root.context[1], test1.root);
        System.out.println(test1);
        if(!test1.toLevelOrderString().equals("[ 2, 1, 4 ")) {
            return false;
        }
        return true;
    }

    /**
     * Tester checks left rotation on a multi level with parent and child as bottom two
     * @return returns false if rotation failed and true otherwise
     */
    public static boolean test2() {
        RedBlackTree<Integer> test2 = new RedBlackTree<Integer>();
        test2.insert(40);
        test2.insert(20);
        test2.insert(60);
        test2.insert(10);
        test2.insert(30);
        test2.insert(50);
        test2.insert(70);
        test2.rotate(test2.root.context[1].context[2], test2.root.context[1]);
        System.out.println(test2);
        if(!test2.toLevelOrderString().equals("[ 40, 30, 60, 20, 50, 70, 10 ]")){
            return false;
        }
        return true;
    }

    /**
     * Tester checks right rotation on a 4 level tree with parent and child as the top two
     * @return returns false if rotation failed and true otherwise
     */
    public static boolean test3() {
        RedBlackTree<Integer> test3 = new RedBlackTree<Integer>();
        test3.insert(50);
        test3.insert(40);
        test3.insert(20);
        test3.insert(10);
        test3.rotate(test3.root.context[1], test3.root);
        System.out.println(test3);
        if(!test3.toLevelOrderString().equals("[ 40, 20, 50, 10 ]")) {
            return false;
        }
        return true;
    }

    /**
     * Tester checks left rotation wtih a 3 level tree with parent and child as top two
     * @return returns false if rotation failed and true otherwise
     */
    public static boolean test4() {
        RedBlackTree<Integer> test4 = new RedBlackTree<Integer>();
        test4.insert(1);
        test4.insert(4);
        test4.insert(5);
        test4.rotate(test4.root.context[2], test4.root);
        System.out.println(test4);
        if(!test4.toLevelOrderString().equals("[ 4, 1, 5 ]")){
            return false;
        }
        return true;
    }

    /**
     * Tester checks right rotation on a multilevel, imbalanced tree
     * @return returns false if rotation failed and true otherwise
     */
    public static boolean test5() {
        RedBlackTree<Integer> test5 = new RedBlackTree<Integer>();
        test5.insert(50);
        test5.insert(40);
        test5.insert(60);
        test5.insert(30);
        test5.insert(45);
        test5.insert(31);
        test5.insert(10);
        test5.rotate(test5.root.context[1], test5.root);
        System.out.println(test5);
        if(!test5.toLevelOrderString().equals("[ 40, 30, 50, 10, 31, 45, 60 ]")){
            return false;
        }
        return true;
    }

    /**
     * Tester checks right rotation on a balanced multilevel tree
     * @return returns false if rotation failed and true otherwise
     */
    public static boolean test6() {
        RedBlackTree<Integer> test6 = new RedBlackTree<Integer>();
        test6.insert(40);
        test6.insert(20);
        test6.insert(60);
        test6.insert(10);
        test6.insert(30);
        test6.insert(50);
        test6.insert(70);
        System.out.println(test6);
        test6.rotate(test6.root.context[2].context[1], test6.root.context[2]);
        System.out.println(test6);
        if(!test6.toLevelOrderString().equals("[ 40, 20, 50, 10, 30, 60, 70 ]")){
            return false;
        }
        return true;
    }

    /**
     * Tester checks a double rotation on an imbalanced tree
     * @return returns false if rotation failed and true otherwise
     */
    public static boolean test7() {
        RedBlackTree<Integer> test7 = new RedBlackTree<Integer>();
        test7.insert(3);
        test7.insert(1);
        test7.insert(2);
        test7.rotate(test7.root.context[1].context[2], test7.root.context[1]);
        if(!test7.toLevelOrderString().equals("[ 3, 2, 1 ]")){
            return false;
        }
        test7.rotate(test7.root.context[1], test7.root);
        if(!test7.toLevelOrderString().equals("[ 2, 1, 3 ]")){
            return false;
        }
        return true;
    }

    /**
     * checks for illegal argument exception to make sure parent and child are always connected
     * @return true if illegal argument is caught and false otherwise
     */
    public static boolean test8(){
        RedBlackTree<Integer> test8 = new RedBlackTree<Integer>();
        test8.insert(40);
        test8.insert(20);
        test8.insert(60);
        test8.insert(10);
        test8.insert(30);
        test8.insert(50);
        test8.insert(70);
        try{
            test8.rotate(test8.root, test8.root.context[2].context[1]);
        }
        catch(Exception e){
            return true;
        }
        return false;
    }


    /**
     * Main method to run tests. Comment out the lines for each test
     * to run them.
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Test 1 passed: " + test1());
        System.out.println("Test 2 passed: " + test2());
        System.out.println("Test 3 passed: " + test3());
        System.out.println("Test 4 passed: " + test4());
        System.out.println("Test 5 passed: " + test5());
        System.out.println("Test 6 passed: " + test6());
        System.out.println("Test 7 passed: " + test7());
        System.out.println("Test 8 passed: " + test8());
    }

}
