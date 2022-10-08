package au.edu.anu.cecs.linkhome.AVL;

/**
 * AVL tree implementation. Adapted from lab4.
 *
 * @author Avani Dhaliwal, Devanshi Dhall, lab4
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
        // To ensure immutability
        AVLTree<Data> newTreeCopy= new AVLTree<>(this.value,this.leftNode,this.rightNode);

        // Ensure input is not null
        if (element == null)
            throw new IllegalArgumentException("Input cannot be null");

        // Using the property of the BST
        if (element.compareTo(value) > 0) {
            newTreeCopy.rightNode=newTreeCopy.rightNode.insert(element);
        }
        else if (element.compareTo(value) < 0) {
            newTreeCopy.leftNode=newTreeCopy.leftNode.insert(element);
        }
        else {
            return newTreeCopy;
        }

        // For Advanced Rotations, check the balance factor
        return balanceTree(newTreeCopy, element);
    }

    /**
     * Balances the given tree by doing left/right rotations on the tree
     *
     * @param avlTree the tree to be balanced
     * @param element the inserted/deleted element
     * @return the balanced avlTree
     */
    public AVLTree<Data> balanceTree(AVLTree<Data> avlTree, Data element){
        if(avlTree.getBalanceFactor()<-1){
            assert avlTree.rightNode.value != null;
            if(element.compareTo(avlTree.rightNode.value)<=0) {
                AVLTree<Data> rightNode = (AVLTree<Data>) avlTree.rightNode;
                avlTree.rightNode = rightNode.rightRotate();
            }
            return avlTree.leftRotate();
        }
        else if(avlTree.getBalanceFactor()>1){
            assert avlTree.leftNode.value != null;
            if(element.compareTo(avlTree.leftNode.value)>=0) {
                AVLTree<Data> leftNode = (AVLTree<Data>) avlTree.leftNode;
                avlTree.leftNode= leftNode.leftRotate();
            }
            return avlTree.rightRotate();
        }

        return avlTree;
    }

    /**
     * Deletion of a certain element from the AVLTree.
     * Adapted from code written by Peicheng Liu on stubents.
     *
     * @author Peicheng Liu
     * @param element
     * @return A new AVLTree without the element
     */
    @Override
    public AVLTree<Data> delete(Data element){
        if(this.find(element) == null){
            //No such element exists
            return this;
        }
        if(element == null)
            throw new IllegalArgumentException("Input cannot be null");

        AVLTree<Data> avlTree = new AVLTree<>(value, this.leftNode, this.rightNode);

        //element is in the right subtree
        if (element.compareTo(this.value) > 0){
            AVLTree<Data> right = new AVLTree<>(avlTree.rightNode.value, avlTree.rightNode.leftNode, avlTree.rightNode.rightNode);
            right = right.delete(element);

            if(right.value == null) //Case 0: element has no child
                avlTree.rightNode = new EmptyAVL<>();
            else //Case 1/2: element has child
                avlTree.rightNode = right;

        }
        //element is in the left subtree
        else if(element.compareTo(this.value) < 0){
            AVLTree<Data> left = new AVLTree<>(avlTree.leftNode.value, avlTree.leftNode.leftNode, avlTree.leftNode.rightNode);
            left = left.delete(element);

            if(left.value == null) //Case 0: element has no child
                avlTree.leftNode = new EmptyAVL<>();
            else //Case 1/2: element has child
                avlTree.leftNode = left;
        }

        //element is in current position
        else {
            if(this.leftNode.value == null && this.rightNode.value == null){ //Case 0: no child
                return null;
            } else if (this.leftNode.value == null){ //Case 1: one right child
                return new AVLTree<Data>(this.rightNode.value, this.rightNode.leftNode, this.rightNode.rightNode);
            } else if (this.rightNode.value == null){ //Case 1: one left child
                return new AVLTree<Data>(this.leftNode.value, this.leftNode.leftNode, this.leftNode.rightNode);
            } else { //Case 2: both children
                AVLTree<Data> right = new AVLTree<>(avlTree.rightNode.value, avlTree.rightNode.leftNode, avlTree.rightNode.rightNode);
                AVLTree<Data> successor = right.findLeftMost();
                right = right.delete(successor.value);
                if(right == null || right.value == null){
                    return new AVLTree<Data>(successor.value, this.leftNode, new EmptyAVL<>());
                } else {
                    return new AVLTree<Data>(successor.value, this.leftNode, right);
                }
            }
        }

        return balanceTree(avlTree, element);
    }

    /**
     * Helper function for delete(), recursive, fine the leftmost (smallest) element in current tree
     * @author Peicheng Liu
     * @return the leftmost element
     */
    public AVLTree<Data> findLeftMost(){
        if(this.leftNode.value == null){
            return new AVLTree<>(this.value);
        } else {
            return new AVLTree<Data>(this.leftNode.value, this.leftNode.leftNode, this.leftNode.rightNode).findLeftMost();
        }
    }

    /**
     * Conducts a left rotation on the current node.
     *
     * @return the new 'current' or 'top' node after rotation.
     */
    public AVLTree<Data> leftRotate() {
        Tree<Data> newParent = this.rightNode;
        Tree<Data> newRightOfCurrent = newParent.leftNode;
        newParent.leftNode=this;
        newParent.leftNode.rightNode=newRightOfCurrent;
        return (AVLTree<Data>) newParent;
    }

    /**
     * Conducts a right rotation on the current node.
     *
     * @return the new 'current' or 'top' node after rotation.
     */
    public AVLTree<Data> rightRotate() {
        Tree<Data> newParent = this.leftNode;
        Tree<Data> newRightOfCurrent = newParent.rightNode;
        newParent.rightNode=this;
        newParent.rightNode.leftNode=newRightOfCurrent;
        return (AVLTree<Data>) newParent;
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
            return new AVLTree<>(element);
        }

        @Override
        public Tree<Data> delete(Data element) {
            // The creation of a new Tree, hence, return tree.
            return new EmptyAVL<>();
        }
    }
}
