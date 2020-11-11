package com.example.eight.Repository;

import com.example.eight.ModelList.LeaguesModel;
import com.example.eight.ModelList.ListLive;
import com.example.eight.ModelList.PlayerMediaModel;
import com.example.eight.ModelList.PlayerModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiCall {

    String url = "https://rapidapi.p.rapidapi.com/";
    String leagueUrl = "https://rapidapi.p.rapidapi.com/leagues/";
    String playerMedia = "https://rapidapi.p.rapidapi.com/players/";
    String Basketball = "https://rapidapi.p.rapidapi.com/sports/3/";

        @Headers({  "x-rapidapi-host: sportscore1.p.rapidapi.com",
                    "x-rapidapi-key: 07e55202eemshd454005e3a79774p103cccjsn4b32f05d3a2f",
                    "useQueryString: true"})
        @GET("events/live")
            Call<ListLive> getLive();

        @Headers({"x-rapidapi-host: sportscore1.p.rapidapi.com",
                "x-rapidapi-key: 07e55202eemshd454005e3a79774p103cccjsn4b32f05d3a2f",
                "useQueryString: true"})
        @GET("players")
        Call<PlayerModel> getPlayers();

        @Headers({"x-rapidapi-host: sportscore1.p.rapidapi.com",
                "x-rapidapi-key: 07e55202eemshd454005e3a79774p103cccjsn4b32f05d3a2f",
                "useQueryString: true"})
        @GET("leagues")
        Call<LeaguesModel> getLeagues();

        @Headers({"x-rapidapi-host: sportscore1.p.rapidapi.com",
                "x-rapidapi-key: 07e55202eemshd454005e3a79774p103cccjsn4b32f05d3a2f",
                "useQueryString: true"})
        @GET("{id}/events")
        Call<ListLive> getLeaguesEvents(@Path("id") String id);

        @Headers({"x-rapidapi-host: sportscore1.p.rapidapi.com",
                "x-rapidapi-key: 07e55202eemshd454005e3a79774p103cccjsn4b32f05d3a2f",
                "useQueryString: true"})
        @GET("{id}/medias")
        Call<PlayerMediaModel> getPMedia(@Path("id") String id);
}
