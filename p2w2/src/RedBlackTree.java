import java.util.LinkedList;
import java.util.Stack;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// --== CS400 Spring 2023 File Header Information ==--
// Name: Bruno Inzunza
// Email: binzunza@wisc.edu
// Team: DJ
// TA: Yuye Jiang
// Lecturer: Florian
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

        public int blackHeight;
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
        public Node(T data) {
            this.data = data;
            blackHeight = 0;
        }

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
            root = newNode; size++;
            enforceRBTreePropertiesAfterInsert(newNode);
            return true;
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
                        enforceRBTreePropertiesAfterInsert(newNode);
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
                        enforceRBTreePropertiesAfterInsert(newNode);
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
     * this method makes sure that the red black tree follows all of its rules. If any of the rules are broken during the insertion, the method fixes the tree so that it becomes valid.
     * @param newNode input node
     */
    protected void enforceRBTreePropertiesAfterInsert(Node<T> newNode){
        if(newNode == null || newNode.context[0] == null) {
            root.blackHeight = 1;
            return;
        }
        //parent and child are same location and aunt/uncle are black
        if (!newNode.isRightChild() && !newNode.context[0].isRightChild() && newNode.context[0].blackHeight == 0 && (newNode.context[0].context[0].context[2] == null || newNode.context[0].context[0].context[2].blackHeight == 1)) {
            rotate(newNode.context[0], newNode.context[0].context[0]);
            newNode.context[0].blackHeight = 1;
            newNode.context[0].context[2].blackHeight = 0;
        }
        if (newNode.isRightChild() && newNode.context[0].isRightChild() && newNode.context[0].blackHeight == 0 && (newNode.context[0].context[0].context[1] == null || newNode.context[0].context[0].context[1].blackHeight == 1)) {
            rotate(newNode.context[0], newNode.context[0].context[0]);
            newNode.context[0].blackHeight = 1;
            newNode.context[0].context[2].blackHeight = 0;
        }


            //parent and child are same location and aunt/uncle are red
            if (!newNode.isRightChild() && !newNode.context[0].isRightChild() && newNode.context[0].blackHeight == 0 && newNode.context[0].context[0].context[2].blackHeight == 0) {
                newNode.context[0].blackHeight = 1;
                newNode.context[0].context[0].context[2].blackHeight = 1;
                newNode.context[0].context[0].blackHeight = 0;
            }
            if (newNode.isRightChild() && newNode.context[0].isRightChild() && newNode.context[0].blackHeight == 0 && newNode.context[0].context[0].context[1].blackHeight == 0) {
                newNode.context[0].blackHeight = 1;
                newNode.context[0].context[0].context[1].blackHeight = 1;
                newNode.context[0].context[0].blackHeight = 0;
            }





            //parent and child are different child locations and uncle/aunt is black
            if (newNode.isRightChild() && !newNode.context[0].isRightChild() && newNode.context[0].blackHeight == 0 && (newNode.context[0].context[0].context[1] == null || newNode.context[0].context[0].context[2].blackHeight == 1)) {
                rotate(newNode, newNode.context[0]);
                rotate(newNode, newNode.context[0]);
                if(newNode.context[0] == null){
                    newNode.blackHeight = 1;
                    newNode.context[2].blackHeight = 0;
                }
                else{
                    newNode.context[0].blackHeight = 1;
                    newNode.context[0].context[2].blackHeight = 0;
                }

            }
            if (!newNode.isRightChild() && newNode.context[0].isRightChild() && newNode.context[0].blackHeight == 0 && (newNode.context[0].context[0].context[1] == null || newNode.context[0].context[0].context[1].blackHeight == 1)) {
                rotate(newNode, newNode.context[0]);
                rotate(newNode, newNode.context[0]);
                if(newNode.context[0] == null){
                    newNode.blackHeight = 1;
                    newNode.context[1].blackHeight = 0;
                }
                else {
                    newNode.context[0].blackHeight = 1;
                    newNode.context[0].context[1].blackHeight = 0;
                }
            }

            //parent and child are different child locations and aunt/uncle is red
        if(newNode.context[0] == null){
            root.blackHeight = 1;
            return;
        }
            if (newNode.isRightChild() && !newNode.context[0].isRightChild() && newNode.context[0].blackHeight == 0 && newNode.context[0].context[0].context[2].blackHeight == 0) {
                newNode.context[0].blackHeight = 1;
                newNode.context[0].context[0].context[2].blackHeight = 1;
                newNode.context[0].context[0].blackHeight = 0;
            }
            if (!newNode.isRightChild() && newNode.context[0].isRightChild() && newNode.context[0].blackHeight == 0 && newNode.context[0].context[0].context[1].blackHeight == 0) {
                newNode.context[0].blackHeight = 1;
                newNode.context[0].context[0].context[1].blackHeight = 1;
                newNode.context[0].context[0].blackHeight = 0;
            }


        root.blackHeight = 1;
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
//    private void rotate(Node<T> child, Node<T> parent) throws IllegalArgumentException {
//        //if child node and parent node are not connected
//        if (!child.context[0].equals(parent)) {
//            throw new IllegalArgumentException("Not a valid parent and child input");
//        }
//        if (!child.isRightChild()) {
//            if (parent.context[0] == null) {
//                this.root = child;
//            }
//            //right rotation
//            if (parent.context[0] != null) {
//                if (parent.isRightChild()) {
//                    child.context[0] = parent.context[1];
//                    parent.context[0].context[2] = child;
//                }
//                else {
//                    child.context[0] = parent.context[0];
//                    parent.context[0].context[1] = child;
//                }
//            }
//            parent.context[1] = child.context[2];
//            child.context[2] = parent;
//            parent.context[0] = child;
//        }
//        //left rotation
//        else {
//            if (parent.context[0] == null) {
//                this.root = child;
//            }
//
//            if (parent.context[0] != null) {
//                if (parent.isRightChild()) {
//                    child.context[0] = parent.context[0];
//                    parent.context[0].context[2] = child;
//                } else {
//                    child.context[0] = parent.context[0];
//                    parent.context[0].context[1] = child;
//                }
//            }
//            parent.context[2] = child.context[1];
//            child.context[1] = parent;
//        }
//    }

    private void rotate(Node<T> child, Node<T> parent) throws IllegalArgumentException {
        //Not related
        if(!child.context[0].equals(parent)){
            throw new IllegalArgumentException();
        }

        //Right Rotation
        if(!child.isRightChild()) {
            if(parent.context[0] == null) {
                child.context[0] = parent.context[0];
                this.root = child;
            }
            if(parent.context[0] != null) {
                if(parent.isRightChild()) {
                    child.context[0] = parent.context[0];
                    parent.context[0].context[2] = child;

                } else {
                    child.context[0] = parent.context[0];
                    parent.context[0].context[1] = child;
                }
            }
            parent.context[1] = child.context[2];
            child.context[2] = parent;
            child.context[2].context[0] = parent;
            parent.context[0] = child;

        }

        //Left Rotation
        else if(child.isRightChild()) {
            if(parent.context[0] == null) {
                child.context[0] = parent.context[0];
                this.root = child;
            }
            if(parent.context[0] != null) {
                if(parent.isRightChild()) {
                    child.context[0] = parent.context[0];
                    parent.context[0].context[2] = child;
                } else {
                    child.context[0] = parent.context[0];
                    parent.context[0].context[1] = child;
                }
            }
            parent.context[2] = child.context[1];
            child.context[0] = parent.context[0];
            parent.context[0] = child;
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




    /**
     * Main method to run tests. Comment out the lines for each test
     * to run them.
     * @param args
     */
    public static void main(String[] args) {
        //System.out.println("Test 1 passed: " + test1());

    }
    /**
     * test1 tests the inputs 5, 4, 6, 7. This includes a recolor
     */
    @Test
    public void test1(){
        RedBlackTree<Integer> test = new RedBlackTree<Integer>();
        test.insert(5);
        test.insert(4);
        test.insert(6);
        test.insert(7);

        assertEquals(1, test.findNodeWithData(5).blackHeight);
        assertEquals(1, test.findNodeWithData(4).blackHeight);
        assertEquals(1, test.findNodeWithData(6).blackHeight);
        assertEquals(0, test.findNodeWithData(7).blackHeight);


    }

    /**
     * test2 tests the inputs 5, 4, 3, 2, 1.  This includes a rotate and a recolor
     */
    @Test
    public void test2(){
        RedBlackTree<Integer> test = new RedBlackTree<Integer>();
        test.insert(5);
        test.insert(4);
        test.insert(3);
        test.insert(2);
        test.insert(1);

        assertEquals(1, test.findNodeWithData(4).blackHeight);
        assertEquals(1, test.findNodeWithData(5).blackHeight);
        assertEquals(1, test.findNodeWithData(2).blackHeight);
        assertEquals(0, test.findNodeWithData(1).blackHeight);
        assertEquals(0, test.findNodeWithData(3).blackHeight);
    }

    /**
     * test3 tests the RBT of inputs 1, 2, 4, 6, 5. This should include multiple rotations
     */
    @Test
    public void test3(){
        RedBlackTree<Integer> test = new RedBlackTree<Integer>();
        test.insert(1);
        test.insert(2);
        test.insert(4);
        test.insert(6);
        test.insert(5);

        assertEquals(1, test.findNodeWithData(2).blackHeight);
        assertEquals(1, test.findNodeWithData(1).blackHeight);
        assertEquals(0, test.findNodeWithData(6).blackHeight);
        assertEquals(0, test.findNodeWithData(4).blackHeight);
        assertEquals(1, test.findNodeWithData(5).blackHeight);
    }
}


