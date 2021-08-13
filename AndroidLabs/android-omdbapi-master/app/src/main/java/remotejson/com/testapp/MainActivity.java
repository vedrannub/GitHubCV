package remotejson.com.testapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    EditText movie_edt;
    String jsonData;
    MovieDetails movie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void searchMovie(View v){
        movie_edt = (EditText) findViewById(R.id.editText);
        String api_url = getResources().getString(R.string.api_url) + movie_edt.getText().toString();
        getData(api_url);
    }

    public void processData(){
        movie = new MovieDetails();
        try {
            movie.mapJson(this.jsonData);
            TextView textView = (TextView) findViewById(R.id.movieName);
            textView.setText(movie.movieName);

            textView = (TextView) findViewById(R.id.movieYear);
            textView.setText(movie.movieYear);

            textView = (TextView) findViewById(R.id.movieGenre);
            textView.setText(movie.genre);

            textView = (TextView) findViewById(R.id.movieWriter);
            textView.setText(movie.writer);

            textView = (TextView) findViewById(R.id.movieDirector);
            textView.setText(movie.director);

            textView = (TextView) findViewById(R.id.moviePlot);
            textView.setText(movie.plot);

            textView = (TextView) findViewById(R.id.movieActors);
            textView.setText(movie.actors);

            textView = (TextView) findViewById(R.id.movieLanguage);
            textView.setText(movie.language);

            textView = (TextView) findViewById(R.id.movieImdbRating);
            textView.setText(movie.imdbRating);

        } catch (JSONException e) {
            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void getData(String api_url){
        AsyncTask<String,String,String> task = new AsyncTask<String, String, String>() {
            @Override
            protected String doInBackground(String... params) {
                String response = "";
                try{
                    URL url = new URL(params[0]);
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    BufferedReader
                            reader = new
                            BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    String line = "";
                    while((line = reader.readLine()) != null){
                        response += line + "\n";
                    }
                } catch (Exception e){
                    return "Exception";
                }
                return response;
            }

            @Override
            protected void onPostExecute(String result) {
                MainActivity.this.jsonData = result;
                processData();
            }
        };

        task.execute(api_url);
    }
}