package au.edu.anu.cecs.linkhome.StateDesignPattern;

import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

import au.edu.anu.cecs.linkhome.Data;

public class LoginState extends UserState{

    public LoginState(User user) {
        super(user);
    }

    @Override
    public boolean login(FirebaseUser firebaseUser) {
        return false;
    }

    @Override
    public boolean logout() {
        return true;
    }

    @Override
    public List<Data> bookmarksPage() {
        // TODO
        return new ArrayList<>();
    }

    @Override
    public List<Data> myPosts() {
        //TODO
        return new ArrayList<>();
    }
}
