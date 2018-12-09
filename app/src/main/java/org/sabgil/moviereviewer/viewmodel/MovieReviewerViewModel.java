package org.sabgil.moviereviewer.viewmodel;

import android.databinding.ObservableArrayList;

import org.sabgil.moviereviewer.model.NaverSearcher;

import java.util.Random;

public class MovieReviewerViewModel implements ViewModel {
    private NaverSearcher searcher;
    public final ObservableArrayList<MovieItemViewModel> items = new ObservableArrayList<>();

    Random gen = new Random();


    public MovieReviewerViewModel() {
        searcher = new NaverSearcher();
    }

    @Override
    public void onCreate() {
        searcher.setClientId("N5HWwaV7Q7Ih4NsIulvK");
        searcher.setClientSecret("tpQBkkyj_w");
        searcher.setRequestMethod("GET");
        searcher.setApiUrl("https://openapi.naver.com/v1/search/movie.xml?query=");
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
        searcher.search("액스맨");
    }
}
