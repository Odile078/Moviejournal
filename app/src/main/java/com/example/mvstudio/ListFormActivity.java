package com.example.mvstudio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.google.android.material.navigation.NavigationView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListFormActivity extends AppCompatActivity  implements View.OnClickListener {

    DatabaseHelper db;
    @BindView(R.id.editTextMovieName)
    EditText editTextMovieName;
    @BindView(R.id.editTextMovieCategory) EditText editTextMovieCategory;
    @BindView(R.id.editTextMovieDetail)
    EditText editTextMovieDetail;
    @BindView(R.id.submitButton)
    Button submitButton;
    @BindView(R.id.ListButton) Button ListButton;
    Toolbar toolbar;
    DrawerLayout drawer;
    ActionBarDrawerToggle toggle;

    AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_form);
        ButterKnife.bind(this);
        db = new DatabaseHelper(this);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer);
        toggle = new ActionBarDrawerToggle(this,drawer,toolbar,R.string.open,R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();


        submitButton.setOnClickListener(this);
        ListButton.setOnClickListener(this);

        //validation style
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(this,R.id.editTextMovieName, RegexTemplate.NOT_EMPTY,R.string.invalid_name);
        awesomeValidation.addValidation(this,R.id.editTextMovieCategory, RegexTemplate.NOT_EMPTY,R.string.invalid_category);
        awesomeValidation.addValidation(this,R.id.editTextMovieDetail, RegexTemplate.NOT_EMPTY,R.string.invalid_detail);

        NavigationView navigationView = findViewById((R.id.navi_view));
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId()==R.id.nav_home){
                    startActivity (new Intent(ListFormActivity.this, MainActivity.class));

                }
                else if(item.getItemId()==R.id.nav_movie){
                    startActivity(new Intent(ListFormActivity.this, MovieActivity.class));

                }
                else if(item.getItemId()==R.id.nav_watchlist){
                    startActivity(new Intent(ListFormActivity.this, ListFormActivity.class));

                }
                else if(item.getItemId()==R.id.nav_form){
                    startActivity (new Intent(ListFormActivity.this, WatchListActivity.class));

                }
                DrawerLayout drawerLayout = findViewById(R.id.drawer);
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;


            }
        });

    }

    @Override
    public void onClick(View view) {
        if (view == submitButton) {
            //String movieName = editTextMovieName.getText().toString();
            if(awesomeValidation.validate() ) {
                // insertData(movieName);
                // editTextMovieName.setText("");

                FragmentManager fm = getSupportFragmentManager();
                FormSuccessFragment FormSuccessFragment = new FormSuccessFragment();
                FormSuccessFragment.show(fm, "sms");
            }else{
                Toast.makeText(getApplicationContext(),"Validation faild",Toast.LENGTH_LONG).show();

            }

        }
        if (view == ListButton) {
            if(awesomeValidation.validate()) {

                String movieName = editTextMovieName.getText().toString();
                String movieCategory = editTextMovieCategory.getText().toString();
                String movieDetail = editTextMovieDetail.getText().toString();

                Intent intent = new Intent(ListFormActivity.this, WatchListActivity.class);

                intent.putExtra("movieName", movieName);
                intent.putExtra("movieCategory", movieCategory);
                intent.putExtra("movieDetail", movieDetail);


                startActivity(intent);
            }else{
                Toast.makeText(getApplicationContext(),"Validation faild", Toast.LENGTH_LONG).show();
            }
        }

    }
    /*
    public void insertData(String movieName){
        boolean insertData = db.insertData(movieName);
        if(insertData == true){
            Toast.makeText(ListFormActivity.this,"Successfully saved!",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(ListFormActivity.this,"Something went wrong",Toast.LENGTH_LONG).show();
        }
    }*/
}