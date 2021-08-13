package com.example.lab3_mpip.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OMDbApiClient {
    private static OMDbApi omdbClient = null;

    public static OMDbApi getOMDbApiInstance(){
        if(omdbClient == null){
            omdbClient = new Retrofit.Builder()
                    .baseUrl("http://www.omdbapi.com/?i=tt3896198&apikey=cefacaa8")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(OMDbApi.class);
        }
        return omdbClient;
    }
}
