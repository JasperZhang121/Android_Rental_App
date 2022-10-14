package au.edu.anu.cecs.linkhome.Login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;

import au.edu.anu.cecs.linkhome.R;
import au.edu.anu.cecs.linkhome.StateDesignPattern.User;

public class LoginActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        mAuth = FirebaseAuth.getInstance();

//        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.fab_google);

//        tabLayout.addTab(tabLayout.newTab().setText("Login"));
//        tabLayout.addTab(tabLayout.newTab().setText("Sign Up"));
//        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        //final LoginAdapter adapter = new LoginAdapter(getSupportFragmentManager(), this, tabLayout.getTabCount());
        //viewPager.setAdapter(adapter);

        //viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setupWithViewPager(viewPager);
        LoginAdapter loginAdapter = new LoginAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);

        Bundle bundle = new Bundle();
        bundle.putSerializable("USER", getIntent().getExtras().getSerializable("USER"));
        Fragment loginFragment = new LoginTabFragment();
        loginFragment.setArguments(bundle);
        loginAdapter.addFragment(loginFragment, "Login");

        loginAdapter.addFragment(new SignUpTabFragment(), "SignUp");

        viewPager.setAdapter(loginAdapter);
    }
}