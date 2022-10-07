package au.edu.anu.cecs.linkhome;

import org.junit.Test;
import static org.junit.Assert.*;

public class AVLTreeTest {
    @Test(timeout = 1000)
    public void immutableTest() {
        // Simply check if the implementation is immutable.
        Data data1 = new Data("University Avenue", "Canberra", "2612", 350);
        AVLTree<Data> avl = new AVLTree<>(data1);

        Data data2 = new Data("something", "Canberra", "2612", 400);
        avl.insert(data2);

        String expected = "{rent350, leftNode={}, rightNode={}}";
        assertNull("\nAVL tree implementation is not immutable" +
                        "\nYour AVL tree should look like: " + expected + "\nBut it actually looks like: " + avl,
                avl.find(data2));
    }

    @Test (timeout = 1000)
    public void insertInOrderTest() {
        // Simply check if the insertion correctly places values (no rotation check).
        Data data1 = new Data("University Avenue", "Canberra", "2612", 350);
        AVLTree<Data> avl = new AVLTree<>(data1);
        Data data2 = new Data("something", "Canberra", "2612", 400);
        avl = avl.insert(data2);

        String expected = "{rent=350, leftNode={}, rightNode={rent=400, leftNode={}, rightNode={}}}";
        assertNotNull(
                "\nInsertion does not properly position values" +
                        "\nYour AVL tree should look like: " + expected + "\nBut it actually looks like: " + avl,
                avl.rightNode.value);
        assertEquals(
                "\nInsertion does not properly position values" +
                        "\nYour AVL tree should look like: " + expected + "\nBut it actually looks like: " + avl
                ,
                data2, avl.rightNode.value);

        Data data3 = new Data("something", "Canberra", "2612", 300);
        avl = avl.insert(data3);
        expected = "{rent=350, leftNode={rent=300, leftNode={}, rightNode={}}, rightNode={rent=400, leftNode={}, rightNode={}}}";
        assertNotNull(
                "\nInsertion does not properly position values" +
                        "\nYour AVL tree should look like: " + expected + "\nBut it actually looks like: " + avl,
                avl.leftNode.value);
        assertEquals(
                "\nInsertion does not properly position values" +
                        "\nYour AVL tree should look like: " + expected + "\nBut it actually looks like: " + avl,
                data3, avl.leftNode.value);
    }

    @Test(timeout = 1000)
    public void insertDuplicateTest() {
        // As per the implementation requirements, duplicates should be ignored.
        Data data1 = new Data("University Avenue", "Canberra", "2612", 350);
        AVLTree<Data> avl = new AVLTree<>(data1).insert(data1);

        String expected = "{rent=350, leftNode={}, rightNode={}}";
        assertEquals(
                "\nInsertion does not properly position values" +
                        "\nYour AVL tree should look like: " + expected + "\nBut it actually looks like: " + avl,
                0, avl.getHeight());

        // Double checking encase anyone changes height output.
        assertNull("\nInsertion does not properly handle duplicates" +
                "\nYour AVL tree should look like: " + expected + "\nBut it actually looks like: " + avl, avl.leftNode.value);

        assertNull("\nInsertion does not properly handle duplicates" +
                "\nYour AVL tree should look like: " + expected + "\nBut it actually looks like: " + avl, avl.rightNode.value);
    }

