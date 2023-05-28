package com.example.fitnessApp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.example.fitnessuygulamasi.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomePageActivty extends AppCompatActivity {
    private BottomNavigationView bottomNavi;
    public static String gelenEmail;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page_activty);
        bottomNavi=findViewById(R.id.bottomNav);
        NavHostFragment navHostFragment=(NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.navHostFragment);
        NavigationUI.setupWithNavController(bottomNavi,navHostFragment.getNavController());
        gelenEmail=getIntent().getStringExtra("email");
    }
}