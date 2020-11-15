package com.example.eight.Repository;

import androidx.lifecycle.MutableLiveData;

import com.example.eight.Adapters.LeagueAdapter;
import com.example.eight.Adapters.PlayersAdapter;
import com.example.eight.ModelList.LeaguesModel;
import com.example.eight.ModelList.ListLive;
import com.example.eight.ModelList.PlayerMediaModel;
import com.example.eight.ModelList.PlayerModel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class Repository {

    private static Repository instance;
    final Retro rfit = new Retro();
    String leagueid = String.valueOf(LeagueAdapter.leagueID);

    public static Repository getInstance(){
        if(instance == null){
            instance = new Repository();
        }
        return instance;
    }

    public MutableLiveData<List<ListLive.Datum>> getNicePlaces()
    {
        final MutableLiveData<List<ListLive.Datum>> data = new MutableLiveData<>();

        Call<ListLive> call = rfit.retrofitCalls().getLive();
        call.enqueue(new Callback<ListLive>() {
            @Override
            public void onResponse(Call<ListLive> call, Response<ListLive> response) {
                data.setValue(response.body().getData());
            }
            @Override
            public void onFailure(Call<ListLive> call, Throwable t) {

            }
        });
        return data;
    }

    public MutableLiveData<List<PlayerModel.Datum>> getPlayers()
    {
        final MutableLiveData<List<PlayerModel.Datum>> data = new MutableLiveData<>();

        Call<PlayerModel> call = rfit.retrofitCalls().getPlayers();
        call.enqueue(new Callback<PlayerModel>() {
            @Override
            public void onResponse(Call<PlayerModel> call, Response<PlayerModel> response) {
                data.setValue(response.body().getData());

            }
            @Override
            public void onFailure(Call<PlayerModel> call, Throwable t) {

            }
        });
        return data;
    }

    public MutableLiveData<List<PlayerMediaModel.Datum>> getPlayersVid()
    {
        final MutableLiveData<List<PlayerMediaModel.Datum>> data = new MutableLiveData<>();

        Call<PlayerMediaModel> call = rfit.retrofitPlayerVid().getPMedia(PlayersAdapter.playerid);
        call.enqueue(new Callback<PlayerMediaModel>() {
            @Override
            public void onResponse(Call<PlayerMediaModel> call, Response<PlayerMediaModel> response) {
                data.setValue(response.body().getData());

            }
            @Override
            public void onFailure(Call<PlayerMediaModel> call, Throwable t) {

            }
        });
        return data;
    }

    public MutableLiveData<List<PlayerMediaModel.Datum>> getPlayersVidteam()
    {
        final MutableLiveData<List<PlayerMediaModel.Datum>> data = new MutableLiveData<>();

        Call<PlayerMediaModel> call = rfit.retrofitPlayerVid().getPMedia("665");
        call.enqueue(new Callback<PlayerMediaModel>() {
            @Override
            public void onResponse(Call<PlayerMediaModel> call, Response<PlayerMediaModel> response) {

                data.setValue(response.body().getData());

            }
            @Override
            public void onFailure(Call<PlayerMediaModel> call, Throwable t) {

            }
        });
        return data;
    }

    public MutableLiveData<List<LeaguesModel.Datum>> getLeague()
    {
        final MutableLiveData<List<LeaguesModel.Datum>> data = new MutableLiveData<>();

        Call<LeaguesModel> call = rfit.retrofitCalls().getLeagues();
        call.enqueue(new Callback<LeaguesModel>() {
            @Override
            public void onResponse(Call<LeaguesModel> call, Response<LeaguesModel> response) {
                data.setValue(response.body().getData());

            }
            @Override
            public void onFailure(Call<LeaguesModel> call, Throwable t) {

            }
        });
        return data;
    }

    public MutableLiveData<List<ListLive.Datum>> getLeagueEvents()
    {
        final MutableLiveData<List<ListLive.Datum>> data = new MutableLiveData<>();

        Call<ListLive> call = rfit.retrofitLeague().getLeaguesEvents(LeagueAdapter.leagueID);
        call.enqueue(new Callback<ListLive>() {
            @Override
            public void onResponse(Call<ListLive> call, Response<ListLive> response) {
                data.setValue(response.body().getData());

            }
            @Override
            public void onFailure(Call<ListLive> call, Throwable t) {

            }
        });
        return data;
    }

    public MutableLiveData<List<PlayerModel.Datum>> getTeam()
    {
        final MutableLiveData<List<PlayerModel.Datum>> datas = new MutableLiveData<>();

        Call<PlayerModel> call = rfit.retrofitCalls().getPlayers();
        call.enqueue(new Callback<PlayerModel>() {
            @Override
            public void onResponse(Call<PlayerModel> call, Response<PlayerModel> response) {
                datas.setValue(response.body().getData());
                /*PlayerModel manList = response.body();
                List<PlayerModel.Datum> data = manList.getData();
                List<PlayerModel.Datum> playerss = new ArrayList<>();
                try {
                    for (int i = 0; i < data.size(); i++) {
                        if(data.get(i).getMainTeam() !=null){
                            playerss.add(data.get(i).getMainTeam().getLogo(),data.get(i).getMainTeam().getName());
                        }
                    }
                }catch(Exception e){}

                HashSet<PlayerModel.Datum> set = new HashSet<>(playerss);
                playerss.clear();
                playerss.addAll(set);
                datas.setValue(playerss);*/

                /*List<String> playerss = new ArrayList<>();
                ArrayList<PlayerModel.Datum> data = (ArrayList<PlayerModel.Datum>) response.body().getData();
                try {
                    for (int i = 0; i < data.size(); i++) {
                        if(data.get(i).getMainTeam() !=null){
                            playerss.add(data.get(i).getMainTeam().getLogo());
                        }
                    }
                }catch(Exception e){}
                Set<String> hashset = new HashSet<>(playerss);
                data.clear();
                data.addAll(hashset);
                datas.setValue(data);*/

            }
            @Override
            public void onFailure(Call<PlayerModel> call, Throwable t) {

            }
        });
        return datas;
    }

}
