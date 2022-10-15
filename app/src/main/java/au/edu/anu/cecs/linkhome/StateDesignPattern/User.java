package au.edu.anu.cecs.linkhome.StateDesignPattern;

import com.google.firebase.auth.FirebaseUser;

import java.io.Serializable;
import java.util.List;

import au.edu.anu.cecs.linkhome.Data;

public class User implements Serializable {

    private static User instance;
    UserState userState;

    String username;

    public User(){
        UserState defaultState = new LogoutState(this);
        changeState(defaultState);
    }

    public static User getInstance(){
        if(instance == null){
            instance = new User();
        }
        return instance;
    }

    public void changeState(UserState state){
        this.userState = state;
    }

    public boolean login(FirebaseUser firebaseUser){
        boolean loginOk = userState.login(firebaseUser);
        if(loginOk){
            this.username = firebaseUser.getEmail();
        }
        return loginOk;
    }

    public boolean logout(){
        boolean logoutOk = userState.logout();
        if(logoutOk){
            this.username = null;
        }
        return logoutOk;
    }

    public List<Data> bookmarksPage(){
        return userState.bookmarksPage();
    }
}
