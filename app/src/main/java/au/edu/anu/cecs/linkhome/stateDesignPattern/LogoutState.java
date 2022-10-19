package au.edu.anu.cecs.linkhome.stateDesignPattern;

import com.google.firebase.auth.FirebaseUser;

import java.util.List;

import au.edu.anu.cecs.linkhome.homePage.posts.Data;

/**
 * To implement to State Design Pattern for User's Logout state
 * Adapted from Design Pattern Lecture Slides
 */
public class LogoutState extends UserState {
    public LogoutState(User user) {
        super(user);
    }

    @Override
    public boolean login(FirebaseUser firebaseUser) {
        return true;
    }

    @Override
    public boolean logout() {
        return false;
    }

    @Override
    public List<Data> bookmarksPage() {
        return null;
    }

    @Override
    public List<Data> myPosts() {
        return null;
    }
}
