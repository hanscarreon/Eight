package com.example.eight.Repository;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Retro {

    public ApiCall retrofitPlayerVid(){
        retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl(ApiCall.playerMedia)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();

        ApiCall ApiCall = retrofit.create(ApiCall.class);

        return ApiCall;
    }

        public ApiCall retrofitCalls(){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(ApiCall.Basketball)
                    .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                    .build();

            ApiCall apiCall = retrofit.create(ApiCall.class);

            return apiCall;
        }

    public ApiCall retrofitLeague(){
        retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl(ApiCall.leagueUrl)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();

        ApiCall ApiCall = retrofit.create(ApiCall.class);

        return ApiCall;
    }
    
}