    @Test(timeout = 1000)
    public void leftRotateTest() {
        Data data1 = new Data("University Avenue", "Canberra", "2612", 350);
        Data data2 = new Data("something", "Canberra", "2612", 400);
        Data data3 = new Data("something", "Canberra", "2612", 300);


        AVLTree<Data> avl = new AVLTree<>(data1).insert(data2).insert(data3);
        String expected = "{rent=350, leftNode={rent=300, leftNode={}, rightNode={}}, rightNode={rent=400, leftNode={}, rightNode={}}}";

        // Check root value
        assertNotNull(
                "\nLeft rotation failed" +
                        "\nYour AVL tree should look like: " + expected + "\nBut it actually looks like: " + avl,
                avl.value);
        assertEquals(
                "\nLeft rotation failed" +
                        "\nYour AVL tree should look like: " + expected + "\nBut it actually looks like: " + avl,
                data1, avl.value);

        // Check left child value
        assertNotNull(
                "\nLeft rotation failed" +
                        "\nYour AVL tree should look like: " + expected + "\nBut it actually looks like: " + avl,
                avl.leftNode.value);
        assertEquals(
                "\nLeft rotation failed" +
                        "\nYour AVL tree should look like: " + expected + "\nBut it actually looks like: " + avl,
                data3, avl.leftNode.value);

        // Check right child value
        assertNotNull(
                "\nLeft rotation failed" +
                        "\nYour AVL tree should look like: " + expected + "\nBut it actually looks like: " + avl,
                avl.rightNode.value);
        assertEquals(
                "\nLeft rotation failed" +
                        "\nYour AVL tree should look like: " + expected + "\nBut it actually looks like: " + avl,
                data2, avl.rightNode.value);
    }

    @Test(timeout = 1000)
    public void rightRotateTest() {
        Data data1 = new Data("University Avenue", "Canberra", "2612", 400);
        Data data2 = new Data("something", "Canberra", "2612", 350);
        Data data3 = new Data("something", "Canberra", "2612", 300);

        AVLTree<Data> avl = new AVLTree<>(data1).insert(data2).insert(data3);
        String expected = "{rent=350, leftNode={rent=300, leftNode={}, rightNode={}}, rightNode={rent=400, leftNode={}, rightNode={}}}";
        // Check root value
        assertNotNull(
                "\nRight rotation failed" +
                        "\nYour AVL tree should look like: " + expected + "\nBut it actually looks like: " + avl,
                avl.value);
        assertEquals(
                "\nRight rotation failed" +
                        "\nYour AVL tree should look like: " + expected + "\nBut it actually looks like: " + avl,
                data2, avl.value);

        // Check left child value
        assertNotNull(
                "\nRight rotation failed" +
                        "\nYour AVL tree should look like: " + expected + "\nBut it actually looks like: " + avl,
                avl.leftNode.value);
        assertEquals(
                "\nRight rotation failed" +
                        "\nYour AVL tree should look like: " + expected + "\nBut it actually looks like: " + avl,
                data3, avl.leftNode.value);

        // Check right child value
        assertNotNull(
                "\nRight rotation failed" +
                        "\nYour AVL tree should look like: " + expected + "\nBut it actually looks like: " + avl,
                avl.rightNode.value);
        assertEquals(
                "\nRight rotation failed" +
                        "\nYour AVL tree should look like: " + expected + "\nBut it actually looks like: " + avl,
                data1, avl.rightNode.value);
    }

