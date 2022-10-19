package au.edu.anu.cecs.linkhome.avl;

import java.util.ArrayList;
import java.util.Objects;

import au.edu.anu.cecs.linkhome.tokenizer.expressions.Exp;
import au.edu.anu.cecs.linkhome.tokenizer.expressions.LessExp;
import au.edu.anu.cecs.linkhome.tokenizer.expressions.MoreExp;

/**
 * AVL tree implementation. Adapted from lab4.
 * @author Avani Dhaliwal, Devanshi Dhall, lab4
 */
public class AVLTree<T extends Comparable<T>> extends BinarySearchTree<T> {
    /*
        As a result of inheritance by using 'extends BinarySearchTree<T>,
        all class fields within BinarySearchTree are also present here.
        So while not explicitly written here, this class has:
            - value
            - leftNode
            - rightNode
     */

    public AVLTree(T value) {
        super(value);

        // Set left and right children to be of EmptyAVL as opposed to EmptyBST.

        this.leftNode = new EmptyAVL<>();
        this.rightNode = new EmptyAVL<>();
    }

    public AVLTree(T value, Tree<T> leftNode, Tree<T> rightNode) {
        super(value, leftNode, rightNode);
    }

    /**
     * @return balance factor of the current node.
     */
    public int getBalanceFactor() {
        return leftNode.getHeight() - rightNode.getHeight();
    }

    @Override
    public AVLTree<T> insert(T element) {

        // To ensure immutability
        AVLTree<T> newTreeCopy = new AVLTree<>(this.value, this.leftNode, this.rightNode);

        // Ensure input is not null
        if (element == null)
            throw new IllegalArgumentException("Input cannot be null");

        // Using the property of the BST
        if (element.compareTo(value) > 0) {
            newTreeCopy.rightNode = newTreeCopy.rightNode.insert(element);
        } else if (element.compareTo(value) < 0) {
            newTreeCopy.leftNode = newTreeCopy.leftNode.insert(element);
        } else {
            return newTreeCopy;
        }

        // For Advanced Rotations, check the balance factor
        if (newTreeCopy.getBalanceFactor() < -1) {
            assert newTreeCopy.rightNode.value != null;
            if (element.compareTo(newTreeCopy.rightNode.value) <= 0) {
                AVLTree<T> rightNode = (AVLTree<T>) newTreeCopy.rightNode;
                newTreeCopy.rightNode = rightNode.rightRotate();
            }
            return newTreeCopy.leftRotate();
        } else if (newTreeCopy.getBalanceFactor() > 1) {
            assert newTreeCopy.leftNode.value != null;
            if (element.compareTo(newTreeCopy.leftNode.value) >= 0) {
                AVLTree<T> leftNode = (AVLTree<T>) newTreeCopy.leftNode;
                newTreeCopy.leftNode = leftNode.leftRotate();
            }
            return newTreeCopy.rightRotate();
        }
        return newTreeCopy;
    }

    /**
     * Deletes the given element and returns a new instance of itself without the new node.
     * Adapted from code written by Peicheng Liu on stubents.
     * @param element the element to be removed from the tree
     * @return A new AVLTree without the element
     * @author Peicheng Liu
     */
    public AVLTree<T> delete(T element) {
        if (element == null)
            throw new IllegalArgumentException("Input cannot be null");

        if (find(element) == null)
            throw new IllegalArgumentException("No such element in the tree");

        AVLTree<T> avlTree = new AVLTree<>(value, this.leftNode, this.rightNode);

        //element is in current position
        if (element.equals(this.value)) {
            //Case 0: no child
            if (this.leftNode.value == null && this.rightNode.value == null) {
                return null;
            }
            //Case 1: one right child
            else if (this.leftNode.value == null) {
                return new AVLTree<>(this.rightNode.value, this.rightNode.leftNode, this.rightNode.rightNode);
            }
            //Case 1: one left child
            else if (this.rightNode.value == null) {
                return new AVLTree<>(this.leftNode.value, this.leftNode.leftNode, this.leftNode.rightNode);
            }
            //Case 2: both children
            else {
                AVLTree<T> right = new AVLTree<>(avlTree.rightNode.value, avlTree.rightNode.leftNode, avlTree.rightNode.rightNode);
                T successor = right.findLeftMost();
                right = right.delete(successor);
                if (right == null || right.value == null) {
                    return new AVLTree<>(successor, this.leftNode, new EmptyAVL<>());
                } else {
                    return new AVLTree<>(successor, this.leftNode, right);
                }
            }
        }
        //element is in the right subtree
        else if (element.compareTo(this.value) > 0) {
            AVLTree<T> right = new AVLTree<>(avlTree.rightNode.value, avlTree.rightNode.leftNode, avlTree.rightNode.rightNode);
            right = right.delete(element);

            if (right == null || right.value == null) {
                //Case 0: element has no child
                avlTree.rightNode = new EmptyAVL<>();
            } else {
                //Case 1/2: element has child
                avlTree.rightNode = right;
            }
        }
        //element is in the left subtree
        else {
            AVLTree<T> left = new AVLTree<>(avlTree.leftNode.value, avlTree.leftNode.leftNode, avlTree.leftNode.rightNode);
            left = left.delete(element);

            if (left == null || left.value == null) {
                //Case 0: element has no child
                avlTree.leftNode = new EmptyAVL<>();
            } else {
                //Case 1/2: element has child
                avlTree.leftNode = left;
            }
        }

        if (avlTree.getBalanceFactor() < -1) {
            assert avlTree.rightNode.value != null;
            if (element.compareTo(avlTree.rightNode.value) >= 0) {
                AVLTree<T> rightNode = (AVLTree<T>) avlTree.rightNode;
                avlTree.rightNode = rightNode.rightRotate();
            }
            return avlTree.leftRotate();
        } else if (avlTree.getBalanceFactor() > 1) {
            assert avlTree.leftNode.value != null;
            if (element.compareTo(avlTree.leftNode.value) <= 0) {
                AVLTree<T> leftNode = (AVLTree<T>) avlTree.leftNode;
                avlTree.leftNode = leftNode.leftRotate();
            }
            return avlTree.rightRotate();
        }

        return avlTree;
    }

