package org.sabgil.moviereviewer.view;

import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;

import org.sabgil.moviereviewer.databinding.MovieReviewerBinding;
import org.sabgil.moviereviewer.R;
import org.sabgil.moviereviewer.viewmodel.MovieReviewerViewModel;
import org.sabgil.moviereviewer.viewmodel.MovieItemViewModel;

import java.util.ArrayList;

public class MovieReviewerActivity extends AppCompatActivity {
    private final static String TAG = MovieReviewerActivity.class.getSimpleName();

    MovieReviewerViewModel viewModel = new MovieReviewerViewModel(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MovieReviewerBinding binding =
                DataBindingUtil.setContentView(this, R.layout.movie_reviewer);
        binding.setViewModel(viewModel);
        binding.recyclerView.addItemDecoration(
                new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));
        viewModel.onCreate();
    }

    @BindingAdapter("app:items")
    public static void setItemList(RecyclerView recyclerView, ArrayList<MovieItemViewModel> items) {
        MovieItemRecyclerViewAdapter adapter;

        if (recyclerView.getAdapter() == null) {
            adapter = new MovieItemRecyclerViewAdapter();
            recyclerView.setAdapter(adapter);

        } else {
            adapter = (MovieItemRecyclerViewAdapter) recyclerView.getAdapter();
        }

        adapter.swap(items);
    }
}
