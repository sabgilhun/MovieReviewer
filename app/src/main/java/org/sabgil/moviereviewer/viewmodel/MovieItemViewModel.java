package org.sabgil.moviereviewer.viewmodel;

import android.databinding.ObservableField;
import android.databinding.ObservableFloat;

public class MovieItemViewModel {
    public final ObservableField<String> name = new ObservableField<>();
    public final ObservableField<String> email = new ObservableField<>();
    public final ObservableFloat rating = new ObservableFloat();

    public MovieItemViewModel(String name, String email, float rating) {
        this.name.set(name);
        this.email.set(email);
        this.rating.set(rating);
    }
}