    /**
     * Helper function for delete(), recursive, finds the leftmost (smallest) element in current tree
     * @return the leftmost element
     * @author Peicheng Liu
     */
    public T findLeftMost() {
        if (this.leftNode.value == null) {
            return this.value;
        } else {
            return new AVLTree<T>(this.leftNode.value, this.leftNode.leftNode, this.leftNode.rightNode).findLeftMost();
        }
    }

    /**
     * Conducts a left rotation on the current node.
     * @return the new 'current' or 'top' node after rotation.
     */
    public AVLTree<T> leftRotate() {
        Tree<T> newParent = this.rightNode;
        Tree<T> newRightOfCurrent = newParent.leftNode;
        newParent.leftNode = this;
        newParent.leftNode.rightNode = newRightOfCurrent;
        return (AVLTree<T>) newParent;
    }

    /**
     * Conducts a right rotation on the current node.
     * @return the new 'current' or 'top' node after rotation.
     */
    public AVLTree<T> rightRotate() {
        Tree<T> newParent = this.leftNode;
        Tree<T> newRightOfCurrent = newParent.rightNode;
        newParent.rightNode = this;
        newParent.rightNode.leftNode = newRightOfCurrent;
        return (AVLTree<T>) newParent;
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
    }

    public ArrayList<T> treeToListInOrder(AVLTree<T> tree) {
        ArrayList<T> list = new ArrayList<>();

        // Recurse through left subtree.
        if (tree.leftNode != null) {
            if (tree.leftNode.value != null) {
                list.addAll(treeToListInOrder((AVLTree<T>) tree.leftNode));
            }
        }

        // Add current node's value
        if (tree.value != null) {
            list.add(tree.value);
        }

        // Recurse through left subtree.
        if (tree.rightNode != null) {
            if (tree.rightNode.value != null) {
                list.addAll(treeToListInOrder((AVLTree<T>) tree.rightNode));
            }
        }
        return list;
    }

    /**
     * Filter Data method is used to filter records on the UI by meeting the search condition in AVL tree
     * @param  tree, exp, rent
     * @return  AVLTree
     */
    public AVLTree<T> filterData(AVLTree<T> tree, Exp exp, T rent){
        if(exp instanceof LessExp) {
            if (Objects.requireNonNull(value).compareTo(rent) < 0) {
                if (tree.rightNode instanceof EmptyAVL) {
                    return tree;
                } else {
                    return new AVLTree<>(value, this.leftNode, filterData((AVLTree<T>) tree.rightNode, exp, rent));
                }
            } else {
                return null;
            }
        }

        if(exp instanceof MoreExp) {
            if (Objects.requireNonNull(value).compareTo(rent) > 0) {
                if (tree.leftNode instanceof EmptyAVL) {
                    return tree;
                } else {
                    return new AVLTree<>(value, this.leftNode, filterData((AVLTree<T>) tree.leftNode, exp, rent));
                }

            } else {
                return null;
            }
        }

        return null;
    }

}
