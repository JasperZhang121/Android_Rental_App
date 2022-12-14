package au.edu.anu.cecs.linkhome.homePage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import au.edu.anu.cecs.linkhome.homePage.bookmarks.BookmarkFragment;
import au.edu.anu.cecs.linkhome.homePage.posts.DatabaseFragment;
import au.edu.anu.cecs.linkhome.login.LoginActivity;
import au.edu.anu.cecs.linkhome.R;
import au.edu.anu.cecs.linkhome.stateDesignPattern.LoginState;
import au.edu.anu.cecs.linkhome.stateDesignPattern.LogoutState;
import au.edu.anu.cecs.linkhome.stateDesignPattern.User;

/**
 * HomePage stores all the details related to the navigation
 * All the different states such as going from login to clicking on logout are implemented
 * Each user can navigate to different pages (bookmarks and homepage) in the app as per their preference
 *
 * @author Avani Dhaliwal
 */
public class HomePage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_viewer);

        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //Display the username on the navigation header
        TextView userEmail = (TextView) navigationView.getHeaderView(0).findViewById(R.id.username_tv);
        User user = User.getInstance();
        if (user.getUserState() instanceof LoginState) {
            userEmail.setText(user.getUsername());
        }

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new DatabaseFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }
    }

    /**
     * onBackPressed method helps to open or close the
     * navigation menu by clicking on hamburger menu icon
     * @author Avani Dhaliwal
     */
    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    /**
     * onNavigationItemSelected helps to navigate from the item selected to the destination page
     *
     * @param item MenuItem
     * @return true or false
     * @author Avani Dhaliwal
     */
    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        // Creating an instance of a User class to access where an instance of a user is required
        User user = User.getInstance();
        switch (item.getItemId()) {
            //If user clicks on Wishlist start the bookmark fragment
            case R.id.nav_bookmarks:
                if (user.getUserState() instanceof LoginState) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            new BookmarkFragment()).commit();
                } else {
                    Toast.makeText(this, "Login to add posts to Wishlist", Toast.LENGTH_SHORT).show();
                }
                break;

            //If user clicks on home start the database fragment
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new DatabaseFragment()).commit();
                break;

            //When login is clicked
            case R.id.nav_login:
                //if the user is logged out go to the login page
                if (user.getUserState() instanceof LogoutState) {
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    intent.putExtra("USER", user);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "You are currently logged in", Toast.LENGTH_SHORT).show();
                }
                break;

            //When logout is clicked
            case R.id.nav_logout:
                //if the user is logged in go to the login page
                if (user.getUserState() instanceof LoginState) {
                    FirebaseAuth mAuth = FirebaseAuth.getInstance();
                    mAuth.signOut();
                    user.changeState(new LogoutState(user));
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    intent.putExtra("USER", user);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "You are currently logged out", Toast.LENGTH_SHORT).show();
                }
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}