package au.edu.anu.cecs.linkhome.avl;

/**
 * Code from lab04
 * An AVL tree is actually an extension of a Binary Search Tree
 * with self balancing properties. Hence, our AVL trees will 'extend'
 * this Binary Search tree data structure.
 */
public class BinarySearchTree<T extends Comparable<T>> extends Tree<T> {

    public BinarySearchTree(T value) {
        super(value);
        this.leftNode = new EmptyBST<>();
        this.rightNode = new EmptyBST<>();
    }

    public BinarySearchTree(T value, Tree<T> leftNode, Tree<T> rightNode) {
        super(value, leftNode, rightNode);
    }

    @Override
    public Tree<T> find(T element) {
        /*
            Left is less, right is greater in this implementation.
            compareTo returns 0 if both elements are equal.
            compareTo returns <= 0 if the element's value is less than or equal to the node's value.
            compareTo returns > 0 if the element's value is greater than the node's value.
         */

        // Ensure input is not null.
        if (element == null)
            throw new IllegalArgumentException("Input cannot be null");

        if (element.equals(value)) {
            return this;
        } else if (element.compareTo(value) <= 0) {
            //If element not in tree
            if (leftNode == null)
                return null;
            return leftNode.find(element);
        } else {
            //If element not in tree
            if (rightNode == null)
                return null;
            return rightNode.find(element);
        }
    }

    @Override
    public BinarySearchTree<T> insert(T element) {

        // Ensure input is not null.
        if (element == null)
            throw new IllegalArgumentException("Input cannot be null");

        // If the two rents are equal, in this implementation we want to insert to the left.
        if (element.compareTo(value) > 0) {
            return new BinarySearchTree<>(value, leftNode, rightNode.insert(element));
        } else {
            return new BinarySearchTree<>(value, leftNode.insert(element), rightNode);
        }
    }

    /**
     * Note that this is not within a file of its own... WHY?
     * The answer is: this is just a design decision. 'insert' here will return something specific
     * to the parent class inheriting Tree. In this case a BinarySearchTree.
     */
    public static class EmptyBST<Data extends Comparable<Data>> extends EmptyTree<Data> {
        @Override
        public Tree<Data> insert(Data element) {
            // The creation of a new Tree, hence, return tree.
            return new BinarySearchTree<>(element);
        }
    }
}
