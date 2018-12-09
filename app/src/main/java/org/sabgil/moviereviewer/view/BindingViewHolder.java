package org.sabgil.moviereviewer.view;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class BindingViewHolder<T extends ViewDataBinding> extends RecyclerView.ViewHolder {
    private final T binding;

    public BindingViewHolder(View view) {
        super(view);
        this.binding = (T) DataBindingUtil.bind(view);
    }

    public T binding() {
        return binding;
    }
}