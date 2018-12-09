package org.sabgil.moviereviewer.viewmodel;

import android.databinding.ObservableArrayList;

import java.util.Random;

public class MovieReviewerViewModel implements ViewModel {
    public final ObservableArrayList<MovieItemViewModel> items = new ObservableArrayList<>();

    Random gen = new Random();
    public MovieReviewerViewModel() {

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
    }
}
