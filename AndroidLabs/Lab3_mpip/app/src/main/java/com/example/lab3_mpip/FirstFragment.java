package com.example.lab3_mpip;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.lab3_mpip.adapter.MovieAdapter;
import com.example.lab3_mpip.api.OMDbApi;
import com.example.lab3_mpip.api.OMDbApiClient;
import com.example.lab3_mpip.model.Movies;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FirstFragment extends Fragment {

    private EditText etMovieId;
    private OMDbApi omdbApi;
    private TextView tvTitle;
    private ImageView tvImage;
    private RecyclerView moviesList;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etMovieId = (EditText) view.findViewById(R.id.etMovieId);
        tvTitle = (TextView) view.findViewById(R.id.tvID);
        tvImage = (ImageView) view.findViewById(R.id.tvPicture);
        moviesList = (RecyclerView)  view.findViewById(R.id.recView);



        omdbApi = OMDbApiClient.getOMDbApiInstance();

        etMovieId.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_NEXT) {
                    String id = etMovieId.getText().toString();
                    if (id != null && id.isEmpty()) {
                        searchMovies(id);
                    } else {
                        Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                    }
                    return true;
                }
                    return false;
            }
        });
    }

    private void searchMovies(String id) {
        omdbApi.getAllMovies(id).enqueue(new Callback<Movies>() {
            @Override
            public void onResponse(Call<Movies> call, Response<Movies> response) {
                //Log.d("RetrofitResponse", "Success");
                displayData(response.body());
            }

            @Override
            public void onFailure(Call<Movies> call, Throwable t) {

            }
        });
    }

    private void displayData(Movies movies) {
        tvTitle.setText(movies.getName());
        Glide.with(this).load(movies.getPoster()).into(tvImage);
        moviesList.setAdapter(new MovieAdapter(getContext()));
    }
}