package org.sabgil.moviereviewer.viewmodel;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import org.sabgil.moviereviewer.R;
import org.sabgil.moviereviewer.databinding.ListItemBinding;
import org.sabgil.moviereviewer.view.BindingViewHolder;

import java.util.ArrayList;

public class MovieItemRecyclerViewAdapter extends RecyclerView.Adapter<BindingViewHolder<ListItemBinding>> {
    private final static String TAG = MovieItemRecyclerViewAdapter.class.getSimpleName();
    private ArrayList<MovieItemViewModel> movieItems = new ArrayList<>();

    public void swap(ArrayList<MovieItemViewModel> items) {
        movieItems.clear();
        movieItems.addAll(items);
        notifyDataSetChanged();
    }

    @Override
    public BindingViewHolder<ListItemBinding> onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new BindingViewHolder<>(inflater.inflate(R.layout.list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(BindingViewHolder<ListItemBinding> holder, int position) {
        holder.binding().setItem(movieItems.get(position));
        movieItems.get(position).loadImageView();
    }

    @Override
    public int getItemCount() {
        return movieItems.size();
    }
}