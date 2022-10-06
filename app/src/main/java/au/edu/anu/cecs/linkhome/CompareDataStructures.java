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
        int[] values = new int[10000]; // Feel free to edit this to be even larger!
        Random rand = new Random();
        for (int i = 0; i < values.length; i++) {
            values[i] = rand.nextInt();
        }

        /*
             Just a fun bit of code :)
             Want to see the distribution of positive and negative numbers? Uncomment the code below!
         */
//        int pos = 0;
//        int neg = 0;
//        int zero = 0;
//        for (int v : values) {
//            if (v > 0)
//                pos += 1;
//            else if (v < 0)
//                neg += 1;
//            else
//                zero += 1;
//        }
//        System.out.println("number of negative numbers: " + neg + " which makes up: " + (float) (neg)/(neg+pos+zero)*100 + "% of numbers generated");
//        System.out.println("number of positive numbers: " + pos + " which makes up: " + (float) (pos)/(neg+pos+zero)*100 + "% of numbers generated");
//        System.out.println("number of times zero occurs (neither positive nor negative): " + zero + " which makes up: " + (float) (zero)/(neg+pos+zero)*100 + "% of numbers generated");


        // Create BST to test
        BinarySearchTree<Integer> bst = new BinarySearchTree<>(values[0]);
        for (int i = 1; i < values.length; i++) {
            bst = bst.insert(values[i]);
        }

        // Create AVL tree to test
        AVLTree<Integer> avl = new AVLTree<>(values[0]);
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
    private static int getRangeFromArray(int[] array) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int val : array) {
            if (val < min)
                min = val;
            if (val > max)
                max = val;
        }

        return min < 0 ? max + min : max - min;
    }

    /**
     * @param AVLTree<Integer> AVL tree which holds type integer.
     * @return range of avl tree.
     */
    private static int getRangeFromTree(Tree<Integer> tree) {
        int max = tree.max();
        int min = tree.min();
        return min < 0 ? max + min : max - min;
    }
}
