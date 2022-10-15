package au.edu.anu.cecs.linkhome.HomePage;

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
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import au.edu.anu.cecs.linkhome.Login.LoginActivity;
import au.edu.anu.cecs.linkhome.MainActivity;
import au.edu.anu.cecs.linkhome.R;
import au.edu.anu.cecs.linkhome.StateDesignPattern.LogoutState;
import au.edu.anu.cecs.linkhome.StateDesignPattern.User;

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

        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new DatabaseFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }
    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//        User user = (User) getIntent().getExtras().getSerializable("USER");
        User user = User.getInstance();

        switch (item.getItemId()){
            case R.id.nav_bookmarks:
                if(user.bookmarksPage() != null){
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            new BookmarkFragment()).commit();
                } else {
                    Toast.makeText(this, "Login to bookmark posts", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new DatabaseFragment()).commit();
                break;
            case R.id.nav_login:
                if(user.bookmarksPage() == null){
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    intent.putExtra("USER", user);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "You are currently logged in", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.nav_logout:
                if(user.bookmarksPage() != null){
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