    @Test(timeout = 1000)
    public void balanceFactorTest() {
        // Ensure insertion results in balanced tree.
        Data data1 = new Data("University Avenue", "Canberra", "2612", 300);
        Data data2 = new Data("something", "Canberra", "2612", 350);
        Data data3 = new Data("something", "Canberra", "2612", 400);

        AVLTree<Data> avl = new AVLTree<>(data1).insert(data2).insert(data3);
        String expected = "{rent=350, leftNode={rent=300, leftNode={}, rightNode={}}, rightNode={rent=400, leftNode={}, rightNode={}}}";
        assertEquals(
                "\nInsertion does not properly balance tree (must left rotate)" +
                        "\nYour AVL tree should look like: " + expected + "\nBut it actually looks like: " + avl,
                0, avl.getBalanceFactor()
        );

        Data data4 = new Data("something", "Canberra", "2612", 500);
        Data data5 = new Data("something", "Canberra", "2612", 450);
        avl = avl.insert(data4).insert(data5);
        expected = "{rent=350, leftNode={rent=300, leftNode={}, rightNode={}}, rightNode={rent=450, leftNode={rent=400, leftNode={}, rightNode={}}, rightNode={rent=500, leftNode={}, rightNode={}}}}";
        assertEquals(
                "\nInsertion does not properly balance tree (must left, right, left rotate)" +
                        "\nYour AVL tree should look like: " + expected + "\nBut it actually looks like: " + avl,
                -1, avl.getBalanceFactor()
        );

        Data data6 = new Data("something", "Canberra", "2612", 550);
        avl = avl.insert(data6);
        expected = "{rent=450, leftNode={rent=350, leftNode={rent=300, leftNode={}, rightNode={}}, rightNode={rent=400, leftNode={}, rightNode={}}}, rightNode={rent=500, leftNode={}, rightNode={rent=550, leftNode={}, rightNode={}}}}";
        assertEquals(
                "\nInsertion does not properly balance tree (must left, right, left, left rotate)" +
                        "\nYour AVL tree should look like: " + expected + "\nBut it actually looks like: " + avl,
                0, avl.getBalanceFactor()
        );

        //Advanced
        avl = new AVLTree<>(new Data("something", "Canberra", "2612", 500))
                .insert(new Data("something", "Canberra", "2612", 350))
                .insert(new Data("something", "Canberra", "2612", 400))
                .insert(new Data("something", "Canberra", "2612", 300))
                .insert(new Data("something", "Canberra", "2612", 450))
                .insert(new Data("something", "Canberra", "2612", 200))
                .insert(new Data("something", "Canberra", "2612", 150))
                .insert(new Data("something", "Canberra", "2612", 100))
                .insert(new Data("something", "Canberra", "2612", 250));
        expected = "{rent=400, leftNode={rent=200, leftNode={rent=150, leftNode={rent=100, leftNode={}, rightNode={}}, rightNode={}}, rightNode={rent=300, leftNode={rent=250, leftNode={}, rightNode={}}, rightNode={rent=350, leftNode={}, rightNode={}}}}, rightNode={rent=500, leftNode={rent=450, leftNode={}, rightNode={}}, rightNode={}}}";
        assertEquals(
                "\nInsertion does not properly balance tree (must left, right, right, right, left, right rotate)" +
                        "\nYour AVL tree should look like: " + expected + "\nBut it actually looks like: " + avl,
                1, avl.getBalanceFactor()
        );
    }

