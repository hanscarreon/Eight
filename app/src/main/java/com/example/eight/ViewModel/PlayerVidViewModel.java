package com.example.eight.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.eight.ModelList.PlayerMediaModel;
import com.example.eight.ModelList.PlayerModel;
import com.example.eight.Repository.Repository;

import java.util.List;

public class PlayerVidViewModel extends ViewModel {

    private MutableLiveData<List<PlayerMediaModel.Datum>> players;
    private MutableLiveData<List<PlayerMediaModel.Datum>> playersVid;
    private Repository mRepo;

    public void init(){
        if(players != null){
            return;
        }
        mRepo = Repository.getInstance();
        players = mRepo.getPlayersVid();
        playersVid = mRepo.getPlayersVidteam();
    }
    public LiveData<List<PlayerMediaModel.Datum>> getPlayersVid(){
        return players;
    }
    public LiveData<List<PlayerMediaModel.Datum>> getPlayersVidteam(){
        return playersVid;
    }
}
