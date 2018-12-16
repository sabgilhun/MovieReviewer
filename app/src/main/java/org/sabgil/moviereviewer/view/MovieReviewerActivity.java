package org.sabgil.moviereviewer.view;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import org.sabgil.moviereviewer.databinding.MovieReviewerBinding;
import org.sabgil.moviereviewer.R;
import org.sabgil.moviereviewer.viewmodel.MovieReviewerViewModel;
import org.sabgil.moviereviewer.viewmodel.MovieItemViewModel;

import java.util.ArrayList;

public class MovieReviewerActivity extends AppCompatActivity {
    private final static String TAG = MovieReviewerActivity.class.getSimpleName();

    private Context context;
    MovieReviewerViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = getBaseContext();
        viewModel = new MovieReviewerViewModel(context);

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

    @BindingAdapter("android:html")
    public static void setText(TextView view, String text) {
        Spanned parsedText = Html.fromHtml(text);
        view.setText(parsedText);
    }

    @BindingAdapter("android:image")
    public static void setImage(ImageView view, Bitmap bm) {
        view.setImageBitmap(bm);
    }

}
