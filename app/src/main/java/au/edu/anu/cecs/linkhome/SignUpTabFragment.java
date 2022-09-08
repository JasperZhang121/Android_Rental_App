package au.edu.anu.cecs.linkhome;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import au.edu.anu.cecs.linkhome.R;

public class SignUpTabFragment extends Fragment {

    TextView email;
    TextView password;
    TextView confirm_password;
    Button signup;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.signup_tab_fragment,container,false);

        email = root.findViewById(R.id.email_address);
        password = root.findViewById(R.id.password);
        confirm_password = root.findViewById(R.id.confirm_password);
        signup = root.findViewById(R.id.signup);

        return root;
    }
}
