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
import android.view.View;
import android.widget.TextView;

import com.example.eight.Adapters.EventsLive;
import com.example.eight.Adapters.LeagueAdapter;
import com.example.eight.Adapters.PlayersAdapter;
import com.example.eight.ModelList.LeaguesModel;
import com.example.eight.ModelList.ListLive;
import com.example.eight.R;
import com.example.eight.ViewModel.LeaguesEventsVM;
import com.example.eight.ViewModel.LeaguesViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class LeagueEvent extends AppCompatActivity {

    private LeaguesEventsVM levw;
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
        setContentView(R.layout.activity_league_event);
        load();
        declare();
        init();
    }

    private void declare()
    {
        levw = ViewModelProviders.of(this).get(LeaguesEventsVM.class);
        levw.init();

        levw.getLeagues().observe(this, new Observer<List<ListLive.Datum>>() {
            @Override
            public void onChanged(@Nullable List<ListLive.Datum> leagues) {
                getLeagues(leagues);
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

    private void getLeagues(List<ListLive.Datum> leagues)
    {
        pView = findViewById(R.id.leaguesEventView);
        rvLayout = new LinearLayoutManager(this);
        pView.setLayoutManager(rvLayout);
        mAdapter = new EventsLive(this,leagues);
        pView.setAdapter(mAdapter);
    }

    private void init()
    {
       TextView back = findViewById(R.id.backleague);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(LeagueEvent.this, Leagues.class);
                startActivity(back);
            }
        });
    }

}