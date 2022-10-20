package au.edu.anu.cecs.linkhome.stateDesignPattern;

import com.google.firebase.auth.FirebaseUser;

import java.io.Serializable;
import java.util.List;

import au.edu.anu.cecs.linkhome.homePage.posts.Data;

/**
 * User class to implement state design pattern
 * Adapted from Design Pattern Lecture Slides
 *
 * @author Avani Dhaliwal
 */
public class User implements Serializable {

    // Instance of the User class
    private static User instance;
    private UserState userState;
    private String username;

    public User() {
        UserState defaultState = new LogoutState(this);
        changeState(defaultState);
    }

    //Setters
    public void setUsername(String username) {
        this.username = username;
    }

    public void changeState(UserState state) {
        this.userState = state;
    }

    //Getters
    public UserState getUserState() {
        return userState;
    }

    public String getUsername() {
        return username;
    }

    /**
     * Singleton Design Pattern for User
     *
     * @return User
     * @author Devanshi Dhall
     */
    public static User getInstance() {
        if (instance == null) {
            instance = new User();
        }
        return instance;
    }

    /**
     * @param firebaseUser the current user
     * @return if the user can login
     * @author Avani Dhaliwal
     */
    public boolean login(FirebaseUser firebaseUser) {
        boolean loginOk = userState.login(firebaseUser);
        if (loginOk) {
            setUsername(firebaseUser.getEmail());
        }
        return loginOk;
    }

    /**
     * @return if the user can logout
     * @author Avani Dhaliwal
     */
    public boolean logout() {
        boolean logoutOk = userState.logout();
        if (logoutOk) {
            this.username = null;
        }
        return logoutOk;
    }
}
