package org.sabgil.moviereviewer.view;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.sabgil.moviereviewer.databinding.MovieReviewerBinding;
import org.sabgil.moviereviewer.R;
import org.sabgil.moviereviewer.viewmodel.MovieReviewerViewModel;

public class MovieReviewerActivity extends AppCompatActivity {

    MovieReviewerViewModel viewModel = new MovieReviewerViewModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MovieReviewerBinding binding =
                DataBindingUtil.setContentView(this, R.layout.movie_reviewer);
        binding.setViewModel(viewModel);
        viewModel.onCreate();
    }
}
