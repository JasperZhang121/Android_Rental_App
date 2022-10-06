package au.edu.anu.cecs.linkhome;

import java.util.Random;

/**
 * A class which simply demonstrates the efficiency (in human time) of different data structures.
 * <p>
 * Here we will compare calculating the range (max-min) of an array vs your AVL tree!
 */
public class CompareDataStructures {
    public static void main(String[] args) {
        /*
            To compare our implementation speeds we will generate random numbers.
            Then we will count how long it takes for us to get the range from
            our array vs our AVL tree.

            Note that you can only run this test after you have properly implemented
            the AVL tree.
         */
        Data[] values = new Data[10000]; // Feel free to edit this to be even larger!
        Random rand = new Random();
        for (int i = 0; i < values.length; i++) {
            values[i] = new Data("adshiuwhf", "wuigfwui", "wgfuw", rand.nextInt());
        }

        // Create BST to test
        BinarySearchTree<Data> bst = new BinarySearchTree<>(values[0]);
        for (int i = 1; i < values.length; i++) {
            bst = bst.insert(values[i]);
        }

        // Create AVL tree to test
        AVLTree<Data> avl = new AVLTree<>(values[0]);
        for (int i = 1; i < values.length; i++) {
            avl = avl.insert(values[i]);
        }

        // Now time each
        long startTime = System.nanoTime();
        int range = getRangeFromArray(values);
        long endTime = System.nanoTime();
        System.out.println("The array took: " + (endTime - startTime) + " nanoseconds and provided a range of " + range);

        startTime = System.nanoTime();
        range = getRangeFromTree(bst);
        endTime = System.nanoTime();
        System.out.println("The BST took: " + (endTime - startTime) + " nanoseconds and provided a range of " + range);

        startTime = System.nanoTime();
        range = getRangeFromTree(avl);
        endTime = System.nanoTime();
        System.out.println("The AVL tree took: " + (endTime - startTime) + " nanoseconds and provided a range of " + range);
    }

    /**
     * @param array of integers.
     * @return range of array.
     */
    private static int getRangeFromArray(Data[] array) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (Data val : array) {
            int rent = val.getRent();
            if (rent < min)
                min = rent;
            if (rent > max)
                max = rent;
        }

        return min < 0 ? max + min : max - min;
    }

    /**
     * @param tree<Data> AVL tree which holds type integer.
     * @return range of avl tree.
     */
    private static int getRangeFromTree(Tree<Data> tree) {
        int max = tree.max().getRent();
        int min = tree.min().getRent();
        return min < 0 ? max + min : max - min;
    }
}
