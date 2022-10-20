package au.edu.anu.cecs.linkhome.avl;

import androidx.annotation.NonNull;

/**
 * Code from lab04
 * To avoid null pointer errors (and because this implementation is immutable)
 * we have a class that represents an 'empty' tree.
 */
public abstract class EmptyTree<T extends Comparable<T>> extends Tree<T> {

    // Will need to be implemented by the subclass inheriting this class.
    public abstract Tree<T> insert(T element);

    @Override
    public Tree<T> find(T element) {
        // Was unable to find the item. Hence, return null.
        return null;
    }

    @Override
    public int getHeight() {
        return -1;
    }

    @NonNull
    @Override
    public String toString() {
        return "{}";
    }
}