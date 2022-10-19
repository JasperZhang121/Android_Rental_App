package au.edu.anu.cecs.linkhome.Login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.firebase.auth.FirebaseAuth;

import au.edu.anu.cecs.linkhome.R;
import au.edu.anu.cecs.linkhome.StateDesignPattern.User;

/**
 * LoginActivity sets up the login tab fragment and sign up fragment
 * for the users to login and sign up accordingly
 * The data is stored on firebase for all the logged in users or those users who sign up
 * @author Devanshi Dhall
 */

public class LoginActivity extends AppCompatActivity {

    // Firebase Authentication
    FirebaseAuth mAuth;
    private final String[] fragmentTitles = new String[]{"Login", "Signup"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Instance of the User
        User user = User.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Tabs for Login and SignUp
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        ViewPager2 viewPager = findViewById(R.id.view_pager);
        mAuth = FirebaseAuth.getInstance();
        LoginAdapter loginAdapter = new LoginAdapter(this);
        Bundle bundle = new Bundle();
        bundle.putSerializable("USER", user);

        Fragment loginFragment = new LoginTabFragment();
        loginFragment.setArguments(bundle);

        loginAdapter.addFragment(loginFragment);
        loginAdapter.addFragment(new SignUpTabFragment());
        viewPager.setAdapter(loginAdapter);

        new TabLayoutMediator(tabLayout, viewPager, ((tab, position) -> tab.setText(fragmentTitles[position]))).attach();

    }
}