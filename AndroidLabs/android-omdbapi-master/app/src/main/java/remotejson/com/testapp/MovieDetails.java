package remotejson.com.testapp;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Akshay on 4/10/2016.
 */
public class MovieDetails {
    public String movieName,movieYear,genre,director,writer,actors,plot,language,imdbRating;

    public void mapJson(String jsonData) throws JSONException {
        JSONObject movieData = new JSONObject(jsonData);
        movieName = "Movie: " + movieData.getString("Title");
        movieYear = "Year: " + movieData.getString("Year");
        genre = "Genre: " + movieData.getString("Genre");
        director = "Director: " + movieData.getString("Director");
        writer = "Writer:" + movieData.getString("Writer");
        actors = "Actors: " + movieData.getString("Actors");
        plot = "Plot: " + movieData.getString("Plot");
        language = "Language: " + movieData.getString("Language");
        imdbRating = "IMDB Rating: " + movieData.getString("imdbRating");
    }
}
