package au.edu.anu.cecs.linkhome.Login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import au.edu.anu.cecs.linkhome.R;

/**
 * SignUpTabFragment deals with the new users registration
 */
public class SignUpTabFragment extends Fragment {

    // Storing UI components
    TextView email;
    TextView password;
    TextView confirm_password;
    Button signup;

    // Firebase authentication
    FirebaseAuth mAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.signup_tab_fragment,container,false);

        email = root.findViewById(R.id.email_address);
        password = root.findViewById(R.id.password);
        confirm_password = root.findViewById(R.id.confirm_password);
        signup = root.findViewById(R.id.signup);
        mAuth= FirebaseAuth.getInstance();

        signup.setOnClickListener(view -> createUser());
        return root;
    }

    /**
     * createUser method creates an account for the new users who have signed up in the app
     */
    private void createUser(){
        String emailID= email.getText().toString();
        String passwordNew= password.getText().toString();
        String passwordConfirm = confirm_password.getText().toString();

        // Check for Email
        if(TextUtils.isEmpty(emailID)){
            email.setError("Email cannot be empty");
            email.requestFocus();
        }
        // Check for Password
        else if(TextUtils.isEmpty(passwordNew)){
            password.setError("Password cannot be empty");
            password.requestFocus();
        }
        // Check for PasswordConfirm
        else if(TextUtils.isEmpty(passwordConfirm)){
            confirm_password.setError("Password cannot be empty");
            confirm_password.requestFocus();
        }
        // Check if both the passwords match
        else if(!passwordNew.equals(passwordConfirm)){
            confirm_password.setError("Passwords do not match");
            confirm_password.requestFocus();
        }

        // Checks for if a user has signed up successfully or not
        else{
            mAuth.createUserWithEmailAndPassword(emailID,passwordConfirm).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(getContext(),"User Registered ", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getContext(), LoginTabFragment.class));
                    }
                    else{
                        Toast.makeText(getContext(),"Registration error " +
                                task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }
}

