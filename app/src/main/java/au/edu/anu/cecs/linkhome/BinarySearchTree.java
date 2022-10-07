package au.edu.anu.cecs.linkhome;

/**
 * An AVL tree is actually an extension of a Binary Search Tree
 * with self balancing properties. Hence, our AVL trees will 'extend'
 * this Binary Search tree data structure.
 */
public class BinarySearchTree<Data extends Comparable<Data>> extends Tree<Data> {

    public BinarySearchTree(Data value) {
        super(value);
        this.leftNode = new EmptyBST<>();
        this.rightNode = new EmptyBST<>();
    }

    public BinarySearchTree(Data value, Tree<Data> leftNode, Tree<Data> rightNode) {
        super(value, leftNode, rightNode);
    }

    @Override
    public Data min() {
        return (leftNode instanceof EmptyTree) ? value : leftNode.min();
    }

    @Override
    public Data max() {
        return (rightNode instanceof EmptyTree) ? value : rightNode.max();
    }

    @Override
    public Tree<Data> find(Data element) {
        /*
            Left is less, right is greater in this implementation.
            compareTo returns 0 if both elements are equal.
            compareTo returns < 0 if the element is less than the node.
            compareTo returns > 0 if the element is greater than the node.
         */

        // Ensure input is not null.
        if (element == null)
            throw new IllegalArgumentException("Input cannot be null");

        if (element.compareTo(value) == 0) {
            return this;
        } else if (element.compareTo(value) < 0) {
            return leftNode.find(element);
        } else {
            return rightNode.find(element);
        }
    }

    @Override
    public BinarySearchTree<Data> insert(Data element) {
        // Ensure input is not null.
        if (element == null)
            throw new IllegalArgumentException("Input cannot be null");

        // If the two values are equal, in this implementation we want to insert to the left.
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
            return new BinarySearchTree<Data>(element);
        }
    }
}
