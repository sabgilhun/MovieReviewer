package org.sabgil.moviereviewer.viewmodel;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import org.sabgil.moviereviewer.R;
import org.sabgil.moviereviewer.databinding.ListItemBinding;
import org.sabgil.moviereviewer.view.BindingViewHolder;

import java.util.ArrayList;

public class MovieItemRecyclerViewAdapter extends RecyclerView.Adapter<BindingViewHolder<ListItemBinding>> {
    private ArrayList<MovieItemViewModel> movieItems = new ArrayList<>();

    public void add(ArrayList<MovieItemViewModel> items) {
        for (MovieItemViewModel item : items) {
            if (!this.movieItems.contains(item)) {
                this.movieItems.add(item);
                notifyItemInserted(this.movieItems.size() - 1);
            }
        }
    }

    @Override
    public BindingViewHolder<ListItemBinding> onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new BindingViewHolder<>(inflater.inflate(R.layout.list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(BindingViewHolder<ListItemBinding> holder, int position) {
        holder.binding().setItem(movieItems.get(position));
    }

    @Override
    public int getItemCount() {
        return movieItems.size();
    }
}