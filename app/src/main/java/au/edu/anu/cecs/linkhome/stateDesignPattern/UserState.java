package au.edu.anu.cecs.linkhome.stateDesignPattern;

import com.google.firebase.auth.FirebaseUser;

import java.io.Serializable;
import java.util.List;

import au.edu.anu.cecs.linkhome.homePage.posts.Data;

/**
 * @author Avani Dhaliwal, Adapted from Design Pattern Lecture Slides,
 */
public abstract class UserState implements Serializable {
    protected final User user;

    public UserState(User user) {
        this.user = user;
    }

    public abstract boolean login(FirebaseUser firebaseUser);

    public abstract boolean logout();

    public abstract List<Data> bookmarksPage();

    public abstract List<Data> myPosts();

}
