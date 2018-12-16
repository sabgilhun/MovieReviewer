package org.sabgil.moviereviewer.model;

import android.util.Log;

import java.util.HashMap;

public class MovieItem {
    private String title;
    private String pubDate;
    private String director;
    private String actor;
    private String link;
    private String imageUrl;
    private float rating;

    public MovieItem(HashMap<String, String> data) {
        this.title = data.get("title");
        this.pubDate = data.get("pubDate");
        this.director = data.get("director");
        this.actor = data.get("actor");
        this.link = data.get("link");
        this.rating = (Float.parseFloat(data.get("userRating"))) / 2;
        this.imageUrl = data.get("image");
    }

    public String getTitle() {
        return title;
    }

    public String getPubDate() {
        return pubDate;
    }

    public String getDirector() {
        return director;
    }

    public String getActor() {
        return actor;
    }

    public String getLink() {
        return link;
    }

    public float getRating() {
        return rating;
    }

    public String getImageUrl() {
        return imageUrl;
    }

}
