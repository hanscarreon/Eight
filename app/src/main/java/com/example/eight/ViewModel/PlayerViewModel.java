package com.example.eight.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.eight.ModelList.ListLive;
import com.example.eight.ModelList.PlayerModel;
import com.example.eight.Repository.Repository;

import java.util.List;

public class PlayerViewModel extends ViewModel {

    private MutableLiveData<List<PlayerModel.Datum>> players;
    private Repository mRepo;

    public void init(){
        if(players != null){
            return;
        }
        mRepo = Repository.getInstance();
        players = mRepo.getPlayers();
    }
    public LiveData<List<PlayerModel.Datum>> getPlayers(){
        return players;
    }
}