    @Test(timeout = 1000)
    public void advancedRotationsTest() {
        // Cause a situation with a RR, RL, LL or LR rotation is required.
        Data data1 = new Data("something", "Canberra", "2612", 250);
        Data data2 = new Data("something", "Canberra", "2612", 175);
        Data data3 = new Data("something", "Canberra", "2612", 325);
        AVLTree<Data> avl = new AVLTree<>(data1)
                .insert(new Data("something", "Canberra", "2612", 300))
                .insert(data2)
                .insert(new Data("something", "Canberra", "2612", 125))
                .insert(new Data("something", "Canberra", "2612", 375))
                .insert(new Data("something", "Canberra", "2612", 100))
                .insert(new Data("something", "Canberra", "2612", 225))
                .insert(new Data("something", "Canberra", "2612", 200))
                .insert(new Data("something", "Canberra", "2612", 150))
                .insert(new Data("something", "Canberra", "2612", 400))
                .insert(data3)
                .insert(new Data("something", "Canberra", "2612", 275))
                .insert(new Data("something", "Canberra", "2612", 350));

        String expected = "{rent=250, leftNode={rent=175, leftNode={rent=125, leftNode={rent=100, leftNode={}, rightNode={}}, rightNode={rent=150, leftNode={}, rightNode={}}}, rightNode={rent=200, leftNode={}, rightNode={rent=225, leftNode={}, rightNode={}}}}, rightNode={rent=325, leftNode={rent=300, leftNode={rent=275, leftNode={}, rightNode={}}, rightNode={}}, rightNode={rent=375, leftNode={rent=350, leftNode={}, rightNode={}}, rightNode={rent=400, leftNode={}, rightNode={}}}}}";
        assertNotNull(
                "\nInsertion cannot handle either right right, right left, left left or left right double rotations." +
                        "\nYour AVL tree should look like: " + expected + "\nBut it actually looks like: " + avl,
                avl.value);
        assertNotNull(
                "\nInsertion cannot handle either right right, right left, left left or left right double rotations." +
                        "\nYour AVL tree should look like: " + expected + "\nBut it actually looks like: " + avl,
                avl.leftNode.value);
        assertNotNull(
                "\nInsertion cannot handle either right right, right left, left left or left right double rotations." +
                        "\nYour AVL tree should look like: " + expected + "\nBut it actually looks like: " + avl,
                avl.rightNode.value);
        assertEquals(
                "\nInsertion cannot handle either right right, right left, left left or left right double rotations." +
                        "\nYour AVL tree should look like: " + expected + "\nBut it actually looks like: " + avl,
                0, avl.getBalanceFactor()
        );
        assertEquals(
                "\nInsertion cannot handle either right right, right left, left left or left right double rotations." +
                        "\nYour AVL tree should look like: " + expected + "\nBut it actually looks like: " + avl,
                data1, avl.value
        );
        assertEquals(
                "\nInsertion cannot handle either right right, right left, left left or left right double rotations." +
                        "\nYour AVL tree should look like: " + expected + "\nBut it actually looks like: " + avl,
                data2, avl.leftNode.value
        );
        assertEquals(
                "\nInsertion cannot handle either right right, right left, left left or left right double rotations." +
                        "\nYour AVL tree should look like: " + expected + "\nBut it actually looks like: " + avl,
                data3, avl.rightNode.value
        );

//        // Another double rotation requiring test.
//        avl = new AVLTree<>(40)
//                .insert(20)
//                .insert(10)
//                .insert(25)
//                .insert(30)
//                .insert(22)
//                .insert(50);
//
//        expected = "{value=25, leftNode={value=20, leftNode={value=10, leftNode={}, rightNode={}}, rightNode={value=22, leftNode={}, rightNode={}}}, rightNode={value=40, leftNode={value=30, leftNode={}, rightNode={}}, rightNode={value=50, leftNode={}, rightNode={}}}}";
//        assertNotNull(
//                "\nInsertion cannot handle either right right, right left, left left or left right double rotations." +
//                        "\nYour AVL tree should look like: " + expected + "\nBut it actually looks like: " + avl,
//                avl.value);
//        assertNotNull(
//                "\nInsertion cannot handle either right right, right left, left left or left right double rotations." +
//                        "\nYour AVL tree should look like: " + expected + "\nBut it actually looks like: " + avl,
//                avl.leftNode.value);
//        assertNotNull(
//                "\nInsertion cannot handle either right right, right left, left left or left right double rotations." +
//                        "\nYour AVL tree should look like: " + expected + "\nBut it actually looks like: " + avl,
//                avl.rightNode.value);
//        assertEquals(
//                "\nInsertion cannot handle either right right, right left, left left or left right double rotations." +
//                        "\nYour AVL tree should look like: " + expected + "\nBut it actually looks like: " + avl,
//                0, avl.getBalanceFactor()
//        );
//        assertEquals(
//                "\nInsertion cannot handle either right right, right left, left left or left right double rotations." +
//                        "\nYour AVL tree should look like: " + expected + "\nBut it actually looks like: " + avl,
//                25, (int) avl.value
//        );
//        assertEquals(
//                "\nInsertion cannot handle either right right, right left, left left or left right double rotations." +
//                        "\nYour AVL tree should look like: " + expected + "\nBut it actually looks like: " + avl,
//                20, (int) avl.leftNode.value
//        );
//        assertEquals(
//                "\nInsertion cannot handle either right right, right left, left left or left right double rotations." +
//                        "\nYour AVL tree should look like: " + expected + "\nBut it actually looks like: " + avl,
//                40, (int) avl.rightNode.value
//        );
    }
}

