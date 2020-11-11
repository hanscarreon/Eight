package com.example.eight.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.eight.ModelList.ListLive;
import com.example.eight.Repository.Repository;

import java.util.List;

public class MainViewModel extends ViewModel {

    private MutableLiveData<List<ListLive.Datum>> mNicePlaces;
    private Repository mRepo;
    private MutableLiveData<Boolean> mIsUpdating = new MutableLiveData<>();

    public void init(){
        if(mNicePlaces != null){
            return;
        }
        mRepo = Repository.getInstance();
        mNicePlaces = mRepo.getNicePlaces();
    }

    public LiveData<List<ListLive.Datum>> getNicePlaces(){
        return mNicePlaces;
    }
}
