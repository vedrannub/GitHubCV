package com.example.movieslab2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.movieslab2.data.FakeApi;
import com.example.movieslab2.data.models.Actor;
import com.example.movieslab2.data.models.Movie;
import com.example.movieslab2.data.util.Constants;

import java.util.ArrayList;
import java.util.List;

public class MovieDetailsFragment extends Fragment {

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView tvId,tvName, tvDirector, tvDescription, tvActors;

        tvId = view.findViewById(R.id.tvId);
        tvName = view.findViewById(R.id.tvName);
        tvDirector = view.findViewById(R.id.tvDirector);
        tvDescription = view.findViewById(R.id.tvDescription);
        tvActors = view.findViewById(R.id.tvActors);

        int moviePostition = getArguments().getInt(Constants.MOVIE_ARG_KEY);
        Movie currentMovie = FakeApi.getInstance().getMovies().get(moviePostition);
        tvId.setText(currentMovie.id);
        tvName.setText(currentMovie.name);
        tvDirector.setText(currentMovie.director);
        tvDescription.setText(currentMovie.description);

        List<String> actrs = new ArrayList<>();
        actrs=currentMovie.actors;
        StringBuilder sb = new StringBuilder();
        for(String actor: actrs){
            sb.append(actor);
            sb.append(", ");
        }
        tvActors.setText(sb.toString());
    }
}