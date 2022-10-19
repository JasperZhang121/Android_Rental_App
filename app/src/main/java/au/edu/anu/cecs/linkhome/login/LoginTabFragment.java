package au.edu.anu.cecs.linkhome.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

import au.edu.anu.cecs.linkhome.homePage.HomePage;
import au.edu.anu.cecs.linkhome.R;
import au.edu.anu.cecs.linkhome.stateDesignPattern.LoginState;
import au.edu.anu.cecs.linkhome.stateDesignPattern.User;

/**
 * LoginTabFragment deals with the login state of the users
 * @author Devanshi Dhall, Avani Dhaliwal
 */
public class LoginTabFragment extends Fragment {

    // Firebase authentication
    FirebaseAuth mAuth;

    // Storing UI components
    TextView email;
    TextView password;
    Button login;
    Button continueButton;

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.login_tab_fragment, container, false);

        // To store all the components of UI with respect to their data type
        email = root.findViewById(R.id.email_address);
        password = root.findViewById(R.id.password);
        login = root.findViewById(R.id.loginButton);
        continueButton = root.findViewById(R.id.continueButton);
        mAuth = FirebaseAuth.getInstance();
        login.setOnClickListener(view -> loginUser());
        continueButton.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), HomePage.class);
            User user = User.getInstance();
            user.logout();
            intent.putExtra("USER", user);
            startActivity(intent);
        });
        return root;
    }

    /**
     * loginUser method checks for certain conditions if a user is trying to log in
     */
    private void loginUser() {
        String emailID = email.getText().toString();
        String passwordNew = password.getText().toString();

        // Check for Email
        if (TextUtils.isEmpty(emailID)) {
            email.setError("Email cannot be empty");
            email.requestFocus();
        }

        // Check for Password
        else if (TextUtils.isEmpty(passwordNew)) {
            password.setError("Password cannot be empty");
            password.requestFocus();
        }

        // Checks for if a user has logged in successfully or not
        else {
            mAuth.signInWithEmailAndPassword(emailID, passwordNew).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Toast.makeText(getContext(), "User logged in successfully ", Toast.LENGTH_SHORT).show();
                    User user = User.getInstance();
                    user.changeState(new LoginState(user));
                    user.login(mAuth.getCurrentUser());
                    Intent intent = new Intent(getContext(), HomePage.class);
                    intent.putExtra("USER", user);
                    startActivity(intent);
                } else {
                    Toast.makeText(getContext(), "Login Error" +
                            Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
