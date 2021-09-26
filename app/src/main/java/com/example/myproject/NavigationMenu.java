package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavHostController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class NavigationMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_menu);
//      //  setupDrawer();
//        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView2);
//
//      //  NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        NavController navController = Navigation.findNavController(this,R.id.nav_host_fragment);
//
//
//       // NavigationUI.setupWithNavController(bottomNavigationView,navController);
//
//        NavigationUI.setupWithNavController(bottomNavigationView, navController);


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView2);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);


        NavigationUI.setupWithNavController(bottomNavigationView, navController);

        NavigationUI.setupActionBarWithNavController(this, navController);





    }
}