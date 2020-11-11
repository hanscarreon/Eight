package com.example.eight.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.eight.ModelList.PlayerModel;
import com.example.eight.Repository.Repository;

import java.util.List;

public class TeamViewModel extends ViewModel {

    private MutableLiveData<List<PlayerModel.Datum>> teamview;
    private Repository mRepo;

    public void init() {
    if(teamview !=null){
        return;
    }
    mRepo = Repository.getInstance();
    teamview = mRepo.getTeam();
    }

    public MutableLiveData<List<PlayerModel.Datum>> getTeam(){return teamview;}
}
