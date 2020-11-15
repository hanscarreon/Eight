package com.example.eight.ViewClasses;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.eight.Adapters.EventsLive;
import com.example.eight.ModelList.ListLive;
import com.example.eight.R;
import com.example.eight.ViewModel.MainViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MainViewModel mvw;
    public static RecyclerView pView;
    public static RecyclerView tView;
    private RecyclerView.Adapter mAdapter,tAdapter;
    private RecyclerView.LayoutManager rvLayout,tLayout;
    private ProgressDialog pdLoading;
    private BottomNavigationView bottomNavigationView;
    CountDownTimer countDownTimer;
    RelativeLayout noEvent;
    boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        load();
        declare();
        initialize();
        navBottom();
    }

    private void initialize()
    {
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.one);
    }

    private void declare()
    {
        mvw = ViewModelProviders.of(this).get(MainViewModel.class);
        mvw.init();

        mvw.getNicePlaces().observe(this, new Observer<List<ListLive.Datum>>() {
            @Override
            public void onChanged(@Nullable List<ListLive.Datum> nicePlaces) {
                getLive(nicePlaces);
                mAdapter.notifyDataSetChanged();
                pdLoading.dismiss();
            }
        });
    }

    private void load(){
        pdLoading = new ProgressDialog(this);
        pdLoading.setMessage("\tPlease Wait...");
        pdLoading.setCancelable(false);
        pdLoading.show();
    }

    private void getLive(List<ListLive.Datum> nicePlaces)
    {
        noEvent = findViewById(R.id.noEvent);
        pView = findViewById(R.id.mainView);
        rvLayout = new LinearLayoutManager(this);
        pView.setLayoutManager(rvLayout);
        if(nicePlaces != null){
            noEvent.setVisibility(View.GONE);
        }
        mAdapter = new EventsLive(this,nicePlaces);
        pView.setAdapter(mAdapter);
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

                    case R.id.five:
                        Intent five = new Intent(getApplicationContext(), Privacy.class);
                        startActivity(five);
                        overridePendingTransition(0,0);
                        break;

                }
                return true;
            }
        });
    }

    private AlertDialog AskOption()
    {
        AlertDialog myQuittingDialogBox = new AlertDialog.Builder(this)
                .setTitle("Logout")
                .setMessage("Are you sure you want to Exit?")
                .setIcon(R.drawable.ic_close_black)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();
        return myQuittingDialogBox;

    }

    @Override
    public void onBackPressed() {
        AlertDialog diaBox = AskOption();
        diaBox.show();
/*
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }
            AskOption();
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);*/

    }
}