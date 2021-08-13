package com.example.movieslab2.data.models;


import java.util.List;

public class Movie {
    public String id;
    public String name;
    public String description;
    public String director;
    public List<String> actors;

    public Movie(String id, String name, String description, String director, List<String> actors) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.director = director;
        this.actors = actors;
    }
}
