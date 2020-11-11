package com.example.eight.ViewClasses;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.eight.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Privacy extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_privacy);

        initialize();
        navBottom();
    }

    private void initialize()
    {
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.five);
    }

    private void navBottom()
    {
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {

                case R.id.one:
                    Intent one = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(one);
                    overridePendingTransition(0,0);
                    break;

                case R.id.two:
                    Intent two = new Intent(getApplicationContext(), Players.class);
                    startActivity(two);
                    overridePendingTransition(0,0);
                    break;

                   case R.id.three:
                    Intent three = new Intent(getApplicationContext(), Team.class);
                    startActivity(three);
                    overridePendingTransition(0,0);
                    break;

                case R.id.four:
                    Intent four = new Intent(getApplicationContext(), Leagues.class);
                    startActivity(four);
                    overridePendingTransition(0,0);
                    break;

            }
            return true;
            }
        });
    }
}
