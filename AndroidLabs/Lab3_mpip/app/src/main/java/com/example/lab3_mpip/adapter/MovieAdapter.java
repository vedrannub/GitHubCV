package com.example.lab3_mpip.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.lab3_mpip.R;
import com.example.lab3_mpip.model.Movies;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> {

    public List<Movies> movieList;
    private Context context;

    public MovieAdapter(Context context) {
        this.context = context;
        movieList = new ArrayList<>();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View veiw = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rv_movie_item, parent, false);
        return new MyViewHolder(veiw);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Movies m = movieList.get(position);
        holder.tvMovieTitle.setText(m.getName());
        holder.tvMovieYear.setText(m.getYear());
        holder.tvMovieId.setText(m.getImdbID());
        Picasso.
                get()
                .load(m.getPoster())
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.ivMoviePoster);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public void setData(List<Movies> data) {
        this.movieList = data;
    }

    // View holder class
    public class MyViewHolder extends RecyclerView.ViewHolder {

        public ImageView ivMoviePoster;
        public TextView tvMovieTitle;
        public TextView tvMovieYear;
        public TextView tvMovieId;

        public MyViewHolder(View itemView) {
            super(itemView);
            ivMoviePoster = (ImageView) itemView.findViewById(R.id.item_movie_poster);
            tvMovieTitle = (TextView) itemView.findViewById(R.id.item_movie_title);
            tvMovieYear = (TextView) itemView.findViewById(R.id.item_movie_year);
            tvMovieId = (TextView) itemView.findViewById(R.id.item_movie_id);
        }

    }

}
