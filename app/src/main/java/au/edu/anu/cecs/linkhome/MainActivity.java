package au.edu.anu.cecs.linkhome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import au.edu.anu.cecs.linkhome.Login.LoginActivity;
import au.edu.anu.cecs.linkhome.StateDesignPattern.User;

/**
 * MainActivity to create connection
 * with the Firebase and jump to the LoginActivity class
 * @author Devanshi Dhall, Avani Dhaliwal
 */
public class MainActivity extends AppCompatActivity {

    // Initialization of FirebaseAuth and User
    FirebaseAuth mAuth;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser firebaseUser = mAuth.getCurrentUser();
        user = User.getInstance();

        if (firebaseUser != null) {
            finish();
        }
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.putExtra("USER", user);
        startActivity(intent);
    }

}