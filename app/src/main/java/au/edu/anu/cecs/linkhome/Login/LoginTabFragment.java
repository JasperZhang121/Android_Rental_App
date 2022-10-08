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

import au.edu.anu.cecs.linkhome.Database;
import au.edu.anu.cecs.linkhome.R;


public class LoginTabFragment extends Fragment {

    FirebaseAuth mAuth;
    TextView email;
    TextView password;
    Button login;



    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.login_tab_fragment,container,false);

        email = root.findViewById(R.id.email_address);
        password = root.findViewById(R.id.password);
        View forgot_password = root.findViewById(R.id.forgot_password);
        View remember_me = root.findViewById(R.id.remember_me);
        login = root.findViewById(R.id.button);
        mAuth = FirebaseAuth.getInstance();

        // I think this should be deleted
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();

            }
        });

        return root;
    }

    private void loginUser(){
        String emailID= email.getText().toString();
        String passwordNew= password.getText().toString();

        if(TextUtils.isEmpty(emailID)){
            email.setError("Email cannot be empty");
            email.requestFocus();
        }else if(TextUtils.isEmpty(passwordNew)){
            password.setError("Password cannot be empty");
            password.requestFocus();
        }
        else{
            mAuth.signInWithEmailAndPassword(emailID, passwordNew).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getContext(),"User logged in successfully ", Toast.LENGTH_SHORT).show();
                            // startActivity(new Intent(getContext(), HomePage.class));
                            startActivity(new Intent(getContext(), Database.class));
                        }

                        else{
                            Toast.makeText(getContext(),"Login Error" +
                                    task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                }
            });
        }
    }




}
