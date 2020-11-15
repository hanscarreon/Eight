package com.example.eight.ViewClasses;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MenuItem;

import com.example.eight.Adapters.EventsLive;
import com.example.eight.Adapters.PlayersAdapter;
import com.example.eight.ModelList.ListLive;
import com.example.eight.ModelList.PlayerModel;
import com.example.eight.R;
import com.example.eight.ViewModel.MainViewModel;
import com.example.eight.ViewModel.PlayerViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class Players extends AppCompatActivity {

    private PlayerViewModel pvw;
    public static RecyclerView pView;
    public static RecyclerView tView;
    private RecyclerView.Adapter mAdapter,tAdapter;
    private RecyclerView.LayoutManager rvLayout,tLayout;
    private ProgressDialog pdLoading;
    private BottomNavigationView bottomNavigationView;
    CountDownTimer countDownTimer;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players);
        load();
        declare();
        initialize();
        navBottom();
    }

    private void initialize()
    {
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.two);
    }

    private void declare()
    {
        pvw = ViewModelProviders.of(this).get(PlayerViewModel.class);
        pvw.init();

        pvw.getPlayers().observe(this, new Observer<List<PlayerModel.Datum>>() {
            @Override
            public void onChanged(@Nullable List<PlayerModel.Datum> players) {
                getPlayers(players);
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

    private void getPlayers(List<PlayerModel.Datum> players)
    {
        pView = findViewById(R.id.playersView);
        rvLayout = new LinearLayoutManager(this);
        pView.setLayoutManager(rvLayout);
        mAdapter = new PlayersAdapter(this,players);
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
}