package com.example.timescreeningapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class UserDashboard extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    Button mybids,logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ngodash);

        // Initialize Views
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        toolbar = findViewById(R.id.toolbar);

        mybids=(Button)findViewById(R.id.mybids);
        logout=(Button) findViewById(R.id.logout);
        mybids.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent i1= new Intent(UserDashboard.this,AllInstallApp.class);
            startActivity(i1);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               Intent i1= new Intent(UserDashboard.this,MainActivity.class);
                startActivity(i1);
               //finish();
            }
        });

        // Set Toolbar
        setSupportActionBar(toolbar);

        // Toggle Button for Drawer
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // Handle Navigation Item Clicks
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.nav_home) {
                  //  Toast.makeText(AdvocateDashboard.this, "Home Clicked", Toast.LENGTH_SHORT).show();
                } else if (id == R.id.nav_installapp) {

                  Intent i1= new Intent(UserDashboard.this,AllInstallApp.class);
                  startActivity(i1);

                }else if (id == R.id.nav_addrestr) {

                    Intent i1= new Intent(UserDashboard.this,AddRestrication1.class);
                    startActivity(i1);

                } else if (id == R.id.nav_viewrestr) {
                    //Intent i1= new Intent(NGODashboard.this,ViewSolvedComplaint.class);
                    //startActivity(i1);

                }
                else if (id == R.id.nav_logout1) {
                    //Intent i1= new Intent(NGODashboard.this,MainActivity.class);
                    //startActivity(i1);
                    finish();

                }

                // Close drawer after selection
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
