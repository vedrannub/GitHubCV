package com.example.lab3_mpip.dao;

import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.lab3_mpip.api.OMDbApiClient;
import com.example.lab3_mpip.model.ApiSearchResult;
import com.example.lab3_mpip.model.Movies;

import retrofit2.Call;
import retrofit2.http.GET;


public interface MoviesDao {
    @GET("/")
    Call<ApiSearchResult> searchMovieByName(@Query("s") String name,
                                            @Query("apikey") String apiKey);

    @GET("/")
    Call<Movies> getMovieById(@Query("i") String id,
                              @Query("apikey") String apiKey,
                              @Query("plot") String plotType);

}
