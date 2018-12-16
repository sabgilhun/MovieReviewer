package org.sabgil.moviereviewer.viewmodel;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.databinding.ObservableField;
import android.databinding.ObservableFloat;

import org.sabgil.moviereviewer.model.MovieItem;

import java.net.HttpURLConnection;
import java.net.URL;

public class MovieItemViewModel {
    private final static String TAG = MovieItemViewModel.class.getSimpleName();

    private Context context;
    private MovieItem item;
    public final ObservableField<String> title = new ObservableField<>();
    public final ObservableFloat rating = new ObservableFloat();
    public final ObservableField<String> pubDate = new ObservableField<>();
    public final ObservableField<String> director = new ObservableField<>();
    public final ObservableField<String> actor = new ObservableField<>();
    public final ObservableField<Bitmap> img = new ObservableField<>();

    public MovieItemViewModel(Context context, MovieItem item) {
        this.context = context;
        this.item = item;
        title.set(item.getTitle());
        rating.set(item.getRating());
        pubDate.set(item.getPubDate());
        director.set(item.getDirector());
        actor.set(item.getActor());
    }

    public void loadImageView() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    URL url = new URL(item.getImageUrl());

                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setDoInput(true);
                    con.connect();

                    img.set(BitmapFactory.decodeStream(con.getInputStream()));

                } catch (Exception e) {

                }
            }
        }.start();
    }

    public void onClickItem() {
        Uri uri = Uri.parse(item.getLink());

        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        context.startActivity(intent);
    }
}