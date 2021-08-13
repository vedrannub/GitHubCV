package com.example.lab3_mpip.api;

import com.example.lab3_mpip.model.ApiSearchResult;
import com.example.lab3_mpip.model.Movies;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface OMDbApi {
//    Class<Movies> getAllMovies();

    @GET("movies/{id}")
    Call<Movies> getAllMovies(@Path("id") String id);

//    @GET("/")
//    Call<Movies> getMovieById(@Query("i") String id,
//                             @Query("apikey") String apiKey,
//                             @Query("plot") String plotType);
}
