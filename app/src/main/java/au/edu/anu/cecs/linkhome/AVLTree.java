package au.edu.anu.cecs.linkhome;

/**
 * Welcome! Make sure to check out 'readme.md' for a rundown of requirements/description of this implementation
 * that may differ from normal implementations. Before starting, it may also be worth checking out Tree.java
 * and BinarySearchTree.java as all method description is contained in the superclass unless edited. For
 * example: the description for 'insert' cannot be found here. It is in the superclass!
 * <p>
 * Please note that you may edit this class as much as you like (i.e.create helper methods if you want!).
 * So long as you genuinely pass the tests (i.e. do not change existing methods signatures). Ask questions if you are
 * lost or unsure.
 * You SHALL NOT edit any other classes.
 * <p>
 * Lastly, if you are looking to better visualise the results of your insertion, you are free print the contents
 * of the method '.display()' (found in Tree.java which class, AVLTree, extends through BinarySearchTree). This
 * method will provide you with a graphical representation of the tree.
 */
public class AVLTree<Data extends Comparable<Data>> extends BinarySearchTree<Data> {
    /*
        As a result of inheritance by using 'extends BinarySearchTree<T>,
        all class fields within BinarySearchTree are also present here.
        So while not explicitly written here, this class has:
            - value
            - leftNode
            - rightNode
     */

    public AVLTree(Data value) {
        super(value);
        // Set left and right children to be of EmptyAVL as opposed to EmptyBST.
        this.leftNode = new EmptyAVL<>();
        this.rightNode = new EmptyAVL<>();
    }

    public AVLTree(Data value, Tree<Data> leftNode, Tree<Data> rightNode) {
        super(value, leftNode, rightNode);
    }

    /**
     * @return balance factor of the current node.
     */
    public int getBalanceFactor() {
        /*
             Note:
             Calculating the balance factor and height each time they are needed is less efficient than
             simply storing the height and balance factor as fields within each tree node (as some
             implementations of the AVLTree do). However, although it is inefficient, it is easier to implement.
         */
        return leftNode.getHeight() - rightNode.getHeight();
    }

    @Override
    public AVLTree<Data> insert(Data element) {
        /*
            TODO: Write and or complete your insertion code here
            Note that what each method does is described in its superclass unless edited.
            E.g. what 'insert' does is described in Tree.java.
         */

        // To ensure immutability
        AVLTree<Data> newTreeCopy= new AVLTree<>(this.value,this.leftNode,this.rightNode);

        // Ensure input is not null
        if (element == null)
            throw new IllegalArgumentException("Input cannot be null");

        // Using the property of the BST
        if (element.compareTo(value) > 0) {
            newTreeCopy.rightNode=newTreeCopy.rightNode.insert(element);
            // COMPLETE
        }
        else if (element.compareTo(value) < 0) {
            newTreeCopy.leftNode=newTreeCopy.leftNode.insert(element);

        }

        else {

            return newTreeCopy;

            // COMPLETE
        }

        // For Advanced Rotations, check the balance factor
        if(newTreeCopy.getBalanceFactor()<-1){
            assert newTreeCopy.rightNode.value != null;
            if(element.compareTo(newTreeCopy.rightNode.value)<=0) {
                AVLTree<Data> rightNode = (AVLTree<Data>) newTreeCopy.rightNode;
                newTreeCopy.rightNode = rightNode.rightRotate();
            }
            return newTreeCopy.leftRotate();
        }
        else if(newTreeCopy.getBalanceFactor()>1){
            assert newTreeCopy.leftNode.value != null;
            if(element.compareTo(newTreeCopy.leftNode.value)>=0) {
                AVLTree<Data> leftNode = (AVLTree<Data>) newTreeCopy.leftNode;
                newTreeCopy.leftNode= leftNode.leftRotate();
                return newTreeCopy.rightRotate();
            }
            else{
                return newTreeCopy.rightRotate();
            }

        }

        return newTreeCopy; // Change to return something different

    }

    /**
     * Conducts a left rotation on the current node.
     *
     * @return the new 'current' or 'top' node after rotation.
     */
    public AVLTree<Data> leftRotate() {
        /*
            TODO: Write and or complete this method so that you can conduct a left rotation on the current node.
            This can be quite difficult to get your head around. Try looking for visualisations
            of left rotate if you are confused.

            Note: if this is implemented correctly than the return MUST be an AVL tree. However, the
            rotation may move around EmptyAVL trees. So when moving trees, the type of the trees can
            be 'Tree<T>'. However, the return type should be of AVLTree<T>. To cast the return type into
            AVLTree<T> simply use: (AVLTree<T>).

            If you get an casting exception such as:
            'java.lang.ClassCastException: class AVLTree$EmptyAVL cannot be cast to class AVLTree
            (AVLTree$EmptyAVL and AVLTree are in unnamed module of loader 'app')'
            than something about your code is incorrect!
         */

        Tree<Data> newParent = this.rightNode; // 8, 10
        Tree<Data> newRightOfCurrent = newParent.leftNode; //5
        newParent.leftNode=this;
        newParent.leftNode.rightNode=newRightOfCurrent;

        // COMPLETE

        return (AVLTree<Data>) newParent; // Change to return something different
    }

    /**
     * Conducts a right rotation on the current node.
     *
     * @return the new 'current' or 'top' node after rotation.
     */
    public AVLTree<Data> rightRotate() {
        /*
            TODO: Write this method so that you can conduct a right rotation on the current node.
            This can be quite difficult to get your head around. Try looking for visualisations
            of right rotate if you are confused.

            Note: if this is implemented correctly than the return MUST be an AVL tree. However, the
            rotation may move around EmptyAVL trees. So when moving trees, the type of the trees can
            be 'Tree<T>'. However, the return type should be of AVLTree<T>. To cast the return type into
            AVLTree<T> simply use: (AVLTree<T>).

            If you get an casting exception such as:
            'java.lang.ClassCastException: class AVLTree$EmptyAVL cannot be cast to class AVLTree
            (AVLTree$EmptyAVL and AVLTree are in unnamed module of loader 'app')'
            than something about your code is incorrect!
         */
        Tree<Data> newParent = this.leftNode;
        Tree<Data> newRightOfCurrent = newParent.rightNode;

        newParent.rightNode=this;
        newParent.rightNode.leftNode=newRightOfCurrent;
        // COMPLETE

        return (AVLTree<Data>) newParent;


        //return null; // Change to return something different
    }

    /**
     * Note that this is not within a file of its own... WHY?
     * The answer is: this is just a design decision. 'insert' here will return something specific
     * to the parent class inheriting Tree from BinarySearchTree. In this case an AVL tree.
     */
    public static class EmptyAVL<Data extends Comparable<Data>> extends EmptyTree<Data> {
        @Override
        public Tree<Data> insert(Data element) {
            // The creation of a new Tree, hence, return tree.
            return new AVLTree<Data>(element);
        }
    }
}
