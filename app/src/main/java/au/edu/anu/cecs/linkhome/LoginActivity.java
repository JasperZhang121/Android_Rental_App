package au.edu.anu.cecs.linkhome;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import au.edu.anu.cecs.linkhome.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);

        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.fab_google);

//        tabLayout.addTab(tabLayout.newTab().setText("Login"));
//        tabLayout.addTab(tabLayout.newTab().setText("Sign Up"));
//        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        //final LoginAdapter adapter = new LoginAdapter(getSupportFragmentManager(), this, tabLayout.getTabCount());
        //viewPager.setAdapter(adapter);

        //viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setupWithViewPager(viewPager);
        LoginAdapter loginAdapter = new LoginAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        loginAdapter.addFragment(new LoginTabFragment(), "Login");
        loginAdapter.addFragment(new SignUpTabFragment(), "SignUp");

        viewPager.setAdapter(loginAdapter);


    }
}