package com.example.eight.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.eight.ModelList.LeaguesModel;
import com.example.eight.ModelList.ListLive;
import com.example.eight.Repository.Repository;

import java.util.List;

public class LeaguesEventsVM extends ViewModel {


    private MutableLiveData<List<ListLive.Datum>> mLeagues;
    private Repository mRepo;
    private MutableLiveData<Boolean> mIsUpdating = new MutableLiveData<>();

    public void init(){
        if(mLeagues != null){
            return;
        }
        mRepo = Repository.getInstance();
        mLeagues = mRepo.getLeagueEvents();
    }

    public LiveData<List<ListLive.Datum>> getLeagues(){
        return mLeagues;
    }
}
