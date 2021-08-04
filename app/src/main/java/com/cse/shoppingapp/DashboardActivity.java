package com.cse.shoppingapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class DashboardActivity extends AppCompatActivity {
    private static final String TAG = "DashboardActivity";
    private FirebaseAuth mAuth;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ShapeableImageView nav_icon;
    private ImageView search, cart, nav_header_profile;
    private TextView nav_header_heading,nav_header_subtitle;

    private Runnable hide_toolbar_item_for_cartview = new Runnable() {
        @Override
        public void run() {

            toolbar.findViewById(R.id.dash_search_icon).setVisibility(View.INVISIBLE);
            toolbar.findViewById(R.id.dash_end_icon2).setVisibility(View.INVISIBLE);
        }
    };

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

        toolbar = findViewById(R.id.dash_toolbar);
        setSupportActionBar(toolbar);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frag_container,home_frag.class,null,"home")
                .commit();

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_drawer);
        search = findViewById(R.id.dash_search_icon);
        cart = findViewById(R.id.dash_end_icon2);
        nav_icon = findViewById(R.id.dash_navigation_icon);
        nav_header_profile = navigationView.getHeaderView(0).findViewById(R.id.header_icon);
        nav_header_heading = navigationView.getHeaderView(0).findViewById(R.id.header_heading);
        nav_header_subtitle = navigationView.getHeaderView(0).findViewById(R.id.header_subtitle);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser==null){
            nav_header_heading.setText("Click here to login");
            nav_header_subtitle.setText("Click here to signup");

            nav_header_heading.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //redirect to login
                }
            });
            nav_header_subtitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //redirect to signup
                }
            });
        }

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
                        Log.e(TAG, "onBackPressed: "+getSupportFragmentManager().getBackStackEntryCount());
                        break;
                    case R.id.item2:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.frag_container,orders_frag.class,null,null)
                                .addToBackStack(null)
                                .commit();
                        Log.e(TAG, "onBackPressed: "+getSupportFragmentManager().getBackStackEntryCount());
                        break;
                    case R.id.item3:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.frag_container,wishlist_frag.class,null,null)
                                .addToBackStack(null)
                                .commit();
                        Log.e(TAG, "onBackPressed: "+getSupportFragmentManager().getBackStackEntryCount());
                        break;
                    case R.id.item4:
                        mAuth.signOut();
                        Intent i1 = new Intent(DashboardActivity.this,WelcomeActivity.class);
                        startActivity(i1);
                        break;
                }
                drawerLayout.close();
                return true;
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
                        .addToBackStack(null)
                        .runOnCommit(hide_toolbar_item_for_cartview)
                        .commit();
                Log.e(TAG, "onBackPressed: "+getSupportFragmentManager().getBackStackEntryCount());
            }
        });

    }

    @Override
    public void onBackPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag("home");
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else if (fragment != null && fragment.isVisible()) {
            finishAffinity();
        } else super.onBackPressed();
    }
}