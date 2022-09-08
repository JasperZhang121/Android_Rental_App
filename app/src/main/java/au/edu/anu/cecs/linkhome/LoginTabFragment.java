package au.edu.anu.cecs.linkhome;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;


public class LoginTabFragment extends Fragment {

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.login_tab_fragment,container,false);

        View email = root.findViewById(R.id.email_address);
        View password = root.findViewById(R.id.password);
        View forgot_password = root.findViewById(R.id.forgot_password);
        View remember_me = root.findViewById(R.id.remember_me);
        Button login = root.findViewById(R.id.button);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createUser();

            }
        });

        return root;
    }

    private void createUser(){

    }

}
