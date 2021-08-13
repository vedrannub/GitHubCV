package com.example.lab3_mpip.model;

import android.icu.text.CaseMap;

import com.google.gson.annotations.SerializedName;

public class Movies {
    @SerializedName("Title")
    private String Title;
    @SerializedName("Year")
    private String Year;
    @SerializedName("Plot")
    private String Plot;
    @SerializedName("Poster")
    private String Poster;
    @SerializedName("imdbID")
    private String imdbID;

    public String getName() {
        return Title;
    }

    public void setName(String name) {
        this.Title = name;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String year) {
        this.Year = year;
    }

    public String getPlot() {
        return Plot;
    }

    public String getPoster() {
        return Poster;
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setPlot(String plot) {
        Plot = plot;
    }

    public void setPoster(String poster) {
        Poster = poster;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public Movies(String title, String year, String plot, String poster, String imdbID) {
        Title = title;
        Year = year;
        Plot = plot;
        Poster = poster;
        this.imdbID = imdbID;
    }
}
