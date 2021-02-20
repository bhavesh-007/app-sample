package com.example.sample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    NavigationView nav;
    DrawerLayout drawer;
    ActionBarDrawerToggle toggle;
    BottomNavigationView bottomNav;
    Fragment Home,blank,active;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        nav = findViewById(R.id.nav);
        drawer = findViewById(R.id.drawer);

        toggle = new ActionBarDrawerToggle(this,drawer,toolbar,R.string.open,R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        bottomNav = findViewById(R.id.bottomNav);
        Home = new HomeFragment();
        blank = new Blank();
        active = Home;
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().add(R.id.frame,Home,"1").commit();
        fm.beginTransaction().add(R.id.frame,blank,"blank").hide(blank).commit();

        bottomNav.setOnNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.Home) {
                fm.beginTransaction().hide(active).show(Home).commit();
                active=Home;
            }else {
                fm.beginTransaction().hide(active).show(blank).commit();
                active = blank;
            }
            return true;
        });
    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.header_popup,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.logout){
            finish();
            return true;
        }
        return false;
    }
}