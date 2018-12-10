package org.sabgil.moviereviewer.viewmodel;

import android.databinding.ObservableArrayList;

import org.sabgil.moviereviewer.model.MovieSearcher;

import java.util.Random;

public class MovieReviewerViewModel implements ViewModel {
    private MovieSearcher searcher;
    public final ObservableArrayList<MovieItemViewModel> items = new ObservableArrayList<>();

    Random gen = new Random();


    public MovieReviewerViewModel() {
        searcher = new MovieSearcher();
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {

    }

    public void newItem() {
        items.add(new MovieItemViewModel(
                "name " + gen.nextInt(10),
                "email " + gen.nextInt(10),
                gen.nextInt(4) + gen.nextFloat()));
        searcher.addParam("display", "20");
        searcher.search("GET", "엑스맨");
    }
}
