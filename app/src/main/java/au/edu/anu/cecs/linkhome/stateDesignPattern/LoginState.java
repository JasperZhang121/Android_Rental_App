package au.edu.anu.cecs.linkhome.stateDesignPattern;

import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

import au.edu.anu.cecs.linkhome.homePage.posts.Data;

/**
 * To implement to State Design Pattern for User's Login state
 * Adapted from Design Pattern Lecture Slides
 *
 * @author Avani Dhaliwal
 */
public class LoginState extends UserState {

    public LoginState(User user) {
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
}
