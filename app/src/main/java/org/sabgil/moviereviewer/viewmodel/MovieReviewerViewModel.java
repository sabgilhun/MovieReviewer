package org.sabgil.moviereviewer.viewmodel;

import android.content.Context;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

import org.sabgil.moviereviewer.model.DataLoader;
import org.sabgil.moviereviewer.model.MovieItem;

import java.util.ArrayList;
import java.util.Observable;

public class MovieReviewerViewModel implements ViewModel {
    private final static String TAG = MovieReviewerViewModel.class.getSimpleName();
    private Context context;
    private DataLoader searcher;

    public final ObservableArrayList<MovieItemViewModel> items = new ObservableArrayList<>();
    public final ObservableField<String> word = new ObservableField<>();
    public final ObservableBoolean isResult = new ObservableBoolean();

    public MovieReviewerViewModel(Context context) {
        this.context = context;
        searcher = new DataLoader(this);
        isResult.set(true);
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

    @Override
    public void update(Observable observable, Object o) {
        ArrayList<MovieItem> items;
        items = searcher.getItems();

        if(items.size() > 0) {
            isResult.set(true);
            addItem(searcher.getItems());
        } else {
            isResult.set(false);
        }

    }

    public void movieSearch() {
        String searchWord;

        searchWord = word.get();
        this.items.clear();
        if (searchWord != null) {
            searcher.searchMovieData(searchWord);
        }
    }

    private void addItem(ArrayList<MovieItem> items) {
        for (MovieItem item : items) {
            this.items.add(new MovieItemViewModel(context, item));
        }
    }
}
