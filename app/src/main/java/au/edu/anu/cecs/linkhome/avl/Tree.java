package au.edu.anu.cecs.linkhome.avl;

import androidx.annotation.NonNull;

/**
 * Code from lab04
 * The following interface defines required methods of any Tree.
 *
 * @param <T> the generic type this Tree uses. It extends comparable
 *            which allows us to order two of the same type.
 */
public abstract class Tree<T extends Comparable<T>> {
    /**
     * Here we store our class fields.
     */
    public final T value;       // element stored in this node of the tree.
    public Tree<T> leftNode;    // less than the node.
    public Tree<T> rightNode;   // greater than the node.

    /**
     * Constructor to allow for empty trees
     */
    public Tree() {
        value = null;
    }

    /**
     * Constructor for creating a new child node.
     * Note that the left and right nodes must be set by the subclass.
     *
     * @param value to set as this node's value.
     */
    public Tree(T value) {

        // Ensure input is not null.
        if (value == null)
            throw new IllegalArgumentException("Input cannot be null");

        this.value = value;
    }

    /**
     * Constructor for creating a new node.
     * Note that this is what allows our implementation to be immutable.
     *
     * @param value     to set as this node's value.
     * @param leftNode  left child of current node.
     * @param rightNode right child of current node.
     */
    public Tree(T value, Tree<T> leftNode, Tree<T> rightNode) {

        // Ensure inputs are not null.
        if (value == null || leftNode == null || rightNode == null)
            throw new IllegalArgumentException("Inputs cannot be null");

        this.value = value;
        this.leftNode = leftNode;
        this.rightNode = rightNode;
    }

    public abstract Tree<T> find(T element);     // Finds the element and returns the node.

    public abstract Tree<T> insert(T element);   // Inserts the element and returns a new instance of itself with the new node.

    /**
     * Height of current node.
     *
     * @return The maximum height of either children.
     */
    public int getHeight() {

        // Check whether leftNode or rightNode are EmptyTree
        int leftNodeHeight = leftNode instanceof EmptyTree ? 0 : 1 + leftNode.getHeight();
        int rightNodeHeight = rightNode instanceof EmptyTree ? 0 : 1 + rightNode.getHeight();
        return Math.max(leftNodeHeight, rightNodeHeight);
    }

    @NonNull
    @Override
    public String toString() {
        return "{" +
                "rent=" + value +
                ", leftNode=" + leftNode +
                ", rightNode=" + rightNode +
                '}';
    }
}
