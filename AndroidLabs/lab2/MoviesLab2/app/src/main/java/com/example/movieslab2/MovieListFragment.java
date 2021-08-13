package com.example.movieslab2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieslab2.data.FakeApi;
import com.example.movieslab2.data.adapters.MovieAdapter;
import com.example.movieslab2.data.models.Movie;
import com.example.movieslab2.data.util.Constants;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.Collections;

public class MovieListFragment extends Fragment {

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

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        MovieAdapter adapter = new MovieAdapter(FakeApi.getInstance().getMovies());

        adapter.setMovieClickListener(new MovieAdapter.MovieClickListener() {
            @Override
            public void onMovieClicked(int position) {
                Bundle bundle = new Bundle();
                bundle.putInt(Constants.MOVIE_ARG_KEY, position);
                NavHostFragment.findNavController(MovieListFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment,bundle);

            }
        });
        recyclerView.setAdapter(adapter);

        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
    }
    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Add New Movie");
        View view = LayoutInflater.from(getContext()).inflate(R.layout.add_movie_dialog, null);
        builder.setView(view);
        final EditText etId, etName, etDirector, etDescription, etActors;
        etId = view.findViewById(R.id.etId);
        etName = view.findViewById(R.id.etName);
        etDirector = view.findViewById(R.id.etDirector);
        etDescription = view.findViewById(R.id.etDescription);
        etActors = view.findViewById(R.id.etActors);

        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (etId.getText().toString().isEmpty() ||
                        etName.getText().toString().isEmpty() ||
                        etDirector.getText().toString().isEmpty() ||
                        etDescription.getText().toString().isEmpty() ||
                        etActors.getText().toString().isEmpty()) {
                    Toast.makeText(getActivity(), "Please fill the empty fields", Toast.LENGTH_LONG).show();
                    return;
                }
                Movie newMovie = new Movie(
                        etId.getText().toString(),
                        etName.getText().toString(),
                        etDescription.getText().toString(),
                        etDirector.getText().toString(),
                        Collections.singletonList(etActors.getText().toString())
                );

                FakeApi.getInstance().addNewMovie(newMovie);
            }

        });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();

    }



}