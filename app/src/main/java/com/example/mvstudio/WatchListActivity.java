package com.example.mvstudio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WatchListActivity extends AppCompatActivity {
    @BindView(R.id.movienameTextView)
    TextView movienameTextView;
    @BindView(R.id.moviecategoryTextView) TextView moviecategoryTextView;
    @BindView(R.id.moviedetailTextView)
    TextView moviedetailTextView;

    Toolbar toolbar;
    DrawerLayout drawer;
    ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_list);

        ButterKnife.bind(this);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer);
        toggle = new ActionBarDrawerToggle(this,drawer,toolbar,R.string.open,R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();

        NavigationView navigationView = findViewById((R.id.navi_view));
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId()==R.id.nav_home){
                    startActivity (new Intent(WatchListActivity.this, MainActivity.class));

                }
                else if(item.getItemId()==R.id.nav_movie){
                    startActivity(new Intent(WatchListActivity.this,  PopularMovieListActivity.class));

                }
                else if(item.getItemId()==R.id.nav_watchlist){
                    startActivity(new Intent(WatchListActivity.this, ListFormActivity.class));

                }
                else if(item.getItemId()==R.id.nav_form){
                    startActivity (new Intent(WatchListActivity.this, WatchListActivity.class));

                }
                DrawerLayout drawerLayout = findViewById(R.id.drawer);
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;


            }
        });

        Intent intent = getIntent();
        String movieName = intent.getStringExtra("movieName");
        String movieCategory = intent.getStringExtra("movieCategory");
        String movieDetail = intent.getStringExtra("movieDetail");
        movienameTextView.setText("Here are all the movie: " + movieName);
        moviecategoryTextView.setText("Here are all the Category: " + movieCategory);
        moviedetailTextView.setText("Here are all the Detail: " + movieDetail);
    }
}