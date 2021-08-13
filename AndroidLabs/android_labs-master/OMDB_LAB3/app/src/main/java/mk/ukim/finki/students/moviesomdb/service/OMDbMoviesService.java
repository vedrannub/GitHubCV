package mk.ukim.finki.students.moviesomdb.service;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import mk.ukim.finki.students.moviesomdb.models.OMDbMovies;
import retrofit2.http.Query;

public interface OMDbMoviesService {

    @GET("?apikey=bbc0e412&type=movie")
    Call<OMDbMovies> getMovies(@Query("s") String query);
}
