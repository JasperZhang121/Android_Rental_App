package au.edu.anu.cecs.linkhome.StateDesignPattern;

import com.google.firebase.auth.FirebaseUser;

import java.util.List;

import au.edu.anu.cecs.linkhome.Data;

public class LogoutState extends UserState{
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
