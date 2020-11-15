package com.example.eight.ViewClasses;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.example.eight.Adapters.PMAdapter;
import com.example.eight.Adapters.PlayerMediaAdapter;
import com.example.eight.Adapters.PlayersAdapter;
import com.example.eight.Adapters.TeamAdapter;
import com.example.eight.ModelList.PlayerMediaModel;
import com.example.eight.ModelList.PlayerModel;
import com.example.eight.R;
import com.example.eight.ViewModel.PlayerVidViewModel;
import com.example.eight.ViewModel.PlayerViewModel;
import com.example.eight.ViewModel.TeamViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Team extends AppCompatActivity {

    private PlayerVidViewModel pvidm;
    private TeamViewModel tvw;
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
        setContentView(R.layout.activity_team);
        load();
        declare();
        initialize();
        navBottom();
    }

    private void initialize()
    {
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.three);
    }

    private void load(){
        pdLoading = new ProgressDialog(this);
        pdLoading.setMessage("\tPlease Wait...");
        pdLoading.setCancelable(false);
        pdLoading.show();
    }

    private void declare()
    {
        tvw = ViewModelProviders.of(this).get(TeamViewModel.class);
        tvw.init();

        tvw.getTeam().observe(this, new Observer<List<PlayerModel.Datum>>() {
            @Override
            public void onChanged(@Nullable List<PlayerModel.Datum> team) {
                Set<PlayerModel.Datum> hashset = new HashSet<>(team);
                team.clear();
                team.addAll(hashset);
                getTeam(team);
                mAdapter.notifyDataSetChanged();
            }
        });

        pvidm = ViewModelProviders.of(this).get(PlayerVidViewModel.class);
        pvidm.init();

        pvidm.getPlayersVidteam().observe(this, new Observer<List<PlayerMediaModel.Datum>>() {
            @Override
            public void onChanged(@Nullable List<PlayerMediaModel.Datum> playersVidteam) {
                getPlayers(playersVidteam);
                tAdapter.notifyDataSetChanged();
                pdLoading.dismiss();
            }
        });
    }

    private void getTeam(List<PlayerModel.Datum> team)
    {
        pView = findViewById(R.id.teamView);
        rvLayout = new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);
        pView.setLayoutManager(rvLayout);
        mAdapter = new TeamAdapter(this,team);
        pView.setAdapter(mAdapter);

    }

    private void getPlayers(List<PlayerMediaModel.Datum> playersVidteam)
    {
        tView = findViewById(R.id.mediaView);
        tLayout = new LinearLayoutManager(this);
        tView.setLayoutManager(tLayout);
        tAdapter = new PMAdapter(this,playersVidteam);
        tView.setAdapter(tAdapter);
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