package com.example.movieslab2.data;

import com.example.movieslab2.data.models.Actor;
import com.example.movieslab2.data.models.Movie;

import java.util.ArrayList;
import java.util.List;

public class FakeApi {
    private static FakeApi instance;

    private FakeApi(){}
    private List<Movie> movies = new ArrayList<Movie>();

    public static FakeApi getInstance(){
        if(instance==null){
            instance= new FakeApi();
            List<String> movie1 = new ArrayList<>();
            movie1.add("actor1");
            movie1.add("actor2");
            movie1.add("actor3");
            List<String> movie2 = new ArrayList<>();
            movie2.add("actor4");
            movie2.add("actor5");
            movie2.add("actor6");
            List<String> movie3 = new ArrayList<>();
            movie3.add("actor7");
            movie3.add("actor8");
            movie3.add("actor9");
            List<String> movie4 = new ArrayList<>();
            movie4.add("actor10");
            movie4.add("actor11");
            movie4.add("actor12");
            List<String> movie5 = new ArrayList<>();
            movie5.add("actor13");
            movie5.add("actor14");
            movie5.add("actor15");


            instance.movies.add(new Movie("7890","film1","desc1","dir1",movie1));
            instance.movies.add(new Movie("4753","film2","desc2","dir2",movie2));
            instance.movies.add(new Movie("3532","film3","desc3","dir3",movie3));
            instance.movies.add(new Movie("3435","film4","desc4","dir4",movie4));
            instance.movies.add(new Movie("9085","film5","desc5","dir5",movie5));


        }
        return instance;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void addNewMovie(Movie movie) {
        this.movies.add(movie);
    }
    public void deleteMovie(Movie movie){
        this.movies.remove(movie);
    }
}

