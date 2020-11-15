package com.example.eight.ViewClasses;

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
import android.view.View;
import android.widget.TextView;

import com.example.eight.Adapters.PlayerMediaAdapter;
import com.example.eight.Adapters.PlayersAdapter;
import com.example.eight.ModelList.PlayerMediaModel;
import com.example.eight.ModelList.PlayerModel;
import com.example.eight.R;
import com.example.eight.ViewModel.PlayerVidViewModel;
import com.example.eight.ViewModel.PlayerViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class PlayerVid extends AppCompatActivity {

    private PlayerVidViewModel pvidm;
    public static RecyclerView pView;
    public static RecyclerView tView;
    private RecyclerView.Adapter mAdapter,tAdapter;
    private RecyclerView.LayoutManager rvLayout,tLayout;
    private CircleImageView profImg;
    private TextView profName,profPos,profNum,back;
    CountDownTimer countDownTimer;
    private ProgressDialog pdLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_vid);
        load();
        declare();
        init();
    }

    private void load()
    {
        pdLoading = new ProgressDialog(this);
        pdLoading.setMessage("\tPlease Wait...");
        pdLoading.setCancelable(false);
        pdLoading.show();
    }

    private void declare()
    {
        pvidm = ViewModelProviders.of(this).get(PlayerVidViewModel.class);
        pvidm.init();

        pvidm.getPlayersVid().observe(this, new Observer<List<PlayerMediaModel.Datum>>() {
            @Override
            public void onChanged(@Nullable List<PlayerMediaModel.Datum> playersVid) {
                getPlayers(playersVid);
                mAdapter.notifyDataSetChanged();
                pdLoading.dismiss();
            }
        });
    }

    private void getPlayers(List<PlayerMediaModel.Datum> playersVid)
    {
        pView = findViewById(R.id.pmView);
        rvLayout = new LinearLayoutManager(this);
        pView.setLayoutManager(rvLayout);
        mAdapter = new PlayerMediaAdapter(this,playersVid);
        pView.setAdapter(mAdapter);
    }

    private void init()
    {
        profImg = findViewById(R.id.player_dialog_image);
        profName = findViewById(R.id.player_dialog_name);
        profNum = findViewById(R.id.player_dialog_number);
        profPos = findViewById(R.id.player_dialog_position);
        back = findViewById(R.id.backleague);

        Picasso.get().load(PlayersAdapter.img).into(profImg);
        profName.setText(PlayersAdapter.names);
        profNum.setText("Jersey #"+PlayersAdapter.num);
        profPos.setText("Position: "+PlayersAdapter.pos);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(PlayerVid.this, Players.class);
                startActivity(back);
            }
        });
    }
}