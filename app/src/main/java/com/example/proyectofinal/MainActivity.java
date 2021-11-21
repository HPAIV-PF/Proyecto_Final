package com.example.proyectofinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new PresentationFragment()).commit();

            navigationView.setCheckedItem(R.id.nav_presentation);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_presentation:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new PresentationFragment()).commit();
                break;
            case R.id.nav_entorno:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new EntornoFragment()).commit();
                break;
            case R.id.nav_posicion:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new PositionFragment()).commit();
                break;
            case R.id.nav_movimiento:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new MovementFragment()).commit();
                break;
            case R.id.nav_luz:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new LightFragment()).commit();
                break;
            case R.id.nav_grav:
                Toast.makeText(this, "Send", Toast.LENGTH_SHORT).show();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

}