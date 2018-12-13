package org.sabgil.moviereviewer.viewmodel;

import android.content.Context;
import android.databinding.ObservableArrayList;

import org.sabgil.moviereviewer.model.DataLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Random;

public class MovieReviewerViewModel implements ViewModel {
    private Context context;
    private DataLoader searcher;
    public final ObservableArrayList<MovieItemViewModel> items = new ObservableArrayList<>();
    Random gen = new Random();

    public MovieReviewerViewModel(Context context) {
        this.context = context;
        searcher = new DataLoader(this);
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
        searcher.searchMovieData("엑스맨");
    }

    private void addItem(ArrayList<HashMap<String, String>> items) {

        for (HashMap<String, String> item : items) {
            this.items.add(new MovieItemViewModel(context, item));
        }
    }

    @Override
    public void update(Observable observable, Object o) {
        addItem(searcher.getList());
    }
}
