package com.example.scratches;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
//import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
//import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    TextView rooms,devices;
    Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        drawerLayout=findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.nav_view);
       toolbar=(Toolbar)findViewById(R.id.toolbar);
       rooms=findViewById(R.id.noofrooms_home);
       devices=findViewById(R.id.noofdevices_home);
        logout=findViewById(R.id.logout_home);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   System.out.println("");
                FirebaseAuth.getInstance().signOut();
                SharedPreferences preferences=getSharedPreferences("remember",MODE_PRIVATE);

                SharedPreferences.Editor editor =preferences.edit();
                editor.putString("remember", "false");
                editor.apply();
                startActivity(new Intent(MainActivity.this,MainActivityLogin.class));
                finish();
            }
        });


        if(toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle("");
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
         //  Intent intentn=new Intent(MainActivity.this,MainActivity.class);//changed from Main activity
           // startActivity(intentn);
        }

        navigationView.bringToFront();

        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,
                R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

      
    }


    @Override
    public void onBackPressed() {

        if(drawerLayout.isDrawerOpen(GravityCompat.START))//If navigation drrawer is open when back is presssed do not close the app,just close the drawer.
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else
        super.onBackPressed();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId())
        {
            case R.id.nav_rooms:
                Intent intent =new Intent(MainActivity.this,activity_room.class);
                startActivity(intent);
                break;
           case R.id.nav_devices:
               Intent intent2 =new Intent(MainActivity.this,Devices.class);
                startActivity(intent2);
                break;
             case R.id.nav_use:

                startActivity(new Intent(MainActivity.this,How_to_use.class));
                break;
            case R.id.profile_view:
                startActivity(new Intent(MainActivity.this,UserProfile.class));

        }
        drawerLayout.closeDrawer(GravityCompat.START);//this will close the drawer and perform the selected function
        return true;
    }

    public void devices(View view) {
        startActivity(new Intent(getApplicationContext(),Devices.class));
    }

    public void rooms(View view) {

        startActivity(new Intent(getApplicationContext(),activity_room.class));

    }



}