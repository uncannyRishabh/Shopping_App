package com.cse.shoppingapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class DashboardActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ShapeableImageView nav_icon;
    private ImageView search, cart;

    @Override
    protected void onStart() {
        super.onStart();
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_ShoppingApp);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Toolbar toolbar = findViewById(R.id.dash_toolbar);
        setSupportActionBar(toolbar);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frag_container,home_frag.class,null,null)
                .commit();

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_drawer);
        search = findViewById(R.id.dash_search_icon);
        cart = findViewById(R.id.dash_end_icon2);
        nav_icon = findViewById(R.id.dash_navigation_icon);

    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        nav_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(drawerLayout.isDrawerOpen(GravityCompat.START)){
                    drawerLayout.close();
                }
                else {
                    drawerLayout.open();
                }
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.item1:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.frag_container,home_frag.class,null,null)
                                .commit();
                        drawerLayout.close();
                        break;
                    case R.id.item2:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.frag_container,orders_frag.class,null,null)
                                .commit();
                        drawerLayout.close();
                        break;
                    case R.id.item3:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.frag_container,wishlist_frag.class,null,null)
                                .commit();
                        drawerLayout.close();
                        break;
                    case R.id.item4:
                        mAuth.signOut();
                        Intent i1 = new Intent(DashboardActivity.this,WelcomeActivity.class);
                        startActivity(i1);
                        break;
                }
                return false;
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //search box thing
            }
        });

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frag_container,cart_frag.class,null,null)
                        .commit();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
}