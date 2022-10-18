package au.edu.anu.cecs.linkhome.StateDesignPattern;

import com.google.firebase.auth.FirebaseUser;

import java.io.Serializable;
import java.util.List;

import au.edu.anu.cecs.linkhome.Data;

public abstract class UserState implements Serializable {
    protected User user;

    public UserState(User user) {
        this.user = user;
    }

    public abstract boolean login(FirebaseUser firebaseUser);

    public abstract boolean logout();

    public abstract List<Data> bookmarksPage();

    public abstract List<Data> myPosts();

}
