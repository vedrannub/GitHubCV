package com.example.movieslab2.data.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieslab2.R;
import com.example.movieslab2.data.models.Movie;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private List<Movie> moviesList;
    private MovieClickListener movieClickListener;
    public MovieAdapter(List<Movie> movies){
        this.moviesList= movies;
    }
    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, null);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        holder.bindHolder(moviesList.get(position),movieClickListener);


    }

    @Override
    public int getItemCount() {
        return this.moviesList.size();
    }


    static class MovieViewHolder extends RecyclerView.ViewHolder{
        private TextView tvId, tvName, tvDirector;
        private View view;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            tvId = itemView.findViewById(R.id.tvId);
            tvName =itemView.findViewById(R.id.tvName);
            tvDirector=itemView.findViewById(R.id.tvDirector);
        }
        public void bindHolder(Movie movie,final MovieClickListener listener){
            tvId.setText(movie.id);
            tvName.setText(movie.name);
            tvDirector.setText(movie.director);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onMovieClicked(getAdapterPosition());
                }
            });
        }
    }
    public interface MovieClickListener{
        void onMovieClicked(int position);
    }

    public void setMovieClickListener(MovieClickListener movieClickListener) {
        this.movieClickListener = movieClickListener;
    }
}
